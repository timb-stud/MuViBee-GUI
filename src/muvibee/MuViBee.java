/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;


import java.util.LinkedList;
import muvibee.gui.MainFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import muvibee.lists.BookList;
import muvibee.lists.MediaList;
import muvibee.lists.MusicList;
import muvibee.lists.VideoList;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.utils.NonValidYearException;

/**
 *
 * @author bline
 */
public class MuViBee {
    private MediaList bookList;
    private MediaList musicList;
    private MediaList videoList;
    private MediaList deletedMediaList;

    private MainFrame mainFrame;
    private Book currentBook;
    private Music currentMusic;
    private Video currentVideo;
    private Media[] currentDeletedMediaList;

    public MuViBee() {
        final MuViBee mvb = this;

        //TODO adapterklasse für listen. siehe unten
        bookList = new BookList();
        musicList = new MusicList();
        videoList = new VideoList();
        deletedMediaList = new MediaList();
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

    private void setBookItem(){
        mainFrame.setBookItem(currentBook);
    }

    private void setMusicItem(){
        mainFrame.setMusicItem(currentMusic);
    }

    private void setVideoItem() {
        mainFrame.setVideoItem(currentVideo);
    }

    private void showBookItem(boolean b) {
        mainFrame.bookItemSetVisible(b);
    }

    private void showMusicItem(boolean b) {
        mainFrame.musicItemSetVisible(b);
    }
    private void showVideoItem(boolean b) {
        mainFrame.videoItemSetVisible(b);
    }

    public void setCurrentBook(Book book) {
        currentBook = book;
        setBookItem();
        showBookItem(true);
    }
    
    public void setCurrentMusic(Music music) {
        currentMusic = music;
        setMusicItem();
        showMusicItem(true);
    }

    public void setCurrentVideo(Video video) {
        currentVideo = video;
        setVideoItem();
        showVideoItem(true);
    }

    public void setCurrentDeletedMedia(Media[] medias) {
        currentDeletedMediaList = medias;
    }
    
    public void setCurrentBookItemInformation() throws NonValidYearException {
        mainFrame.setBookItemInformation(currentBook);
    }

    public void setCurrentMusicItemInformation() throws NonValidYearException{
	mainFrame.setMusicItemInformation(currentMusic);
    }

    public void setCurrentVideoItemInformation() throws NonValidYearException {
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
            showBookItem(false);
        }
    }

    public void removeCurrentMusicFromMusicList() {
        if (musicList.remove(currentMusic)) {
            deletedMediaList.add(currentMusic);
            currentMusic = null;
            showMusicItem(false);
        }
    }

    public void removeCurrentVideoFromVideoList() {
        if (videoList.remove(currentVideo)) {
            deletedMediaList.add(currentVideo);
            currentVideo = null;
            showVideoItem(false);
        }
    }
    
    public void removeCurrentDeletedMediaFromDeletedList() {
        for (Media m : currentDeletedMediaList) {
            deletedMediaList.remove(m);
            m = null;
        }
    }

    public void restoreCurrentDeletedMedia() {
        for (Media m : currentDeletedMediaList) {
            if (m instanceof Book) {
                bookList.add(m);
            } else {
                if (m instanceof Music) {
                    musicList.add(m);
                } else {
                    if (m instanceof Video) {
                        videoList.add(m);
                    }
                }
            }
            deletedMediaList.remove(m);
            m = null;
        }
    }

    public MediaList getBookList() {
        return bookList;
    }

    public MediaList getMusicList() {
        return musicList;
    }

    public MediaList getVideoList() {
        return videoList;
    }

    public MediaList getDeletedMediaList() {
        return deletedMediaList;
    }

    


    public static void main(String args[]) {
        MuViBee mvb = new MuViBee();
    }

}
