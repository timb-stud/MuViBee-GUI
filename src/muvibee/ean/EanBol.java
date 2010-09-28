package muvibee.ean;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 *
 * @author Volkan Gökkaya
 */
public class EanBol {

    static String preEAN = "http://www.bol.de/shop/home/suche/?fi=&st=&sa=&sr=&sv=&svb=&ssw=&si=&sk=&sd=&sre=&sq=";
    static String postEAN = "&forward=weiter&sswg=ANY#pm_features";
    static HtmlCleaner cleaner = new HtmlCleaner();
    static final String noVideo = "Das Ergebnis für diese EAN ist kein Video-Media  !(Format(DVD oder Blu-Ray))  Bitte überprüfen Sie Ihre Eingabe";
    static final String noMusic = "Das Ergebnis für diese EAN ist keine Musik! Bitte überprüfen Sie Ihre Eingabe";
    static final String noBook = "Das Ergebnis für diese EAN ist kein Buch! Bitte überprüfen Sie Ihre Eingabe";

    /*
     * Gets the Data for a Media Object, where it doesn ´t matter if the Media Object is a Book, Music or Video.
     * @param node : The page in which the attributes is to be collected.
     * @param media : media is the Object where the Attributes are saved into.
     * @return A Media Object which has some attributes.
     */
    private static Media getData(TagNode node, Media media) throws IOException {
        String title = "";
        String genre = "";
        String ean = "";
        String description = "";
        int yearOfRelease = 0;

        TagNode[] titleNode = node.getElementsByAttValue("class", "pm_titel",
                true, true);
        if(titleNode.length > 0)
            title = titleNode[0].getText().toString();
        TagNode[] descriptionNode = node.getElementsByAttValue("class",
                "inner", true, true);
        description = getDescription(descriptionNode);
        TagNode[] eanNode = node.getElementsByAttValue("class", "value pm_ean",
                true, true);
        if(eanNode.length > 0)
            ean = eanNode[0].getText().toString();
        TagNode[] yearOfReleaseNode = node.getElementsByAttValue("class",
                "value pm_veroeffentlichungsdatum", true, true);
        yearOfRelease = parseReleaseYear(yearOfReleaseNode);
        TagNode[] genreNode = node.getElementsByAttValue("class",
                "value pm_stilrichtung", true, true);
        if (genreNode.length > 0)
            genre = genreNode[0].getText().toString();
        media.setTitle(title);
        media.setReleaseYear(yearOfRelease);
        media.setGenre(genre);
        media.setDescription(description);
        media.setEan(ean);
        BufferedImage cover = loadImage(node);
        if(cover != null)
            media.setCover(cover);
        return media;
    }
    /*
     * This Method donwloads the image from the website.
     * @param node : The page in which the attribut is to be collected.
     */
    private static BufferedImage loadImage(TagNode node) throws IOException {
        TagNode[] previewImage = node.getElementsByAttValue("id",
                "previewImage", true, true);

        String coverSource;
        if(previewImage.length > 0){
            coverSource = (previewImage[0].getOriginalSource());
            coverSource = coverSource.substring(coverSource.indexOf("http:"));
            coverSource = coverSource.substring(0, coverSource.indexOf(".jpg") + 4);
        }else{
            TagNode[] eanNode = node.getElementsByAttValue("class", "value pm_ean",
                true, true);
            String ean = "";
            if(eanNode.length > 0 )
                ean = eanNode[0].getText().toString();
            coverSource = "http://www.bic-media.com/dmrs/cover.do?isbn="+ ean + "&width=200";
        }
        try{
            BufferedImage image = ImageIO.read(new URL(coverSource));
            return image;
        }catch(IOException e){
            return null;
        }
    }
    /*
     * This method takes the description Array from the page and causes it to the structure the media objects need.
     * @param descriptionNode : The descriptionarray of the page
     * @return The description if this media object as a String.
     */
    private static String getDescription(TagNode[] descriptionNode) {
        boolean hasShortDescription = false;
        StringBuilder sb = new StringBuilder();
        boolean hasDescription = false;
        int j = 0;
        int k = 0;
        for (int i = 0; i < descriptionNode.length; i++) {
            if ((descriptionNode[i].getChildTags()[0].getOriginalSource()).contains("hd hd_content_box")) {
                if (descriptionNode[i].getChildTags()[0].getChildTags()[0].getOriginalSource().contains("kurzbeschreibung")) {

                    hasShortDescription = true;
                    j = i;
                    break;
                }
                if (descriptionNode[i].getChildTags()[0].getChildTags()[0].getOriginalSource().contains("beschreibung")) {

                    hasDescription = true;
                    k = i;
                    break;
                }
            }
        }
        if (!hasDescription && !hasShortDescription) {
            return "";
        } else if (hasShortDescription) {
            sb.append(descriptionNode[j].getChildTags()[1].getText().toString().trim());
            return sb.toString();
        } else {
            sb.append(descriptionNode[k].getChildTags()[1].getText().toString().trim());
            return sb.toString();
        }

    }
    /*
     * This method takes the articletype from the website.
     * @param node : The page in whic the attributes is to be collected.
     * @return The articletype as a String.
     */
    private static String getArtikelType(TagNode node) {

        TagNode[] artikelTypeNode = node.getElementsByAttValue("class",
                "pm_artikeltyp", true, true);
        TagNode[] hoerBuchNode = node.getElementsByAttValue("class",
                "bd bd_detail_misc b9Article", true, true);
        if (artikelTypeNode == null) {
            if (hoerBuchNode[0].getText().toString().contains("CD")
                    || hoerBuchNode[0].getText().toString().contains("cd")) {
                return "cd";
            } else if (hoerBuchNode[0].getText().toString().contains("DVD")
                    || hoerBuchNode[0].getText().toString().contains("dvd")) {
                return "dvd";
            } else {
                return "bluray";
            }
        } else {
            if (artikelTypeNode[0].getText().toString().contains("cd")
                    || artikelTypeNode[0].getText().toString().contains("CD")) {
                return "cd";
            } else if (artikelTypeNode[0].getText().toString().contains("book")
                    || artikelTypeNode[0].getText().toString().contains("buch")
                    || artikelTypeNode[0].getText().toString().contains("BUCH")
                    || artikelTypeNode[0].getText().toString().contains("BOOK")) {
                return "book";
            } else if (artikelTypeNode[0].getText().toString().contains("dvd")
                    || artikelTypeNode[0].getText().toString().contains("DVD")) {
                return "dvd";
            } else {
                return "bluray";
            }
        }
    }
    /*
     * This method fills the remaining data of an book - object in
     * @param ean : the data of this ean will be stored in a book object
     * @return Book Object
     */
    public static Book getBookData(String ean) throws
            NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        Book book = new Book();
        URL url = new URL(preEAN + ean + postEAN);
        TagNode node = cleaner.clean(url);
        checkEan(node);
        String artikelTyp = getArtikelType(node);

        if (!artikelTyp.equalsIgnoreCase("book")) {
            throw new WrongArticleTypeException(noBook);
        } else {
            book = (Book) getData(node, book);
            TagNode[] authorNode = node.getElementsByAttValue("class", "b9_author arrow", true, true);
            String author = "";
            if(authorNode.length > 0)
                author = authorNode[0].getText().toString();
            TagNode[] languageNode = node.getElementsByAttValue("class", "value pm_produktsprache", true, true);
            String language = "";
            if(languageNode.length > 0)
                    language = languageNode[0].getText().toString();
            TagNode[] isbnNode = node.getElementsByAttValue("class", "value pm_isbn", true, true);
            String isbn = "";
            if(isbnNode.length > 0)
                isbn = isbnNode[0].getText().toString();

            book.setAuthor(author);
            book.setLanguage(language);
            book.setIsbn(isbn);
            book.setEan(ean);
            return book;
        }
    }
    /*
     * This method fills the remaining data of an music - object in
     * @param ean : the data of this ean will be stored in a music object
     * @return Music Object
     */
    public static Music getMusicData(String ean) throws
            NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        Music music = new Music();
        URL url = new URL(preEAN + ean + postEAN);
        TagNode node = cleaner.clean(url);
        checkEan(node);
        String artikelTyp = getArtikelType(node);

        if (!artikelTyp.equalsIgnoreCase("cd")) {
            throw new WrongArticleTypeException(noMusic);
        } else {
            music = (Music) getData(node, music);

            TagNode[] interpreterNode = node.getElementsByAttValue("class",
                    "bd bd_artikeldetails clearfix", true, true);
            TagNode[] typeNode = node.getElementsByAttValue("class",
                    "pm_mediumbezeichnung", true, true);

            String type = "";
            if(typeNode.length > 0)
                    type = typeNode[0].getText().toString();
            String interpreter = interpreterFix(interpreterNode);

            music.setType(type);
            music.setInterpreter(interpreter);
            music.setEan(ean);
            return music;
        }
    }
    /*
     * This method fills the remaining data of an video - object in
     * @param ean : the data of this ean will be stored in a video object
     * @return Video Object
     */
    public static Video getVideoData(String ean) throws
            NoResultException, MalformedURLException, MoreThanOneResultException, WrongArticleTypeException, IOException {
        Video video = new Video();
        URL url = new URL(preEAN + ean + postEAN);
        TagNode node = cleaner.clean(url);
        checkEan(node);
        String artikelTyp = getArtikelType(node);

        if (!artikelTyp.equalsIgnoreCase("dvd") && !artikelTyp.equalsIgnoreCase("bluray")) {
            throw new WrongArticleTypeException(noVideo);
        } else {
            video = (Video) getData(node, video);

            TagNode[] directorNode = node.getElementsByAttValue("class",
                    "b9_author arrow", true, true);
            String director = "";
            if (directorNode.length > 0)
                director = directorNode[0].getText().toString();
            TagNode[] actorsNode = node.getElementsByAttValue("class",
                    "value pm_actors pm_readableName", true, true);
            String actors = "";
            for (int i = 0; i < actorsNode.length; i++) {
                actors = actors + actorsNode[i].getText().toString() + ", ";
            }
            video.setDirector(director);
            video.setActors(actors);
            video.setEan(ean);
            return video;
        }
    }
    /*
     * This method checks if the ean returns no or more than one result.
     * @param node : The page in which the attributes is to be collected.
     */
    private static void checkEan(TagNode node) throws NoResultException,
            MoreThanOneResultException {

        TagNode[] rubrikNode = node.getElementsByAttValue("class",
                "seo_tag hd_pagetitle", true, true);
        TagNode[] advancedSearchNode = node.getElementsByAttValue("class",
                "seo_tag", true, true);

        if (rubrikNode.length != 0) {
            throw new MoreThanOneResultException("");
        } else if (advancedSearchNode.length != 0) {
            for (int i = 0; i < advancedSearchNode.length; i++) {
                if (advancedSearchNode[i].getText().toString().trim().contains("Erweiterte Suche")) {
                    System.out.println(advancedSearchNode[i].getOriginalSource().toString());
                    throw new NoResultException("");
                }

            }
            return;
        }

    }
    /*
     * This method takes from the date only the year
     * @param releaseYearNode : the array with the releasedate
     * @return releasedate as int
     */
    private static int parseReleaseYear(TagNode[] releaseYearNode) {
        int yearOfRelease;
        if (releaseYearNode.length > 0) {
            if (releaseYearNode[0].getText().toString().contains(".")) {
                String releaseYear = releaseYearNode[0].getText().toString();
                releaseYear = releaseYear.replaceAll("[.]", "");
                releaseYear = releaseYear.substring(4, releaseYear.length());
                yearOfRelease = Integer.parseInt(releaseYear);
                return yearOfRelease;
            } else {
                String releaseYear = releaseYearNode[0].getText().toString();
                releaseYear = releaseYear.replaceAll("&#228", "");
                releaseYear = releaseYear.replaceAll("[a-zA-Z]", "");
                releaseYear = releaseYear.replaceAll(" ", "");
                releaseYear = releaseYear.replaceAll(";", "");
                yearOfRelease = Integer.parseInt(releaseYear.trim());
                return yearOfRelease;
            }
        }
        return 0;
    }
    /*
     * This method takes the correct interpreter.
     * @param interpreterNode : Array with all the Interpreterinformation
     * @return Interpreter as String
     */
    private static String interpreterFix(TagNode[] interpreterNode) {
        String s = "";
        if(interpreterNode.length > 0)
            s = interpreterNode[0].getText().toString();
        if (!s.contains("Interpret")) {
            return "";
        }
        s = s.substring(s.indexOf("Interpret"));
        if (s.contains("bol.de")) {
            s = s.substring(s.indexOf("Interpret"), s.indexOf("bol.de"));
        } else {
            s = s.substring(s.indexOf("Interpret"), s.indexOf("EAN"));
        }
        s = s.trim().replaceAll("  ", "");
        s = s.substring(s.indexOf("\n"));
        s = s.replaceAll("\n", "");
        return s;
    }
}
