/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 03.09.2010, 08:57:34
 */

package muvibee.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import muvibee.actionlistener.AddActionListener;
import muvibee.actionlistener.ComboBoxActionListener;
import muvibee.actionlistener.FinalDeleteListener;
import muvibee.actionlistener.SaveActionListener;
import muvibee.actionlistener.RestoreListener;
import muvibee.actionlistener.DeleteListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableCellRenderer;
import muvibee.IllegalYearException;
import muvibee.MuViBee;
import muvibee.actionlistener.AboutActionListener;
import muvibee.actionlistener.HelpActionListener;
import muvibee.actionlistener.HideActionListener;
import muvibee.actionlistener.ResetSearchActionListener;
import muvibee.actionlistener.LanguageActionListener;
import muvibee.actionlistener.SearchActionListener;
import muvibee.actionlistener.SortActionListener;
import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.utils.ResizeImageIcon;
import util.coversList.CoverList;
import util.coverDetailsList.*;
import util.coversList.CoverListEntry;
import util.deleteditemlist.DeletedItemsList;
import util.detailsList.*;
import util.expiredList.ExpiredItemsList;
import util.tree.PrioTree;



/**
 *
 * @author bline
 */
public class MainFrame extends javax.swing.JFrame {

    private void createExpiredList(MuViBee mvb) {
        eil = new ExpiredItemsList(mvb);
        mvb.getExpiredMediaList().addObserver(eil);
        expiredScrollPane.setViewportView(eil);
    }

    private void createDeletedList(MuViBee mvb) {
        dil = new DeletedItemsList(mvb);
        mvb.getDeletedMediaList().addObserver(dil);
        restoreItemsScrollpane.setViewportView(dil);
    }

    /**
     * Erstellt Cover Liste und setzt diese in die GUI
     * @param mvb
     */
    private void createCoverList(MuViBee mvb){
        coverListBook = new CoverList(mvb);
        mvb.getBookList().addObserver(coverListBook);
        coverListMusic = new CoverList(mvb);
        mvb.getMusicList().addObserver(coverListMusic);
        coverListVideo = new CoverList(mvb);
        mvb.getVideoList().addObserver(coverListVideo);

        coverListBookScrollPane.setViewportView(coverListBook);
        coverListMusicScrollPane.setViewportView(coverListMusic);
        coverListVideoScrollPane.setViewportView(coverListVideo);
    }

        /**
     * Erstellt Tree und setzt diese in die GUI
     * @param mvb
     */
    private void createTree(MuViBee mvb){
        prioTreeBook = new PrioTree(mvb);
        mvb.getBookList().addObserver(prioTreeBook);
        prioTreeMusic = new PrioTree(mvb);
        mvb.getMusicList().addObserver(prioTreeMusic);
        prioTreeVideo = new PrioTree(mvb);
        mvb.getVideoList().addObserver(prioTreeVideo);

        treeBookScrollPane.setViewportView(prioTreeBook);
        treeMusicScrollPane.setViewportView(prioTreeMusic);
        treeVideoScrollPane.setViewportView(prioTreeVideo);
    }


    /**
     * Erstellt Details Table und setzt diese in die GUI
     * @param mvb
     */
    private void createDetailsTable(MuViBee mvb){
        detailsTableBook = new DetailsTable(mvb);
        mvb.getBookList().addObserver(detailsTableBook);
        detailsTableMusic = new DetailsTable(mvb);
        mvb.getMusicList().addObserver(detailsTableMusic);
        detailsTableVideo = new DetailsTable(mvb);
        mvb.getVideoList().addObserver(detailsTableVideo);

        detailsListBookScrollPane.setViewportView(detailsTableBook);
        detailsListMusicScrollPane.setViewportView(detailsTableMusic);
        detailsListVideoScrollPane.setViewportView(detailsTableVideo);
    }

    /**
     * Erstellt Cover Details Liste und setzt diese in die GUI
     * @param mvb
     */
    private void createCoverDetailsList(MuViBee mvb){
        coverDetailsBookList = new CoverDetailsList(mvb);
        mvb.getBookList().addObserver(coverDetailsBookList);
        coverDetailsMusicList = new CoverDetailsList(mvb);
        mvb.getMusicList().addObserver(coverDetailsMusicList);
        coverDetailsVideoList = new CoverDetailsList(mvb);
        mvb.getVideoList().addObserver(coverDetailsVideoList);

        coverDetailsListBookScrollPane.setViewportView(coverDetailsBookList);
        coverDetailsListMusicScrollPane.setViewportView(coverDetailsMusicList);
        coverDetailsListVideoScrollPane.setViewportView(coverDetailsVideoList);
    }

    public void unselectLists() {
        coverListBook.clearSelection();
        coverListMusic.clearSelection();
        coverListVideo.clearSelection();

        coverDetailsBookList.clearSelection();
        coverDetailsMusicList.clearSelection();
        coverDetailsVideoList.clearSelection();

        detailsTableBook.clearSelection();
        detailsTableMusic.clearSelection();
        detailsTableVideo.clearSelection();

        prioTreeBook.clearSelection();
        prioTreeMusic.clearSelection();
        prioTreeVideo.clearSelection();
    }

    /** Creates new form MainFrame */
    public MainFrame(MuViBee mvb) {
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 580, Toolkit.getDefaultToolkit().getScreenSize().height/2 - 350);
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    } catch (ClassNotFoundException ex) {
                    } catch (InstantiationException ex) {
                    } catch (IllegalAccessException ex) {
                    } catch (UnsupportedLookAndFeelException ex) {
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        } 
        initComponents();

        setBookLentFieldsEnabled(false);
        setMusicLentFieldsEnabled(false);
        setVideoLentFieldsEnabled(false);

        coverDetailsListBookScrollPane = new JScrollPane();
        detailsListBookScrollPane = new JScrollPane();
        coverListBookScrollPane = new JScrollPane();
        coverDetailsListMusicScrollPane = new JScrollPane();
        detailsListMusicScrollPane = new JScrollPane();
        coverListMusicScrollPane = new JScrollPane();
        coverDetailsListVideoScrollPane = new JScrollPane();
        detailsListVideoScrollPane = new JScrollPane();
        coverListVideoScrollPane = new JScrollPane();
        treeBookScrollPane = new JScrollPane();
        treeMusicScrollPane = new JScrollPane();
        treeVideoScrollPane = new JScrollPane();


        coverDetailsListBookScrollPane.setName("cover details");
        detailsListBookScrollPane.setName("details");
        coverListBookScrollPane.setName("cover");
        treeBookScrollPane.setName("tree");
        
        coverDetailsListMusicScrollPane.setName("cover details");
        detailsListMusicScrollPane.setName("details");
        coverListMusicScrollPane.setName("cover");
        treeMusicScrollPane.setName("tree");
        
        coverDetailsListVideoScrollPane.setName("cover details");
        detailsListVideoScrollPane.setName("details");
        coverListVideoScrollPane.setName("cover");
        treeVideoScrollPane.setName("tree");

        bookCardPanel.add(coverDetailsListBookScrollPane, "cover details");
        bookCardPanel.add(detailsListBookScrollPane, "details");
        bookCardPanel.add(coverListBookScrollPane, "cover");
        bookCardPanel.add(treeBookScrollPane, "tree");

        musicCardPanel.add(coverDetailsListMusicScrollPane, "cover details");
        musicCardPanel.add(detailsListMusicScrollPane, "details");
        musicCardPanel.add(coverListMusicScrollPane, "cover");
        musicCardPanel.add(treeMusicScrollPane, "tree");

        videoCardPanel.add(coverDetailsListVideoScrollPane, "cover details");
        videoCardPanel.add(detailsListVideoScrollPane, "details");
        videoCardPanel.add(coverListVideoScrollPane, "cover");
        videoCardPanel.add(treeVideoScrollPane, "tree");

//        createDetailsList(mvb);
        createCoverDetailsList(mvb);
        createCoverList(mvb);
        createTree(mvb);
        createDetailsTable(mvb);
        createDeletedList(mvb);
        createExpiredList(mvb);

        itemBookScrollPane.setVisible(false);
        itemMusicScrollPane.setVisible(false);
        itemVideoScrollPane.setVisible(false);

        hideBookButton.setVisible(false);
        hideMusicButton.setVisible(false);
        hideVideoButton.setVisible(false);

        deleteSearchButton.setVisible(false);

        //init languageComboBox
        languagesComboBox.addActionListener(new LanguageActionListener(mvb));
        String[] languages = {"en", "de", "ro","ru", "tr"};
        ComboBoxModel cbm = new DefaultComboBoxModel(languages);
        languagesComboBox.setModel(cbm);

            languagesComboBox.setRenderer(new DefaultListCellRenderer(){
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                String flag = "../../muvibee/resources/flag/default_flag.gif";
                try {
                    if (((String)value).equals("en"))
                        flag = "../../muvibee/resources/flag/usa_flag.gif";
                    else if (((String)value).equals("de"))
                        flag = "../../muvibee/resources/flag/de_flag.jpg";
                    else if (((String)value).equals("ru"))
                        flag = "../../muvibee/resources/flag/ru_flag.gif";
                    else if (((String)value).equals("tr"))
                        flag = "../../muvibee/resources/flag/tr_flag.gif";
                    else if (((String)value).equals("ro"))
                        flag = "../../muvibee/resources/flag/ro_flag.gif";

                    label.setIcon(ResizeImageIcon.resizeIcon(24, 20, ImageIO.read(getClass().getResource(flag))));
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return label;
            }
        });

        //init dayComboBoxes
	String[] days = new String[32];
        days[0] = "";
	for(int i=1; i<32;i++)
	    days[i] = String.valueOf(i);
	lentDayBookComboBox.setModel(new DefaultComboBoxModel(days));
	lentUntilDayBookComboBox.setModel(new DefaultComboBoxModel(days));
	lentDayMusicComboBox.setModel(new DefaultComboBoxModel(days));
	lentUntilDayMusicComboBox.setModel(new DefaultComboBoxModel(days));
	lentDayVideoComboBox.setModel(new DefaultComboBoxModel(days));
	lentUntilDayVideoComboBox.setModel(new DefaultComboBoxModel(days));

        //init years ComboBoxes
        String[] years = new String[64];
        years[0] = "";
        for(int i=1;i<years.length;i++)
            years[i] = String.valueOf(2013 - i);
        releaseYearBookComboBox.setModel(new DefaultComboBoxModel(years));
        releaseYearBookComboBox.setSelectedIndex(0);
        releaseYearMusicComboBox.setModel(new DefaultComboBoxModel(years));
        releaseYearMusicComboBox.setSelectedIndex(0);
        releaseYearVideoComboBox.setModel(new DefaultComboBoxModel(years));
        releaseYearVideoComboBox.setSelectedIndex(0);
        lentYearBookComboBox.setModel(new DefaultComboBoxModel(years));
        lentYearBookComboBox.setSelectedIndex(0);
        lentYearMusicComboBox.setModel(new DefaultComboBoxModel(years));
        lentYearMusicComboBox.setSelectedIndex(0);
        lentYearVideoComboBox.setModel(new DefaultComboBoxModel(years));
        lentYearVideoComboBox.setSelectedIndex(0);
        lentUntilYearBookComboBox.setModel(new DefaultComboBoxModel(years));
        lentUntilYearBookComboBox.setSelectedIndex(0);
        lentUntilYearMusicComboBox.setModel(new DefaultComboBoxModel(years));
        lentUntilYearBookComboBox.setSelectedIndex(0);
        lentUntilYearVideoComboBox.setModel(new DefaultComboBoxModel(years));
        lentUntilYearBookComboBox.setSelectedIndex(0);

        DefaultComboBoxModel cbBooksModel = new DefaultComboBoxModel();
        cbBooksModel.addElement(coverDetailsListBookScrollPane);
        cbBooksModel.addElement(coverListBookScrollPane);
        cbBooksModel.addElement(detailsListBookScrollPane);
        cbBooksModel.addElement(treeBookScrollPane);
        viewBookComboBox.setModel(cbBooksModel);
        

        DefaultComboBoxModel cbMusicModel = new DefaultComboBoxModel();
        cbMusicModel.addElement(coverDetailsListMusicScrollPane);
        cbMusicModel.addElement(coverListMusicScrollPane);
        cbMusicModel.addElement(detailsListMusicScrollPane);
        cbMusicModel.addElement(treeMusicScrollPane);
        viewMusicComboBox.setModel(cbMusicModel);
        

        DefaultComboBoxModel cbVideoModel = new DefaultComboBoxModel();
        cbVideoModel.addElement(coverDetailsListVideoScrollPane);
        cbVideoModel.addElement(coverListVideoScrollPane);
        cbVideoModel.addElement(detailsListVideoScrollPane);
        cbVideoModel.addElement(treeVideoScrollPane);
        viewVideoComboBox.setModel(cbVideoModel);
        

        ComboBoxItemRenderer cbRenderer = new ComboBoxItemRenderer();
        viewBookComboBox.setRenderer(cbRenderer);
        viewMusicComboBox.setRenderer(cbRenderer);
        viewVideoComboBox.setRenderer(cbRenderer);
        
        ComboBoxActionListener cbal = new ComboBoxActionListener(bookCardPanel, musicCardPanel, videoCardPanel, viewBookComboBox, viewMusicComboBox, viewVideoComboBox);
        viewBookComboBox.addActionListener(cbal);
        viewMusicComboBox.addActionListener(cbal);
        viewVideoComboBox.addActionListener(cbal);

        AddActionListener aal = new AddActionListener(mvb);
        addBookButton.addActionListener(aal);
        addMusicButton.addActionListener(aal);
        addVideoButton.addActionListener(aal);

        SaveActionListener sal = new SaveActionListener(mvb);
        saveBookButton.addActionListener(sal);
        saveMusicButton.addActionListener(sal);
        saveVideoButton.addActionListener(sal);

        DeleteListener dl = new DeleteListener(mvb);
        deleteBookButton.addActionListener(dl);
        deleteMusicButton.addActionListener(dl);
        deleteVideoButton.addActionListener(dl);

        FinalDeleteListener fdl = new FinalDeleteListener(mvb);
        finalDeleteItemButton.addActionListener(fdl);
        finalDeleteEverythingButton.addActionListener(fdl);

        RestoreListener rl = new RestoreListener(mvb);
        restoreItemButton.addActionListener(rl);
        restoreEverythingButton.addActionListener(rl);


        SearchActionListener searchActionListener = new SearchActionListener(mvb);
        searchButton.addActionListener(searchActionListener);
        advancedSearchButton.addActionListener(searchActionListener);

        AboutActionListener aboutActionListener = new AboutActionListener(mvb);
        aboutButton.addActionListener(aboutActionListener);

        HelpActionListener helpActionListner = new HelpActionListener(mvb);
        helpButton.addActionListener(helpActionListner);
        
        deleteSearchButton.addActionListener(new ResetSearchActionListener(mvb));

        HideActionListener hal = new HideActionListener(mvb);
        hideBookButton.addActionListener(hal);
        hideMusicButton.addActionListener(hal);
        hideVideoButton.addActionListener(hal);

        SortActionListener sortal = new SortActionListener(mvb);
        sortBookTitleButton.addActionListener(sortal);
        sortBookGenreButton.addActionListener(sortal);
        sortBookRatingButton.addActionListener(sortal);
        sortBookAuthorButton.addActionListener(sortal);
        sortBookLanguageButton.addActionListener(sortal);

        sortMusicTitleButton.addActionListener(sortal);
        sortMusicGenreButton.addActionListener(sortal);
        sortMusicRatingButton.addActionListener(sortal);
        sortMusicInterpeterButton.addActionListener(sortal);
        sortMusicTypeButton.addActionListener(sortal);

        sortVideoTitleButton.addActionListener(sortal);
        sortVideoGenreButton.addActionListener(sortal);
        sortVideoRatingButton.addActionListener(sortal);
        sortVideoRegisseurButton.addActionListener(sortal);
        sortVideoActorsButton.addActionListener(sortal);
        
        StatusBar sb = new StatusBar(StatusBarModel.getInstance());
        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sb, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sb, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        reloadLabels(MuViBee.mainBundlePath);
    }

    public final void setOverviewInformation(MuViBee mvb) {
        int lentToBooks = mvb.getLentToBook();
        int lentToMusic = mvb.getLentToMusic();
        int lentToVideos = mvb.getLentToVideo();

        int numberBooks = mvb.getNumberOfBooks();
        int numberMusic = mvb.getNumberOfMusic();
        int numberVideos = mvb.getNumberOfVideo();

        int deletedBooks = mvb.getNumberOfDeletedBooks();
        int deletedMusic = mvb.getNumberOfDeletedMusic();
        int deletedVideos = mvb.getNumberOfDeletedVideos();

        mvb.addExpiredMedia();


        overviewTable.getModel().setValueAt(numberBooks, 0, 1);
        overviewTable.getModel().setValueAt(numberMusic, 1, 1);
        overviewTable.getModel().setValueAt(numberVideos, 2, 1);
        overviewTable.getModel().setValueAt(lentToBooks, 0, 2);
        overviewTable.getModel().setValueAt(lentToMusic, 1, 2);
        overviewTable.getModel().setValueAt(lentToVideos, 2, 2);
        overviewTable.getModel().setValueAt(deletedBooks, 0, 3);
        overviewTable.getModel().setValueAt(deletedMusic, 1, 3);
        overviewTable.getModel().setValueAt(deletedVideos, 2, 3);


    }

    public final void reloadLabels(String bundlePath){
        ResourceBundle bundle = ResourceBundle.getBundle(bundlePath);
        setTitle(bundle.getString("title"));
        searchButton.setText(bundle.getString("searchButton"));
        advancedSearchButton.setText(bundle.getString("advancedSearchButton"));
        helpButton.setText(bundle.getString("helpButton"));
        aboutButton.setText(bundle.getString("aboutButton"));
        tabbedPane.setTitleAt(0, bundle.getString("overviewTab"));
        tabbedPane.setTitleAt(1, bundle.getString("bookTab"));
        tabbedPane.setTitleAt(2, bundle.getString("musikTab"));
        tabbedPane.setTitleAt(3, bundle.getString("videoTab"));
        tabbedPane.setTitleAt(4, bundle.getString("restoreTab"));

        tabbedPane.setBackgroundAt(1, Color.CYAN);
        tabbedPane.setBackgroundAt(2, Color.RED);
        tabbedPane.setBackgroundAt(3, Color.GREEN);

        String day = bundle.getString("dayComboBox");
        updateComboBoxLabel(lentDayBookComboBox, day);
        updateComboBoxLabel(lentUntilDayBookComboBox, day);
        updateComboBoxLabel(lentDayMusicComboBox, day);
        updateComboBoxLabel(lentUntilDayMusicComboBox, day);
        updateComboBoxLabel(lentDayVideoComboBox, day);
        updateComboBoxLabel(lentUntilDayVideoComboBox, day);

        String year = bundle.getString("yearComboBox");
        updateComboBoxLabel(releaseYearBookComboBox, year);
        updateComboBoxLabel(releaseYearMusicComboBox, year);
        updateComboBoxLabel(releaseYearVideoComboBox, year);
        updateComboBoxLabel(lentYearBookComboBox, year);
        updateComboBoxLabel(lentYearMusicComboBox, year);
        updateComboBoxLabel(lentYearVideoComboBox, year);
        updateComboBoxLabel(lentUntilYearBookComboBox, year);
        updateComboBoxLabel(lentUntilYearMusicComboBox, year);
        updateComboBoxLabel(lentUntilYearVideoComboBox, year);

        
	String[] months = new String[13];
        months[0] = bundle.getString("monthComboBox");
        months[1] = bundle.getString("januaryComboBox");
        months[2] = bundle.getString("februaryComboBox");
        months[3] = bundle.getString("marchComboBox");
        months[4] = bundle.getString("aprilComboBox");
        months[5] = bundle.getString("mayComboBox");
        months[6] = bundle.getString("juneComboBox");
        months[7] = bundle.getString("julyComboBox");
        months[8] = bundle.getString("augustComboBox");
        months[9] = bundle.getString("septemberComboBox");
        months[10] = bundle.getString("octoberComboBox");
        months[11] = bundle.getString("novemberComboBox");
        months[12] = bundle.getString("decemberComboBox");
	updateComboBoxLabels(lentMonthBookComboBox, months);
	updateComboBoxLabels(lentUntilMonthBookComboBox, months);
	updateComboBoxLabels(lentMonthMusicComboBox, months);
	updateComboBoxLabels(lentUntilMonthMusicComboBox, months);
	updateComboBoxLabels(lentMonthVideoComboBox, months);
	updateComboBoxLabels(lentUntilMonthVideoComboBox, months);

        String[] musicTypes = new String[3];
        musicTypes[0] = bundle.getString("album");
        musicTypes[1] = bundle.getString("sampler");
        musicTypes[2] = bundle.getString("single");
        updateComboBoxLabels(typeMusicComboBox, musicTypes);

	String[] musicFormats = new String[3];
        musicFormats[0] = bundle.getString("cd");
        musicFormats[1] = bundle.getString("lp");
        musicFormats[2] = bundle.getString("cassette");
	String[] videoFormats = new String[3];
        videoFormats[0] = bundle.getString("cd/dvd");
        videoFormats[1] = bundle.getString("blu-ray");
        videoFormats[2] = bundle.getString("vhs");
	updateComboBoxLabels(formatMusicComboBox, musicFormats);
	updateComboBoxLabels(formatVideoComboBox, videoFormats);

    }

    public void setCover(BufferedImage cover, JLabel label){
        if (cover != null) {
            label.setIcon(ResizeImageIcon.resizeIcon(140, 160, cover));
        } else {
            label.setIcon(null);
        }
    }

    public void setYear(int year, JComboBox cb){
        if(year > 0){
            cb.setSelectedItem(String.valueOf(year));
        }else{
            cb.setSelectedIndex(0);
        }
    }

    public void setBookItem(Book book) {
        setCover(book.getCover(), coverBookLabel);
        titleBookTextField.setText(book.getTitle());
        authorBookTextField.setText(book.getAuthor());
        languageBookTextField.setText(book.getLanguage());
        isbnBookTextField.setText(book.getIsbn());
        eanBookTextField.setText(book.getEan());
        genreBookTextField.setText(book.getGenre());
        setYear(book.getReleaseYear(), releaseYearBookComboBox);
        locationBookTextField.setText(book.getLocation());
        lentBookCheckBox.setSelected(book.isLent());
        lentToBookTextField.setText(book.getLentTo());
        lentDayBookComboBox.setSelectedIndex(book.getLentDay());
        lentMonthBookComboBox.setSelectedIndex(book.getLentMonth());
        setYear(book.getLentYear(), lentYearBookComboBox);
        lentUntilDayBookComboBox.setSelectedIndex(book.getLentUntilDay());
        lentUntilMonthBookComboBox.setSelectedIndex(book.getLentUntilMonth());
        setYear(book.getLentUntilYear(), lentUntilYearBookComboBox);

        switch (book.getRating()) {
            case 0:
                ratingNoneBookRadioButton.setSelected(true);
                break;
            case 1:
                ratingOneBookRadioButton.setSelected(true);
                break;
            case 2:
                ratingTwoBookRadioButton.setSelected(true);
                break;
            case 3:
                ratingThreeBookRadioButton.setSelected(true);
                break;
            default:
                throw new RuntimeException("Illegal Rating Value");
        }
        descriptionBookTextArea.setText(book.getDescription());
        annotationBookTextArea.setText(book.getComment());
    }

    public void setMusicItem(Music music) {
        setCover(music.getCover(), coverMusicLabel);
        titleMusicTextField.setText(music.getTitle());
        artistMusicTextField.setText(music.getInterpreter());

        typeMusicComboBox.setSelectedItem(music.getType());
        formatMusicComboBox.setSelectedItem(music.getFormat());

        eanMusicTextField.setText(music.getEan());
        genreMusicTextField.setText(music.getGenre());
        setYear(music.getReleaseYear(), releaseYearMusicComboBox);
        locationMusicTextField.setText(music.getLocation());
        lentMusicCheckBox.setSelected(music.isLent());
        lentToMusicTextField.setText(music.getLentTo());
        lentDayMusicComboBox.setSelectedIndex(music.getLentDay());
        lentMonthMusicComboBox.setSelectedIndex(music.getLentMonth());
        setYear(music.getLentYear(), lentYearMusicComboBox);
        lentUntilDayMusicComboBox.setSelectedIndex(music.getLentUntilDay());
        lentUntilMonthMusicComboBox.setSelectedIndex(music.getLentUntilMonth());
        setYear(music.getLentUntilYear(), lentUntilYearMusicComboBox);

        switch (music.getRating()) {
            case 0:
                ratingNoneMusicRadioButton.setSelected(true);
                break;
            case 1:
                ratingOneMusicRadioButton.setSelected(true);
                break;
            case 2:
                ratingTwoMusicRadioButton.setSelected(true);
                break;
            case 3:
                ratingThreeMusicRadioButton.setSelected(true);
                break;
            default:
                throw new RuntimeException("Illegal Rating Value");
        }
        descriptionMusicTextArea.setText(music.getDescription());
        annotationMusicTextArea.setText(music.getComment());
    }

    public void setVideoItem(Video video) {
        setCover(video.getCover(), coverVideoLabel);
        titleVideoTextField.setText(video.getTitle());
        directorVideoTextField.setText(video.getDirector());
        actorsVideoTextField.setText(video.getActors());
        formatVideoComboBox.setSelectedItem(video.getFormat());
        eanVideoTextField.setText(video.getEan());
        genreVideoTextField.setText(video.getGenre());
        setYear(video.getReleaseYear(), releaseYearVideoComboBox);
        locationVideoTextField.setText(video.getLocation());
        lentVideoCheckBox.setSelected(video.isLent());
        lentToVideoTextField.setText(video.getLentTo());
        lentDayVideoComboBox.setSelectedIndex(video.getLentDay());
        lentMonthVideoComboBox.setSelectedIndex(video.getLentMonth());
        setYear(video.getLentYear(), lentYearVideoComboBox);
        lentUntilDayVideoComboBox.setSelectedIndex(video.getLentUntilDay());
        lentUntilMonthVideoComboBox.setSelectedIndex(video.getLentUntilMonth());
        setYear(video.getLentUntilYear(), lentUntilYearVideoComboBox);

        switch (video.getRating()) {
            case 0:
                ratingNoneVideoRadioButton.setSelected(true);
                break;
            case 1:
                ratingOneVideoRadioButton.setSelected(true);
                break;
            case 2:
                ratingOneVideoRadioButton.setSelected(true);
                break;
            case 3:
                ratingOneVideoRadioButton.setSelected(true);
                break;
            default:
                throw new RuntimeException("Illegal Rating Value");
        }
        descriptionVideoTextArea.setText(video.getDescription());
        annotationVideoTextArea.setText(video.getComment());
    }

    public int getYear(JComboBox cb) throws IllegalYearException {
        if(cb.getSelectedIndex() == 0)
            return 0;
        Object selectedYear = cb.getSelectedItem();
        String yearS = "";
        if (selectedYear != null) {
            yearS = selectedYear.toString().trim();
        }
        try{
            return Integer.parseInt(yearS);
        }catch(NumberFormatException e){
            throw new IllegalYearException();
        }
    }

    public String getSelected(JComboBox cb){
        Object selected = cb.getSelectedItem();
        if (selected != null) {
            return selected.toString().trim();
        }else{
            return "";
        }
    }

    public void setBookItemInformation(Book book) throws IllegalYearException{
        String title = titleBookTextField.getText().trim();
        String author = authorBookTextField.getText().trim();
        String language = languageBookTextField.getText().trim();
        String isbn = isbnBookTextField.getText().trim();   //TODO Ueberpruefen!?!?
        String ean = eanBookTextField.getText().trim();	    //TODO Ueberpruefen!?!?
        String genre = genreBookTextField.getText().trim();
        int releaseYear = getYear(releaseYearBookComboBox);
        String location = locationBookTextField.getText().trim();
        boolean lent = lentBookCheckBox.isSelected();
        String lendTo = lentToBookTextField.getText().trim();
        int lendDay = lentDayBookComboBox.getSelectedIndex();
        int lendMonth = lentMonthBookComboBox.getSelectedIndex();
        int lentYear = getYear(lentYearBookComboBox);
        int lendUntilDay = lentUntilDayBookComboBox.getSelectedIndex();
        int lendUntilMonth = lentUntilMonthBookComboBox.getSelectedIndex();
        int lentUntilYear = getYear(lentUntilYearBookComboBox);
        String description = descriptionBookTextArea.getText().trim();
        String annotation = annotationBookTextArea.getText().trim();

        //Rating
        int rating = 0;
        if (ratingOneBookRadioButton.isSelected()) {
            rating = 1;
        } else if (ratingTwoBookRadioButton.isSelected()) {
            rating = 2;
        } else if (ratingThreeBookRadioButton.isSelected()) {
            rating = 3;
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setLanguage(language);
        book.setIsbn(isbn);
        book.setEan(ean);
        book.setGenre(genre);
        book.setReleaseYear(releaseYear);
        book.setLocation(location);
        book.setIsLent(lent);
        book.setLendTo(lendTo);
        book.setLentDay(lendDay);
        book.setLentMonth(lendMonth);
        book.setLentYear(lentYear);
        book.setLentUntilDay(lendUntilDay);
        book.setLentUntilMonth(lendUntilMonth);
        book.setLentUntilYear(lentUntilYear);
        book.setRating(rating);
        book.setDescription(description);
        book.setComment(annotation);
        book.updateObservers();
    }

    public void setMusicItemInformation(Music music) throws IllegalYearException {
        String title = titleMusicTextField.getText().trim();
        String artist = artistMusicTextField.getText().trim();
        String type = getSelected(typeMusicComboBox);
        String format = getSelected(formatMusicComboBox);
        String ean = eanMusicTextField.getText().trim();    //TODO Ueberpruefen!?!?
        String genre = genreMusicTextField.getText().trim();
        int releaseYear = getYear(releaseYearMusicComboBox);
        String location = locationMusicTextField.getText().trim();
        boolean lent = lentMusicCheckBox.isSelected();
        String lendTo = lentToMusicTextField.getText().trim();
        int lendDay = lentDayMusicComboBox.getSelectedIndex();
        int lendMonth = lentMonthMusicComboBox.getSelectedIndex();
        int lentYear = getYear(lentYearMusicComboBox);
        int lendUntilDay = lentUntilDayMusicComboBox.getSelectedIndex();
        int lendUntilMonth = lentUntilMonthMusicComboBox.getSelectedIndex();
        int lentUntilYear = getYear(lentUntilYearMusicComboBox);
        String description = descriptionMusicTextArea.getText().trim();
        String annotation = annotationMusicTextArea.getText().trim();

        //Rating
        int rating = 0;
        if (ratingOneMusicRadioButton.isSelected()) {
            rating = 1;
        } else if (ratingOneMusicRadioButton.isSelected()) {
            rating = 2;
        } else if (ratingOneMusicRadioButton.isSelected()) {
            rating = 3;
        }

        music.setTitle(title);
        music.setInterpreter(artist);
        music.setType(type);
        music.setFormat(format);
        music.setEan(ean);
        music.setGenre(genre);
        music.setReleaseYear(releaseYear);
        music.setLocation(location);
        music.setIsLent(lent);
        music.setLendTo(lendTo);
        music.setLentDay(lendDay);
        music.setLentMonth(lendMonth);
        music.setLentYear(lentYear);
        music.setLentUntilDay(lendUntilDay);
        music.setLentUntilMonth(lendUntilMonth);
        music.setLentUntilYear(lentUntilYear);
        music.setRating(rating);
        music.setDescription(description);
        music.setComment(annotation);
        music.updateObservers();

    }

    public void setVideoItemInformation(Video video) throws IllegalYearException {
        String title = titleVideoTextField.getText().trim();
        String director = directorVideoTextField.getText().trim();
        String actors = actorsVideoTextField.getText().trim();
        String format = getSelected(formatVideoComboBox);
        String ean = eanVideoTextField.getText().trim();    //TODO Ueberpruefen!?!?
        String genre = genreVideoTextField.getText().trim();
        int releaseYear = getYear(releaseYearVideoComboBox);
        String location = locationVideoTextField.getText().trim();
        boolean lent = lentVideoCheckBox.isSelected();
        String lendTo = lentToVideoTextField.getText().trim();
        int lendDay = lentDayVideoComboBox.getSelectedIndex();
        int lendMonth = lentMonthVideoComboBox.getSelectedIndex();
        int lentYear = getYear(lentYearVideoComboBox);
        int lendUntilDay = lentUntilDayVideoComboBox.getSelectedIndex();
        int lendUntilMonth = lentUntilMonthVideoComboBox.getSelectedIndex();
        int lentUntilYear = getYear(lentUntilYearVideoComboBox);
        String description = descriptionVideoTextArea.getText().trim();
        String annotation = annotationVideoTextArea.getText().trim();

        //Rating
        int rating = 0;
        if (ratingOneVideoRadioButton.isSelected()) {
            rating = 1;
        } else if (ratingOneVideoRadioButton.isSelected()) {
            rating = 2;
        } else if (ratingOneVideoRadioButton.isSelected()) {
            rating = 3;
        }


        video.setTitle(title);
        video.setDirector(director);
        video.setActors(actors);
        video.setFormat(format);
        video.setEan(ean);
        video.setGenre(genre);
        video.setReleaseYear(releaseYear);
        video.setLocation(location);
        video.setIsLent(lent);
        video.setLendTo(lendTo);
        video.setLentDay(lendDay);
        video.setLentMonth(lendMonth);
        video.setLentYear(lentYear);
        video.setLentUntilDay(lendUntilDay);
        video.setLentUntilMonth(lendUntilMonth);
        video.setLentUntilYear(lentUntilYear);
        video.setRating(rating);
        video.setDescription(description);
        video.setComment(annotation);
        video.updateObservers();
    }

    public void setBookLentFieldsEnabled(boolean b){
        lentToBookLabel.setEnabled(b);
        lentToBookTextField.setEditable(b);
        lentDateBookLabel.setEnabled(b);
        lentDayBookComboBox.setEnabled(b);
        lentMonthBookComboBox.setEnabled(b);
        lentYearBookComboBox.setEnabled(b);
        lentUntilDateBookLabel.setEnabled(b);
        lentUntilDayBookComboBox.setEnabled(b);
        lentUntilMonthBookComboBox.setEnabled(b);
        lentUntilYearBookComboBox.setEnabled(b);
    }

    public void setMusicLentFieldsEnabled(boolean b) {
        lentToMusicLabel.setEnabled(b);
        lentToMusicTextField.setEditable(b);
        lentDateMusicLabel.setEnabled(b);
        lentDayMusicComboBox.setEnabled(b);
        lentMonthMusicComboBox.setEnabled(b);
        lentYearMusicComboBox.setEnabled(b);
        lentUntilDateMusicLabel.setEnabled(b);
        lentUntilDayMusicComboBox.setEnabled(b);
        lentUntilMonthMusicComboBox.setEnabled(b);
        lentUntilYearMusicComboBox.setEnabled(b);
    }

    public void setVideoLentFieldsEnabled(boolean b) {
        lentToVideoLabel.setEnabled(b);
        lentToVideoTextField.setEditable(b);
        lentDateVideoLabel.setEnabled(b);
        lentDayVideoComboBox.setEnabled(b);
        lentMonthVideoComboBox.setEnabled(b);
        lentYearVideoComboBox.setEnabled(b);
        lentUntilDateVideoLabel.setEnabled(b);
        lentUntilDayVideoComboBox.setEnabled(b);
        lentUntilMonthVideoComboBox.setEnabled(b);
        lentUntilYearVideoComboBox.setEnabled(b);
    }
    
    public void bookItemSetVisible(boolean b) {
        itemBookScrollPane.setVisible(b);
        hideBookButton.setVisible(b);
        itemBookScrollPane.getParent().validate();
    }

    public void musicItemSetVisible(boolean b) {
        itemMusicScrollPane.setVisible(b);
        hideMusicButton.setVisible(b);
        itemMusicScrollPane.getParent().validate();
    }


   public void videoItemSetVisible(boolean b) {
        itemVideoScrollPane.setVisible(b);
        hideVideoButton.setVisible(b);
        itemVideoScrollPane.getParent().validate();
    }

    public String getSearchString() {
        return searchTextField.getText().trim();
    }

    public void resetSearch() {
        searchTextField.setText("");
    }

    public void updateComboBoxLabel(JComboBox cb, String label){
        int selectedIndex = cb.getSelectedIndex();
        cb.removeItemAt(0);
        cb.insertItemAt(label, 0);
        cb.setSelectedIndex(selectedIndex);
    }

    public void updateComboBoxLabels(JComboBox cb, String[] months){
        int selectedIndex = cb.getSelectedIndex();
        cb.removeAllItems();
        for(int i=0; i<months.length; i++)
            cb.addItem(months[i]);
        cb.setSelectedIndex(selectedIndex);
    }

    public JButton getDeleteSearchButton() {
        return deleteSearchButton;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookRatingButtonGroup = new javax.swing.ButtonGroup();
        musicRatingButtonGroup = new javax.swing.ButtonGroup();
        videoRatingButtonGroup = new javax.swing.ButtonGroup();
        headPanel = new javax.swing.JPanel();
        aboutButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        languagesComboBox = new javax.swing.JComboBox();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        advancedSearchButton = new javax.swing.JButton();
        deleteSearchButton = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        overviewPanel = new javax.swing.JPanel();
        overviewScrollPane = new javax.swing.JScrollPane();
        overviewTable =        new javax.swing.JTable(){
            //prepareRenderer überschreiben:
            @Override public Component prepareRenderer(final TableCellRenderer renderer,
                final int row, final int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if(c instanceof javax.swing.JLabel){
                    javax.swing.JLabel label = (javax.swing.JLabel)c;
                    label.setHorizontalAlignment(javax.swing.JLabel.LEFT);
                    label.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                }
                return c;
            }
        };
        expiredPanel = new javax.swing.JPanel();
        expiredScrollPane = new javax.swing.JScrollPane();
        bookPanel = new javax.swing.JPanel();
        viewBookPanel = new javax.swing.JPanel();
        viewBookComboBox = new javax.swing.JComboBox();
        addBookButton = new javax.swing.JButton();
        bookCardPanel = new javax.swing.JPanel();
        sortPanelBooks = new javax.swing.JPanel();
        sortBookTitleButton = new javax.swing.JToggleButton();
        sortBookGenreButton = new javax.swing.JToggleButton();
        sortBookRatingButton = new javax.swing.JToggleButton();
        sortBookAuthorButton = new javax.swing.JToggleButton();
        sortBookLanguageButton = new javax.swing.JToggleButton();
        itemBookScrollPane = new javax.swing.JScrollPane();
        itemBookPanel = new javax.swing.JPanel();
        coverBookLabel = new javax.swing.JLabel();
        titleBookLabel = new javax.swing.JLabel();
        authorBookLabel = new javax.swing.JLabel();
        languageBookLabel = new javax.swing.JLabel();
        isbnBookLabel = new javax.swing.JLabel();
        eanBookLabel = new javax.swing.JLabel();
        genreBookLabel = new javax.swing.JLabel();
        releaseYearBookLabel = new javax.swing.JLabel();
        locationBookLabel = new javax.swing.JLabel();
        lentBookLabel = new javax.swing.JLabel();
        lentToBookLabel = new javax.swing.JLabel();
        lentDateBookLabel = new javax.swing.JLabel();
        lentUntilDateBookLabel = new javax.swing.JLabel();
        ratingBookLabel = new javax.swing.JLabel();
        descriptionBookLabel = new javax.swing.JLabel();
        annotationBookLabel = new javax.swing.JLabel();
        titleBookTextField = new javax.swing.JTextField();
        authorBookTextField = new javax.swing.JTextField();
        languageBookTextField = new javax.swing.JTextField();
        isbnBookTextField = new javax.swing.JTextField();
        eanBookTextField = new javax.swing.JTextField();
        genreBookTextField = new javax.swing.JTextField();
        releaseYearBookComboBox = new javax.swing.JComboBox();
        locationBookTextField = new javax.swing.JTextField();
        lentBookCheckBox = new javax.swing.JCheckBox();
        lentToBookTextField = new javax.swing.JTextField();
        lentDayBookComboBox = new javax.swing.JComboBox();
        lentMonthBookComboBox = new javax.swing.JComboBox();
        lentYearBookComboBox = new javax.swing.JComboBox();
        lentUntilDayBookComboBox = new javax.swing.JComboBox();
        lentUntilMonthBookComboBox = new javax.swing.JComboBox();
        lentUntilYearBookComboBox = new javax.swing.JComboBox();
        ratingOneBookRadioButton = new javax.swing.JRadioButton();
        ratingTwoBookRadioButton = new javax.swing.JRadioButton();
        ratingThreeBookRadioButton = new javax.swing.JRadioButton();
        ratingNoneBookRadioButton = new javax.swing.JRadioButton();
        deleteBookButton = new javax.swing.JButton();
        saveBookButton = new javax.swing.JButton();
        descriptionBookScrollPane = new javax.swing.JScrollPane();
        descriptionBookTextArea = new javax.swing.JTextArea();
        annotationBookScrollPane = new javax.swing.JScrollPane();
        annotationBookTextArea = new javax.swing.JTextArea();
        hideBookButton = new javax.swing.JButton();
        musicPanel = new javax.swing.JPanel();
        viewMusicPanel = new javax.swing.JPanel();
        viewMusicComboBox = new javax.swing.JComboBox();
        addMusicButton = new javax.swing.JButton();
        musicCardPanel = new javax.swing.JPanel();
        sortPanelMusic = new javax.swing.JPanel();
        sortMusicTitleButton = new javax.swing.JToggleButton();
        sortMusicGenreButton = new javax.swing.JToggleButton();
        sortMusicRatingButton = new javax.swing.JToggleButton();
        sortMusicInterpeterButton = new javax.swing.JToggleButton();
        sortMusicTypeButton = new javax.swing.JToggleButton();
        itemMusicScrollPane = new javax.swing.JScrollPane();
        itemMusicPanel = new javax.swing.JPanel();
        coverMusicLabel = new javax.swing.JLabel();
        titleMusicLabel = new javax.swing.JLabel();
        interpretMusicLabel = new javax.swing.JLabel();
        typeMusicLabel = new javax.swing.JLabel();
        formatMusicLabel = new javax.swing.JLabel();
        eanMusicLabel = new javax.swing.JLabel();
        genreMusicLabel = new javax.swing.JLabel();
        releaseYearMusicLabel = new javax.swing.JLabel();
        locationMusicLabel = new javax.swing.JLabel();
        lentMusicLabel = new javax.swing.JLabel();
        lentToMusicLabel = new javax.swing.JLabel();
        lentDateMusicLabel = new javax.swing.JLabel();
        lentUntilDateMusicLabel = new javax.swing.JLabel();
        ratingMusicLabel = new javax.swing.JLabel();
        descriptionMusicLabel = new javax.swing.JLabel();
        annotationMusicLabel = new javax.swing.JLabel();
        titleMusicTextField = new javax.swing.JTextField();
        artistMusicTextField = new javax.swing.JTextField();
        typeMusicComboBox = new javax.swing.JComboBox();
        formatMusicComboBox = new javax.swing.JComboBox();
        eanMusicTextField = new javax.swing.JTextField();
        genreMusicTextField = new javax.swing.JTextField();
        releaseYearMusicComboBox = new javax.swing.JComboBox();
        locationMusicTextField = new javax.swing.JTextField();
        lentMusicCheckBox = new javax.swing.JCheckBox();
        lentToMusicTextField = new javax.swing.JTextField();
        lentDayMusicComboBox = new javax.swing.JComboBox();
        lentMonthMusicComboBox = new javax.swing.JComboBox();
        lentYearMusicComboBox = new javax.swing.JComboBox();
        lentUntilDayMusicComboBox = new javax.swing.JComboBox();
        lentUntilMonthMusicComboBox = new javax.swing.JComboBox();
        lentUntilYearMusicComboBox = new javax.swing.JComboBox();
        ratingOneMusicRadioButton = new javax.swing.JRadioButton();
        ratingTwoMusicRadioButton = new javax.swing.JRadioButton();
        ratingThreeMusicRadioButton = new javax.swing.JRadioButton();
        ratingNoneMusicRadioButton = new javax.swing.JRadioButton();
        deleteMusicButton = new javax.swing.JButton();
        saveMusicButton = new javax.swing.JButton();
        descriptionMusicScrollPane = new javax.swing.JScrollPane();
        descriptionMusicTextArea = new javax.swing.JTextArea();
        annotationMusicScrollPane = new javax.swing.JScrollPane();
        annotationMusicTextArea = new javax.swing.JTextArea();
        hideMusicButton = new javax.swing.JButton();
        videoPanel = new javax.swing.JPanel();
        viewVideoPanel = new javax.swing.JPanel();
        viewVideoComboBox = new javax.swing.JComboBox();
        addVideoButton = new javax.swing.JButton();
        videoCardPanel = new javax.swing.JPanel();
        sortPanelVideo = new javax.swing.JPanel();
        sortVideoTitleButton = new javax.swing.JToggleButton();
        sortVideoGenreButton = new javax.swing.JToggleButton();
        sortVideoRatingButton = new javax.swing.JToggleButton();
        sortVideoRegisseurButton = new javax.swing.JToggleButton();
        sortVideoActorsButton = new javax.swing.JToggleButton();
        itemVideoScrollPane = new javax.swing.JScrollPane();
        itemVideoPanel = new javax.swing.JPanel();
        coverVideoLabel = new javax.swing.JLabel();
        titleVideoLabel = new javax.swing.JLabel();
        directorVideoLabel = new javax.swing.JLabel();
        actorsVideoLabel = new javax.swing.JLabel();
        formatVideoLabel = new javax.swing.JLabel();
        eanVideoLabel = new javax.swing.JLabel();
        genreVideoLabel = new javax.swing.JLabel();
        releaseYearVideoLabel = new javax.swing.JLabel();
        locationVideoLabel = new javax.swing.JLabel();
        lentVideoLabel = new javax.swing.JLabel();
        lentToVideoLabel = new javax.swing.JLabel();
        lentDateVideoLabel = new javax.swing.JLabel();
        lentUntilDateVideoLabel = new javax.swing.JLabel();
        ratingVideoLabel = new javax.swing.JLabel();
        descriptionVideoLabel = new javax.swing.JLabel();
        annotationVideoLabel = new javax.swing.JLabel();
        titleVideoTextField = new javax.swing.JTextField();
        directorVideoTextField = new javax.swing.JTextField();
        actorsVideoTextField = new javax.swing.JTextField();
        eanVideoTextField = new javax.swing.JTextField();
        genreVideoTextField = new javax.swing.JTextField();
        releaseYearVideoComboBox = new javax.swing.JComboBox();
        locationVideoTextField = new javax.swing.JTextField();
        lentVideoCheckBox = new javax.swing.JCheckBox();
        lentToVideoTextField = new javax.swing.JTextField();
        lentDayVideoComboBox = new javax.swing.JComboBox();
        lentMonthVideoComboBox = new javax.swing.JComboBox();
        lentYearVideoComboBox = new javax.swing.JComboBox();
        lentUntilDayVideoComboBox = new javax.swing.JComboBox();
        lentUntilMonthVideoComboBox = new javax.swing.JComboBox();
        lentUntilYearVideoComboBox = new javax.swing.JComboBox();
        ratingOneVideoRadioButton = new javax.swing.JRadioButton();
        ratingTwoVideoRadioButton = new javax.swing.JRadioButton();
        ratingThreeVideoRadioButton = new javax.swing.JRadioButton();
        ratingNoneVideoRadioButton = new javax.swing.JRadioButton();
        deleteVideoButton = new javax.swing.JButton();
        saveVideoButton = new javax.swing.JButton();
        descriptionVideoScrollPane = new javax.swing.JScrollPane();
        descriptionVideoTextArea = new javax.swing.JTextArea();
        annotationVideoScrollPane = new javax.swing.JScrollPane();
        annotationVideoTextArea = new javax.swing.JTextArea();
        formatVideoComboBox = new javax.swing.JComboBox();
        hideVideoButton = new javax.swing.JButton();
        restorePanel = new javax.swing.JPanel();
        restoreItemButton = new javax.swing.JButton();
        finalDeleteItemButton = new javax.swing.JButton();
        restoreItemsScrollpane = new javax.swing.JScrollPane();
        restoreEverythingButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        finalDeleteEverythingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MuViBee");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        aboutButton.setText("Über");

        helpButton.setText("Hilfe");

        languagesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        languagesComboBox.setName("LangaugesComboBox"); // NOI18N

        searchButton.setText("Suchen");
        searchButton.setName("searchButton"); // NOI18N

        advancedSearchButton.setText("Erweiterte Suche");
        advancedSearchButton.setName("advancedSearchButton"); // NOI18N

        deleteSearchButton.setText("X");

        javax.swing.GroupLayout headPanelLayout = new javax.swing.GroupLayout(headPanel);
        headPanel.setLayout(headPanelLayout);
        headPanelLayout.setHorizontalGroup(
            headPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(advancedSearchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 485, Short.MAX_VALUE)
                .addComponent(languagesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutButton)
                .addContainerGap())
        );
        headPanelLayout.setVerticalGroup(
            headPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aboutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(languagesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(advancedSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        statusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1121, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        tabbedPane.setName("add video button"); // NOI18N

        overviewTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        overviewTable.setFont(new java.awt.Font("Plantagenet Cherokee", 0, 24));
        overviewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Bücher", "", null, null},
                {"Musik", null, null, null},
                {"Videos", null, null, null}
            },
            new String [] {
                "Medium", "Anzahl", "Ausgeliehen", "Gelöscht"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        overviewTable.setCellSelectionEnabled(true);
        overviewTable.setRowHeight(64);
        overviewTable.setSelectionBackground(new java.awt.Color(235, 232, 238));
        overviewTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        overviewScrollPane.setViewportView(overviewTable);

        expiredPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Überfällige Medien"));

        javax.swing.GroupLayout expiredPanelLayout = new javax.swing.GroupLayout(expiredPanel);
        expiredPanel.setLayout(expiredPanelLayout);
        expiredPanelLayout.setHorizontalGroup(
            expiredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expiredPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(expiredScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                .addContainerGap())
        );
        expiredPanelLayout.setVerticalGroup(
            expiredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expiredPanelLayout.createSequentialGroup()
                .addComponent(expiredScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout overviewPanelLayout = new javax.swing.GroupLayout(overviewPanel);
        overviewPanel.setLayout(overviewPanelLayout);
        overviewPanelLayout.setHorizontalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(expiredPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(overviewScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE))
                .addContainerGap())
        );
        overviewPanelLayout.setVerticalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overviewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expiredPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Übersicht", overviewPanel);

        viewBookPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        viewBookPanel.setMinimumSize(new java.awt.Dimension(350, 0));
        viewBookPanel.setPreferredSize(new java.awt.Dimension(311, 414));

        viewBookComboBox.setName("ViewComboBox"); // NOI18N

        addBookButton.setText("Hinzufügen");
        addBookButton.setName("add book button"); // NOI18N
        addBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookButtonActionPerformed(evt);
            }
        });

        bookCardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookCardPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        bookCardPanel.setPreferredSize(new java.awt.Dimension(218, 361));
        bookCardPanel.setLayout(new java.awt.CardLayout());

        sortPanelBooks.setBorder(javax.swing.BorderFactory.createTitledBorder("Sortieren nach"));

        sortBookTitleButton.setText("Titel");
        sortBookTitleButton.setName("sort book title"); // NOI18N

        sortBookGenreButton.setText("Genre");
        sortBookGenreButton.setName("sort book genre"); // NOI18N

        sortBookRatingButton.setText("Bewertung");
        sortBookRatingButton.setName("sort book rating"); // NOI18N

        sortBookAuthorButton.setText("Autor");
        sortBookAuthorButton.setName("sort book author"); // NOI18N

        sortBookLanguageButton.setText("Sprache");
        sortBookLanguageButton.setName("sort book language"); // NOI18N

        javax.swing.GroupLayout sortPanelBooksLayout = new javax.swing.GroupLayout(sortPanelBooks);
        sortPanelBooks.setLayout(sortPanelBooksLayout);
        sortPanelBooksLayout.setHorizontalGroup(
            sortPanelBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sortPanelBooksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortBookTitleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortBookGenreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortBookRatingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortBookAuthorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortBookLanguageButton)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        sortPanelBooksLayout.setVerticalGroup(
            sortPanelBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sortPanelBooksLayout.createSequentialGroup()
                .addGroup(sortPanelBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortBookTitleButton)
                    .addComponent(sortBookGenreButton)
                    .addComponent(sortBookRatingButton)
                    .addComponent(sortBookAuthorButton)
                    .addComponent(sortBookLanguageButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewBookPanelLayout = new javax.swing.GroupLayout(viewBookPanel);
        viewBookPanel.setLayout(viewBookPanelLayout);
        viewBookPanelLayout.setHorizontalGroup(
            viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sortPanelBooks, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addGroup(viewBookPanelLayout.createSequentialGroup()
                        .addComponent(viewBookComboBox, 0, 404, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBookButton)))
                .addContainerGap())
        );
        viewBookPanelLayout.setVerticalGroup(
            viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortPanelBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addBookButton)
                    .addComponent(viewBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemBookPanel.setPreferredSize(new java.awt.Dimension(500, 880));

        coverBookLabel.setText("Cover");
        coverBookLabel.setPreferredSize(new java.awt.Dimension(120, 150));

        titleBookLabel.setText("Titel");

        authorBookLabel.setText("Autor");

        languageBookLabel.setText("Sprache");

        isbnBookLabel.setText("ISBN");

        eanBookLabel.setText("EAN");

        genreBookLabel.setText("Genre");

        releaseYearBookLabel.setText("Erscheinungsjahr");

        locationBookLabel.setText("Standort");

        lentBookLabel.setText("Verliehen");

        lentToBookLabel.setText("Verliehen an");

        lentDateBookLabel.setText("Verliehen am");

        lentUntilDateBookLabel.setText("Verliehen bis");

        ratingBookLabel.setText("Bewertung");

        descriptionBookLabel.setText("Beschreibung");

        annotationBookLabel.setText("Kommentar");

        releaseYearBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentBookCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lentBookCheckBoxActionPerformed(evt);
            }
        });

        lentDayBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentMonthBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentYearBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentUntilDayBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentUntilMonthBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentUntilYearBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        bookRatingButtonGroup.add(ratingOneBookRadioButton);
        ratingOneBookRadioButton.setText("1");

        bookRatingButtonGroup.add(ratingTwoBookRadioButton);
        ratingTwoBookRadioButton.setText("2");

        bookRatingButtonGroup.add(ratingThreeBookRadioButton);
        ratingThreeBookRadioButton.setText("3");

        bookRatingButtonGroup.add(ratingNoneBookRadioButton);
        ratingNoneBookRadioButton.setText("keine");

        deleteBookButton.setText("Löschen");
        deleteBookButton.setName("delete books"); // NOI18N

        saveBookButton.setText("Speichern");
        saveBookButton.setName("save book button"); // NOI18N

        descriptionBookTextArea.setColumns(20);
        descriptionBookTextArea.setLineWrap(true);
        descriptionBookTextArea.setRows(5);
        descriptionBookScrollPane.setViewportView(descriptionBookTextArea);

        annotationBookTextArea.setColumns(20);
        annotationBookTextArea.setLineWrap(true);
        annotationBookTextArea.setRows(5);
        annotationBookScrollPane.setViewportView(annotationBookTextArea);

        javax.swing.GroupLayout itemBookPanelLayout = new javax.swing.GroupLayout(itemBookPanel);
        itemBookPanel.setLayout(itemBookPanelLayout);
        itemBookPanelLayout.setHorizontalGroup(
            itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(itemBookPanelLayout.createSequentialGroup()
                                .addComponent(titleBookLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(titleBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                            .addGroup(itemBookPanelLayout.createSequentialGroup()
                                .addComponent(coverBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(languageBookLabel)
                                    .addComponent(releaseYearBookLabel)
                                    .addComponent(locationBookLabel)
                                    .addComponent(authorBookLabel)
                                    .addComponent(isbnBookLabel)
                                    .addComponent(eanBookLabel)
                                    .addComponent(genreBookLabel)
                                    .addComponent(ratingBookLabel)
                                    .addComponent(lentBookLabel)
                                    .addComponent(lentToBookLabel)
                                    .addComponent(lentDateBookLabel)
                                    .addComponent(lentUntilDateBookLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                                        .addComponent(lentUntilDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilMonthBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilYearBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(itemBookPanelLayout.createSequentialGroup()
                                            .addComponent(lentDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lentMonthBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lentYearBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lentBookCheckBox, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, itemBookPanelLayout.createSequentialGroup()
                                                .addComponent(ratingOneBookRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingTwoBookRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingThreeBookRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingNoneBookRadioButton))
                                            .addComponent(locationBookTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                            .addComponent(releaseYearBookComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eanBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                            .addComponent(authorBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                            .addComponent(isbnBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                            .addComponent(languageBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                            .addComponent(genreBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                            .addComponent(lentToBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanelLayout.createSequentialGroup()
                        .addComponent(saveBookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBookButton)
                        .addContainerGap())
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(descriptionBookLabel)
                        .addContainerGap(455, Short.MAX_VALUE))
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(descriptionBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(annotationBookLabel)
                        .addContainerGap(465, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanelLayout.createSequentialGroup()
                        .addComponent(annotationBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        itemBookPanelLayout.setVerticalGroup(
            itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleBookLabel)
                    .addComponent(titleBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(authorBookLabel)
                            .addComponent(authorBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(languageBookLabel)
                            .addComponent(languageBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isbnBookLabel)
                            .addComponent(isbnBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eanBookLabel)
                            .addComponent(eanBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreBookLabel)
                            .addComponent(genreBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(releaseYearBookLabel)
                            .addComponent(releaseYearBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(locationBookLabel)
                            .addComponent(locationBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ratingBookLabel)
                    .addComponent(ratingOneBookRadioButton)
                    .addComponent(ratingTwoBookRadioButton)
                    .addComponent(ratingThreeBookRadioButton)
                    .addComponent(ratingNoneBookRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lentBookLabel)
                    .addComponent(lentBookCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentToBookLabel)
                    .addComponent(lentToBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentDateBookLabel)
                    .addComponent(lentDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentMonthBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentYearBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lentUntilDateBookLabel)
                    .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lentUntilDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilMonthBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilYearBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBookButton)
                    .addComponent(saveBookButton))
                .addGap(11, 11, 11)
                .addComponent(descriptionBookLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionBookScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(annotationBookLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annotationBookScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        itemBookScrollPane.setViewportView(itemBookPanel);

        hideBookButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        hideBookButton.setText(">");
        hideBookButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        hideBookButton.setName("hide book"); // NOI18N

        javax.swing.GroupLayout bookPanelLayout = new javax.swing.GroupLayout(bookPanel);
        bookPanel.setLayout(bookPanelLayout);
        bookPanelLayout.setHorizontalGroup(
            bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookPanelLayout.createSequentialGroup()
                .addComponent(viewBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemBookScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bookPanelLayout.setVerticalGroup(
            bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viewBookPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(hideBookButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(itemBookScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Bücher", bookPanel);

        viewMusicPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        viewMusicPanel.setMinimumSize(new java.awt.Dimension(350, 0));

        viewMusicComboBox.setName("ViewComboBox"); // NOI18N

        addMusicButton.setText("Hinzufügen");
        addMusicButton.setName("add music button"); // NOI18N

        musicCardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        musicCardPanel.setLayout(new java.awt.CardLayout());

        sortPanelMusic.setBorder(javax.swing.BorderFactory.createTitledBorder("Sortieren nach"));

        sortMusicTitleButton.setText("Titel");
        sortMusicTitleButton.setName("sort music title"); // NOI18N

        sortMusicGenreButton.setText("Genre");
        sortMusicGenreButton.setName("sort music genre"); // NOI18N

        sortMusicRatingButton.setText("Rating");
        sortMusicRatingButton.setName("sort music rating"); // NOI18N

        sortMusicInterpeterButton.setText("Interpreter");
        sortMusicInterpeterButton.setName("sort music interpreter"); // NOI18N

        sortMusicTypeButton.setText("Typ");
        sortMusicTypeButton.setName("sort music type"); // NOI18N

        javax.swing.GroupLayout sortPanelMusicLayout = new javax.swing.GroupLayout(sortPanelMusic);
        sortPanelMusic.setLayout(sortPanelMusicLayout);
        sortPanelMusicLayout.setHorizontalGroup(
            sortPanelMusicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sortPanelMusicLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortMusicTitleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortMusicGenreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortMusicRatingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortMusicInterpeterButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortMusicTypeButton)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        sortPanelMusicLayout.setVerticalGroup(
            sortPanelMusicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sortPanelMusicLayout.createSequentialGroup()
                .addGroup(sortPanelMusicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortMusicGenreButton)
                    .addComponent(sortMusicRatingButton)
                    .addComponent(sortMusicInterpeterButton)
                    .addComponent(sortMusicTypeButton)
                    .addComponent(sortMusicTitleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewMusicPanelLayout = new javax.swing.GroupLayout(viewMusicPanel);
        viewMusicPanel.setLayout(viewMusicPanelLayout);
        viewMusicPanelLayout.setHorizontalGroup(
            viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(musicCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addGroup(viewMusicPanelLayout.createSequentialGroup()
                        .addComponent(viewMusicComboBox, 0, 404, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMusicButton))
                    .addComponent(sortPanelMusic, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        viewMusicPanelLayout.setVerticalGroup(
            viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortPanelMusic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(musicCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addMusicButton)
                    .addComponent(viewMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemMusicPanel.setPreferredSize(new java.awt.Dimension(500, 880));

        coverMusicLabel.setText("Cover");
        coverMusicLabel.setPreferredSize(new java.awt.Dimension(120, 150));

        titleMusicLabel.setText("Titel");

        interpretMusicLabel.setText("Interpret");

        typeMusicLabel.setText("Typ");

        formatMusicLabel.setText("Format");

        eanMusicLabel.setText("EAN");

        genreMusicLabel.setText("Genre");

        releaseYearMusicLabel.setText("Erscheinungsjahr");

        locationMusicLabel.setText("Standort");

        lentMusicLabel.setText("Verliehen");

        lentToMusicLabel.setText("Verliehen an");

        lentDateMusicLabel.setText("Verliehen am");

        lentUntilDateMusicLabel.setText("Verliehen bis");

        ratingMusicLabel.setText("Bewertung");

        descriptionMusicLabel.setText("Beschreibung");

        annotationMusicLabel.setText("Kommentar");

        releaseYearMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentMusicCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lentMusicCheckBoxActionPerformed(evt);
            }
        });

        lentDayMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentMonthMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentYearMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentUntilDayMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentUntilMonthMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentUntilYearMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        musicRatingButtonGroup.add(ratingOneMusicRadioButton);
        ratingOneMusicRadioButton.setText("1");

        musicRatingButtonGroup.add(ratingTwoMusicRadioButton);
        ratingTwoMusicRadioButton.setText("2");

        musicRatingButtonGroup.add(ratingThreeMusicRadioButton);
        ratingThreeMusicRadioButton.setText("3");

        musicRatingButtonGroup.add(ratingNoneMusicRadioButton);
        ratingNoneMusicRadioButton.setText("keine");

        deleteMusicButton.setText("Löschen");
        deleteMusicButton.setName("delete music"); // NOI18N

        saveMusicButton.setText("Speichern");
        saveMusicButton.setName("save music button"); // NOI18N

        descriptionMusicTextArea.setColumns(20);
        descriptionMusicTextArea.setLineWrap(true);
        descriptionMusicTextArea.setRows(5);
        descriptionMusicScrollPane.setViewportView(descriptionMusicTextArea);

        annotationMusicTextArea.setColumns(20);
        annotationMusicTextArea.setLineWrap(true);
        annotationMusicTextArea.setRows(5);
        annotationMusicScrollPane.setViewportView(annotationMusicTextArea);

        javax.swing.GroupLayout itemMusicPanelLayout = new javax.swing.GroupLayout(itemMusicPanel);
        itemMusicPanel.setLayout(itemMusicPanelLayout);
        itemMusicPanelLayout.setHorizontalGroup(
            itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                .addComponent(titleMusicLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(titleMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                            .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                .addComponent(coverMusicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeMusicLabel)
                                    .addComponent(releaseYearMusicLabel)
                                    .addComponent(locationMusicLabel)
                                    .addComponent(interpretMusicLabel)
                                    .addComponent(formatMusicLabel)
                                    .addComponent(eanMusicLabel)
                                    .addComponent(genreMusicLabel)
                                    .addComponent(ratingMusicLabel)
                                    .addComponent(lentMusicLabel)
                                    .addComponent(lentToMusicLabel)
                                    .addComponent(lentDateMusicLabel)
                                    .addComponent(lentUntilDateMusicLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                        .addComponent(lentUntilDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilMonthMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilYearMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                        .addComponent(lentDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentMonthMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentYearMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lentMusicCheckBox)
                                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                        .addComponent(ratingOneMusicRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ratingTwoMusicRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ratingThreeMusicRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ratingNoneMusicRadioButton))
                                    .addComponent(locationMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(releaseYearMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eanMusicTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(artistMusicTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(genreMusicTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(lentToMusicTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(typeMusicComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 291, Short.MAX_VALUE)
                                    .addComponent(formatMusicComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 291, Short.MAX_VALUE))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(saveMusicButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMusicButton)
                        .addContainerGap())
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(descriptionMusicLabel)
                        .addContainerGap(455, Short.MAX_VALUE))
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(descriptionMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(annotationMusicLabel)
                        .addContainerGap(465, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(annotationMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        itemMusicPanelLayout.setVerticalGroup(
            itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleMusicLabel)
                    .addComponent(titleMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverMusicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(interpretMusicLabel)
                            .addComponent(artistMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(typeMusicLabel)
                            .addComponent(typeMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formatMusicLabel)
                            .addComponent(formatMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eanMusicLabel)
                            .addComponent(eanMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreMusicLabel)
                            .addComponent(genreMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(releaseYearMusicLabel)
                            .addComponent(releaseYearMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(locationMusicLabel)
                            .addComponent(locationMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ratingMusicLabel)
                    .addComponent(ratingOneMusicRadioButton)
                    .addComponent(ratingTwoMusicRadioButton)
                    .addComponent(ratingThreeMusicRadioButton)
                    .addComponent(ratingNoneMusicRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lentMusicLabel)
                    .addComponent(lentMusicCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentToMusicLabel)
                    .addComponent(lentToMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentDateMusicLabel)
                    .addComponent(lentDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentMonthMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentYearMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lentUntilDateMusicLabel)
                    .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lentUntilDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilMonthMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilYearMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteMusicButton)
                    .addComponent(saveMusicButton))
                .addGap(11, 11, 11)
                .addComponent(descriptionMusicLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionMusicScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(annotationMusicLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annotationMusicScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        itemMusicScrollPane.setViewportView(itemMusicPanel);

        hideMusicButton.setText(">");
        hideMusicButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        hideMusicButton.setName("hide music"); // NOI18N

        javax.swing.GroupLayout musicPanelLayout = new javax.swing.GroupLayout(musicPanel);
        musicPanel.setLayout(musicPanelLayout);
        musicPanelLayout.setHorizontalGroup(
            musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, musicPanelLayout.createSequentialGroup()
                .addComponent(viewMusicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideMusicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemMusicScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        musicPanelLayout.setVerticalGroup(
            musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewMusicPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hideMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(itemMusicScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Musik", musicPanel);

        viewVideoPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        viewVideoPanel.setMinimumSize(new java.awt.Dimension(350, 0));

        addVideoButton.setText("Hinzufügen");
        addVideoButton.setName("add video button"); // NOI18N

        videoCardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        videoCardPanel.setLayout(new java.awt.CardLayout());

        sortPanelVideo.setBorder(javax.swing.BorderFactory.createTitledBorder("Sortieren nach"));

        sortVideoTitleButton.setText("Titel");
        sortVideoTitleButton.setName("sort video title"); // NOI18N

        sortVideoGenreButton.setText("Genre");
        sortVideoGenreButton.setName("sort video genre"); // NOI18N

        sortVideoRatingButton.setText("Rating");
        sortVideoRatingButton.setName("sort video rating"); // NOI18N

        sortVideoRegisseurButton.setText("Regisseur");
        sortVideoRegisseurButton.setName("sort video regisseur"); // NOI18N

        sortVideoActorsButton.setText("Schauspieler");
        sortVideoActorsButton.setName("sort video actors"); // NOI18N

        javax.swing.GroupLayout sortPanelVideoLayout = new javax.swing.GroupLayout(sortPanelVideo);
        sortPanelVideo.setLayout(sortPanelVideoLayout);
        sortPanelVideoLayout.setHorizontalGroup(
            sortPanelVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sortPanelVideoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortVideoTitleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortVideoGenreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortVideoRatingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortVideoRegisseurButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortVideoActorsButton)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        sortPanelVideoLayout.setVerticalGroup(
            sortPanelVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sortPanelVideoLayout.createSequentialGroup()
                .addGroup(sortPanelVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortVideoTitleButton)
                    .addComponent(sortVideoGenreButton)
                    .addComponent(sortVideoRatingButton)
                    .addComponent(sortVideoRegisseurButton)
                    .addComponent(sortVideoActorsButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewVideoPanelLayout = new javax.swing.GroupLayout(viewVideoPanel);
        viewVideoPanel.setLayout(viewVideoPanelLayout);
        viewVideoPanelLayout.setHorizontalGroup(
            viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(videoCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addComponent(sortPanelVideo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(viewVideoPanelLayout.createSequentialGroup()
                        .addComponent(viewVideoComboBox, 0, 404, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addVideoButton)))
                .addContainerGap())
        );
        viewVideoPanelLayout.setVerticalGroup(
            viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortPanelVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(videoCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addVideoButton)
                    .addComponent(viewVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemVideoPanel.setPreferredSize(new java.awt.Dimension(500, 880));

        coverVideoLabel.setText("Cover");
        coverVideoLabel.setPreferredSize(new java.awt.Dimension(120, 150));

        titleVideoLabel.setText("Titel");

        directorVideoLabel.setText("Regisseur");

        actorsVideoLabel.setText("Schauspieler");

        formatVideoLabel.setText("Format");

        eanVideoLabel.setText("EAN");

        genreVideoLabel.setText("Genre");

        releaseYearVideoLabel.setText("Erscheinungsjahr");

        locationVideoLabel.setText("Standort");

        lentVideoLabel.setText("Verliehen");

        lentToVideoLabel.setText("Verliehen an");

        lentDateVideoLabel.setText("Verliehen am");

        lentUntilDateVideoLabel.setText("Verliehen bis");

        ratingVideoLabel.setText("Bewertung");

        descriptionVideoLabel.setText("Beschreibung");

        annotationVideoLabel.setText("Kommentar");

        releaseYearVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentVideoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lentVideoCheckBoxActionPerformed(evt);
            }
        });

        lentDayVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentMonthVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentYearVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentUntilDayVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentUntilMonthVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentUntilYearVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        videoRatingButtonGroup.add(ratingOneVideoRadioButton);
        ratingOneVideoRadioButton.setText("1");

        videoRatingButtonGroup.add(ratingTwoVideoRadioButton);
        ratingTwoVideoRadioButton.setText("2");

        videoRatingButtonGroup.add(ratingThreeVideoRadioButton);
        ratingThreeVideoRadioButton.setText("3");

        videoRatingButtonGroup.add(ratingNoneVideoRadioButton);
        ratingNoneVideoRadioButton.setText("keine");

        deleteVideoButton.setText("Löschen");
        deleteVideoButton.setName("delete video"); // NOI18N

        saveVideoButton.setText("Speichern");
        saveVideoButton.setName("save video button"); // NOI18N

        descriptionVideoTextArea.setColumns(20);
        descriptionVideoTextArea.setLineWrap(true);
        descriptionVideoTextArea.setRows(5);
        descriptionVideoScrollPane.setViewportView(descriptionVideoTextArea);

        annotationVideoTextArea.setColumns(20);
        annotationVideoTextArea.setLineWrap(true);
        annotationVideoTextArea.setRows(5);
        annotationVideoScrollPane.setViewportView(annotationVideoTextArea);

        javax.swing.GroupLayout itemVideoPanelLayout = new javax.swing.GroupLayout(itemVideoPanel);
        itemVideoPanel.setLayout(itemVideoPanelLayout);
        itemVideoPanelLayout.setHorizontalGroup(
            itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                .addComponent(titleVideoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(titleVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                            .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                .addComponent(coverVideoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(actorsVideoLabel)
                                    .addComponent(releaseYearVideoLabel)
                                    .addComponent(locationVideoLabel)
                                    .addComponent(directorVideoLabel)
                                    .addComponent(formatVideoLabel)
                                    .addComponent(eanVideoLabel)
                                    .addComponent(genreVideoLabel)
                                    .addComponent(ratingVideoLabel)
                                    .addComponent(lentVideoLabel)
                                    .addComponent(lentToVideoLabel)
                                    .addComponent(lentDateVideoLabel)
                                    .addComponent(lentUntilDateVideoLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                        .addComponent(lentUntilDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilMonthVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilYearVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                        .addComponent(lentDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentMonthVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentYearVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lentVideoCheckBox)
                                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                        .addComponent(ratingOneVideoRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ratingTwoVideoRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ratingThreeVideoRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ratingNoneVideoRadioButton))
                                    .addComponent(locationVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(releaseYearVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eanVideoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(directorVideoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(actorsVideoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(genreVideoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(lentToVideoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(formatVideoComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 291, Short.MAX_VALUE))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(saveVideoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteVideoButton)
                        .addContainerGap())
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(descriptionVideoLabel)
                        .addContainerGap(455, Short.MAX_VALUE))
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(descriptionVideoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(annotationVideoLabel)
                        .addContainerGap(465, Short.MAX_VALUE))
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(annotationVideoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        itemVideoPanelLayout.setVerticalGroup(
            itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleVideoLabel)
                    .addComponent(titleVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverVideoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(directorVideoLabel)
                            .addComponent(directorVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(actorsVideoLabel)
                            .addComponent(actorsVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formatVideoLabel)
                            .addComponent(formatVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eanVideoLabel)
                            .addComponent(eanVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreVideoLabel)
                            .addComponent(genreVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(releaseYearVideoLabel)
                            .addComponent(releaseYearVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(locationVideoLabel)
                            .addComponent(locationVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ratingVideoLabel)
                    .addComponent(ratingOneVideoRadioButton)
                    .addComponent(ratingTwoVideoRadioButton)
                    .addComponent(ratingThreeVideoRadioButton)
                    .addComponent(ratingNoneVideoRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lentVideoLabel)
                    .addComponent(lentVideoCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentToVideoLabel)
                    .addComponent(lentToVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentDateVideoLabel)
                    .addComponent(lentDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentMonthVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentYearVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lentUntilDateVideoLabel)
                    .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lentUntilDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilMonthVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilYearVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteVideoButton)
                    .addComponent(saveVideoButton))
                .addGap(11, 11, 11)
                .addComponent(descriptionVideoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionVideoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(annotationVideoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annotationVideoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        itemVideoScrollPane.setViewportView(itemVideoPanel);

        hideVideoButton.setText(">");
        hideVideoButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        hideVideoButton.setName("hide video"); // NOI18N

        javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(videoPanel);
        videoPanel.setLayout(videoPanelLayout);
        videoPanelLayout.setHorizontalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoPanelLayout.createSequentialGroup()
                .addComponent(viewVideoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideVideoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemVideoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viewVideoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hideVideoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(itemVideoScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Videos", videoPanel);

        restoreItemButton.setText("Wiederhestellen");
        restoreItemButton.setName("restore"); // NOI18N

        finalDeleteItemButton.setText("Endgültig löschen");
        finalDeleteItemButton.setName("final delete"); // NOI18N

        restoreEverythingButton.setText("Alles wiederherstellen");
        restoreEverythingButton.setName("restore everything"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        finalDeleteEverythingButton.setText("Alles löschen");
        finalDeleteEverythingButton.setName("final delete everything"); // NOI18N
        finalDeleteEverythingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalDeleteEverythingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout restorePanelLayout = new javax.swing.GroupLayout(restorePanel);
        restorePanel.setLayout(restorePanelLayout);
        restorePanelLayout.setHorizontalGroup(
            restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restorePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restoreItemsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
                    .addGroup(restorePanelLayout.createSequentialGroup()
                        .addComponent(restoreItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(finalDeleteItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(restoreEverythingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(finalDeleteEverythingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        restorePanelLayout.setVerticalGroup(
            restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, restorePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(restoreItemsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(restoreEverythingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalDeleteEverythingButton))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restoreItemButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finalDeleteItemButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tabbedPane.addTab("Wiederherstellen", restorePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBookButtonActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

    private void finalDeleteEverythingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalDeleteEverythingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_finalDeleteEverythingButtonActionPerformed

    private void lentBookCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lentBookCheckBoxActionPerformed
        setBookLentFieldsEnabled(lentBookCheckBox.isSelected());
    }//GEN-LAST:event_lentBookCheckBoxActionPerformed

    private void lentMusicCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lentMusicCheckBoxActionPerformed
        setMusicLentFieldsEnabled(lentMusicCheckBox.isSelected());
    }//GEN-LAST:event_lentMusicCheckBoxActionPerformed

    private void lentVideoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lentVideoCheckBoxActionPerformed
        setVideoLentFieldsEnabled(lentVideoCheckBox.isSelected());
    }//GEN-LAST:event_lentVideoCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JLabel actorsVideoLabel;
    private javax.swing.JTextField actorsVideoTextField;
    private javax.swing.JButton addBookButton;
    private javax.swing.JButton addMusicButton;
    private javax.swing.JButton addVideoButton;
    private javax.swing.JButton advancedSearchButton;
    private javax.swing.JLabel annotationBookLabel;
    private javax.swing.JScrollPane annotationBookScrollPane;
    private javax.swing.JTextArea annotationBookTextArea;
    private javax.swing.JLabel annotationMusicLabel;
    private javax.swing.JScrollPane annotationMusicScrollPane;
    private javax.swing.JTextArea annotationMusicTextArea;
    private javax.swing.JLabel annotationVideoLabel;
    private javax.swing.JScrollPane annotationVideoScrollPane;
    private javax.swing.JTextArea annotationVideoTextArea;
    private javax.swing.JTextField artistMusicTextField;
    private javax.swing.JLabel authorBookLabel;
    private javax.swing.JTextField authorBookTextField;
    private javax.swing.JPanel bookCardPanel;
    private javax.swing.JPanel bookPanel;
    private javax.swing.ButtonGroup bookRatingButtonGroup;
    private javax.swing.JLabel coverBookLabel;
    private javax.swing.JLabel coverMusicLabel;
    private javax.swing.JLabel coverVideoLabel;
    private javax.swing.JButton deleteBookButton;
    private javax.swing.JButton deleteMusicButton;
    private javax.swing.JButton deleteSearchButton;
    private javax.swing.JButton deleteVideoButton;
    private javax.swing.JLabel descriptionBookLabel;
    private javax.swing.JScrollPane descriptionBookScrollPane;
    private javax.swing.JTextArea descriptionBookTextArea;
    private javax.swing.JLabel descriptionMusicLabel;
    private javax.swing.JScrollPane descriptionMusicScrollPane;
    private javax.swing.JTextArea descriptionMusicTextArea;
    private javax.swing.JLabel descriptionVideoLabel;
    private javax.swing.JScrollPane descriptionVideoScrollPane;
    private javax.swing.JTextArea descriptionVideoTextArea;
    private javax.swing.JLabel directorVideoLabel;
    private javax.swing.JTextField directorVideoTextField;
    private javax.swing.JLabel eanBookLabel;
    private javax.swing.JTextField eanBookTextField;
    private javax.swing.JLabel eanMusicLabel;
    private javax.swing.JTextField eanMusicTextField;
    private javax.swing.JLabel eanVideoLabel;
    private javax.swing.JTextField eanVideoTextField;
    private javax.swing.JPanel expiredPanel;
    private javax.swing.JScrollPane expiredScrollPane;
    private javax.swing.JButton finalDeleteEverythingButton;
    private javax.swing.JButton finalDeleteItemButton;
    private javax.swing.JComboBox formatMusicComboBox;
    private javax.swing.JLabel formatMusicLabel;
    private javax.swing.JComboBox formatVideoComboBox;
    private javax.swing.JLabel formatVideoLabel;
    private javax.swing.JLabel genreBookLabel;
    private javax.swing.JTextField genreBookTextField;
    private javax.swing.JLabel genreMusicLabel;
    private javax.swing.JTextField genreMusicTextField;
    private javax.swing.JLabel genreVideoLabel;
    private javax.swing.JTextField genreVideoTextField;
    private javax.swing.JPanel headPanel;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton hideBookButton;
    private javax.swing.JButton hideMusicButton;
    private javax.swing.JButton hideVideoButton;
    private javax.swing.JLabel interpretMusicLabel;
    private javax.swing.JLabel isbnBookLabel;
    private javax.swing.JTextField isbnBookTextField;
    private javax.swing.JPanel itemBookPanel;
    private javax.swing.JScrollPane itemBookScrollPane;
    private javax.swing.JPanel itemMusicPanel;
    private javax.swing.JScrollPane itemMusicScrollPane;
    private javax.swing.JPanel itemVideoPanel;
    private javax.swing.JScrollPane itemVideoScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel languageBookLabel;
    private javax.swing.JTextField languageBookTextField;
    private javax.swing.JComboBox languagesComboBox;
    private javax.swing.JCheckBox lentBookCheckBox;
    private javax.swing.JLabel lentBookLabel;
    private javax.swing.JLabel lentDateBookLabel;
    private javax.swing.JLabel lentDateMusicLabel;
    private javax.swing.JLabel lentDateVideoLabel;
    private javax.swing.JComboBox lentDayBookComboBox;
    private javax.swing.JComboBox lentDayMusicComboBox;
    private javax.swing.JComboBox lentDayVideoComboBox;
    private javax.swing.JComboBox lentMonthBookComboBox;
    private javax.swing.JComboBox lentMonthMusicComboBox;
    private javax.swing.JComboBox lentMonthVideoComboBox;
    private javax.swing.JCheckBox lentMusicCheckBox;
    private javax.swing.JLabel lentMusicLabel;
    private javax.swing.JLabel lentToBookLabel;
    private javax.swing.JTextField lentToBookTextField;
    private javax.swing.JLabel lentToMusicLabel;
    private javax.swing.JTextField lentToMusicTextField;
    private javax.swing.JLabel lentToVideoLabel;
    private javax.swing.JTextField lentToVideoTextField;
    private javax.swing.JLabel lentUntilDateBookLabel;
    private javax.swing.JLabel lentUntilDateMusicLabel;
    private javax.swing.JLabel lentUntilDateVideoLabel;
    private javax.swing.JComboBox lentUntilDayBookComboBox;
    private javax.swing.JComboBox lentUntilDayMusicComboBox;
    private javax.swing.JComboBox lentUntilDayVideoComboBox;
    private javax.swing.JComboBox lentUntilMonthBookComboBox;
    private javax.swing.JComboBox lentUntilMonthMusicComboBox;
    private javax.swing.JComboBox lentUntilMonthVideoComboBox;
    private javax.swing.JComboBox lentUntilYearBookComboBox;
    private javax.swing.JComboBox lentUntilYearMusicComboBox;
    private javax.swing.JComboBox lentUntilYearVideoComboBox;
    private javax.swing.JCheckBox lentVideoCheckBox;
    private javax.swing.JLabel lentVideoLabel;
    private javax.swing.JComboBox lentYearBookComboBox;
    private javax.swing.JComboBox lentYearMusicComboBox;
    private javax.swing.JComboBox lentYearVideoComboBox;
    private javax.swing.JLabel locationBookLabel;
    private javax.swing.JTextField locationBookTextField;
    private javax.swing.JLabel locationMusicLabel;
    private javax.swing.JTextField locationMusicTextField;
    private javax.swing.JLabel locationVideoLabel;
    private javax.swing.JTextField locationVideoTextField;
    private javax.swing.JPanel musicCardPanel;
    private javax.swing.JPanel musicPanel;
    private javax.swing.ButtonGroup musicRatingButtonGroup;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JScrollPane overviewScrollPane;
    private javax.swing.JTable overviewTable;
    private javax.swing.JLabel ratingBookLabel;
    private javax.swing.JLabel ratingMusicLabel;
    private javax.swing.JRadioButton ratingNoneBookRadioButton;
    private javax.swing.JRadioButton ratingNoneMusicRadioButton;
    private javax.swing.JRadioButton ratingNoneVideoRadioButton;
    private javax.swing.JRadioButton ratingOneBookRadioButton;
    private javax.swing.JRadioButton ratingOneMusicRadioButton;
    private javax.swing.JRadioButton ratingOneVideoRadioButton;
    private javax.swing.JRadioButton ratingThreeBookRadioButton;
    private javax.swing.JRadioButton ratingThreeMusicRadioButton;
    private javax.swing.JRadioButton ratingThreeVideoRadioButton;
    private javax.swing.JRadioButton ratingTwoBookRadioButton;
    private javax.swing.JRadioButton ratingTwoMusicRadioButton;
    private javax.swing.JRadioButton ratingTwoVideoRadioButton;
    private javax.swing.JLabel ratingVideoLabel;
    private javax.swing.JComboBox releaseYearBookComboBox;
    private javax.swing.JLabel releaseYearBookLabel;
    private javax.swing.JComboBox releaseYearMusicComboBox;
    private javax.swing.JLabel releaseYearMusicLabel;
    private javax.swing.JComboBox releaseYearVideoComboBox;
    private javax.swing.JLabel releaseYearVideoLabel;
    private javax.swing.JButton restoreEverythingButton;
    private javax.swing.JButton restoreItemButton;
    private javax.swing.JScrollPane restoreItemsScrollpane;
    private javax.swing.JPanel restorePanel;
    private javax.swing.JButton saveBookButton;
    private javax.swing.JButton saveMusicButton;
    private javax.swing.JButton saveVideoButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JToggleButton sortBookAuthorButton;
    private javax.swing.JToggleButton sortBookGenreButton;
    private javax.swing.JToggleButton sortBookLanguageButton;
    private javax.swing.JToggleButton sortBookRatingButton;
    private javax.swing.JToggleButton sortBookTitleButton;
    private javax.swing.JToggleButton sortMusicGenreButton;
    private javax.swing.JToggleButton sortMusicInterpeterButton;
    private javax.swing.JToggleButton sortMusicRatingButton;
    private javax.swing.JToggleButton sortMusicTitleButton;
    private javax.swing.JToggleButton sortMusicTypeButton;
    private javax.swing.JPanel sortPanelBooks;
    private javax.swing.JPanel sortPanelMusic;
    private javax.swing.JPanel sortPanelVideo;
    private javax.swing.JToggleButton sortVideoActorsButton;
    private javax.swing.JToggleButton sortVideoGenreButton;
    private javax.swing.JToggleButton sortVideoRatingButton;
    private javax.swing.JToggleButton sortVideoRegisseurButton;
    private javax.swing.JToggleButton sortVideoTitleButton;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel titleBookLabel;
    private javax.swing.JTextField titleBookTextField;
    private javax.swing.JLabel titleMusicLabel;
    private javax.swing.JTextField titleMusicTextField;
    private javax.swing.JLabel titleVideoLabel;
    private javax.swing.JTextField titleVideoTextField;
    private javax.swing.JComboBox typeMusicComboBox;
    private javax.swing.JLabel typeMusicLabel;
    private javax.swing.JPanel videoCardPanel;
    private javax.swing.JPanel videoPanel;
    private javax.swing.ButtonGroup videoRatingButtonGroup;
    private javax.swing.JComboBox viewBookComboBox;
    private javax.swing.JPanel viewBookPanel;
    private javax.swing.JComboBox viewMusicComboBox;
    private javax.swing.JPanel viewMusicPanel;
    private javax.swing.JComboBox viewVideoComboBox;
    private javax.swing.JPanel viewVideoPanel;
    // End of variables declaration//GEN-END:variables
    private JScrollPane coverDetailsListBookScrollPane;
    private JScrollPane detailsListBookScrollPane;
    private JScrollPane coverListBookScrollPane;
    private JScrollPane coverDetailsListMusicScrollPane;
    private JScrollPane detailsListMusicScrollPane;
    private JScrollPane coverListMusicScrollPane;
    private JScrollPane coverDetailsListVideoScrollPane;
    private JScrollPane detailsListVideoScrollPane;
    private JScrollPane coverListVideoScrollPane;
    private JScrollPane treeBookScrollPane;
    private JScrollPane treeMusicScrollPane;
    private JScrollPane treeVideoScrollPane;
    private CoverList coverListBook;
    private CoverList coverListMusic;
    private CoverList coverListVideo;
    private CoverDetailsList coverDetailsBookList;
    private CoverDetailsList coverDetailsMusicList;
    private CoverDetailsList coverDetailsVideoList;
    private PrioTree prioTreeBook;
    private PrioTree prioTreeMusic;
    private PrioTree prioTreeVideo;
    private DetailsTable detailsTableBook;
    private DetailsTable detailsTableMusic;
    private DetailsTable detailsTableVideo;
    private DeletedItemsList dil;
    private ExpiredItemsList eil;

    public DeletedItemsList getDeletedList() {
        return dil;
    }


    public void deleteSearchButtonSetVisible(boolean b) {
        deleteSearchButton.setVisible(b);
    }

    public void setListsColor(Color color) {
        detailsTableBook.setBackground(color);
        detailsTableMusic.setBackground(color);
        detailsTableVideo.setBackground(color);

        coverDetailsBookList.setBackground(color);
        coverDetailsMusicList.setBackground(color);
        coverDetailsVideoList.setBackground(color);

        coverListBook.setBackground(color);
        coverListMusic.setBackground(color);
        coverListVideo.setBackground(color);

        prioTreeBook.setBackground(color);
        prioTreeMusic.setBackground(color);
        prioTreeVideo.setBackground(color);
    }

    public void selectBookTabAndAndCell(int index) {
        tabbedPane.getModel().setSelectedIndex(1);
        coverDetailsBookList.setSelectedIndex(index);
        detailsTableBook.getSelectionModel().setSelectionInterval(index, index);
        coverListBook.setSelectedIndex(index);
        coverDetailsListBookScrollPane.getViewport().setViewPosition(new java.awt.Point(0,(((CoverDetailsListEntry)coverDetailsBookList.getModel().getElementAt(0)).getySize()*index)));
        coverListBookScrollPane.getViewport().setViewPosition(new java.awt.Point(0,(((CoverListEntry)coverListBook.getModel().getElementAt(0)).getySize()*index)));
        detailsListBookScrollPane.getViewport().setViewPosition(new java.awt.Point(0,30*index));
        treeBookScrollPane.getViewport().setViewPosition(new java.awt.Point(0,30*index));
    }

    public void selectMusicTabAndAndCell(int index) {
        tabbedPane.getModel().setSelectedIndex(2);
        coverDetailsMusicList.setSelectedIndex(index);
        detailsTableMusic.getSelectionModel().setSelectionInterval(index, index);
        coverListMusic.setSelectedIndex(index);
        coverDetailsListMusicScrollPane.getViewport().setViewPosition(new java.awt.Point(0,(((CoverDetailsListEntry)coverDetailsMusicList.getModel().getElementAt(0)).getySize()*index)));
        coverListMusicScrollPane.getViewport().setViewPosition(new java.awt.Point(0,(((CoverListEntry)coverListMusic.getModel().getElementAt(0)).getySize()*index)));
        detailsListMusicScrollPane.getViewport().setViewPosition(new java.awt.Point(0,30*index));
        treeMusicScrollPane.getViewport().setViewPosition(new java.awt.Point(0,30*index));
    }

    public void selectVideoTabAndAndCell(int index) {
        tabbedPane.getModel().setSelectedIndex(3);
        coverDetailsVideoList.setSelectedIndex(index);
        detailsTableVideo.getSelectionModel().setSelectionInterval(index, index);
        coverListVideo.setSelectedIndex(index);
        coverDetailsListVideoScrollPane.getViewport().setViewPosition(new java.awt.Point(0,(((CoverDetailsListEntry)coverDetailsVideoList.getModel().getElementAt(0)).getySize()*index)));
        coverListVideoScrollPane.getViewport().setViewPosition(new java.awt.Point(0,(((CoverListEntry)coverListVideo.getModel().getElementAt(0)).getySize()*index)));
        detailsListVideoScrollPane.getViewport().setViewPosition(new java.awt.Point(0,30*index));
        treeVideoScrollPane.getViewport().setViewPosition(new java.awt.Point(0,30*index));
    }

    public ExpiredItemsList getExpiredList() {
        return eil;
    }






}
