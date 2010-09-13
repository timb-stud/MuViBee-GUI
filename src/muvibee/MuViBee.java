/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.LinkedList;
import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import muvibee.media.Book;
import muvibee.media.Media;
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
    private LinkedList<Media> deletedMediaList;
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
        deletedMediaList = new LinkedList<Media>();
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

    public void setBookItem(Book book){
        mainFrame.setBookItem(book);
    }

    public void setMusicItem(Music music){
        mainFrame.setMusicItem(music);
    }

    public void setVideoItem(Video video) {
        mainFrame.setVideoItem(video);
    }

    public void showBookItem(boolean b) {
        mainFrame.bookItemSetVisible(b);
    }

    public void showMusicItem(boolean b) {
        mainFrame.musicItemSetVisible(b);
    }
    public void showVideoItem(boolean b) {
        mainFrame.videoItemSetVisible(b);
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

    public void setCurrentMusicItemInformation(){
	mainFrame.setMusicItemInformation(currentMusic);
    }

    void setCurrentVideoItemInformation() {
        mainFrame.setVideoItemInformation(currentVideo);
    }

    public void addCurrentBookToBookList() {
        if (!bookList.contains(currentBook)) {
            bookList.add(currentBook);
        }
    }

    public void addCurrentMusicToMusicList(){
	if(!musicList.contains(currentMusic)){
	    musicList.add(currentMusic);
	}
    }

    public void addCurrentVideoToVideoList() {
        if (!videoList.contains(currentVideo)) {
            videoList.add(currentVideo);
        }
    }

    public void removeCurrentBookFromBookList() {
        if (bookList.remove(currentBook)) {
            deletedMediaList.add(currentBook);
            currentBook = null;
        }
    }

    public void removeCurrentMusicFromMusicList() {
        if (musicList.remove(currentMusic)) {
            deletedMediaList.add(currentMusic);
            currentMusic = null;
        }
    }

    public void removeCurrentVideoFromVideoList() {
        if (videoList.remove(currentVideo)) {
            deletedMediaList.add(currentVideo);
            currentVideo = null;
        }
    }
    public static void main(String args[]) {
        MuViBee mvb = new MuViBee();
    }

}
