/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.db.test;

/**
 *
 * @author tobiaslana
 */
import java.util.LinkedList;

import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.db.DBSelector;

public class TestDBSelects {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBSelector dbs = new DBSelector(false, null);
		LinkedList<Book> bookList;
		LinkedList<Music> musicList;
		LinkedList<Video> videoList;

		bookList 	= dbs.getBookList();
		musicList 	= dbs.getMusicList();
		videoList 	= dbs.getVideoList();

		for (Book book : bookList) {
			System.out.println("Books: " + book.toString());
		}
		for (Music music : musicList) {
			System.out.println("Music: " + music.toString());
		}
		for (Video video : videoList) {
			System.out.println("Video: " + video.toString());
		}
	}

}
