package muvibee.db;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import muvibee.gui.StatusBarModel;
import muvibee.media.Book;
import muvibee.media.Media;

import muvibee.media.Music;
import muvibee.media.Video;


/**
 * @author tobiaslana
 * erwartet Media Opbjekt und schreibt alle Attribute der ID in die Datenbank
 */

public class DBUtil {
        private final static String GET_IMAGE_HASH_BOOK = "SELECT cover FROM book WHERE ID = ?";
        private final static String GET_IMAGE_HASH_MUSIC = "SELECT cover FROM music WHERE ID = ?";
        private final static String GET_IMAGE_HASH_VIDEO = "SELECT cover FROM video WHERE ID = ?";
	private final static String COVER_PATH = "data/images/";
	private final static String SQL_UPDATE_BOOK  = "UPDATE book SET title = ?, ean = ?, genre = ?, releaseyear = ?,"
                + " location = ?, lentto = ?, lentdate = ?, lentuntildate = ?, rating = ?, description = ?,"
                + " comment = ?,"
                + " author = ?, language = ?, isbn = ?, isdeleted = ?, islent = ? WHERE ID = ?";
	private final static String SQL_UPDATE_MUSIC = "UPDATE music SET title = ?, ean = ?, genre = ?, releaseyear = ?,"
                + " location = ?, lentto = ?, lentdate = ?, lentuntildate = ?, rating = ?, description = ?,"
                + " comment = ?,"
                + " format = ?, interpreter = ?, type = ?, isdeleted = ?, islent = ? WHERE ID = ?";
	private final static String SQL_UPDATE_VIDEO = "UPDATE video SET title = ?, ean = ?, genre = ?, releaseyear = ?,"
                + " location = ?, lentto = ?, lentdate = ?, lentuntildate = ?, rating = ?, description = ?,"
                + " comment = ?,"
                + " format = ?, director = ?, actor = ?, isdeleted = ?, islent = ? WHERE ID = ?";

        private final static String SQL_INSERT_BOOK  = "INSERT INTO book (ID, title, ean, genre, releaseyear, location,"
                + " lentto, lentdate, lentuntildate, rating, description, comment, cover, author, language, isbn,"
                + " isdeleted, islent) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_INSERT_MUSIC = "INSERT INTO music (ID, title, ean, genre, releaseyear, location,"
                + " lentto, lentdate, lentuntildate, rating, description, comment, cover, format, interpreter, type,"
                + " isdeleted, islent) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_INSERT_VIDEO = "INSERT INTO video (ID, title, ean, genre, releaseyear, location,"
                + " lentto, lentdate, lentuntildate, rating, description, comment, cover, format, director, actor,"
                + " isdeleted, islent) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        private final static String SQL_DELETE_BOOK     = "DELETE FROM book  WHERE ID = ?";
        private final static String SQL_DELETE_MUSIC    = "DELETE FROM music WHERE ID = ?";
        private final static String SQL_DELETE_VIDEO    = "DELETE FROM video WHERE ID = ?";

        private final static String SQL_MAXID_BOOK      = "SELECT MAX(ID) FROM book";
        private final static String SQL_MAXID_MUSIC     = "SELECT MAX(ID) FROM music";
        private final static String SQL_MAXID_VIDEO     = "SELECT MAX(ID) FROM video";

	private static Connection con = null;
		
	public static void dbUpdate(Media m) {
            if (m.getID() == -1) {
                insertMediaDB(m);
            } else {
                updateMediaDB(m);
            }
	}


        private static void insertMediaDB(Media m) {
		try {
			con = DBConnector.getConnection();
			if (m instanceof Book) {
                            insertBook((Book)m);
			}
			if (m instanceof Music) {
                            insertMusic((Music)m);
			}
			if (m instanceof Video) {
                            insertVideo((Video)m);
			}
			con.prepareStatement("SHUTDOWN").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

        private static void insertBook(Book b) throws SQLException {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_BOOK);
            System.out.println(b.toString());
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getEan());
            ps.setString(3, b.getGenre());
            ps.setInt(4, b.getReleaseYear());
            ps.setString(5, b.getLocation());
            ps.setString(6, b.getLentTo());
            ps.setString(7, b.getLentDate());
            ps.setString(8, b.getLentUntilDate());
            ps.setInt(9, b.getRating());
            ps.setString(10, b.getDescription());
            ps.setString(11, b.getComment());
            ps.setString(12, imageWriteToFile(b.getCover()));
            ps.setString(13, b.getAuthor());
            ps.setString(14, b.getLanguage());
            ps.setString(15, b.getIsbn());
            ps.setBoolean(16, b.isDeleted());
            ps.setBoolean(17, b.isLent());
            ps.executeUpdate();
            b.setID(getMaxBookID());
	}

	private static void insertMusic(Music m) throws SQLException {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_MUSIC);
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getEan());
            ps.setString(3, m.getGenre());
            ps.setInt(4, m.getReleaseYear());
            ps.setString(5, m.getLocation());
            ps.setString(6, m.getLentTo());
            ps.setString(7, m.getLentDate());
            ps.setString(8, m.getLentUntilDate());
            ps.setInt(9, m.getRating());
            ps.setString(10, m.getDescription());
            ps.setString(11, m.getComment());
            ps.setString(12, imageWriteToFile(m.getCover()));
            ps.setString(13, m.getFormat());
            ps.setString(14, m.getInterpreter());
            ps.setString(15, m.getType());
            ps.setBoolean(16, m.isDeleted());
            ps.setBoolean(17, m.isLent());
            ps.executeUpdate();
            m.setID(getMaxMusicID());
	}

	private static void insertVideo(Video v) throws SQLException {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_VIDEO);
            ps.setString(1, v.getTitle());
            ps.setString(2, v.getEan());
            ps.setString(3, v.getGenre());
            ps.setInt(4, v.getReleaseYear());
            ps.setString(5, v.getLocation());
            ps.setString(6, v.getLentTo());
            ps.setString(7, v.getLentDate());
            ps.setString(8, v.getLentUntilDate());
            ps.setInt(9, v.getRating());
            ps.setString(10, v.getDescription());
            ps.setString(11, v.getComment());
            ps.setString(12, imageWriteToFile(v.getCover()));
            ps.setString(13, v.getFormat());
            ps.setString(14, v.getDirector());
            ps.setString(15, v.getActors());
            ps.setBoolean(16, v.isDeleted());
            ps.setBoolean(17, v.isLent());
            ps.executeUpdate();
            v.setID(getMaxVideoID());
        }

	private static String imageWriteToFile(BufferedImage i)  {
            String path = COVER_PATH + i.hashCode() + ".jpg";
            File f = new File(path);
            try {
                    ImageIO.write(i, "jpg", f);
            } catch (IOException e) {
                    e.printStackTrace();
            }
            return String.valueOf(i.hashCode());
         }

	private static void updateMediaDB(Media m) {
            try {
                con = DBConnector.getConnection();
                PreparedStatement ps = null;
                if (m instanceof Book) {
                    ps = con.prepareStatement(SQL_UPDATE_BOOK);
                }
                if (m instanceof Music) {
                    ps = con.prepareStatement(SQL_UPDATE_MUSIC);
                }
                if (m instanceof Video) {
                    ps = con.prepareStatement(SQL_UPDATE_VIDEO);
                }
                ps.setString(1, m.getTitle());
                ps.setString(2, m.getEan());
                ps.setString(3, m.getGenre());
                ps.setInt(4, m.getReleaseYear());
                ps.setString(5, m.getLocation());
                ps.setString(6, m.getLentTo());
                ps.setString(7, m.getLentDate());
                ps.setString(8, m.getLentUntilDate());
                ps.setInt(9, m.getRating());
                ps.setString(10, m.getDescription());
                ps.setString(11, m.getComment());
//		ps.setString(12, (String.valueOf(m.getCover().hashCode())));
                if (m instanceof Book) {
                        ps.setString(12, ((Book)m).getAuthor());
                        ps.setString(13, ((Book)m).getLanguage());
                        ps.setString(14, ((Book)m).getIsbn());
                }
                if (m instanceof Music) {
                        ps.setString(12, ((Music)m).getFormat());
                        ps.setString(13, ((Music)m).getInterpreter());
                        ps.setString(14, ((Music)m).getType());
                }
                if (m instanceof Video) {
                        ps.setString(12, ((Video)m).getFormat());
                        ps.setString(13, ((Video)m).getDirector());
                        ps.setString(14, ((Video)m).getActors());
                }
                ps.setBoolean(15, m.isDeleted());
                ps.setBoolean(16, m.isLent());
                ps.setInt(17, m.getID());
                ps.executeUpdate();
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}

        public static void dbDelete(Media m) {
            try {
                con = DBConnector.getConnection();
                PreparedStatement select_hash = null;
                PreparedStatement ps = null;
                if (m instanceof Book) {
                    select_hash = con.prepareStatement(GET_IMAGE_HASH_BOOK);
                    ps = con.prepareStatement(SQL_DELETE_BOOK);
                }
                if (m instanceof Music) {
                    select_hash = con.prepareStatement(GET_IMAGE_HASH_MUSIC);
                    ps = con.prepareStatement(SQL_DELETE_MUSIC);
                }
                if (m instanceof Video) {
                    select_hash = con.prepareStatement(GET_IMAGE_HASH_VIDEO);
                    ps = con.prepareStatement(SQL_DELETE_VIDEO);
                }
                select_hash.setInt(1, m.getID());
                System.out.println(select_hash.toString());
                ResultSet rs = select_hash.executeQuery();
                rs.next();
                String hash_cover = rs.getString(1);
                System.out.println(COVER_PATH + hash_cover + ".jpg von ID " + m.getID());
                File f = new File(COVER_PATH + hash_cover + ".jpg");
                f.delete();
                ps.setInt(1, m.getID());
                ps.execute();
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }

        private static int getMaxBookID () {
            int maxBookID = -1;
            try {
                con = DBConnector.getConnection();
                PreparedStatement ps = null;
                ps = con.prepareStatement(SQL_MAXID_BOOK);
                ResultSet rs = null;
                rs=ps.executeQuery();
                rs.next();
                maxBookID = rs.getInt(1);
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            return maxBookID;
        }
        private static int getMaxMusicID () {
            int maxMusicID = -1;
            try {
                con = DBConnector.getConnection();
                PreparedStatement ps = null;
                ps = con.prepareStatement(SQL_MAXID_BOOK);
                ResultSet rs = null;
                rs=ps.executeQuery();
                rs.next();
                maxMusicID = rs.getInt(1);
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            return maxMusicID;
        }

        private static int getMaxVideoID () {
            int maxVideoID = -1;
            try {
                con = DBConnector.getConnection();
                PreparedStatement ps = null;
                ps = con.prepareStatement(SQL_MAXID_VIDEO);
                ResultSet rs = null;
                rs=ps.executeQuery();
                rs.next();
                maxVideoID = rs.getInt(1);
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            return maxVideoID;
        }
}