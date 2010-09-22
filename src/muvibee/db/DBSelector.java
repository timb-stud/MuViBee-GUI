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
 * @author Tobias Lana
 * 
 * Klasse erwartet beim Aufruf einen Parameter
 * Boolean deleted steuert ob geloeschte Daten selektiert werden oder nicht XOR !!
 * 
 * es werden drei LinkedList erstellt bookList, musicList, videoList
 * 
 * 
 * Aufruf:
 * orderBy im Format "ORDER BY title, author, ID"
 * LinkedList<Book> bookList;
 * LinkedList<Music> musicList;
 * LinkedList<Video> videoList;
 * bookList 	= DBSelector.getBookList([false|true], int[] orderBy);
 * musicList 	= DBSelector.getMusicList([false|true], int[] orderBy);
 * videoList 	= DBSelector.getVideoList([false|true], int[] orderBy);
 * 
 * Testklasse:
 * db.test.TestDBSelects
 *
 */

public class DBSelector {
        private final static String COVER_PATH          = "data/images/";
	private final static String SQL_GET_BOOKS  	= "SELECT * FROM book  WHERE isdeleted = ? ";
	private final static String SQL_GET_MUSIC  	= "SELECT * FROM music WHERE isdeleted = ? ";
	private final static String SQL_GET_VIDEOS      = "SELECT * FROM video WHERE isdeleted = ? ";
	private final static String SQL_ORDER_BY	= " ORDER BY title";

	private static Connection con = null;
	
	private static LinkedList<Book> bookList;
	private static LinkedList<Music> musicList;
	private static LinkedList<Video> videoList;
        
        // Media
        public static int SORT_TITLE        = 2;
        public static int SORT_EAN          = 3;
        public static int SORT_GENRE        = 4;
        public static int SORT_RELEASEYEAR  = 5;
        public static int SORT_RATING       = 10;
        // individuell Book
        public static int SORT_B_AUTHOR     = 14;
        public static int SORT_B_LANGUAGE   = 15;
        // individuell Music
        public static int SORT_M_FORMAT     = 14;
        public static int SORT_M_INTERPRETER= 15;
        public static int SORT_M_TYPE       = 16;
        // individuell Video
        public static int SORT_V_FORMAT     = 14;
        public static int SORT_V_DIRECTOR   = 15;
        public static int SORT_V_ACTORS     = 16;

        public static LinkedList<Book> getBookList(Boolean deleted, int[] orderBy) {
            try {
                con = DBConnector.getConnection();
                createBookList(deleted, orderBy);
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bookList;
	}
        
	public static LinkedList<Music> getMusicList(Boolean deleted, int[] orderBy) {
            try {
                con = DBConnector.getConnection();
                createMusicList(deleted, orderBy);
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return musicList;
	}
        
	public static LinkedList<Video> getVideoList(Boolean deleted, int[] orderBy) {
            try {
                con = DBConnector.getConnection();
                createVideoList(deleted, orderBy);
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return videoList;
	}


	private static void createBookList(Boolean isDeleted, int[] orderBy) throws SQLException, IOException {
            bookList = new LinkedList<Book>();
            String orderBySQL = orderByIntToString(orderBy);
            try {
                PreparedStatement psBook = null;
                psBook 	= con.prepareStatement(SQL_GET_BOOKS + orderBySQL);
                psBook.setBoolean(1, isDeleted);
                ResultSet rsBook = psBook.executeQuery();
                while (rsBook.next()) {
                    Book b = new Book();
                    b.setID(rsBook.getInt(1));
                    b.setTitle(rsBook.getString(2));
                    b.setEan(rsBook.getString(3));
                    b.setGenre(rsBook.getString(4));
                    b.setReleaseYear(rsBook.getInt(5));
                    b.setLocation(rsBook.getString(6));
                    b.setLentTo(rsBook.getString(7));
                    b.setLentDate(rsBook.getString(8));
                    b.setLentUntilDate(rsBook.getString(9));
                    b.setRating(rsBook.getInt(10));
                    b.setDescription(rsBook.getString(11));
                    b.setComment(rsBook.getString(12));
                    try {
                        BufferedImage cover = ImageIO.read(new File (COVER_PATH + rsBook.getString(13) + ".jpg"));
                        b.setCover(cover);
                    } catch (IIOException e){
                        b.setCover(Book.defaultCover);
                    }
                    b.setAuthor(rsBook.getString(14));
                    b.setLanguage(rsBook.getString(15));
                    b.setIsbn(rsBook.getString(16));
                    b.setDeleted(rsBook.getBoolean(17));
                    b.setIsLent(rsBook.getBoolean(18));
                    bookList.add(b);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
	private static void createMusicList(Boolean isDeleted, int[] orderBy) throws SQLException, IOException {
            musicList = new LinkedList<Music>();
            String orderBySQL = orderByIntToString(orderBy);
            try {
                PreparedStatement psMusic = null;
                psMusic = con.prepareStatement(SQL_GET_MUSIC + orderBySQL);
                psMusic.setBoolean(1, isDeleted);
                ResultSet rsMusic = psMusic.executeQuery();
                while (rsMusic.next()) {
                    Music m = new Music();
                    m.setID(rsMusic.getInt(1));
                    m.setTitle(rsMusic.getString(2));
                    m.setEan(rsMusic.getString(3));
                    m.setGenre(rsMusic.getString(4));
                    m.setReleaseYear(rsMusic.getInt(5));
                    m.setLocation(rsMusic.getString(6));
                    m.setLentTo(rsMusic.getString(7));
                    m.setLentDate(rsMusic.getString(8));
                    m.setLentUntilDate(rsMusic.getString(9));
                    m.setRating(rsMusic.getInt(10));
                    m.setDescription(rsMusic.getString(11));
                    m.setComment(rsMusic.getString(12));
                    try {
                        BufferedImage cover = ImageIO.read(new File (COVER_PATH + rsMusic.getString(13) + ".jpg"));
                        m.setCover(cover);
                    } catch (IIOException e){
                        m.setCover(Music.defaultCover);
                    }
                    m.setFormat(rsMusic.getString(14));
                    m.setInterpreter(rsMusic.getString(15));
                    m.setType(rsMusic.getString(16));
                    m.setDeleted(rsMusic.getBoolean(17));
                    m.setIsLent(rsMusic.getBoolean(18));
                    musicList.add(m);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	private static void createVideoList(Boolean isDeleted, int[] orderBy) throws SQLException, IOException {
            videoList = new LinkedList<Video>();
            String orderBySQL = orderByIntToString(orderBy);
            try {
                PreparedStatement psVideo = null;
                psVideo = con.prepareStatement(SQL_GET_VIDEOS + orderBySQL);
                psVideo.setBoolean(1, isDeleted);
                ResultSet rsVideo = psVideo.executeQuery();
                while (rsVideo.next()) {
                    Video v = new Video();
                    v.setID(rsVideo.getInt(1));
                    v.setTitle(rsVideo.getString(2));
                    v.setEan(rsVideo.getString(3));
                    v.setGenre(rsVideo.getString(4));
                    v.setReleaseYear(rsVideo.getInt(5));
                    v.setLocation(rsVideo.getString(6));
                    v.setLentTo(rsVideo.getString(7));
                    v.setLentDate(rsVideo.getString(8));
                    v.setLentUntilDate(rsVideo.getString(9));
                    v.setRating(rsVideo.getInt(10));
                    v.setDescription(rsVideo.getString(11));
                    v.setComment(rsVideo.getString(12));
                    try {
                        BufferedImage cover = ImageIO.read(new File (COVER_PATH + rsVideo.getString(13) + ".jpg"));
                        v.setCover(cover);
                    } catch (IIOException e){
                        v.setCover(Video.defaultCover);
                    }
                    v.setFormat(rsVideo.getString(14));
                    v.setDirector(rsVideo.getString(15));
                    v.setActors(rsVideo.getString(16));
                    v.setDeleted(rsVideo.getBoolean(17));
                    v.setIsLent(rsVideo.getBoolean(18));
                    videoList.add(v);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }	
        }
        
        private static String orderByIntToString (int[] orderBy) {
            String orderBySQL;
            if (orderBy == null || orderBy.length == 0) {
                    orderBySQL = SQL_ORDER_BY;
                } else {
                    orderBySQL = " ORDER BY ";
                    for (int i : orderBy) {
                        orderBySQL = orderBySQL + i + ", ";
                    }
                    orderBySQL = orderBySQL.substring(0, orderBySQL.length()-2);
            }
            return orderBySQL;
        }
}