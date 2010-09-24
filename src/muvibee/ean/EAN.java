/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static muvibee.media.Book getBookData(String ean) throws NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getBookData(ean);
        } else {
            return (muvibee.media.Book) EanAmazon.searchEan(ean, "book");
        }
    }

    public static muvibee.media.Music getMusicData(String ean) throws NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getMusicData(ean);
        } else {
            return (muvibee.media.Music) EanAmazon.searchEan(ean, "music");
        }
    }

    public static muvibee.media.Video getVideoData(String ean) throws NoResultException, MalformedURLException, IOException, MoreThanOneResultException, WrongArticleTypeException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getVideoData(ean);
        } else {
            return (muvibee.media.Video) EanAmazon.searchEan(ean, "video");
        }
    }

    public static void setProxy(boolean proxySet, String proxy, String port) {
        if(proxySet)
            System.getProperties().put("proxySet", "true");
        else
            System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", proxy);
        System.getProperties().put("proxyPort", port);
    }
}
