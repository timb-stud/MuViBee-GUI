package muvibee.ean;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 * Die Klasse wird benutzt um anhand einer eingegebenen EAN die restlichen Daten mit Hilfe von Amazon automatisch zu beschaffen
 *
 * @author Thomas Altmeyer
 */
public class EanAmazon {

    /**
     * Standardkonstruktor
     */
    private EanAmazon() {
    }

    /**
     * Sucht nach einer EAN in Amazon und liefert dann ein Media zurück oder wirft eine Exception
     *
     * @param ean eingegebene EAN
     * @param type Type nach dem gesucht werden soll
     * @return Media das angelegt werden soll
     */
    public static Media searchEan(String ean, String type) throws IOException, NoResultException, WrongArticleTypeException{
        long eanLong = checkEan(ean);
        try {
            InputStream inputStream = request(eanLong);
            return checkRequest(inputStream, type);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (FactoryConfigurationError e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prüft ob die eingegeben EAN nur aus Zahlen besteht
     *
     * @param inData eingebebene EAN
     * @return wenn kein Fehler die EAN, wenn nicht eine -1
     */
    private static long checkEan(String inData) {
        long result = -1;
        inData = inData.replaceAll("-", "");
        try {
            result = Long.parseLong(inData);
        } catch (NumberFormatException fehler) {
            System.out.println("FALSE_EAN_FORMAT");
            return -1;
        }
        return result;
    }

    /**
     * Baut den http Request zusammen und baut eine Connection auf
     *
     * @param ean EAN nach der gesucht werden soll
     * @return Stream Antwort Stream von Amazon im XML-Format
     */
    private static InputStream request(long ean) throws IOException {
        URL url = new URL("http://co.uk.free.apisigning.com/onca/xml"
                + "?Service=AWSECommerceService"
                + "&AWSAccessKeyId=0000"
                + "&Operation=ItemLookup"
                + "&ItemId=" + ean
                + "&ResponseGroup=ItemAttributes,Images,Medium"
                + "&SearchIndex=Blended"
                + "&IdType=EAN");
        System.out.println(url);
        URLConnection conn = url.openConnection();
        conn.connect();
        return conn.getInputStream();
    }

    /**
     * Wertet den Amazon Request aus und setzt die Eingeschafter der Media's oder werft eine Exception
     *
     * @param inputStream Antwort Stream von Amazon im XML-Format
     * @param type Type nach dem gesucht wird
     * @return Objekt das erstellt werden soll
     */
    private static Media checkRequest(InputStream inputStream, String type) throws XMLStreamException, FactoryConfigurationError, MalformedURLException, IOException, NoResultException, WrongArticleTypeException {
        String error = null;
        String title = "";
        String artist = "";
        String author = "";
        String actor = "";
        String director = "";
        String isbn = "";
        String language = "";
        String productGroup = "";
        String releaseYear = null;
        String ean = "";
        String binding = null;
        String description = "";
        BufferedImage cover = null;

        XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);

        int i = 1;
        int event = 0;
        while (xmlStreamReader.hasNext()) {
            event = xmlStreamReader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (xmlStreamReader.getLocalName().equals("Message")) {
                    error = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Title")) {
                    title = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Artist")) {
                    artist = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Author")) {
                    author = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Actor")) {
                    if (actor.isEmpty()) {
                        actor = xmlStreamReader.getElementText();
                    } else {
                        actor = actor + ", " + xmlStreamReader.getElementText();
                    }
                }
                if (xmlStreamReader.getLocalName().equals("Director")) {
                    if (director.isEmpty()) {
                        director = xmlStreamReader.getElementText();
                    } else {
                        director = director + ", " + xmlStreamReader.getElementText();
                    }
                }
                if (xmlStreamReader.getLocalName().equals("ISBN")) {
                    isbn = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Name")) {
                    language = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("ProductGroup")) {
                    productGroup = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("TheatricalReleaseDate")) {
                    releaseYear = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("PublicationDate")) {
                    releaseYear = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("ReleaseDate")) {
                    releaseYear = xmlStreamReader.getElementText();
                }
                if (releaseYear != null && releaseYear.contains("-")) {
                    releaseYear = releaseYear.substring(0, releaseYear.indexOf("-"));
                }
                if (xmlStreamReader.getLocalName().equals("EAN")) {
                    ean = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Binding")) {
                    binding = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Content")) {
                    if (description.isEmpty()) {
                        description = xmlStreamReader.getElementText();
                    } else {
                        description = description + xmlStreamReader.getElementText();
                    }
                }
                if (xmlStreamReader.getLocalName().equals("URL")) {
                    if (i == 2) {
                        String urlCover = xmlStreamReader.getElementText();
                        URL url_cover = new URL(urlCover);
                        cover = ImageIO.read(url_cover);
                        i++;
                    } else {
                        i++;
                    }
                }
            }
        }
        xmlStreamReader.close();

        if (!description.equals("")) {
            description = replaceHTMLTags(description);
        }
        
        if (error == null) {
            if ((productGroup.equals("DVD") || productGroup.equals("Video")) && type.equals("video")) {
                Video v = new Video();
                String format = "";
                if (binding != null) {
                    if (binding.equals("Videokassette")) {
                        format = "VHS";
                    } else if (binding.contains("Blu-ray")) {
                        format = "Blu-ray";
                    } else {
                        format = "CD/DVD";
                    }
                }
                v.setTitle(title);
                v.setEan(ean);
                v.setReleaseYear(Integer.parseInt(releaseYear));
                v.setCover(cover);
                v.setFormat(format);
                v.setActors(actor);
                v.setDirector(director);
                v.setDescription(description);
                System.out.println("EAN_FOUND");
                return v;
            } else if (((productGroup.equals("Music")) ||
                       (productGroup.equals("Book") && (binding.equals("Hörkassette") || binding.contains("Musikkassette"))))
                       && type.equals("music")) {
                Music m = new Music ();
                String format = "";
                m.setTitle(title);
                m.setEan(ean);
                m.setReleaseYear(Integer.parseInt(releaseYear));
                m.setCover(cover);
                m.setInterpreter(artist);
                if (binding != null) {
                    if (binding.contains("LP")) {
                        format = "LP";
                    } else if (binding.contains("Hörkassette") || binding.contains("Musikkassette")) {
                        format = "Kassette";
                    } else {
                        format = "CD";
                    }
                }
                m.setFormat(format);
                m.setDescription(description);
                System.out.println("EAN_FOUND");
                return m;
            } else if (productGroup.equals("Book") && type.equals("book")) {
                Book b = new Book();
                b.setTitle(title);
                b.setEan(ean);
                b.setReleaseYear(Integer.parseInt(releaseYear));
                b.setCover(cover);
                b.setAuthor(author);
                b.setIsbn(isbn);
                b.setLanguage(language);
                b.setDescription(description);
                System.out.println("EAN_FOUND");
                return b;
            } else {
                throw new WrongArticleTypeException("");
            }
        } else {
            throw new NoResultException("");
        }
    }

    /**
     * Ersetzt die HTML Tags aus dem Amazon Request durch Java verständlich Tags
     *
     * @param description Text der ersetzt werden soll
     * @return Gibt den verständlichen Text zurück
     */
    private static String replaceHTMLTags(String description) {
        description = description.replaceAll("<i>", "");
        description = description.replaceAll("</i>", "");
        description = description.replaceAll("<b>", "");
        description = description.replaceAll("</b>", "");
        description = description.replaceAll("<u>", "");
        description = description.replaceAll("</u>", "");
        description = description.replaceAll("<I>", "");
        description = description.replaceAll("</I>", "");
        description = description.replaceAll("<p>", "\n\n");
        description = description.replaceAll("</p>", "\n");
        description = description.replaceAll("<br>", "");
        description = description.replaceAll("<br />", "\n");
        description = description.replaceAll("<em>", "");
        description = description.replaceAll("</em>", "");
        if (description.indexOf("<img") != -1) {
            String temp = description.substring(description.indexOf("<img"));
            System.out.println(temp);
            temp = temp.substring(0, temp.indexOf(">") + 1);
            System.out.println(temp);
            description = description.replaceAll(temp, "");
        }

        if (description.indexOf("<table") != -1) {
            String temp = description.substring(description.indexOf("<table"));
            System.out.println(temp);
            temp = temp.substring(0, temp.indexOf("</table>") + 8);
            System.out.println(temp);
            description = description.replaceAll(temp, "");
        }

        return description;
    }
}
