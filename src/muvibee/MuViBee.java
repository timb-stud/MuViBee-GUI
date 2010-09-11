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
    private Book currentBook;
    private Music currentMusic;
    private Video currentVideo;

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

public String showEanInputFrame(){
        return (String)JOptionPane.showInputDialog(
                    mainFrame,
                    "Bitte EAN eingeben:"
                    + "",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
    }

    public void showBookItem(Book book){
        mainFrame.setBookItem(book);
        mainFrame.bookItemSetVisible(true);
    }

    public void showMusicItem(Music music){
        mainFrame.setMusicItem(music);
        mainFrame.musicItemSetVisible(true);
    }

    public void showVideoItem(Video video) {
        mainFrame.setVideoItem(video);
        mainFrame.videoItemSetVisible(true);
    }

    public void setCurrentBook(Book book) {
        currentBook = book;
    }
    
    public void setCurrentMusic(Music music) {
        currentMusic = music;
    }

    public void setCurrentVideo(Video video) {
        currentVideo = video;
    }

    public void setCurrentBookItemInformation() {
        mainFrame.setBookItemInformation(currentBook);
    }

    void setCurrentVideoItemInformation() {
        mainFrame.setVideoItemInformation(currentVideo);
    }

    public void updateBookList() {
        if (!bookList.contains(currentBook)) {
            bookList.add(currentBook);
        }
    }


    public void updateVideoList() {
        if (!videoList.contains(currentVideo)) {
            videoList.add(currentVideo);
        }
    }

    public static void main(String args[]) {
        MuViBee mvb = new MuViBee();
    }

}
