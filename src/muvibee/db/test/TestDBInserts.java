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

import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;

public class TestDBInserts {

	public static void main(String[] args) {

		Book b = new Book();
                b.setTitle("Buchtitel");
		Music m = new Music();
                m.setTitle("Musiktitel");
		Video v = new Video();
                v.setTitle("Videotitel");
		BufferedImage bi1 = new BufferedImage(1, 2, 3);
		BufferedImage bi2 = new BufferedImage(4, 5, 6);
		BufferedImage bi3 = new BufferedImage(7, 8, 9);
		b.setCover(bi1);
		b.setLendDate("2010-11-10");
		b.setLendUntilDate("2010-10-10");
		System.out.println(b.toString());
		b.insertIntoDB();

		m.setCover(bi2);
		m.setLendDate("2010-11-10");
		m.setLendUntilDate("2010-10-10");
		System.out.println(m.toString());
		m.insertIntoDB();

		v.setCover(bi3);
		v.setLendDate("2010-11-10");
		v.setLendUntilDate("2010-10-10");
		System.out.println(v.toString());
		v.insertIntoDB();



	}
}
