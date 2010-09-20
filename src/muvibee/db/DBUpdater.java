package muvibee.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import muvibee.media.Book;
import muvibee.media.Media;

import muvibee.media.Music;
import muvibee.media.Video;


/**
 * @author tobiaslana
 * erwartet Media Opbjekt und schreibt alle Attribute der ID in die Datenbank
 */

public class DBUpdater {
	
	private final static String SQL_UPDATE_BOOK  = "UPDATE books SET (title, ean, genre, year, location, lendto, lenddate, backdate, rating, description, comment, cover, author, language, isbn, isdeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE ID = ?";
	private final static String SQL_UPDATE_MUSIC = "UPDATE music SET (title, ean, genre, year, location, lendto, lenddate, backdate, rating, description, comment, cover, format, interpreter, type, isdeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE ID = ?";
	private final static String SQL_UPDATE_VIDEO = "UPDATE videos SET (title, ean, genre, year, location, lendto, lenddate, backdate, rating, description, comment, cover, format, director, actor, isdeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE ID = ?";

	private static Connection con = null;
		
	public DBUpdater(Media m) {
		UpdateMedia(m);
	}

	private void UpdateMedia(Media m) {
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
			//ps.setInt(4, m.getYear());
			ps.setString(5, m.getLocation());
			//ps.setString(6, m.getLendTo());
			//ps.setString(7, m.getLendDate());
			//ps.setString(8, m.getBackDate());
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
				//ps.setString(15, ((Video)m).getActor());
			}
			ps.setBoolean(16, m.isDeleted());
			//ps.setInt(17, m.getID());
			ps.executeUpdate();
			con.prepareStatement("SHUTDOWN").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	
	}
}
