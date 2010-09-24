package muvibee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import muvibee.MuViBee;

/**
 * @author Yassir Klos, Tobias Lana
 * Klasse stellt eine Verbindung mit der internen HSQL Datenbank her
 * Die HSQL Datenbank-Files liegen relativ zum Laufzeitverzeichnis im Ordner data/database
 *
 */
public class DBConnector {

    private DBConnector() { }

    /**
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        }
        //Connection con = DriverManager.getConnection("jdbc:hsqldb:file:./data/database/muvibee", "SA", "");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:file:" + MuViBee.PATH + "/data/database/muvibee", "SA", "");
        con.setAutoCommit(true);
        return con;
    }
}
