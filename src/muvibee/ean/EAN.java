/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.ean;

import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author Volkan Gökkaya
 */
public class EAN {

    public EAN() {
    }

    public static void getEanDates(String ean) throws IOException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            EanBol.getBookData(ean);
        } else {
            EanAmazon.searchEan(ean);
        }
    }

    public static muvibee.media.Book getBookData(String ean) throws IOException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getBookData(ean);
        } else {
            return (muvibee.media.Book) EanAmazon.searchEan(ean);
        }
    }

    public static muvibee.media.Music getMusicData(String ean) throws IOException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getMusicData(ean);
        } else {
            return (muvibee.media.Music) EanAmazon.searchEan(ean);
        }
    }

    public static muvibee.media.Video getVideoData(String ean) throws IOException {
        if (Locale.getDefault().equals(Locale.GERMAN)) {
            return EanBol.getVideoData(ean);
        } else {
            return (muvibee.media.Video) EanAmazon.searchEan(ean);
        }
    }
}