/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.print.Book;
import java.util.LinkedList;
import javax.swing.SwingUtilities;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 *
 * @author bline
 */
public class MuViBee {
    private LinkedList<Book> bookList;
    private LinkedList<Music> musicList;
    private LinkedList<Video> videoList;
    private MainFrame mainFrame;

    public MuViBee() {

        //TODO adapterklasse für listen. siehe unten
        bookList = new LinkedList<Book>();
        musicList = new LinkedList<Music>();
        videoList = new LinkedList<Video>();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }

    public static void main(String args[]) {
        new MuViBee();
    }
}
