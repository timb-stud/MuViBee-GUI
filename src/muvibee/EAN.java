package muvibee;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.text.html.HTML;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;

public class EAN {

    private EAN() {
    }

    public static Media searchEan(String ean) {
        long eanLong = checkEan(ean);
        try {
            InputStream inputStream = request(eanLong);
            return checkRequest(inputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XMLStreamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

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

    private static InputStream request(long ean) throws IOException {
        URL url = new URL("http://de.free.apisigning.com/onca/xml"
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

    private static Media checkRequest(InputStream inputStream) throws XMLStreamException, FactoryConfigurationError, MalformedURLException, IOException {
        String error = null;
        String title = null;
        String artist = null;
        String author = null;
        String actor = null;
        String director = null;
        String isbn = null;
        String language = null;
        String productGroup = null;
        String releaseYear = null;
        String ean = null;
        String binding = null;
        String description = null;
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
                    if (actor == null) {
                        actor = xmlStreamReader.getElementText();
                    } else {
                        actor = actor + ", " + xmlStreamReader.getElementText();
                    }
                }
                if (xmlStreamReader.getLocalName().equals("Director")) {
                    if (director == null) {
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
                if (xmlStreamReader.getLocalName().equals("EAN")) {
                    ean = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Binding")) {
                    binding = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.getLocalName().equals("Content")) {
                    if (description == null) {
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
        if (error == null) {
            if (productGroup.equals("DVD") || productGroup.equals("Video")) {
                Video v = new Video(title, ean, releaseYear, cover, binding, actor, director, description);
                System.out.println("EAN_FOUND");
                return v;
            } else if (productGroup.equals("Music")) {
                Music m = new Music(title, ean, releaseYear, cover, artist, binding, description);
                System.out.println("EAN_FOUND");
                return m;
            } else if (productGroup.equals("Book") && (binding.equals("Hörkassette") || binding.contains("Musikkassette"))) {
                Music m = new Music(title, ean, releaseYear, cover, artist, binding, description);
                System.out.println("EAN_FOUND");
                return m;
            } else if (productGroup.equals("Book")) {
                Book b = new Book(title, ean, releaseYear, cover, author, isbn, language, description);
                System.out.println("EAN_FOUND");
                return b;
            } else {
                System.out.println("FALSE_EAN_MEDIA");
            }
        } else {
            System.out.println("FALSE_EAN");
        }
        return null;
    }
}
