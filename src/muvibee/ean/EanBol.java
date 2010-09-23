package muvibee.ean;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
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

    private static Media getData(TagNode node, Media media) throws IOException {
        String title;
        String genre = "";
        String description;
        int yearOfRelease;

        TagNode[] titleNode = node.getElementsByAttValue("class", "pm_titel",
                true, true);
        title = titleNode[0].getText().toString();
        TagNode[] descriptionNode = node.getElementsByAttValue("class",
                "inner", true, true);
        description = getDescription(descriptionNode);
        TagNode[] eanNode = node.getElementsByAttValue("class", "value pm_ean",
                true, true);
        String ean = eanNode[0].getText().toString();
        TagNode[] yearOfReleaseNode = node.getElementsByAttValue("class",
                "value pm_veroeffentlichungsdatum", true, true);
        yearOfRelease = parseReleaseYear(yearOfReleaseNode);
        TagNode[] genreNode = node.getElementsByAttValue("class",
                "value pm_stilrichtung", true, true);
        if (genreNode.length != 0) {
            genre = genreNode[0].getText().toString();
        }
        media.setTitle(title);
        media.setReleaseYear(yearOfRelease);
        media.setGenre(genre);
        media.setDescription(description);
        media.setEan(ean);
        loadImage(ean, media);

        return media;
    }

    private static void loadImage(String ean, Media media) throws IOException {
        URL url = new URL(preEAN + ean + postEAN);
        TagNode node = cleaner.clean(url);
        TagNode[] previewImage = node.getElementsByAttValue("id",
                "previewImage", true, true);

        String coverSource = (previewImage[0].getOriginalSource());
        coverSource = coverSource.substring(coverSource.indexOf("http:"));
        coverSource = coverSource.substring(0, coverSource.indexOf(".jpg") + 4);

        URL url_cover = new URL(coverSource);
        InputStream inputStr = url_cover.openStream();

        BufferedImage image = ImageIO.read(new URL(coverSource));
        media.setCover(image);
    }

    private static String getDescription(TagNode[] descriptionNode) {
        StringBuffer sb = new StringBuffer();
        boolean hasDescription = false;
        int j = 0;

        for (int i = 0; i < descriptionNode.length; i++) {
            if ((descriptionNode[i].getChildTags()[0].getOriginalSource()).contains("hd hd_content_box")) {
                if (descriptionNode[i].getChildTags()[0].getChildTags()[0].getOriginalSource().contains("kurzbeschreibung")) {

                    hasDescription = true;
                    j = i;
                    break;
                }
            }
        }
        if (!hasDescription) {
            return "";
        } else {
            sb.append(descriptionNode[j].getChildTags()[1].getText().toString().trim());
            return sb.toString();
        }
    }

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

    public static Book getBookData(String ean) throws
            NoResultException, MalformedURLException, IOException, MoreThanOneResultException {
        Book book = new Book();
        URL url = new URL(preEAN + ean + postEAN);
        TagNode node = cleaner.clean(url);
        checkEan(node);
        String artikelTyp = getArtikelType(node);

        if (!artikelTyp.equalsIgnoreCase("book")) {
            throw new IOException(
                    "Das Ergebnis für diese EAN ist kein Buch! Bitte überprüfen Sie Ihre Eingabe");
        } else {
            book = (Book) getData(node, book);
            TagNode[] authorNode = node.getElementsByAttValue("class",
                    "b9_author arrow", true, true);
            String author = authorNode[0].getText().toString();
            TagNode[] languageNode = node.getElementsByAttValue("class",
                    "value pm_produktsprache", true, true);
            String language = languageNode[0].getText().toString();
            TagNode[] isbnNode = node.getElementsByAttValue("class",
                    "value pm_isbn", true, true);
            String isbn = isbnNode[0].getText().toString();

            book.setAuthor(author);
            book.setLanguage(language);
            book.setIsbn(isbn);
            return book;
        }
    }

    public static Music getMusicData(String ean) throws
            NoResultException, MalformedURLException, IOException, MoreThanOneResultException {
        Music music = new Music();
        URL url = new URL(preEAN + ean + postEAN);
        TagNode node = cleaner.clean(url);
        checkEan(node);
        String artikelTyp = getArtikelType(node);

        if (!artikelTyp.equalsIgnoreCase("cd")) {
            throw new IOException(
                    "Das Ergebnis für diese EAN ist keine Musik! Bitte überprüfen Sie Ihre Eingabe");
        } else {
            music = (Music) getData(node, music);

            TagNode[] interpreterNode = node.getElementsByAttValue("class",
                    "bd bd_artikeldetails clearfix", true, true);
            TagNode[] typeNode = node.getElementsByAttValue("class",
                    "pm_mediumbezeichnung", true, true);

            String type = typeNode[0].getText().toString();
            String interpreter = interpreterFix(interpreterNode);

            music.setType(type);
            music.setInterpreter(interpreter);
            music.setEan(ean);
            return music;
        }
    }

    public static Video getVideoData(String ean) throws
            NoResultException, MalformedURLException, IOException, MoreThanOneResultException {
        Video video = new Video();
        URL url = new URL(preEAN + ean + postEAN);
        TagNode node = cleaner.clean(url);
        checkEan(node);
        String artikelTyp = getArtikelType(node);

        if (!artikelTyp.equalsIgnoreCase("dvd") && !artikelTyp.equalsIgnoreCase("bluray")) {
            throw new IOException(
                    "Das Ergebnis für diese EAN ist kein Video-Media  !(Format(DVD oder Blu-Ray))  Bitte überprüfen Sie Ihre Eingabe");
        } else {
            video = (Video) getData(node, video);

            TagNode[] directorNode = node.getElementsByAttValue("class",
                    "b9_author arrow", true, true);
            String director = directorNode[0].getText().toString();
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

    private static void checkEan(TagNode node) throws NoResultException,
            MoreThanOneResultException {

        TagNode[] rubrikNode = node.getElementsByAttValue("class",
                "seo_tag hd_pagetitle", true, true);
        TagNode[] advancedSearchNode = node.getElementsByAttValue("class",
                "seo_tag", true, true);

        if (rubrikNode.length != 0) {
            throw new NoResultException("");
        } else if (advancedSearchNode.length != 0) {
            for (int i = 0; i < advancedSearchNode.length; i++) {
                if (advancedSearchNode[i].getText().toString().trim().contains("Erweiterte Suche")) {
                    throw new MoreThanOneResultException("");
                } else {
                    return;
                }
            }
        }
        return;
    }

    private static int parseReleaseYear(TagNode[] releaseYearNode) {
        int yearOfRelease;

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

    private static String interpreterFix(TagNode[] interpreterNode) {
        String s = interpreterNode[0].getText().toString();
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

    public static void setProxy(String proxy, String port) {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", proxy);
        System.getProperties().put("proxyPort", port);
    }
}
