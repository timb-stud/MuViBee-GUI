package muvibee.db;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import muvibee.media.Book;
import muvibee.media.Media;

import muvibee.media.Music;
import muvibee.media.Video;


/**
 * @author tobiaslana
 * erwartet Media Opbjekt und schreibt alle Attribute der ID in die Datenbank
 */

public class DBUtil {
	
	private final static String SQL_UPDATE_BOOK  = "UPDATE books SET title = ?, ean = ?, genre = ?, year = ?,"
                + " location = ?, lendto = ?, lenddate = ?, backdate = ?, rating = ?, description = ?,"
                + " comment = ?, cover = ?,"
                + " author = ?, language = ?, isbn = ?, isdeleted = ? WHERE ID = ?";
	private final static String SQL_UPDATE_MUSIC = "UPDATE music SET title = ?, ean = ?, genre = ?, year = ?,"
                + " location = ?, lendto = ?, lenddate = ?, backdate = ?, rating = ?, description = ?,"
                + " comment = ?, cover = ?,"
                + " format = ?, interpreter = ?, type = ?, isdeleted = ? WHERE ID = ?";
	private final static String SQL_UPDATE_VIDEO = "UPDATE video SET title = ?, ean = ?, genre = ?, year = ?,"
                + " location = ?, lendto = ?, lenddate = ?, backdate = ?, rating = ?, description = ?,"
                + " comment = ?, cover = ?,"
                + " format = ?, director = ?, actor = ?, isdeleted = ? WHERE ID = ?";

        private final static String SQL_INSERT_BOOK  = "INSERT INTO books (ID, title, ean, genre, year, location,"
                + " lendto, lenddate, backdate, rating, description, comment, cover, author, language, isbn,"
                + " isdeleted) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_INSERT_MUSIC = "INSERT INTO music (ID, title, ean, genre, year, location,"
                + " lendto, lenddate, backdate, rating, description, comment, cover, format, interpreter, type,"
                + " isdeleted) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_INSERT_VIDEO = "INSERT INTO video (ID, title, ean, genre, year, location,"
                + " lendto, lenddate, backdate, rating, description, comment, cover, format, director, actor,"
                + " isdeleted) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        private final static String SQL_DELETE_BOOK = "DELETE FROM books WHERE ID = ?";
        private final static String SQL_DELETE_MUSIC = "DELETE FROM music WHERE ID = ?";
        private final static String SQL_DELETE_VIDEO = "DELETE FROM video WHERE ID = ?";

	private static Connection con = null;

		
	public static void dbUpdate(Media m) {
            if (m.getID() == -1) {
                insertMediaDB(m);
                System.out.println("INSERT NEW");
            } else {
                updateMediaDB(m);
                System.out.println("UPDATE OLD");
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
            ps.setString(7, b.getLendDate());
            ps.setString(8, b.getLendUntilDate());
            ps.setInt(9, b.getRating());
            ps.setString(10, b.getDescription());
            ps.setString(11, b.getComment());
            ps.setString(12, imageWriteToFile(b.getCover()));
            ps.setString(13, b.getAuthor());
            ps.setString(14, b.getLanguage());
            ps.setString(15, b.getIsbn());
            ps.setBoolean(16, b.isDeleted());
            ps.executeUpdate();
            System.out.println("Book added");
	}

	private static void insertMusic(Music m) throws SQLException {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_MUSIC);
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getEan());
            ps.setString(3, m.getGenre());
            ps.setInt(4, m.getReleaseYear());
            ps.setString(5, m.getLocation());
            ps.setString(6, m.getLentTo());
            ps.setString(7, m.getLendDate());
            ps.setString(8, m.getLendUntilDate());
            ps.setInt(9, m.getRating());
            ps.setString(10, m.getDescription());
            ps.setString(11, m.getComment());
            ps.setString(12, imageWriteToFile(m.getCover()));
            ps.setString(13, m.getFormat());
            ps.setString(14, m.getInterpreter());
            ps.setString(15, m.getType());
            ps.setBoolean(16, m.isDeleted());
            ps.executeUpdate();
            System.out.println("Music added");

	}

	private static void insertVideo(Video v) throws SQLException {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_VIDEO);
            ps.setString(1, v.getTitle());
            ps.setString(2, v.getEan());
            ps.setString(3, v.getGenre());
            ps.setInt(4, v.getReleaseYear());
            ps.setString(5, v.getLocation());
            ps.setString(6, v.getLentTo());
            ps.setString(7, v.getLendDate());
            ps.setString(8, v.getLendUntilDate());
            ps.setInt(9, v.getRating());
            ps.setString(10, v.getDescription());
            ps.setString(11, v.getComment());
            ps.setString(12, imageWriteToFile(v.getCover()));
            ps.setString(13, v.getFormat());
            ps.setString(14, v.getDirector());
            ps.setString(15, v.getActors());
            ps.setBoolean(16, v.isDeleted());
            ps.executeUpdate();
            System.out.println("Video added");
        }

	private static String imageWriteToFile(BufferedImage i)  {
            String path = "data/images/" + i.hashCode() + ".jpg";
            File f = new File(path);
            try {
                    ImageIO.write(i, "jpg", f);
            } catch (IOException e) {
                    e.printStackTrace();
            }
            return path;
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
                System.out.println(ps.toString());
                ps.setString(1, m.getTitle());
                ps.setString(2, m.getEan());
                ps.setString(3, m.getGenre());
                ps.setInt(4, m.getReleaseYear());
                ps.setString(5, m.getLocation());
                ps.setString(6, m.getLentTo());
                ps.setString(7, m.getLendDate());
                ps.setString(8, m.getLendUntilDate());
                ps.setInt(9, m.getRating());
                ps.setString(10, m.getDescription());
                ps.setString(11, m.getComment());
		ps.setString(12, ("data/images/" + m.getCover().hashCode() + ".jpg"));
                if (m instanceof Book) {
                        ps.setString(13, ((Book)m).getAuthor());
                        ps.setString(14, ((Book)m).getLanguage());
                        ps.setString(15, ((Book)m).getIsbn());
                }
                if (m instanceof Music) {
                        ps.setString(13, ((Music)m).getFormat());
                        ps.setString(14, ((Music)m).getInterpreter());
                        ps.setString(15, ((Music)m).getType());
                }
                if (m instanceof Video) {
                        ps.setString(13, ((Video)m).getFormat());
                        ps.setString(14, ((Video)m).getDirector());
                        ps.setString(15, ((Video)m).getActors());
                }
                ps.setBoolean(16, m.isDeleted());
                ps.setInt(17, m.getID());
                System.out.println(ps.toString());
                ps.executeUpdate();
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException e) {
                e.printStackTrace();

            }
	
	}


        public static void dbDelete(Media m) {
            try {
                con = DBConnector.getConnection();
                PreparedStatement ps = null;
                if (m instanceof Book) {
                    ps = con.prepareStatement(SQL_DELETE_BOOK);
                }
                if (m instanceof Music) {
                    ps = con.prepareStatement(SQL_DELETE_MUSIC);
                }
                if (m instanceof Video) {
                    ps = con.prepareStatement(SQL_DELETE_VIDEO);
                }
                ps.setInt(1, m.getID());
                ps.execute();
                con.prepareStatement("SHUTDOWN").execute();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
}
