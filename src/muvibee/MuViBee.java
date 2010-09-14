/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;


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

/**
 *
 * @author bline
 */
public class MuViBee {
    private BookList bookList;
    private MusicList musicList;
    private VideoList videoList;
    private MediaList deletedMediaList;

    private MainFrame mainFrame;
    private Book currentBook;
    private Music currentMusic;
    private Video currentVideo;
    private Media currentDeletedMedia;

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

    public void setBookItem(){
        mainFrame.setBookItem(currentBook);
    }

    public void setMusicItem(){
        mainFrame.setMusicItem(currentMusic);
    }

    public void setVideoItem() {
        mainFrame.setVideoItem(currentVideo);
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

    //TODO wird in listenlistener der Gelöschtliste aufgerufen wenn auf item geklickt
    public void setCurrentDeletedMedia(Media media) {
        currentDeletedMedia = media;
    }
    
    public void setCurrentBookItemInformation() {
        mainFrame.setBookItemInformation(currentBook);
    }

    public void setCurrentMusicItemInformation(){
	mainFrame.setMusicItemInformation(currentMusic);
    }

    public void setCurrentVideoItemInformation() {
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
    
    public void removeCurrentDeletedMediaFromDeletedList() {
        deletedMediaList.remove(currentDeletedMedia);
        currentDeletedMedia = null;
    }

    public void restoreCurrentDeletedMedia() {
        if (currentDeletedMedia instanceof Book) {
            bookList.add(currentDeletedMedia);
        } else {
            if (currentDeletedMedia instanceof Music) {
                musicList.add(currentDeletedMedia);
            } else {
                if (currentDeletedMedia instanceof Video) {
                    videoList.add(currentDeletedMedia);
                }
            }
        }
        deletedMediaList.remove(currentDeletedMedia);
        currentDeletedMedia = null;
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


    public static void main(String args[]) {
        MuViBee mvb = new MuViBee();
    }

}
