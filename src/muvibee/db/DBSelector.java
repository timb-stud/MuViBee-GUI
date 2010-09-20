package muvibee.db;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import java.awt.print.Book;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 * @author tobiaslana
 * 
 * Klasse erwartet beim Aufruf einen Parameter
 * Boolean deleted steuert ob geloeschte Daten selektiert werden oder nicht XOR !!
 * 
 * es werden drei LinkedList erstellt bookList, musicList, videoList
 * 
 * 
 * Aufruf:
 * DBSelector dbs = new DBSelector([false|true], String orderBy);
 * orderBy im Format "ORDER BY title, author, ID"
 * LinkedList<Book> bookList;
 * LinkedList<Music> musicList;
 * LinkedList<Video> videoList;
 * bookList 	= dbs.getBookList();
 * musicList 	= dbs.getMusicList();
 * videoList 	= dbs.getVideoList();
 * 
 * Testklasse:
 * trash.TestDBSelects
 *
 */

public class DBSelector {
	private final static String SQL_GET_BOOKS  	= "SELECT * FROM books WHERE isdeleted = ? ";
	private final static String SQL_GET_MUSIC  	= "SELECT * FROM music WHERE isdeleted = ? ";
	private final static String SQL_GET_VIDEOS  = "SELECT * FROM video WHERE isdeleted = ? ";
	private final static String SQL_ORDER_BY	= " ORDER BY title";

	private static Connection con = null;
	
	private static LinkedList<Book> bookList;
	private static LinkedList<Music> musicList;
	private static LinkedList<Video> videoList;
	
	public DBSelector(Boolean deleted, String orderBy) {
		selectMedia(deleted, orderBy);
	}
	
	public void selectMedia(Boolean isDeleted, String orderBy) {
		try {
			if (orderBy == null || orderBy.compareTo("") == 0 || orderBy.compareTo(" ") == 0 || orderBy.compareTo("none") == 0) {
				orderBy = SQL_ORDER_BY;
			}
			con = DBConnector.getConnection();
			PreparedStatement psBook = null;
			PreparedStatement psMusic = null;
			PreparedStatement psVideo = null;
			psBook 	= con.prepareStatement(SQL_GET_BOOKS + orderBy);
			psMusic = con.prepareStatement(SQL_GET_MUSIC + orderBy);
			psVideo = con.prepareStatement(SQL_GET_VIDEOS + orderBy);
			psBook.setBoolean(1, isDeleted);
			psMusic.setBoolean(1, isDeleted);
			psVideo.setBoolean(1, isDeleted);
			ResultSet rsBook = psBook.executeQuery();
			ResultSet rsMusic = psMusic.executeQuery();
			ResultSet rsVideo = psVideo.executeQuery();
			CreateBookList(rsBook);
			CreateMusicList(rsMusic);
			CreateVideoList(rsVideo);
			con.prepareStatement("SHUTDOWN").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void CreateBookList(ResultSet rs) throws SQLException, IOException {
		bookList = new LinkedList<Book>();
		while (rs.next()) {
			int ID 				= rs.getInt(1);
			String title 		= rs.getString(2);
			String ean 			= rs.getString(3);
			String genre 		= rs.getString(4);
			int year 			= rs.getInt(5);
			String location 	= rs.getString(6);
			String lendTo 		= rs.getString(7);
			String lendDate 	= rs.getString(8);
			String backDate 	= rs.getString(9);
			int rating	 		= rs.getInt(10);
			String description 	= rs.getString(11);
			String comment 		= rs.getString(12);
			String imagepath 	= rs.getString(13);
			BufferedImage cover = ImageIO.read(new File(imagepath));
			String author 	= rs.getString(14);
			String language = rs.getString(15);
			String isbn 	= rs.getString(16);
			Boolean isDeleted 	= rs.getBoolean(17);
			//Book b = new Book(ID, author, language, isbn, title, ean, genre, year, location, lendTo, lendDate, backDate, rating, description, comment, cover, isDeleted);
			//bookList.add(b);
			
		}
	}
	private void CreateMusicList(ResultSet rs) throws SQLException, IOException {
		musicList = new LinkedList<Music>();
		while (rs.next()) {
			int ID 				= rs.getInt(1);
			String title 		= rs.getString(2);
			String ean 			= rs.getString(3);
			String genre 		= rs.getString(4);
			int year 			= rs.getInt(5);
			String location 	= rs.getString(6);
			String lendTo 		= rs.getString(7);
			String lendDate 	= rs.getString(8);
			String backDate 	= rs.getString(9);
			int rating	 		= rs.getInt(10);
			String description 	= rs.getString(11);
			String comment 		= rs.getString(12);
			String imagepath 	= rs.getString(13);
			BufferedImage cover = ImageIO.read(new File(imagepath));
			Boolean isDeleted 	= rs.getBoolean(17);
			String format 		= rs.getString(14);
			String interpreter 	= rs.getString(15);
			String type			= rs.getString(16);	
			//Music m = new Music(ID, format, interpreter, type, title, ean, genre, year, location, lendTo, lendDate, backDate, rating, description, comment, cover, isDeleted);
			//musicList.add(m);
		}
	}
	private void CreateVideoList(ResultSet rs) throws SQLException, IOException {
		videoList = new LinkedList<Video>();
		while (rs.next()) {
			int ID 				= rs.getInt(1);
			String title 		= rs.getString(2);
			String ean 			= rs.getString(3);
			String genre 		= rs.getString(4);
			int year 			= rs.getInt(5);
			String location 	= rs.getString(6);
			String lendTo 		= rs.getString(7);
			String lendDate 	= rs.getString(8);
			String backDate 	= rs.getString(9);
			int rating	 		= rs.getInt(10);
			String description 	= rs.getString(11);
			String comment 		= rs.getString(12);
			String imagepath 	= rs.getString(13);
			BufferedImage cover = ImageIO.read(new File(imagepath));
			Boolean isDeleted 	= rs.getBoolean(17);
			String format 	= rs.getString(14);
			String director = rs.getString(15);
			String actor 	= rs.getString(16);	
			//Video v = new Video(ID, format, director, actor, title, ean, genre, year, location, lendTo, lendDate, backDate, rating, description, comment, cover, isDeleted);
			//videoList.add(v);
		}
	}

	public static LinkedList<Book> getBookList() {
		return bookList;
	}
	public static LinkedList<Music> getMusicList() {
		return musicList;
	}
	public static LinkedList<Video> getVideoList() {
		return videoList;
	}
	

}
