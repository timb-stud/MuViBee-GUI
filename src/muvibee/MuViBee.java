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
    private LinkedList<Book> bookList;
    private LinkedList<Music> musicList;
    private LinkedList<Video> videoList;
    private MediaList filterBookList;
    private MediaList filterMusicList;
    private MediaList filterVideoList;
    private MediaList deletedMediaList;

    private MainFrame mainFrame;
    private Book currentBook;
    private Music currentMusic;
    private Video currentVideo;
    private Media[] currentDeletedMediaList;

    private String mainBundlePath = "muvibee.resources.MuViBee";

    public MuViBee() {
        final MuViBee mvb = this;

        //TODO adapterklasse für listen. siehe unten
        filterBookList = new BookList();
        filterMusicList = new MusicList();
        filterVideoList = new VideoList();
        deletedMediaList = new MediaList();

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

    public int showSignOverFrame(){
        return JOptionPane.showConfirmDialog(mainFrame,
                    "Möchten Sie die Änderungen speichern ?",
                    "Bitte wählen",
                    JOptionPane.YES_NO_OPTION);
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

    public void addCurrentBookToBookLists() {
        if (!filterBookList.contains(currentBook)) {
            filterBookList.add(currentBook);
            bookList.add(currentBook);
        }
    }

    public void addCurrentMusicToMusicLists(){
	if(!filterMusicList.contains(currentMusic)){
	    filterMusicList.add(currentMusic);
            musicList.add(currentMusic);
	}
    }

    public void addCurrentVideoToVideoLists() {
        if (!filterVideoList.contains(currentVideo)) {
            filterVideoList.add(currentVideo);
            videoList.add(currentVideo);
        }
    }

    public void removeCurrentBookFromBookLists() {
        if (bookList.remove(currentBook)) {
            deletedMediaList.add(currentBook);
            filterBookList.remove(currentBook);
            currentBook = null;
            showBookItem(false);
        }
    }

    public void removeCurrentMusicFromMusicLists() {
        if (musicList.remove(currentMusic)) {
            deletedMediaList.add(currentMusic);
            filterMusicList.remove(currentMusic);
            currentMusic = null;
            showMusicItem(false);
        }
    }

    public void removeCurrentVideoFromVideoLists() {
        if (videoList.remove(currentVideo)) {
            deletedMediaList.add(currentVideo);
            filterVideoList.remove(currentVideo);
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
                filterBookList.add(m);
                bookList.add((Book) m);
            } else {
                if (m instanceof Music) {
                    filterMusicList.add(m);
                    musicList.add((Music) m);
                } else {
                    if (m instanceof Video) {
                        filterVideoList.add(m);
                        videoList.add((Video) m);
                    }
                }
            }
            deletedMediaList.remove(m);
            m = null;
        }
    }

    public MediaList getBookList() {
        return filterBookList;
    }

    public MediaList getMusicList() {
        return filterMusicList;
    }

    public MediaList getVideoList() {
        return filterVideoList;
    }

    public MediaList getDeletedMediaList() {
        return deletedMediaList;
    }

    public String getMainBundlePath() {
        return mainBundlePath;
    }

    public static void main(String args[]) {
        MuViBee mvb = new MuViBee();
    }

    public void resetSearch() {
        mainFrame.resetSearch();
        resetFilterLists();
    }

    public void resetFilterLists() {
        filterBookList.getList().clear();
        filterBookList.addAll(bookList);

        filterMusicList.getList().clear();
        filterMusicList.addAll(musicList);

        filterVideoList.getList().clear();
        filterVideoList.addAll(videoList);
    }

    public void search() {
        String str = mainFrame.getSearchString();
        resetFilterLists();
        for (Book b : bookList) {
            if (!b.matches(str)) {
                filterBookList.remove(b);
            }
        }
        for (Music m : musicList) {
            if (!m.matches(str)) {
                filterMusicList.remove(m);
            }
        }
        for (Video v : videoList) {
            if (!v.matches(str)) {
                filterVideoList.remove(v);
            }
        }
    }

}
