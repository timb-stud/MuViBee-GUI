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
		LinkedList<Book> bookList;
		LinkedList<Music> musicList;
		LinkedList<Video> videoList;

		bookList 	= DBSelector.getBookList(false, null);
		musicList 	= DBSelector.getMusicList(false, null);
		videoList 	= DBSelector.getVideoList(false, null);

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
