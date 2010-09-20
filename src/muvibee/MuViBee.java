/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;


import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.ResourceBundle;
import muvibee.gui.MainFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import muvibee.gui.AboutDialog;
import muvibee.gui.AdvancedSearchDialog;
import muvibee.gui.HelpDialog;
import muvibee.gui.StatusBarModel;
import muvibee.lists.BookList;
import muvibee.lists.MediaList;
import muvibee.lists.MusicList;
import muvibee.lists.VideoList;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.utils.SortTypes;

/**
 *
 * @author bline
 */
public class MuViBee {
    private LinkedList<Book> bookList;
    private LinkedList<Music> musicList;
    private LinkedList<Video> videoList;
    private BookList filterBookList;
    private MusicList filterMusicList;
    private VideoList filterVideoList;
    private MediaList deletedMediaList;

    private MainFrame mainFrame;
    private Book currentBook;
    private Music currentMusic;
    private Video currentVideo;
    private Media[] currentDeletedMediaList;

    private int sortBookByTitle = 0;
    private int sortBookByGenre = 0;
    private int sortBookByRating = 0;
    private int sortBookByAuthor = 0;
    private int sortBookByLanguage = 0;

    private int sortMusicByTitle = 0;
    private int sortMusicByGenre = 0;
    private int sortMusicByRating = 0;
    private int sortMusicByType = 0;
    private int sortMusicByInterpreter = 0;

    private int sortVideoByTitle = 0;
    private int sortVideoByGenre = 0;
    private int sortVideoByRating = 0;
    private int sortVideoByActors = 0;
    private int sortVideoByRegisseur = 0;

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

    public int showSelfEANDecisionFrame(){
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

        public int showSaveChangeDecisionFrame(){
        Object[] options = {"Ändern", "Abbrechen"};

        return JOptionPane.showOptionDialog(mainFrame,
                    "Wollen Sie das Medium ändern ?",
                    "Bitte wählen",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
    }

    public int showSaveAddDecisionFrame(){
        Object[] options = {"Hinzufügen", "Abbrechen"};

        return JOptionPane.showOptionDialog(mainFrame,
                    "Wollen Sie das Medium hinzufügen ?",
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

    public void showAdvancedSearchDialog(){
        ResourceBundle bundle = ResourceBundle.getBundle(mainBundlePath);
        AdvancedSearchDialog dialog = new AdvancedSearchDialog(mainFrame, 
                bundle.getString("advancedSearchDialogTitle"), true, this);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
        int returnStatus = dialog.getReturnStatus();
        if(returnStatus == AdvancedSearchDialog.RET_ILLEGAL_YEAR){
            StatusBarModel.getInstance().setFailMessage(bundle.getString("illegalYear"));
        }else{
            if(returnStatus == AdvancedSearchDialog.RET_OK){
                mainFrame.getDeleteSearchButton().setForeground(Color.red);
                Book b = dialog.getBook();
                Music m = dialog.getMusic();
                Video v = dialog.getVideo();
                advancedSearch(b, m, v);
            }
        }
    }


    public void showAboutDialog(){
        AboutDialog dialog = new AboutDialog(mainFrame);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    public void showHelpDialog(){
        HelpDialog helpDialog = new HelpDialog(mainFrame);
        helpDialog.setLocationRelativeTo(mainFrame);
        helpDialog.setVisible(true);
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

    public void showBookItem(boolean b) {
        mainFrame.bookItemSetVisible(b);
    }

    public void showMusicItem(boolean b) {
        mainFrame.musicItemSetVisible(b);
    }
    public void showVideoItem(boolean b) {
        mainFrame.videoItemSetVisible(b);
    }

    public void unselectLists() {
        mainFrame.unselectLists();
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
    
    public boolean setCurrentBookItemInformation() throws IllegalYearException {
        if (bookList.contains(currentBook)) {
            if (showSaveChangeDecisionFrame() == 0) {
                mainFrame.setBookItemInformation(currentBook);
                return true;
            }
        } else {
            if (showSaveAddDecisionFrame() == 0) {
                mainFrame.setBookItemInformation(currentBook);
                return true;
            }
        }
        return false;
    }

    public boolean setCurrentMusicItemInformation() throws IllegalYearException {
	if (musicList.contains(currentMusic)) {
            if (showSaveChangeDecisionFrame() == 0) {
                mainFrame.setMusicItemInformation(currentMusic);
                return true;
            }
        } else {
            if (showSaveAddDecisionFrame() == 0) {
                mainFrame.setMusicItemInformation(currentMusic);
                return true;
            }
        }
        return false;
    }

    public boolean setCurrentVideoItemInformation() throws IllegalYearException {
        if (videoList.contains(currentVideo)) {
            if (showSaveChangeDecisionFrame() == 0) {
                mainFrame.setVideoItemInformation(currentVideo);
                return true;
            }
        } else {
            if (showSaveAddDecisionFrame() == 0) {
                mainFrame.setVideoItemInformation(currentVideo);
                return true;
            }
        }
        return false;
    }

    public void addCurrentBookToBookLists() {
        if (!bookList.contains(currentBook)) {
            filterBookList.add(currentBook);
            bookList.add(currentBook);
        }
    }

    public void addCurrentMusicToMusicLists(){
	if(!musicList.contains(currentMusic)){
	    filterMusicList.add(currentMusic);
            musicList.add(currentMusic);
	}
    }

    public void addCurrentVideoToVideoLists() {
        if (!videoList.contains(currentVideo)) {
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
        mainFrame.getDeleteSearchButton().setForeground(Color.BLACK);
        mainFrame.getDeleteSearchButton().setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
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
        mainFrame.getDeleteSearchButton().setForeground(Color.red);
        mainFrame.getDeleteSearchButton().setFont(new Font(Font.SANS_SERIF,Font.BOLD,13));
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

    public void advancedSearch(Book book, Music music, Video video){
        resetFilterLists();
        for (Book b : bookList) {
            if (!b.matches(book)) {
                filterBookList.remove(b);
            }
        }
        for (Music m : musicList) {
            if (!m.matches(music)) {
                filterMusicList.remove(m);
            }
        }
        for (Video v : videoList) {
            if (!v.matches(video)) {
                filterVideoList.remove(v);
            }
        }
    }

    public void reloadLabels(){
        mainFrame.reloadLabels(mainBundlePath);
    }

    public void sortBooksByTitle() {
        if (sortBookByTitle == 0) {
            sortBookByTitle = 1;
        } else {
            sortBookByTitle = 0;
        }
        sortLists();
    }

    public void sortBooksByGenre() {
        if (sortBookByGenre == 0) {
            sortBookByGenre = 1;
        } else {
            sortBookByGenre = 0;
        }
        sortLists();
    }

    public void sortBooksByRating() {
        if (sortBookByRating == 0) {
            sortBookByRating = 1;
        } else {
            sortBookByRating = 0;
        }
        sortLists();
    }

    public void sortMusicByTitle() {
        if (sortMusicByTitle == 0) {
            sortMusicByTitle = 1;
        } else {
            sortMusicByTitle = 0;
        }
        sortLists();
    }

    public void sortMusicByGenre() {
        if (sortMusicByGenre == 0) {
            sortMusicByGenre = 1;
        } else {
            sortMusicByGenre = 0;
        }
        sortLists();
    }

    public void sortMusicByRating() {
        if (sortMusicByRating == 0) {
            sortMusicByRating = 1;
        } else {
            sortMusicByRating = 0;
        }
        sortLists();
    }

    public void sortVideoByTitle() {
        if (sortVideoByTitle == 0) {
            sortVideoByTitle = 1;
        } else {
            sortVideoByTitle = 0;
        }
        sortLists();
    }

    public void sortVideoByGenre() {
        if (sortVideoByGenre == 0) {
            sortVideoByGenre = 1;
        } else {
            sortVideoByGenre = 0;
        }
        sortLists();
    }

    public void sortVideoByRating() {
        if (sortVideoByRating == 0) {
            sortVideoByRating = 1;
        } else {
            sortVideoByRating = 0;
        }
        sortLists();
    }

    public void sortBookByAuthor() {
        if (sortBookByAuthor == 0) {
            sortBookByAuthor = 1;
        } else {
            sortBookByAuthor = 0;
        }
        sortLists();
    }

    public void sortBookByLanguage() {
        if (sortBookByLanguage == 0) {
            sortBookByLanguage = 1;
        } else {
            sortBookByLanguage = 0;
        }
        sortLists();
    }

    public void sortMusicByType() {
        if (sortMusicByType == 0) {
            sortMusicByType = 1;
        } else {
            sortMusicByType = 0;
        }
        sortLists();
    }

    public void sortMusicByInterpreter() {
        if (sortMusicByInterpreter == 0) {
            sortMusicByInterpreter = 1;
        } else {
            sortMusicByInterpreter = 0;
        }
        sortLists();
    }

    public void sortVideoByRegisseur() {
        if (sortVideoByRegisseur == 0) {
            sortVideoByRegisseur = 1;
        } else {
            sortVideoByRegisseur = 0;
        }
        sortLists();
    }

    public void sortVideoByActors() {
        if (sortVideoByActors == 0) {
            sortVideoByActors = 1;
        } else {
            sortVideoByActors = 0;
        }
        sortLists();
    }

    public void sortLists() {
        if (sortBookByTitle == 1) {
            filterBookList.sortByTitle();
        } else {
            filterBookList.removeSort(SortTypes.TITLE);
        }
        if (sortBookByGenre == 1) {
            filterBookList.sortByGenre();
        }else {
            filterBookList.removeSort(SortTypes.GENRE);
        }
        if (sortBookByRating == 1) {
            filterBookList.sortByRating();
        }else {
            filterBookList.removeSort(SortTypes.RATING);
        }
        if (sortMusicByTitle == 1) {
            filterMusicList.sortByTitle();
        }else {
            filterMusicList.removeSort(SortTypes.TITLE);
        }
        if (sortMusicByGenre == 1) {
            filterMusicList.sortByGenre();
        }else {
            filterMusicList.removeSort(SortTypes.GENRE);
        }
        if (sortMusicByRating == 1) {
            filterMusicList.sortByRating();
        }else {
            filterMusicList.removeSort(SortTypes.RATING);
        }
        if (sortVideoByTitle == 1) {
            filterVideoList.sortByTitle();
        }else {
            filterVideoList.removeSort(SortTypes.TITLE);
        }
        if (sortVideoByGenre == 1) {
            filterVideoList.sortByGenre();
        }else {
            filterVideoList.removeSort(SortTypes.GENRE);
        }
        if (sortVideoByRating == 1) {
            filterVideoList.sortByRating();
        }else {
            filterVideoList.removeSort(SortTypes.RATING);
        }
        if (sortBookByAuthor == 1) {
            filterBookList.sortByAuthor();
        }else {
            filterBookList.removeSort(SortTypes.AUTHOR);
        }
        if (sortBookByLanguage == 1) {
            filterBookList.sortByLanguage();
        }else {
            filterBookList.removeSort(SortTypes.LANGUAGE);
        }
        if (sortMusicByType == 1) {
            filterMusicList.sortByType();
        }else {
            filterMusicList.removeSort(SortTypes.TYPE);
        }
        if (sortMusicByInterpreter == 1) {
            filterMusicList.sortByInterpreter();
        }else {
            filterMusicList.removeSort(SortTypes.INTERPRETER);
        }
        if (sortVideoByRegisseur == 1) {
            filterVideoList.sortByDirector();
        }else {
            filterVideoList.removeSort(SortTypes.REGISSEUR);
        }
        if (sortVideoByActors == 1) {
            filterVideoList.sortByActors();
        }else {
            filterVideoList.removeSort(SortTypes.ACTORS);
        }
    }

    public int getLentToBook() {
        int sum = 0;
        for (Book b: bookList) {
            if (!b.getLentTo().isEmpty()) {
                sum++;
            }
        }
        return sum;
    }

    public int getLentToMusic() {
        int sum = 0;
        for (Music m: musicList) {
            if (!m.getLentTo().isEmpty()) {
                sum++;
            }
        }
        return sum;
    }

    public int getLentToVideo() {
        int sum = 0;
        for (Video v: videoList) {
            if (!v.getLentTo().isEmpty()) {
                sum++;
            }
        }
        return sum;
    }

    public int getNumberOfBooks() {
        return bookList.size();
    }

    public int getNumberOfMusic() {
        return musicList.size();
    }

    public int getNumberOfVideo() {
        return videoList.size();
    }

    public void setOverviewInformation() {
        mainFrame.setOverviewInformation(this);
    }

    public int getNumberOfDeletedBooks() {
        int sum = 0;
        for (Media m : deletedMediaList.getList()) {
            if (m instanceof Book) {
                sum++;
            }
        }
        return sum;
    }

    public int getNumberOfDeletedMusic() {
        int sum = 0;
        for (Media m : deletedMediaList.getList()) {
            if (m instanceof Music) {
                sum++;
            }
        }
        return sum;
    }

    public int getNumberOfDeletedVideos() {
        int sum = 0;
        for (Media m : deletedMediaList.getList()) {
            if (m instanceof Video) {
                sum++;
            }
        }
        return sum;
    }
}
