package muvibee.db;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;

public class DBInsertor {
	private final static String SQL_INSERT_BOOK  = "INSERT INTO books (ID, title, ean, genre, year, location, lendto, lenddate, backdate, rating, description, comment, cover, author, language, isbn, isdeleted) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_INSERT_MUSIC = "INSERT INTO music (ID, title, ean, genre, year, location, lendto, lenddate, backdate, rating, description, comment, cover, format, interpreter, type, isdeleted) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_INSERT_VIDEO = "INSERT INTO video (ID, title, ean, genre, year, location, lendto, lenddate, backdate, rating, description, comment, cover, format, director, actor, isdeleted) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_SELECT_BOOK  = "SELECT ID From books WHERE ID = ?";
	private final static String SQL_SELECT_MUSIC = "SELECT ID From music WHERE ID = ?";
	private final static String SQL_SELECT_VIDEO = "SELECT ID From video WHERE ID = ?";

	private static Connection con = null;

	public static void insertIntoDB(Media m) {
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
		PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOOK);
		ps.setInt(1, b.hashCode());
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			ps = con.prepareStatement(SQL_INSERT_BOOK);
			ps.setInt(1, b.hashCode());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getEan());
			ps.setString(4, b.getGenre());
			ps.setInt(5, b.getReleaseYear());
			ps.setString(6, b.getLocation());
			ps.setString(7, b.getLentTo());
			//ps.setString(8, b.getLentDate());
			//ps.setString(9, b.getBackDate());
			ps.setInt(10, b.getRating());
			ps.setString(11, b.getDescription());
			ps.setString(12, b.getComment());
			ps.setString(13, imageWriteToFile(b.getCover()));
			ps.setString(14, b.getAuthor());
			ps.setString(15, b.getLanguage());
			ps.setString(16, b.getIsbn());
			ps.setBoolean(17, b.isDeleted());
			ps.executeUpdate();
			System.out.println("Book added");
		}
	}

	private static void insertMusic(Music m) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_SELECT_MUSIC);
		ps.setInt(1, m.hashCode());
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			ps = con.prepareStatement(SQL_INSERT_MUSIC);
			ps.setInt(1, m.hashCode());
			ps.setString(2, m.getTitle());
			ps.setString(3, m.getEan());
			ps.setString(4, m.getGenre());
//			ps.setInt(5, m.getYear());
			ps.setString(6, m.getLocation());
			//ps.setString(7, m.getLendTo());
			//ps.setString(8, m.getLendDate());
			//ps.setString(9, m.getBackDate());
			ps.setInt(10, m.getRating());
			ps.setString(11, m.getDescription());
			ps.setString(12, m.getComment());
			ps.setString(13, imageWriteToFile(m.getCover()));
			ps.setString(14, m.getFormat());
			ps.setString(15, m.getInterpreter());
			ps.setString(16, m.getType());
			ps.setBoolean(17, m.isDeleted());
			ps.executeUpdate();
			System.out.println("Music added");

		}
	}

	private static void insertVideo(Video v) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_SELECT_VIDEO);
		ps.setInt(1, v.hashCode());
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			ps = con.prepareStatement(SQL_INSERT_VIDEO);
			ps.setInt(1, v.hashCode());
			ps.setString(2, v.getTitle());
			ps.setString(3, v.getEan());
			ps.setString(4, v.getGenre());
			//ps.setInt(5, v.getYear());
			ps.setString(6, v.getLocation());
			//ps.setString(7, v.getLendTo());
			//ps.setString(8, v.getLendDate());
			//ps.setString(9, v.getBackDate());
			ps.setInt(10, v.getRating());
			ps.setString(11, v.getDescription());
			ps.setString(12, v.getComment());
			ps.setString(13, imageWriteToFile(v.getCover()));
			ps.setString(14, v.getFormat());
			ps.setString(15, v.getDirector());
			//ps.setString(16, v.getActor());
			ps.setBoolean(17, v.isDeleted());
			ps.executeUpdate();			
			System.out.println("Video added");
		}
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
}