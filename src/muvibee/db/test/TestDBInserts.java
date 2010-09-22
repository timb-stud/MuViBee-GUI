/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.db.test;

/**
 *
 * @author tobiaslana
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.ean.EanAmazon;

public class TestDBInserts {

	public static void main(String[] args) {
        try {
            //Illuminati Buch EAN
            Book b1 = new Book();
            b1 = (Book) EanAmazon.searchEan("9783404148660");
            b1.updateDB();
            //Schindlers Liste Buch
            Book b2 = new Book();
            b2 = (Book) EanAmazon.searchEan("9783570300046");
            b2.updateDB();
            // Schindlers Liste Movie
            Video v1 = new Video();
            v1 = (Video) EanAmazon.searchEan("5050582207804");
            v1.updateDB();
            // Beverly Hills Cop
            Video v2 = new Video();
            v2 = (Video) EanAmazon.searchEan("4010884538366");
            v2.updateDB();
            // Bravo Hits 65 CD
            Music m1 = new Music();
            m1 = (Music) EanAmazon.searchEan("0886974796626");
            m1.updateDB();
            //                Book b1 = new Book();
            //                // Illuminati
            //                b1 = (Book) EAN.searchEan("9783404148660");
            //                b1.updateDB();
            //		Book b = new Book();
            //                b.setID(3);
            //                b.setTitle("Buchtitel ID 3 UPDATED");
            //		BufferedImage bi1 = new BufferedImage(1, 2, 3);
            //                b.setCover(bi1);
            //		b.setLendDate("2010-11-10");
            //		b.setLendUntilDate("2010-10-10");
            //		System.out.println(b.toString());
            //		b.updateDB();
            //                System.out.println("----------------------------");
            //
            //		Music m = new Music();
            //                m.setID(1);
            //                m.setTitle("Musiktitel ID 1 Update");
            //                BufferedImage bi2 = new BufferedImage(4, 5, 6);
            //                m.setCover(bi2);
            //		m.setLendDate("2010-11-10");
            //		m.setLendUntilDate("2010-10-10");
            //		System.out.println(m.toString());
            //		m.updateDB();
            //                System.out.println("----------------------------");
            //
            //
            //		Video v = new Video();
            //                v.setTitle("Videotitel");
            //		BufferedImage bi3 = new BufferedImage(7, 8, 9);
            //		v.setCover(bi3);
            //		v.setLendDate("2010-11-10");
            //		v.setLendUntilDate("2010-10-10");
            //		System.out.println(v.toString());
            //		v.updateDB();
            //                System.out.println("----------------------------");
        } catch (IOException ex) {
            Logger.getLogger(TestDBInserts.class.getName()).log(Level.SEVERE, null, ex);
        }




	}
}
