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
 * bookList 	= DBSelector.getBookList([false|true], String orderBy);
 * musicList 	= DBSelector.getMusicList([false|true], String orderBy);
 * videoList 	= DBSelector.getVideoList([false|true], String orderBy);
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
        

        
	
//	public DBSelector(Boolean deleted, String orderBy) {
//		selectMedia(deleted, orderBy);
//	}

        public static LinkedList<Book> getBookList(Boolean deleted, int[] orderBy) {
            selectMedia(deleted, orderBy);
            return bookList;
	}
	public static LinkedList<Music> getMusicList(Boolean deleted, int[] orderBy) {
            selectMedia(deleted, orderBy);
            return musicList;
	}
	public static LinkedList<Video> getVideoList(Boolean deleted, int[] orderBy) {
            selectMedia(deleted, orderBy);
            return videoList;
	}
	
	private static void selectMedia(Boolean isDeleted, int[] orderBy) {
            String orderBySQL;
            try {
                if (orderBy.length == 0) {
                    orderBySQL = SQL_ORDER_BY;
                } else {
                    orderBySQL = " ORDER BY ";
                    for (int i : orderBy) {
                        orderBySQL = orderBySQL + i + ", ";
                    }
                    orderBySQL = orderBySQL.substring(0, orderBySQL.length()-2);
                }
                con = DBConnector.getConnection();
                PreparedStatement psBook = null;
                PreparedStatement psMusic = null;
                PreparedStatement psVideo = null;

                psBook 	= con.prepareStatement(SQL_GET_BOOKS + orderBySQL);
                psMusic = con.prepareStatement(SQL_GET_MUSIC + orderBySQL);
                psVideo = con.prepareStatement(SQL_GET_VIDEOS + orderBySQL);
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

	private static void CreateBookList(ResultSet rs) throws SQLException, IOException {
            bookList = new LinkedList<Book>();
            while (rs.next()) {
                Book b = new Book();
                b.setID(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setEan(rs.getString(3));
                b.setGenre(rs.getString(4));
                b.setReleaseYear(rs.getInt(5));
                b.setLocation(rs.getString(6));
                b.setLentTo(rs.getString(7));
                b.setLentDate(rs.getString(8));
                b.setLentUntilDate(rs.getString(9));
                b.setRating(rs.getInt(10));
                b.setDescription(rs.getString(11));
                b.setComment(rs.getString(12));
                try {
                    BufferedImage cover = ImageIO.read(new File (COVER_PATH + rs.getString(13) + ".jpg"));
                    b.setCover(cover);
                } catch (IIOException e){
                    BufferedImage cover = ImageIO.read(new File (COVER_PATH + "default_cover.jpg"));
                    System.out.println("Bild nicht gefunden: " + rs.getString(13));
                    b.setCover(cover);
                }
                b.setAuthor(rs.getString(14));
                b.setLanguage(rs.getString(15));
                b.setIsbn(rs.getString(16));
                b.setDeleted(rs.getBoolean(17));
                bookList.add(b);

            }
	}
	private static void CreateMusicList(ResultSet rs) throws SQLException, IOException {
            musicList = new LinkedList<Music>();
            while (rs.next()) {
                Music m = new Music();
                m.setID(rs.getInt(1));
                m.setTitle(rs.getString(2));
                m.setEan(rs.getString(3));
                m.setGenre(rs.getString(4));
                m.setReleaseYear(rs.getInt(5));
                m.setLocation(rs.getString(6));
                m.setLentTo(rs.getString(7));
                m.setLentDate(rs.getString(8));
                m.setLentUntilDate(rs.getString(9));
                m.setRating(rs.getInt(10));
                m.setDescription(rs.getString(11));
                m.setComment(rs.getString(12));
                try {
                    BufferedImage cover = ImageIO.read(new File (COVER_PATH + rs.getString(13) + ".jpg"));
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
	private static void CreateVideoList(ResultSet rs) throws SQLException, IOException {
            videoList = new LinkedList<Video>();
            while (rs.next()) {
                Video v = new Video();
                v.setID(rs.getInt(1));
                v.setTitle(rs.getString(2));
                v.setEan(rs.getString(3));
                v.setGenre(rs.getString(4));
                v.setReleaseYear(rs.getInt(5));
                v.setLocation(rs.getString(6));
                v.setLentTo(rs.getString(7));
                v.setLentDate(rs.getString(8));
                v.setLentUntilDate(rs.getString(9));
                v.setRating(rs.getInt(10));
                v.setDescription(rs.getString(11));
                v.setComment(rs.getString(12));
                try {
                    BufferedImage cover = ImageIO.read(new File (COVER_PATH + rs.getString(13) + ".jpg"));
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
}