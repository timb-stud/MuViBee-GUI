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
import java.util.ResourceBundle;
import javax.swing.ComboBoxModel;
import muvibee.utils.TestUtils;
import muvibee.utils.NonValidYearException;
import muvibee.actionlistener.AddActionListener;
import muvibee.actionlistener.ComboBoxActionListener;
import muvibee.actionlistener.FinalDeleteListener;
import muvibee.actionlistener.SaveActionListener;
import muvibee.actionlistener.RestoreListener;
import muvibee.actionlistener.DeleteListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableCellRenderer;
import muvibee.MuViBee;
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
import util.deleteditemlist.DeletedItemsList;
import util.detailsList.*;
import util.tree.PrioTree;



/**
 *
 * @author bline
 */
public class MainFrame extends javax.swing.JFrame {

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
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 600, Toolkit.getDefaultToolkit().getScreenSize().height/2 - 300);
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
        setOverviewInformation(mvb);
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

        itemBookScrollPane.setVisible(false);
        itemMusicScrollPane.setVisible(false);
        itemVideoScrollPane.setVisible(false);

        hideBookButton.setVisible(false);
        hideMusicButton.setVisible(false);
        hideVideoButton.setVisible(false);

        //init languageComboBox
        languagesComboBox.addActionListener(new LanguageActionListener(mvb));
        String[] languages = {"en", "de", "ru", "tr"};
        ComboBoxModel cbm = new DefaultComboBoxModel(languages);
        languagesComboBox.setModel(cbm);

        //init dayComboBoxes
	String[] days = new String[32];
        days[0] = "lol";
	for(int i=1; i<32;i++)
	    days[i] = String.valueOf(i);
	borrowDayBookComboBox.setModel(new DefaultComboBoxModel(days));
	borrowedUntilDayBookComboBox.setModel(new DefaultComboBoxModel(days));
	borrowDayMusicComboBox.setModel(new DefaultComboBoxModel(days));
	borrowedUntilDayMusicComboBox.setModel(new DefaultComboBoxModel(days));
	borrowDayVideoComboBox.setModel(new DefaultComboBoxModel(days));
	borrowedUntilDayVideoComboBox.setModel(new DefaultComboBoxModel(days));

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
        editBookButton.addActionListener(sal);
        editMusicButton.addActionListener(sal);
        editVideoButton.addActionListener(sal);

        DeleteListener dl = new DeleteListener(mvb);
        deleteBookButton.addActionListener(dl);
        deleteMusicButton.addActionListener(dl);
        deleteVideoButton.addActionListener(dl);

        FinalDeleteListener fdl = new FinalDeleteListener(mvb);
        deleteItemButton.addActionListener(fdl);

        RestoreListener rl = new RestoreListener(mvb);
        restoreItemButton.addActionListener(rl);

        DeletedItemsList dil = new DeletedItemsList(mvb);
        mvb.getDeletedMediaList().addObserver(dil);
        restoreItemsScrollpane.setViewportView(dil);

        SearchActionListener searchActionListener = new SearchActionListener(mvb);
        searchButton.addActionListener(searchActionListener);
        advancedSearchButton.addActionListener(searchActionListener);
        
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

        reloadLabels(mvb.getMainBundlePath());
    }

    public void setOverviewInformation(MuViBee mvb) {
        int lentToBooks = mvb.getLentToBook();
        int lentToMusic = mvb.getLentToMusic();
        int lentToVideos = mvb.getLentToVideo();

        int numberBooks = mvb.getNumberOfBooks();
        int numberMusic = mvb.getNumberOfMusic();
        int numberVideos = mvb.getNumberOfVideo();

        int deletedBooks = mvb.getNumberOfDeletedBooks();
        int deletedMusic = mvb.getNumberOfDeletedMusic();
        int deletedVideos = mvb.getNumberOfDeletedVideos();


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
        updateDayComboBoxLabel(borrowDayBookComboBox, day);
        updateDayComboBoxLabel(borrowedUntilDayBookComboBox, day);
        updateDayComboBoxLabel(borrowDayMusicComboBox, day);
        updateDayComboBoxLabel(borrowedUntilDayMusicComboBox, day);
        updateDayComboBoxLabel(borrowDayVideoComboBox, day);
        updateDayComboBoxLabel(borrowedUntilDayVideoComboBox, day);

        
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
	updateMonthComboBoxLabels(borrowMonthBookComboBox, months);
	updateMonthComboBoxLabels(borrowedUntilMonthBookComboBox, months);
	updateMonthComboBoxLabels(borrowMonthMusicComboBox, months);
	updateMonthComboBoxLabels(borrowedUntilMonthMusicComboBox, months);
	updateMonthComboBoxLabels(borrowMonthVideoComboBox, months);
	updateMonthComboBoxLabels(borrowedUntilMonthVideoComboBox, months);


        Object selectedItem = typeMusicComboBox.getSelectedItem();
        typeMusicComboBox.removeAllItems();
        typeMusicComboBox.addItem(bundle.getString("album"));
        typeMusicComboBox.addItem(bundle.getString("single"));
        typeMusicComboBox.addItem(bundle.getString("sampler"));
        typeMusicComboBox.setSelectedItem(selectedItem);


	String[] musicFormats = new String[3];
        musicFormats[0] = bundle.getString("cd");
        musicFormats[1] = bundle.getString("lp");
        musicFormats[2] = bundle.getString("cassette");
	String[] videoFormats = new String[3];
        videoFormats[0] = bundle.getString("cd/dvd");
        videoFormats[1] = bundle.getString("blu-ray");
        videoFormats[2] = bundle.getString("vhs");
	updateFormatComboBox(formatMusicComboBox, musicFormats);
	updateFormatComboBox(formatVideoComboBox, videoFormats);

    }

    public void setBookItem(Book book){
	BufferedImage cover = book.getCover();
	if(cover != null) {
	    coverBookLabel.setIcon(ResizeImageIcon.resizeIcon(140, 160, cover));
        } else {
            coverBookLabel.setIcon(null);
        }
        titleBookTextField.setText(book.getTitle());
        authorBookTextField.setText(book.getAuthor());
	languageBookTextField.setText(book.getLanguage());
	isbnBookTextField.setText(book.getIsbn());
	eanBookTextField.setText(book.getEan());
	genreBookTextField.setText(book.getGenre());
        int releaseYear = book.getReleaseYear();
        if(releaseYear > -1)
            releaseYearBookTextField.setText(String.valueOf(releaseYear));
        else
            releaseYearBookTextField.setText("");
	locationBookTextField.setText(book.getLocation());
	borrowedToBookTextField.setText(book.getLentTo());
	borrowDayBookComboBox.setSelectedIndex(book.getLendDay());
	borrowMonthBookComboBox.setSelectedIndex(book.getLendMonth());
        int borrowYear = book.getLendYear();
        if(borrowYear > -1)
            borrowYearBookTextField.setText(String.valueOf(borrowYear));
        else
            borrowYearBookTextField.setText("");
	borrowedUntilDayBookComboBox.setSelectedIndex(book.getLendUntilDay());
	borrowedUntilMonthBookComboBox.setSelectedIndex(book.getLendUntilMonth());
        int borrowedUntilYear = book.getLendUntilYear();
        if(borrowedUntilYear > -1)
            borrowedUntilYearBookTextField.setText(String.valueOf(borrowedUntilYear));
        else
            borrowedUntilYearBookTextField.setText("");

	switch (book.getRating()) {
	    case 1:
		oneRatingpointBookRadioButton.setSelected(true);
		break;
	    case 2:
		twoRatingpointsBookRadioButton.setSelected(true);
		break;
	    case 3:
		threeRatingpointsBookRadioButton.setSelected(true);
		break;
	    default:
		oneRatingpointBookRadioButton.setSelected(false);
		twoRatingpointsBookRadioButton.setSelected(false);
		threeRatingpointsBookRadioButton.setSelected(false);
	}
	descriptionBookTextArea.setText(book.getDescription());
        annotationBookTextArea.setText(book.getComment());
    }

    public void setMusicItem(Music music){
	BufferedImage cover = music.getCover();
	if(cover != null) {
	    coverMusicLabel.setIcon(new ImageIcon(cover));
        } else {
            coverMusicLabel.setIcon(null);
        }
        titleMusicTextField.setText(music.getTitle());
        artistMusicTextField.setText(music.getInterpreter());

        typeMusicComboBox.setSelectedItem(music.getType());
        formatMusicComboBox.setSelectedItem(music.getFormat());

	eanMusicTextField.setText(music.getEan());
	genreMusicTextField.setText(music.getGenre());
        int releaseYear = music.getReleaseYear();
        if(releaseYear > -1)
            releaseYearMusicTextField.setText(String.valueOf(releaseYear));
        else
            releaseYearMusicTextField.setText("");
	locationMusicTextField.setText(music.getLocation());
	borrowedToMusicTextField.setText(music.getLentTo());
	borrowDayMusicComboBox.setSelectedIndex(music.getLendDay());
	borrowMonthMusicComboBox.setSelectedIndex(music.getLendMonth());
        int borrowYear = music.getLendYear();
        if(borrowYear > -1)
            borrowYearMusicTextField.setText(String.valueOf(borrowYear));
        else
            borrowYearMusicTextField.setText("");
	borrowedUntilDayMusicComboBox.setSelectedIndex(music.getLendUntilDay());
	borrowedUntilMonthMusicComboBox.setSelectedIndex(music.getLendUntilMonth());
        int borrowedUntilYear = music.getLendUntilYear();
        if(borrowedUntilYear > -1)
            borrowedUntilYearMusicTextField.setText(String.valueOf(borrowedUntilYear));
        else
            borrowedUntilYearMusicTextField.setText("");
        
	switch (music.getRating()) {
	    case 1:
		oneRatingpointMusicRadioButton.setSelected(true);
		break;
	    case 2:
		twoRatingpointsMusicRadioButton.setSelected(true);
		break;
	    case 3:
		threeRatingpointsMusicRadioButton.setSelected(true);
		break;
	    default:
		oneRatingpointMusicRadioButton.setSelected(false);
		twoRatingpointsMusicRadioButton.setSelected(false);
		threeRatingpointsMusicRadioButton.setSelected(false);
	}
	descriptionMusicTextArea.setText(music.getDescription());
        annotationMusicTextArea.setText(music.getComment());
    }

    public void setVideoItem(Video video) {
	BufferedImage cover = video.getCover();
	if(cover != null) {
	    coverVideoLabel.setIcon(new ImageIcon(cover));
        } else {
            coverVideoLabel.setIcon(null);
        }
        titleVideoTextField.setText(video.getTitle());
        directorVideoTextField.setText(video.getDirector());
	actorsVideoTextField.setText(video.getActors());
        formatVideoComboBox.setSelectedItem(video.getFormat());
	eanVideoTextField.setText(video.getEan());
	genreVideoTextField.setText(video.getGenre());
        int releaseYear = video.getReleaseYear();
        if(releaseYear > -1)
            releaseYearVideoTextField.setText(String.valueOf(releaseYear));
        else
            releaseYearVideoTextField.setText("");
	locationVideoTextField.setText(video.getLocation());
	borrowedToVideoTextField.setText(video.getLentTo());
	borrowDayVideoComboBox.setSelectedIndex(video.getLendDay());
	borrowMonthVideoComboBox.setSelectedIndex(video.getLendMonth());
        int borrowYear = video.getLendYear();
        if(borrowYear > -1)
            borrowYearVideoTextField.setText(String.valueOf(borrowYear));
        else
            borrowYearVideoTextField.setText("");
	borrowedUntilDayVideoComboBox.setSelectedIndex(video.getLendUntilDay());
	borrowedUntilMonthVideoComboBox.setSelectedIndex(video.getLendUntilMonth());
        int borrowedUntilYear = video.getLendUntilYear();
        if(borrowedUntilYear > -1)
            borrowedUntilYearVideoTextField.setText(String.valueOf(borrowedUntilYear));
        else
            borrowedUntilYearVideoTextField.setText("");
        
	switch (video.getRating()) {
	    case 1:
		oneRatingpointVideoRadioButton.setSelected(true);
		break;
	    case 2:
		twoRatingpointsVideoRadioButton.setSelected(true);
		break;
	    case 3:
		threeRatingpointsVideoRadioButton.setSelected(true);
		break;
	    default:
		oneRatingpointVideoRadioButton.setSelected(false);
		twoRatingpointsVideoRadioButton.setSelected(false);
		threeRatingpointsVideoRadioButton.setSelected(false);
	}
	descriptionVideoTextArea.setText(video.getDescription());
        annotationVideoTextArea.setText(video.getComment());
    }


    public void setBookItemInformation(Book book) throws NonValidYearException {
	String title = titleBookTextField.getText().trim();
	String author = authorBookTextField.getText().trim();
	String language = languageBookTextField.getText().trim();
	String isbn = isbnBookTextField.getText().trim();   //TODO Ueberpruefen!?!?
	String ean = eanBookTextField.getText().trim();	    //TODO Ueberpruefen!?!?
	String genre = genreBookTextField.getText().trim();
	String releaseYear = releaseYearBookTextField.getText().trim();
	String location = locationBookTextField.getText().trim();
	String lendTo = borrowedToBookTextField.getText().trim();
	int lendDay = borrowDayBookComboBox.getSelectedIndex();
	int lendMonth = borrowMonthBookComboBox.getSelectedIndex();
	String lendYear = borrowYearBookTextField.getText().trim();
	int lendUntilDay = borrowedUntilDayBookComboBox.getSelectedIndex();
	int lendUntilMonth = borrowedUntilMonthBookComboBox.getSelectedIndex();
	String lendUntilYear = borrowedUntilYearBookTextField.getText().trim();
	String description = descriptionBookTextArea.getText().trim();
	String annotation = annotationBookTextArea.getText().trim();

	//Rating
	int rating = 0;
	if(oneRatingpointBookRadioButton.isSelected())
	    rating = 1;
	else
	    if(twoRatingpointsBookRadioButton.isSelected())
		rating = 2;
	    else
		if(threeRatingpointsBookRadioButton.isSelected())
		    rating = 3;

	//year Test
	    int ry = TestUtils.validYear(releaseYear);
	    int ly = TestUtils.validYear(lendYear);
	    int luy = TestUtils.validYear(lendUntilYear);
	    book.setTitle(title);
	    book.setAuthor(author);
	    book.setLanguage(language);
	    book.setIsbn(isbn);
	    book.setEan(ean);
	    book.setGenre(genre);
	    book.setReleaseYear(ry);
	    book.setLocation(location);
	    book.setLendTo(lendTo);
	    book.setLendDay(lendDay);
	    book.setLendMonth(lendMonth);
	    book.setLendYear(ly);
	    book.setLendUntilDay(lendUntilDay);
	    book.setLendUntilMonth(lendUntilMonth);
	    book.setLendUntilYear(luy);
	    book.setRating(rating);
	    book.setDescription(description);
	    book.setComment(annotation);
            book.updateObservers();
    }

    public void setMusicItemInformation(Music music) throws NonValidYearException{
	String title = titleMusicTextField.getText().trim();
	String artist = artistMusicTextField.getText().trim();
	Object selectedType = typeMusicComboBox.getSelectedItem();
        String type = null;
        if(selectedType != null)
            type = selectedType.toString().trim();
        Object selectedFormat = formatMusicComboBox.getSelectedItem();
        String format = null;
        if(selectedFormat != null)
            format = selectedFormat.toString().trim();
	String ean = eanMusicTextField.getText().trim();    //TODO Ueberpruefen!?!?
	String genre = genreMusicTextField.getText().trim();
	String releaseYear = releaseYearMusicTextField.getText().trim();
	String location = locationMusicTextField.getText().trim();
	String lendTo = borrowedToMusicTextField.getText().trim();
	int lendDay = borrowDayMusicComboBox.getSelectedIndex();
	int lendMonth = borrowMonthMusicComboBox.getSelectedIndex();
	String lendYear = borrowYearMusicTextField.getText().trim();
	int lendUntilDay = borrowedUntilDayMusicComboBox.getSelectedIndex();
	int lendUntilMonth = borrowedUntilMonthMusicComboBox.getSelectedIndex();
	String lendUntilYear = borrowedUntilYearMusicTextField.getText().trim();
	String description = descriptionMusicTextArea.getText().trim();
	String annotation = annotationMusicTextArea.getText().trim();

	//Rating
	int rating = 0;
	if(oneRatingpointMusicRadioButton.isSelected())
	    rating = 1;
	else
	    if(twoRatingpointsMusicRadioButton.isSelected())
		rating = 2;
	    else
		if(threeRatingpointsMusicRadioButton.isSelected())
		    rating = 3;

	//year Test
	    int ry = TestUtils.validYear(releaseYear);
	    int ly = TestUtils.validYear(lendYear);
	    int luy = TestUtils.validYear(lendUntilYear);
	    music.setTitle(title);
	    music.setInterpreter(artist);
	    music.setType(type);
	    music.setFormat(format);
	    music.setEan(ean);
	    music.setGenre(genre);
	    music.setReleaseYear(ry);
	    music.setLocation(location);
	    music.setLendTo(lendTo);
	    music.setLendDay(lendDay);
	    music.setLendMonth(lendMonth);
	    music.setLendYear(ly);
	    music.setLendUntilDay(lendUntilDay);
	    music.setLendUntilMonth(lendUntilMonth);
	    music.setLendUntilYear(luy);
	    music.setRating(rating);
	    music.setDescription(description);
	    music.setComment(annotation);
            music.updateObservers();

    }

    public void setVideoItemInformation(Video video) throws NonValidYearException {
        String title = titleVideoTextField.getText().trim();
	String director = directorVideoTextField.getText().trim();
	String actors = actorsVideoTextField.getText().trim();
        Object selectedFormat = formatVideoComboBox.getSelectedItem();
        String format = null;
        if(selectedFormat != null)
            format = selectedFormat.toString().trim();
	String ean = eanVideoTextField.getText().trim();    //TODO Ueberpruefen!?!?
	String genre = genreVideoTextField.getText().trim();
	String releaseYear = releaseYearVideoTextField.getText().trim();
	String location = locationVideoTextField.getText().trim();
	String lendTo = borrowedToVideoTextField.getText().trim();
	int lendDay = borrowDayVideoComboBox.getSelectedIndex();
	int lendMonth = borrowMonthVideoComboBox.getSelectedIndex();
	String lendYear = borrowYearVideoTextField.getText().trim();
	int lendUntilDay = borrowedUntilDayVideoComboBox.getSelectedIndex();
	int lendUntilMonth = borrowedUntilMonthVideoComboBox.getSelectedIndex();
	String lendUntilYear = borrowedUntilYearVideoTextField.getText().trim();
	String description = descriptionVideoTextArea.getText().trim();
	String annotation = annotationVideoTextArea.getText().trim();

	//Rating
	int rating = 0;
	if(oneRatingpointVideoRadioButton.isSelected())
	    rating = 1;
	else
	    if(twoRatingpointsVideoRadioButton.isSelected())
		rating = 2;
	    else
		if(threeRatingpointsVideoRadioButton.isSelected())
		    rating = 3;

	//year Test

	    int ry = TestUtils.validYear(releaseYear);
	    int ly = TestUtils.validYear(lendYear);
	    int luy = TestUtils.validYear(lendUntilYear);
	    video.setTitle(title);
	    video.setDirector(director);
	    video.setActors(actors);
	    video.setFormat(format);
	    video.setEan(ean);
	    video.setGenre(genre);
	    video.setReleaseYear(ry);
	    video.setLocation(location);
	    video.setLendTo(lendTo);
	    video.setLendDay(lendDay);
	    video.setLendMonth(lendMonth);
	    video.setLendYear(ly);
	    video.setLendUntilDay(lendUntilDay);
	    video.setLendUntilMonth(lendUntilMonth);
	    video.setLendUntilYear(luy);
	    video.setRating(rating);
	    video.setDescription(description);
	    video.setComment(annotation);
            video.updateObservers();
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

    public void updateDayComboBoxLabel(JComboBox cb, String label){
        int selectedIndex = cb.getSelectedIndex();
        cb.removeItemAt(0);
        cb.insertItemAt(label, 0);
        cb.setSelectedIndex(selectedIndex);
    }

    public void updateMonthComboBoxLabels(JComboBox cb, String[] months){
        int selectedIndex = cb.getSelectedIndex();
        cb.removeAllItems();
        for(int i=0; i<months.length; i++)
            cb.addItem(months[i]);
        cb.setSelectedIndex(selectedIndex);
    }

    public void updateFormatComboBox(JComboBox cb, String[] formats){
        Object selectedItem = cb.getSelectedItem();
        cb.removeAllItems();
        for(int i=0; i< formats.length; i++){
            cb.addItem(formats[i]);
        }
        cb.setSelectedItem(selectedItem);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        authorMusicLabel = new javax.swing.JLabel();
        languageMusicLabel = new javax.swing.JLabel();
        isbnMusicLabel = new javax.swing.JLabel();
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
        authorMusicTextField = new javax.swing.JTextField();
        languageMusicTextField = new javax.swing.JTextField();
        isbnMusicTextField = new javax.swing.JTextField();
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
        itemBookPanel2 = new javax.swing.JPanel();
        coverBookLabel2 = new javax.swing.JLabel();
        titleBookLabel2 = new javax.swing.JLabel();
        authorBookLabel2 = new javax.swing.JLabel();
        languageBookLabel2 = new javax.swing.JLabel();
        isbnBookLabel2 = new javax.swing.JLabel();
        eanBookLabel2 = new javax.swing.JLabel();
        genreBookLabel2 = new javax.swing.JLabel();
        releaseYearBookLabel2 = new javax.swing.JLabel();
        locationBookLabel2 = new javax.swing.JLabel();
        lentBookLabel2 = new javax.swing.JLabel();
        lentToBookLabel2 = new javax.swing.JLabel();
        lentDateBookLabel2 = new javax.swing.JLabel();
        lentUntilDateBookLabel2 = new javax.swing.JLabel();
        ratingBookLabel2 = new javax.swing.JLabel();
        descriptionBookLabel2 = new javax.swing.JLabel();
        annotationBookLabel2 = new javax.swing.JLabel();
        titleBookTextField2 = new javax.swing.JTextField();
        authorBookTextField2 = new javax.swing.JTextField();
        languageBookTextField2 = new javax.swing.JTextField();
        isbnBookTextField2 = new javax.swing.JTextField();
        eanBookTextField2 = new javax.swing.JTextField();
        genreBookTextField2 = new javax.swing.JTextField();
        releaseYearBookComboBox2 = new javax.swing.JComboBox();
        locationBookTextField2 = new javax.swing.JTextField();
        lentBookCheckBox2 = new javax.swing.JCheckBox();
        lentToBookTextField2 = new javax.swing.JTextField();
        lentDayBookComboBox2 = new javax.swing.JComboBox();
        lentMonthBookComboBox2 = new javax.swing.JComboBox();
        lentYearBookComboBox2 = new javax.swing.JComboBox();
        lentUntilDayBookComboBox2 = new javax.swing.JComboBox();
        lentUntilMonthBookComboBox2 = new javax.swing.JComboBox();
        lentUntilYearBookComboBox2 = new javax.swing.JComboBox();
        ratingOneBookRadioButton2 = new javax.swing.JRadioButton();
        ratingTwoBookRadioButton2 = new javax.swing.JRadioButton();
        ratingThreeBookRadioButton2 = new javax.swing.JRadioButton();
        ratingNoneBookRadioButton2 = new javax.swing.JRadioButton();
        deleteButton2 = new javax.swing.JButton();
        saveButton2 = new javax.swing.JButton();
        descriptionBookScrollPane2 = new javax.swing.JScrollPane();
        descriptionBookTextArea2 = new javax.swing.JTextArea();
        annotationBookScrollPane2 = new javax.swing.JScrollPane();
        annotationBookTextArea2 = new javax.swing.JTextArea();
        hideVideoButton = new javax.swing.JButton();
        restorePanel = new javax.swing.JPanel();
        restoreItemButton = new javax.swing.JButton();
        deleteItemButton = new javax.swing.JButton();
        restoreItemsScrollpane = new javax.swing.JScrollPane();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 478, Short.MAX_VALUE)
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
            .addGap(0, 1107, Short.MAX_VALUE)
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
        overviewTable.setColumnSelectionAllowed(true);
        overviewTable.setRowHeight(64);
        overviewTable.setSelectionBackground(new java.awt.Color(235, 232, 238));
        overviewTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        overviewScrollPane.setViewportView(overviewTable);

        javax.swing.GroupLayout overviewPanelLayout = new javax.swing.GroupLayout(overviewPanel);
        overviewPanel.setLayout(overviewPanelLayout);
        overviewPanelLayout.setHorizontalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overviewScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
                .addContainerGap())
        );
        overviewPanelLayout.setVerticalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overviewScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
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
                .addContainerGap(153, Short.MAX_VALUE))
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
                    .addComponent(bookCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(viewBookPanelLayout.createSequentialGroup()
                        .addComponent(viewBookComboBox, 0, 391, Short.MAX_VALUE)
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
                .addComponent(bookCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addBookButton)
                    .addComponent(viewBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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

        lentDayBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentMonthBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentYearBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentUntilDayBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentUntilMonthBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentUntilYearBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        ratingOneBookRadioButton.setText("1");

        ratingTwoBookRadioButton.setText("2");

        ratingThreeBookRadioButton.setText("3");

        ratingNoneBookRadioButton.setText("keine");

        deleteBookButton.setText("Löschen");

        saveBookButton.setText("Speichern");

        descriptionBookTextArea.setColumns(20);
        descriptionBookTextArea.setRows(5);
        descriptionBookScrollPane.setViewportView(descriptionBookTextArea);

        annotationBookTextArea.setColumns(20);
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
                                .addComponent(titleBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
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
                                            .addComponent(locationBookTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(releaseYearBookComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eanBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(authorBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(isbnBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(languageBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(genreBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(lentToBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanelLayout.createSequentialGroup()
                        .addComponent(saveBookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBookButton)
                        .addContainerGap())
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(descriptionBookLabel)
                        .addContainerGap(436, Short.MAX_VALUE))
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(descriptionBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(annotationBookLabel)
                        .addContainerGap(451, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanelLayout.createSequentialGroup()
                        .addComponent(annotationBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(viewBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
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
                    .addComponent(viewBookPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(hideBookButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(itemBookScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
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
                .addContainerGap(177, Short.MAX_VALUE))
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
                    .addComponent(musicCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(viewMusicPanelLayout.createSequentialGroup()
                        .addComponent(viewMusicComboBox, 0, 391, Short.MAX_VALUE)
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
                .addComponent(musicCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addMusicButton)
                    .addComponent(viewMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        coverMusicLabel.setText("Cover");
        coverMusicLabel.setPreferredSize(new java.awt.Dimension(120, 150));

        titleMusicLabel.setText("Titel");

        authorMusicLabel.setText("Autor");

        languageMusicLabel.setText("Sprache");

        isbnMusicLabel.setText("ISBN");

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

        lentDayMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentMonthMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentYearMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentUntilDayMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentUntilMonthMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentUntilYearMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        ratingOneMusicRadioButton.setText("1");

        ratingTwoMusicRadioButton.setText("2");

        ratingThreeMusicRadioButton.setText("3");

        ratingNoneMusicRadioButton.setText("keine");

        deleteMusicButton.setText("Löschen");

        saveMusicButton.setText("Speichern");

        descriptionMusicTextArea.setColumns(20);
        descriptionMusicTextArea.setRows(5);
        descriptionMusicScrollPane.setViewportView(descriptionMusicTextArea);

        annotationMusicTextArea.setColumns(20);
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
                                .addComponent(titleMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                            .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                .addComponent(coverMusicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(languageMusicLabel)
                                    .addComponent(releaseYearMusicLabel)
                                    .addComponent(locationMusicLabel)
                                    .addComponent(authorMusicLabel)
                                    .addComponent(isbnMusicLabel)
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
                                    .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                            .addComponent(lentDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lentMonthMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lentYearMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lentMusicCheckBox, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, itemMusicPanelLayout.createSequentialGroup()
                                                .addComponent(ratingOneMusicRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingTwoMusicRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingThreeMusicRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingNoneMusicRadioButton))
                                            .addComponent(locationMusicTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(releaseYearMusicComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eanMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(authorMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(isbnMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(languageMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(genreMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(lentToMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(saveMusicButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMusicButton)
                        .addContainerGap())
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(descriptionMusicLabel)
                        .addContainerGap(436, Short.MAX_VALUE))
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(descriptionMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(annotationMusicLabel)
                        .addContainerGap(451, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(annotationMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
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
                            .addComponent(authorMusicLabel)
                            .addComponent(authorMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(languageMusicLabel)
                            .addComponent(languageMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isbnMusicLabel)
                            .addComponent(isbnMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(hideMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(itemMusicScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
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
                .addContainerGap(123, Short.MAX_VALUE))
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
                    .addComponent(videoCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(sortPanelVideo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(viewVideoPanelLayout.createSequentialGroup()
                        .addComponent(viewVideoComboBox, 0, 391, Short.MAX_VALUE)
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
                .addComponent(videoCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addVideoButton)
                    .addComponent(viewVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        coverBookLabel2.setText("Cover");
        coverBookLabel2.setPreferredSize(new java.awt.Dimension(120, 150));

        titleBookLabel2.setText("Titel");

        authorBookLabel2.setText("Autor");

        languageBookLabel2.setText("Sprache");

        isbnBookLabel2.setText("ISBN");

        eanBookLabel2.setText("EAN");

        genreBookLabel2.setText("Genre");

        releaseYearBookLabel2.setText("Erscheinungsjahr");

        locationBookLabel2.setText("Standort");

        lentBookLabel2.setText("Verliehen");

        lentToBookLabel2.setText("Verliehen an");

        lentDateBookLabel2.setText("Verliehen am");

        lentUntilDateBookLabel2.setText("Verliehen bis");

        ratingBookLabel2.setText("Bewertung");

        descriptionBookLabel2.setText("Beschreibung");

        annotationBookLabel2.setText("Kommentar");

        releaseYearBookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentDayBookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentMonthBookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentYearBookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        lentUntilDayBookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        lentUntilMonthBookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        lentUntilYearBookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        ratingOneBookRadioButton2.setText("1");

        ratingTwoBookRadioButton2.setText("2");

        ratingThreeBookRadioButton2.setText("3");

        ratingNoneBookRadioButton2.setText("keine");

        deleteButton2.setText("Löschen");

        saveButton2.setText("Speichern");

        descriptionBookTextArea2.setColumns(20);
        descriptionBookTextArea2.setRows(5);
        descriptionBookScrollPane2.setViewportView(descriptionBookTextArea2);

        annotationBookTextArea2.setColumns(20);
        annotationBookTextArea2.setRows(5);
        annotationBookScrollPane2.setViewportView(annotationBookTextArea2);

        javax.swing.GroupLayout itemBookPanel2Layout = new javax.swing.GroupLayout(itemBookPanel2);
        itemBookPanel2.setLayout(itemBookPanel2Layout);
        itemBookPanel2Layout.setHorizontalGroup(
            itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemBookPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemBookPanel2Layout.createSequentialGroup()
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(itemBookPanel2Layout.createSequentialGroup()
                                .addComponent(titleBookLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(titleBookTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                            .addGroup(itemBookPanel2Layout.createSequentialGroup()
                                .addComponent(coverBookLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(languageBookLabel2)
                                    .addComponent(releaseYearBookLabel2)
                                    .addComponent(locationBookLabel2)
                                    .addComponent(authorBookLabel2)
                                    .addComponent(isbnBookLabel2)
                                    .addComponent(eanBookLabel2)
                                    .addComponent(genreBookLabel2)
                                    .addComponent(ratingBookLabel2)
                                    .addComponent(lentBookLabel2)
                                    .addComponent(lentToBookLabel2)
                                    .addComponent(lentDateBookLabel2)
                                    .addComponent(lentUntilDateBookLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemBookPanel2Layout.createSequentialGroup()
                                        .addComponent(lentUntilDayBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilMonthBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentUntilYearBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(itemBookPanel2Layout.createSequentialGroup()
                                            .addComponent(lentDayBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lentMonthBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lentYearBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lentBookCheckBox2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, itemBookPanel2Layout.createSequentialGroup()
                                                .addComponent(ratingOneBookRadioButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingTwoBookRadioButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingThreeBookRadioButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ratingNoneBookRadioButton2))
                                            .addComponent(locationBookTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(releaseYearBookComboBox2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eanBookTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(authorBookTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(isbnBookTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(languageBookTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(genreBookTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(lentToBookTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanel2Layout.createSequentialGroup()
                        .addComponent(saveButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton2)
                        .addContainerGap())
                    .addGroup(itemBookPanel2Layout.createSequentialGroup()
                        .addComponent(descriptionBookLabel2)
                        .addContainerGap(436, Short.MAX_VALUE))
                    .addGroup(itemBookPanel2Layout.createSequentialGroup()
                        .addComponent(descriptionBookScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(itemBookPanel2Layout.createSequentialGroup()
                        .addComponent(annotationBookLabel2)
                        .addContainerGap(451, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanel2Layout.createSequentialGroup()
                        .addComponent(annotationBookScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        itemBookPanel2Layout.setVerticalGroup(
            itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemBookPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleBookLabel2)
                    .addComponent(titleBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverBookLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemBookPanel2Layout.createSequentialGroup()
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(authorBookLabel2)
                            .addComponent(authorBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(languageBookLabel2)
                            .addComponent(languageBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isbnBookLabel2)
                            .addComponent(isbnBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eanBookLabel2)
                            .addComponent(eanBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreBookLabel2)
                            .addComponent(genreBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(releaseYearBookLabel2)
                            .addComponent(releaseYearBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(locationBookLabel2)
                            .addComponent(locationBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ratingBookLabel2)
                    .addComponent(ratingOneBookRadioButton2)
                    .addComponent(ratingTwoBookRadioButton2)
                    .addComponent(ratingThreeBookRadioButton2)
                    .addComponent(ratingNoneBookRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lentBookLabel2)
                    .addComponent(lentBookCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentToBookLabel2)
                    .addComponent(lentToBookTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentDateBookLabel2)
                    .addComponent(lentDayBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentMonthBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentYearBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lentUntilDateBookLabel2)
                    .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lentUntilDayBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilMonthBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lentUntilYearBookComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(itemBookPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton2)
                    .addComponent(saveButton2))
                .addGap(11, 11, 11)
                .addComponent(descriptionBookLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionBookScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(annotationBookLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annotationBookScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemVideoScrollPane.setViewportView(itemBookPanel2);

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
                    .addComponent(hideVideoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(itemVideoScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Videos", videoPanel);

        restoreItemButton.setText("Wiederhestellen");

        deleteItemButton.setText("Endgültig löschen");

        javax.swing.GroupLayout restorePanelLayout = new javax.swing.GroupLayout(restorePanel);
        restorePanel.setLayout(restorePanelLayout);
        restorePanelLayout.setHorizontalGroup(
            restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restorePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restoreItemsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
                    .addGroup(restorePanelLayout.createSequentialGroup()
                        .addComponent(restoreItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteItemButton)))
                .addContainerGap())
        );
        restorePanelLayout.setVerticalGroup(
            restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, restorePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(restoreItemsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(restoreItemButton)
                    .addComponent(deleteItemButton)))
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
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton addBookButton;
    private javax.swing.JButton addMusicButton;
    private javax.swing.JButton addVideoButton;
    private javax.swing.JButton advancedSearchButton;
    private javax.swing.JLabel annotationBookLabel;
    private javax.swing.JLabel annotationBookLabel2;
    private javax.swing.JScrollPane annotationBookScrollPane;
    private javax.swing.JScrollPane annotationBookScrollPane2;
    private javax.swing.JTextArea annotationBookTextArea;
    private javax.swing.JTextArea annotationBookTextArea2;
    private javax.swing.JLabel annotationMusicLabel;
    private javax.swing.JScrollPane annotationMusicScrollPane;
    private javax.swing.JTextArea annotationMusicTextArea;
    private javax.swing.JLabel authorBookLabel;
    private javax.swing.JLabel authorBookLabel2;
    private javax.swing.JTextField authorBookTextField;
    private javax.swing.JTextField authorBookTextField2;
    private javax.swing.JLabel authorMusicLabel;
    private javax.swing.JTextField authorMusicTextField;
    private javax.swing.JPanel bookCardPanel;
    private javax.swing.JPanel bookPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel coverBookLabel;
    private javax.swing.JLabel coverBookLabel2;
    private javax.swing.JLabel coverMusicLabel;
    private javax.swing.JButton deleteBookButton;
    private javax.swing.JButton deleteButton2;
    private javax.swing.JButton deleteItemButton;
    private javax.swing.JButton deleteMusicButton;
    private javax.swing.JButton deleteSearchButton;
    private javax.swing.JLabel descriptionBookLabel;
    private javax.swing.JLabel descriptionBookLabel2;
    private javax.swing.JScrollPane descriptionBookScrollPane;
    private javax.swing.JScrollPane descriptionBookScrollPane2;
    private javax.swing.JTextArea descriptionBookTextArea;
    private javax.swing.JTextArea descriptionBookTextArea2;
    private javax.swing.JLabel descriptionMusicLabel;
    private javax.swing.JScrollPane descriptionMusicScrollPane;
    private javax.swing.JTextArea descriptionMusicTextArea;
    private javax.swing.JLabel eanBookLabel;
    private javax.swing.JLabel eanBookLabel2;
    private javax.swing.JTextField eanBookTextField;
    private javax.swing.JTextField eanBookTextField2;
    private javax.swing.JLabel eanMusicLabel;
    private javax.swing.JTextField eanMusicTextField;
    private javax.swing.JLabel genreBookLabel;
    private javax.swing.JLabel genreBookLabel2;
    private javax.swing.JTextField genreBookTextField;
    private javax.swing.JTextField genreBookTextField2;
    private javax.swing.JLabel genreMusicLabel;
    private javax.swing.JTextField genreMusicTextField;
    private javax.swing.JPanel headPanel;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton hideBookButton;
    private javax.swing.JButton hideMusicButton;
    private javax.swing.JButton hideVideoButton;
    private javax.swing.JLabel isbnBookLabel;
    private javax.swing.JLabel isbnBookLabel2;
    private javax.swing.JTextField isbnBookTextField;
    private javax.swing.JTextField isbnBookTextField2;
    private javax.swing.JLabel isbnMusicLabel;
    private javax.swing.JTextField isbnMusicTextField;
    private javax.swing.JPanel itemBookPanel;
    private javax.swing.JPanel itemBookPanel2;
    private javax.swing.JScrollPane itemBookScrollPane;
    private javax.swing.JPanel itemMusicPanel;
    private javax.swing.JScrollPane itemMusicScrollPane;
    private javax.swing.JScrollPane itemVideoScrollPane;
    private javax.swing.JLabel languageBookLabel;
    private javax.swing.JLabel languageBookLabel2;
    private javax.swing.JTextField languageBookTextField;
    private javax.swing.JTextField languageBookTextField2;
    private javax.swing.JLabel languageMusicLabel;
    private javax.swing.JTextField languageMusicTextField;
    private javax.swing.JComboBox languagesComboBox;
    private javax.swing.JCheckBox lentBookCheckBox;
    private javax.swing.JCheckBox lentBookCheckBox2;
    private javax.swing.JLabel lentBookLabel;
    private javax.swing.JLabel lentBookLabel2;
    private javax.swing.JLabel lentDateBookLabel;
    private javax.swing.JLabel lentDateBookLabel2;
    private javax.swing.JLabel lentDateMusicLabel;
    private javax.swing.JComboBox lentDayBookComboBox;
    private javax.swing.JComboBox lentDayBookComboBox2;
    private javax.swing.JComboBox lentDayMusicComboBox;
    private javax.swing.JComboBox lentMonthBookComboBox;
    private javax.swing.JComboBox lentMonthBookComboBox2;
    private javax.swing.JComboBox lentMonthMusicComboBox;
    private javax.swing.JCheckBox lentMusicCheckBox;
    private javax.swing.JLabel lentMusicLabel;
    private javax.swing.JLabel lentToBookLabel;
    private javax.swing.JLabel lentToBookLabel2;
    private javax.swing.JTextField lentToBookTextField;
    private javax.swing.JTextField lentToBookTextField2;
    private javax.swing.JLabel lentToMusicLabel;
    private javax.swing.JTextField lentToMusicTextField;
    private javax.swing.JLabel lentUntilDateBookLabel;
    private javax.swing.JLabel lentUntilDateBookLabel2;
    private javax.swing.JLabel lentUntilDateMusicLabel;
    private javax.swing.JComboBox lentUntilDayBookComboBox;
    private javax.swing.JComboBox lentUntilDayBookComboBox2;
    private javax.swing.JComboBox lentUntilDayMusicComboBox;
    private javax.swing.JComboBox lentUntilMonthBookComboBox;
    private javax.swing.JComboBox lentUntilMonthBookComboBox2;
    private javax.swing.JComboBox lentUntilMonthMusicComboBox;
    private javax.swing.JComboBox lentUntilYearBookComboBox;
    private javax.swing.JComboBox lentUntilYearBookComboBox2;
    private javax.swing.JComboBox lentUntilYearMusicComboBox;
    private javax.swing.JComboBox lentYearBookComboBox;
    private javax.swing.JComboBox lentYearBookComboBox2;
    private javax.swing.JComboBox lentYearMusicComboBox;
    private javax.swing.JLabel locationBookLabel;
    private javax.swing.JLabel locationBookLabel2;
    private javax.swing.JTextField locationBookTextField;
    private javax.swing.JTextField locationBookTextField2;
    private javax.swing.JLabel locationMusicLabel;
    private javax.swing.JTextField locationMusicTextField;
    private javax.swing.JPanel musicCardPanel;
    private javax.swing.JPanel musicPanel;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JScrollPane overviewScrollPane;
    private javax.swing.JTable overviewTable;
    private javax.swing.JLabel ratingBookLabel;
    private javax.swing.JLabel ratingBookLabel2;
    private javax.swing.JLabel ratingMusicLabel;
    private javax.swing.JRadioButton ratingNoneBookRadioButton;
    private javax.swing.JRadioButton ratingNoneBookRadioButton2;
    private javax.swing.JRadioButton ratingNoneMusicRadioButton;
    private javax.swing.JRadioButton ratingOneBookRadioButton;
    private javax.swing.JRadioButton ratingOneBookRadioButton2;
    private javax.swing.JRadioButton ratingOneMusicRadioButton;
    private javax.swing.JRadioButton ratingThreeBookRadioButton;
    private javax.swing.JRadioButton ratingThreeBookRadioButton2;
    private javax.swing.JRadioButton ratingThreeMusicRadioButton;
    private javax.swing.JRadioButton ratingTwoBookRadioButton;
    private javax.swing.JRadioButton ratingTwoBookRadioButton2;
    private javax.swing.JRadioButton ratingTwoMusicRadioButton;
    private javax.swing.JComboBox releaseYearBookComboBox;
    private javax.swing.JComboBox releaseYearBookComboBox2;
    private javax.swing.JLabel releaseYearBookLabel;
    private javax.swing.JLabel releaseYearBookLabel2;
    private javax.swing.JComboBox releaseYearMusicComboBox;
    private javax.swing.JLabel releaseYearMusicLabel;
    private javax.swing.JButton restoreItemButton;
    private javax.swing.JScrollPane restoreItemsScrollpane;
    private javax.swing.JPanel restorePanel;
    private javax.swing.JButton saveBookButton;
    private javax.swing.JButton saveButton2;
    private javax.swing.JButton saveMusicButton;
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
    private javax.swing.JLabel titleBookLabel2;
    private javax.swing.JTextField titleBookTextField;
    private javax.swing.JTextField titleBookTextField2;
    private javax.swing.JLabel titleMusicLabel;
    private javax.swing.JTextField titleMusicTextField;
    private javax.swing.JPanel videoCardPanel;
    private javax.swing.JPanel videoPanel;
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


}
