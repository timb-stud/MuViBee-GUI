/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.db.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import muvibee.db.DBConnector;

/**
 *
 * @author tobiaslana
 */
public class TruncateTables {
    public static void main(String[] args) {
        try {
            Connection con = null;
            con = DBConnector.getConnection();
            con.prepareStatement("TRUNCATE TABLE book").execute();
            con.prepareStatement("TRUNCATE TABLE music").execute();
            con.prepareStatement("TRUNCATE TABLE video").execute();
            con.prepareStatement("SHUTDOWN").execute();
        } catch (SQLException ex) {
            Logger.getLogger(TruncateTables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
