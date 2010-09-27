package muvibee.ean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;

/**
 *
 * @author Volkan Gökkaya
 */
public class EAN {

    public EAN() {
    }
    
    /*
     * This method takes the book data according to the language
     * @param ean : A String with the unique number to connect to the correct page.
     * @return A book object
     */

    public static muvibee.media.Book getBookData(String ean) throws NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getBookData(ean);
        } else {
            return (muvibee.media.Book) EanAmazon.searchEan(ean, "book");
        }
    }

    /*
     * This method takes the music data according to the language
     * @param ean : A String with the unique number to connect to the correct page.
     * @return A music object
     */

    public static muvibee.media.Music getMusicData(String ean) throws NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getMusicData(ean);
        } else {
            return (muvibee.media.Music) EanAmazon.searchEan(ean, "music");
        }
    }

    /*
     * This method takes the video data according to the language
     * @param ean : A String with the unique number to connect to the correct page.
     * @return A video object
     */

    public static muvibee.media.Video getVideoData(String ean) throws NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getVideoData(ean);
        } else {
            return (muvibee.media.Video) EanAmazon.searchEan(ean, "video");
        }
    }
    
    /*
     * This method connects with a proxy server
     */

    public static void setProxy(boolean proxySet, String proxy, String port) {
        if (proxySet) {
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("proxyHost", proxy);
            System.getProperties().put("proxyPort", port);
        } else {
            System.getProperties().put("proxySet", "false");
        }
    }
}
