/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import muvibee.media.Book;
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
    private Book tmpBook;
    private Video tmpVideo;

    public MuViBee() {
        final MuViBee mvb = this;

        //TODO adapterklasse für listen. siehe unten
        bookList = new LinkedList<Book>();
        musicList = new LinkedList<Music>();
        videoList = new LinkedList<Video>();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                mainFrame = new MainFrame(mvb);
                mainFrame.setVisible(true);
            }
        });
    }

    public int showDecisionFrame(){
        Object[] options = {"EAN", "Selbst"};

        return JOptionPane.showOptionDialog(mainFrame,
                    "Wollen Sie das Medium selbst anlegen oder per EAN ?",
                    "Bitte wählen",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
    }

    public String showSetEANFrame(){
        return (String)JOptionPane.showInputDialog(
                    mainFrame,
                    "Complete the sentence:\n"
                    + "\"Green eggs and...\"",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "ham");
    }

    public void showBookItem(Book book){
        mainFrame.setBookItem(book);
        mainFrame.bookItemSetVisible(true);
    }

    public void showVideoItem(Video video) {
        mainFrame.setVideoItem(video);
        mainFrame.videoItemSetVisible(true);
    }

    public void setTmpBook(Book book) {
        tmpBook = book;
    }

    public void setTmpVideo(Video video) {
        tmpVideo = video;
    }

    public void setTmpBookItemInformation() {
        mainFrame.setBookItemInformation(tmpBook);
    }

    void setTmpVideoItemInformation() {
        mainFrame.setVideoItemInformation(tmpVideo);
    }

    public void updateBookList() {
        if (!bookList.contains(tmpBook)) {
            bookList.add(tmpBook);
        }
    }


    public void updateVideoList() {
        if (!videoList.contains(tmpVideo)) {
            videoList.add(tmpVideo);
        }
    }

    public static void main(String args[]) {
        MuViBee mvb = new MuViBee();
    }







}
