/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import muvibee.gui.MainFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import muvibee.db.DBSelector;
import muvibee.ean.EAN;
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
import muvibee.utils.Settings;
import muvibee.utils.SortTypes;
import util.deleteditemlist.DeletedItemEntry;
import util.deleteditemlist.DeletedItemsList;
import util.expiredList.ExpiredItemsList;

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
    private MediaList expiredMediaList;
    private MainFrame mainFrame;
    private Book currentBook;
    private Music currentMusic;
    private Video currentVideo;
    private Media[] currentDeletedMediaList;
    public static String mainBundlePath = "muvibee.resources.MuViBee";
    public static String PATH;
    private Settings settings;

    public MuViBee() {
        final MuViBee mvb = this;
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        File file = new File(url.getPath());
//        PATH = file.getParentFile().getParentFile().toURI().getPath(); // keine jar
        PATH = file.getParentFile().toURI().getPath(); //JAR
        try {
            PATH = URLDecoder.decode(PATH, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        System.out.println("path:" + PATH);

        settings = new Settings(PATH);
        try {
            settings.load();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Unable to read the settings file.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        String proxyHost = settings.getProxyHost();
        String proxyPort = settings.getProxyPort();
        if(proxyHost == null || proxyPort == null)
            EAN.setProxy(false, PATH, PATH);
        else
            EAN.setProxy(true, PATH, PATH);
        

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                filterBookList = new BookList();
                filterMusicList = new MusicList();
                filterVideoList = new VideoList();
                deletedMediaList = new MediaList();
                expiredMediaList = new MediaList();



                mainFrame = new MainFrame(mvb);
                mainFrame.setLanguage(settings.getLanguage());
                mainFrame.setVisible(true);

                (new Thread() {

                    @Override
                    public void run() {
                        bookList = DBSelector.getBookList(false, null);
                        musicList = DBSelector.getMusicList(false, null);
                        videoList = DBSelector.getVideoList(false, null);

                        filterBookList.addAll(bookList);
                        filterMusicList.addAll(musicList);
                        filterVideoList.addAll(videoList);
                        deletedMediaList.addAll(DBSelector.getBookList(true, null));
                        deletedMediaList.addAll(DBSelector.getMusicList(true, null));
                        deletedMediaList.addAll(DBSelector.getVideoList(true, null));
                        setOverviewInformation();
                    }
                }).start();

            }
        });

    }

    public int showSignOverFrame() {
        ResourceBundle bundle = ResourceBundle.getBundle(mainBundlePath);
        return JOptionPane.showConfirmDialog(mainFrame,
                bundle.getString("want_to_save"),
                bundle.getString("pleaseChoose"),
                JOptionPane.YES_NO_OPTION);
    }

    public int showSelfEANDecisionFrame() {
        ResourceBundle bundle = ResourceBundle.getBundle(mainBundlePath);
        Object[] options = {bundle.getString("ean"), bundle.getString("self")};

        return JOptionPane.showOptionDialog(mainFrame,
                bundle.getString("want_self_ean"),
                bundle.getString("pleaseChoose"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public int showSaveChangeDecisionFrame() {
        ResourceBundle bundle = ResourceBundle.getBundle(mainBundlePath);
        Object[] options = {bundle.getString("change"), bundle.getString("cancel")};

        return JOptionPane.showOptionDialog(mainFrame,
                bundle.getString("want_to_change"),
                bundle.getString("pleaseChoose"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public int showSaveAddDecisionFrame() {
        ResourceBundle bundle = ResourceBundle.getBundle(mainBundlePath);
        Object[] options = {bundle.getString("add"), bundle.getString("cancel")};

        return JOptionPane.showOptionDialog(mainFrame,
                bundle.getString("want_to_add"),
                bundle.getString("pleaseChoose"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public String showEanInputFrame() {
        ResourceBundle bundle = ResourceBundle.getBundle(mainBundlePath);
        return (String) JOptionPane.showInputDialog(
                mainFrame,
                bundle.getString("insert_ean")
                + "",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
    }

    public boolean showAdvancedSearchDialog() {
        ResourceBundle bundle = ResourceBundle.getBundle(mainBundlePath);
        AdvancedSearchDialog dialog = new AdvancedSearchDialog(mainFrame,
                bundle.getString("advancedSearchDialogTitle"), true, this);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
        int returnStatus = dialog.getReturnStatus();
        if (returnStatus == AdvancedSearchDialog.RET_ILLEGAL_YEAR) {
            StatusBarModel.getInstance().setFailMessage(bundle.getString("illegalYear"));
        } else {
            if (returnStatus == AdvancedSearchDialog.RET_OK) {
                Book b = dialog.getBook();
                Music m = dialog.getMusic();
                Video v = dialog.getVideo();
                advancedSearch(b, m, v);
                return true;
            }
        }
        return false;
    }

    public void showAboutDialog() {
        AboutDialog dialog = new AboutDialog(mainFrame);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    public void showHelpDialog() {
        HelpDialog helpDialog = new HelpDialog(mainFrame);
        helpDialog.setLocationRelativeTo(mainFrame);
        helpDialog.setVisible(true);
    }

    private void setBookItem() {
        mainFrame.setBookItem(currentBook);
    }

    private void setMusicItem() {
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

    public boolean setCurrentBookItemInformation() throws IllegalDateException {
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

    public boolean setCurrentMusicItemInformation() throws IllegalDateException {
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

    public boolean setCurrentVideoItemInformation() throws IllegalDateException {
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
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        currentBook.updateDB();
        bookList = DBSelector.getBookList(false, null);
        filterBookList.clear();
        filterBookList.addAll(bookList);
        StatusBarModel.getInstance().setSuccessMessage(bundle.getString("saved"));
    }

    public void addCurrentMusicToMusicLists() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        currentMusic.updateDB();
        musicList = DBSelector.getMusicList(false, null);
        filterMusicList.clear();
        filterMusicList.addAll(musicList);
        StatusBarModel.getInstance().setSuccessMessage(bundle.getString("saved"));
    }

    public void addCurrentVideoToVideoLists() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        currentVideo.updateDB();
        videoList = DBSelector.getVideoList(false, null);
        filterVideoList.clear();
        filterVideoList.addAll(videoList);
        StatusBarModel.getInstance().setSuccessMessage(bundle.getString("saved"));
    }

    public void removeCurrentBookFromBookLists() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        if (bookList.remove(currentBook)) {
            expiredMediaList.remove(currentBook);
            deletedMediaList.add(currentBook);
            filterBookList.remove(currentBook);
            currentBook.setDeleted(true);
            currentBook.updateDB();
            currentBook = null;
            showBookItem(false);
            StatusBarModel.getInstance().setSuccessMessage(bundle.getString("inBin"));
        }
    }

    public void removeCurrentMusicFromMusicLists() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        if (musicList.remove(currentMusic)) {
            expiredMediaList.remove(currentMusic);
            deletedMediaList.add(currentMusic);
            filterMusicList.remove(currentMusic);
            currentMusic.setDeleted(true);
            currentMusic.updateDB();
            currentMusic = null;
            showMusicItem(false);
            StatusBarModel.getInstance().setSuccessMessage(bundle.getString("inBin"));
        }
    }

    public void removeCurrentVideoFromVideoLists() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        if (videoList.remove(currentVideo)) {
            expiredMediaList.remove(currentVideo);
            deletedMediaList.add(currentVideo);
            filterVideoList.remove(currentVideo);
            currentVideo.setDeleted(true);
            currentVideo.updateDB();
            currentVideo = null;
            showVideoItem(false);
            StatusBarModel.getInstance().setSuccessMessage(bundle.getString("inBin"));
        }
    }

    public void removeCurrentDeletedMediaFromDeletedList() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        for (Media m : currentDeletedMediaList) {
            deletedMediaList.remove(m);
            m.deleteDB();
            m = null;
        }
        StatusBarModel.getInstance().setSuccessMessage(bundle.getString("deleted"));
    }

    public void restoreCurrentDeletedMedia() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
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
            m.setDeleted(false);
            m.updateDB();
            m = null;
            StatusBarModel.getInstance().setSuccessMessage(bundle.getString("restored"));
        }
    }


    private String getSortName(SortTypes sortBy){
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
                switch (sortBy){
                    case TITLE :
                        return bundle.getString("titleLabel");
                    case RELEASEYEAR :
                        return bundle.getString("releaseYearLabel");
                    case GENRE:
                        return bundle.getString("genreLabel");
                    case RATING:
                        return bundle.getString("ratingLabel");
                    case LANGUAGE:
                        return bundle.getString("languageLabel");
                    case FORMAT:
                        return bundle.getString("formatLabel");
                    case DIRECTOR:
                        return bundle.getString("directorLabel");
                    case AUTHOR:
                    case ARTIST:
                        return bundle.getString("artistLabel");
                    default:
                       return bundle.getString("n/a");
                }
    }

    
    public void sortedByBook(){
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        StringBuilder sb = new StringBuilder(bundle.getString("newSortedBy"));
        for (SortTypes sortBy : filterBookList.getSortedBy()){
            sb.append(getSortName(sortBy));
            sb.append(" --> ");
        }
        mainFrame.getSortPanelBooks().setToolTipText(sb.toString());
        StatusBarModel.getInstance().setSuccessMessage(sb.toString());
    }


    public void sortedByMusic() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        StringBuilder sb = new StringBuilder(bundle.getString("newSortedBy"));
        for (SortTypes sortBy : filterMusicList.getSortedBy()) {
            sb.append(getSortName(sortBy));
            sb.append(" --> ");
        }
        mainFrame.getSortPanelMusic().setToolTipText(sb.toString());
        StatusBarModel.getInstance().setSuccessMessage(sb.toString());
    }


    public void sortedByVideo() {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        StringBuilder sb = new StringBuilder(bundle.getString("newSortedBy"));
        for (SortTypes sortBy : filterVideoList.getSortedBy()) {
            sb.append(getSortName(sortBy));
            sb.append(" --> ");
        }
        mainFrame.getSortPanelVideo().setToolTipText(sb.toString());
        StatusBarModel.getInstance().setSuccessMessage(sb.toString());
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

    public static void main(String args[]) {
        Locale locale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        MuViBee mvb = new MuViBee();
    }

    public void resetSearch() {
        mainFrame.resetSearch();
        resetFilterLists();
        setListsColor(Color.white);
        mainFrame.deleteSearchButtonSetVisible(false);
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
            if (!b.sortMatches(str)) {
                filterBookList.remove(b);
            }
        }
        for (Music m : musicList) {
            if (!m.sortMatches(str)) {
                filterMusicList.remove(m);
            }
        }
        for (Video v : videoList) {
            if (!v.sortMatches(str)) {
                filterVideoList.remove(v);
            }
        }
    }

    public void advancedSearch(Book book, Music music, Video video) {
        resetFilterLists();
        for (Book b : bookList) {
            if (!b.sortMatches(book)) {
                filterBookList.remove(b);
            }
        }
        for (Music m : musicList) {
            if (!m.sortMatches(music)) {
                filterMusicList.remove(m);
            }
        }
        for (Video v : videoList) {
            if (!v.sortMatches(video)) {
                filterVideoList.remove(v);
            }
        }
    }

    public void reloadLabels() {
        mainFrame.reloadLabels(mainBundlePath);
    }



    public int getLentToBook() {
        int sum = 0;
        for (Book b : bookList) {
            if (b.isLent()) {
                sum++;
            }
        }
        return sum;
    }

    public int getLentToMusic() {
        int sum = 0;
        for (Music m : musicList) {
            if (m.isLent()) {
                sum++;
            }
        }
        return sum;
    }

    public int getLentToVideo() {
        int sum = 0;
        for (Video v : videoList) {
            if (v.isLent()) {
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

    public DeletedItemsList getDeletedList() {
        return mainFrame.getDeletedList();
    }

    public MediaList getExpiredMediaList() {
        return expiredMediaList;
    }

    public void fillCurrentDeletedMedia(DeletedItemsList deletedList) {
        Media[] medias = new Media[deletedList.getSelectedValues().length];
        for (int i = 0; i < medias.length; i++) {
            medias[i] = ((DeletedItemEntry) deletedList.getSelectedValues()[i]).getMedia();
        }
        setCurrentDeletedMedia(medias);
    }

    public void showDeleteSearchButton(boolean b) {
        mainFrame.deleteSearchButtonSetVisible(b);
    }

    public void setListsColor(Color color) {
        mainFrame.setListsColor(color);
    }

    public void addExpiredMedia() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyy");
        try {
            for (Book b : bookList) {
                if (b.getLentUntilDay() == 0 || b.getLentUntilMonth() == 0 || b.getLentUntilYear() == 0) {
                    continue;
                }
                String bookDate = b.getLentUntilDay() + "." + b.getLentUntilMonth() + "." + b.getLentUntilYear();
                if (df.parse(bookDate).before(Calendar.getInstance().getTime())) {
                    if (!expiredMediaList.contains(b)) {
                        expiredMediaList.add(b);
                    }
                } else {
                    if (expiredMediaList.contains(b)) {
                        expiredMediaList.remove(b);
                    }
                }
            }
            for (Music m : musicList) {
                if (m.getLentUntilDay() == 0 || m.getLentUntilMonth() == 0 || m.getLentUntilYear() == 0) {
                    continue;
                }
                String musicDate = m.getLentUntilDay() + "." + m.getLentUntilMonth() + "." + m.getLentUntilYear();
                if (df.parse(musicDate).before(Calendar.getInstance().getTime())) {
                    if (!expiredMediaList.contains(m)) {
                        expiredMediaList.add(m);
                    }
                } else {
                    if (expiredMediaList.contains(m)) {
                        expiredMediaList.remove(m);
                    }
                }
            }
            for (Video v : videoList) {
                if (v.getLentUntilDay() == 0 || v.getLentUntilMonth() == 0 || v.getLentUntilYear() == 0) {
                    continue;
                }
                String videoDate = v.getLentUntilDay() + "." + v.getLentUntilMonth() + "." + v.getLentUntilYear();
                if (df.parse(videoDate).before(Calendar.getInstance().getTime())) {
                    if (!expiredMediaList.contains(v)) {
                        expiredMediaList.add(v);
                    }
                } else {
                    if (expiredMediaList.contains(v)) {
                        expiredMediaList.remove(v);
                    }
                }
            }
        } catch (ParseException ex) {
        }
    }

    public void showSelectedMediaItem(Media media) {
        int index = -1;
        for (Media m : filterBookList.getList()) {
            index++;
            if (m == media) {
                mainFrame.selectBookTabAndAndCell(index);
                setCurrentBook((Book) m);
                return;
            }
        }
        index = -1;
        for (Media m : filterMusicList.getList()) {
            index++;
            if (m == media) {
                mainFrame.selectMusicTabAndAndCell(index);
                setCurrentMusic((Music) m);
                return;
            }
        }
        index = -1;
        for (Media m : filterVideoList.getList()) {
            index++;
            if (m == media) {
                mainFrame.selectVideoTabAndAndCell(index);
                setCurrentVideo((Video) m);
                return;
            }
        }
    }

    public ExpiredItemsList getExpiredList() {
        return mainFrame.getExpiredList();
    }

    public Settings getSettings() {
        return settings;
    }

}
