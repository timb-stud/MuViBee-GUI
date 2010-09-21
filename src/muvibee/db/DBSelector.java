package muvibee.db;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.imageio.IIOException;

import javax.imageio.ImageIO;

import muvibee.media.Book;
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
 * db.test.TestDBSelects
 *
 */

public class DBSelector {
	private final static String SQL_GET_BOOKS  	= "SELECT * FROM books WHERE isdeleted = ? ";
	private final static String SQL_GET_MUSIC  	= "SELECT * FROM music WHERE isdeleted = ? ";
	private final static String SQL_GET_VIDEOS      = "SELECT * FROM video WHERE isdeleted = ? ";
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
                if (orderBy == null || orderBy.compareTo("") == 0
                                    || orderBy.compareTo(" ") == 0
                                    || orderBy.compareTo("none") == 0) {
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
                Book b = new Book();
                b.setID(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setEan(rs.getString(3));
                b.setGenre(rs.getString(4));
                b.setReleaseYear(rs.getInt(5));
                b.setLocation(rs.getString(6));
                b.setLendTo(rs.getString(7));
                b.setLendDate(rs.getString(8));
                b.setLendUntilDate(rs.getString(9));
                b.setRating(rs.getInt(10));
                b.setDescription(rs.getString(11));
                b.setComment(rs.getString(12));
                try {
                    BufferedImage cover = ImageIO.read(new File (rs.getString(13)));
                    b.setCover(cover);
                } catch (IIOException e){
                    BufferedImage cover = ImageIO.read(new File ("data/images/default_cover.jpg"));
                    System.out.println("Bild nicht gefunden: " + rs.getString(13));
                    b.setCover(cover);
                }
                b.setAuthor(rs.getString(14));
                b.setLanguage(rs.getString(15));
                b.setLanguage(rs.getString(16));
                b.setDeleted(rs.getBoolean(17));
                bookList.add(b);

            }
	}
	private void CreateMusicList(ResultSet rs) throws SQLException, IOException {
            musicList = new LinkedList<Music>();
            while (rs.next()) {
                Music m = new Music();
                m.setID(rs.getInt(1));
                m.setTitle(rs.getString(2));
                m.setEan(rs.getString(3));
                m.setGenre(rs.getString(4));
                m.setReleaseYear(rs.getInt(5));
                m.setLocation(rs.getString(6));
                m.setLendTo(rs.getString(7));
                m.setLendDate(rs.getString(8));
                m.setLendUntilDate(rs.getString(9));
                m.setRating(rs.getInt(10));
                m.setDescription(rs.getString(11));
                m.setComment(rs.getString(12));
                try {
                    BufferedImage cover = ImageIO.read(new File (rs.getString(13)));
                    m.setCover(cover);
                } catch (IIOException e){
                    BufferedImage cover = ImageIO.read(new File ("data/images/default_cover.jpg"));
                    System.out.println("Bild nicht gefunden: " + rs.getString(13));
                    m.setCover(cover);
                }
                m.setFormat(rs.getString(14));
                m.setInterpreter(rs.getString(15));
                m.setType(rs.getString(16));
                m.setDeleted(rs.getBoolean(17));
                musicList.add(m);

            }
	}
	private void CreateVideoList(ResultSet rs) throws SQLException, IOException {
            videoList = new LinkedList<Video>();
            while (rs.next()) {
                Video v = new Video();
                v.setID(rs.getInt(1));
                v.setTitle(rs.getString(2));
                v.setEan(rs.getString(3));
                v.setGenre(rs.getString(4));
                v.setReleaseYear(rs.getInt(5));
                v.setLocation(rs.getString(6));
                v.setLendTo(rs.getString(7));
                v.setLendDate(rs.getString(8));
                v.setLendUntilDate(rs.getString(9));
                v.setRating(rs.getInt(10));
                v.setDescription(rs.getString(11));
                v.setComment(rs.getString(12));
                try {
                    BufferedImage cover = ImageIO.read(new File (rs.getString(13)));
                    v.setCover(cover);
                } catch (IIOException e){
                    BufferedImage cover = ImageIO.read(new File ("data/images/default_cover.jpg"));
                    System.out.println("Bild nicht gefunden: " + rs.getString(13));
                    v.setCover(cover);
                }
                v.setFormat(rs.getString(14));
                v.setDirector(rs.getString(15));
                v.setActors(rs.getString(16));
                v.setDeleted(rs.getBoolean(17));
                videoList.add(v);
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
