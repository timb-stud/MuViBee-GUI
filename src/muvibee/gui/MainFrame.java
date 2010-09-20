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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField7 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField8 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
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

        jLabel1.setText("Titel");

        jLabel2.setText("Cover");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 150));

        jLabel3.setText("Autor");

        jLabel4.setText("Sprache");

        jLabel5.setText("ISBN");

        jLabel6.setText("EAN");

        jLabel7.setText("Genre");

        jLabel8.setText("Erscheinungsjahr");

        jLabel9.setText("Standort");

        jLabel10.setText("Verliehen");

        jLabel11.setText("Verliehen an");

        jLabel12.setText("Verliehen am");

        jLabel13.setText("Verliehen bis");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tag" }));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monat" }));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jahr" }));

        jLabel14.setText("Bewertung");

        jRadioButton1.setText("1");

        jRadioButton2.setText("2");

        jRadioButton3.setText("3");

        jRadioButton4.setText("keine");

        jButton1.setText("Löschen");

        jButton2.setText("Speichern");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel15.setText("Beschreibung");

        jLabel16.setText("Kommentar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jRadioButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRadioButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRadioButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRadioButton4))
                                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap(436, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addContainerGap(451, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(11, 11, 11)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemBookScrollPane.setViewportView(jPanel1);

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
    private javax.swing.JPanel bookCardPanel;
    private javax.swing.JPanel bookPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton deleteItemButton;
    private javax.swing.JButton deleteSearchButton;
    private javax.swing.JPanel headPanel;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton hideBookButton;
    private javax.swing.JButton hideMusicButton;
    private javax.swing.JButton hideVideoButton;
    private javax.swing.JScrollPane itemBookScrollPane;
    private javax.swing.JScrollPane itemMusicScrollPane;
    private javax.swing.JScrollPane itemVideoScrollPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JComboBox languagesComboBox;
    private javax.swing.JPanel musicCardPanel;
    private javax.swing.JPanel musicPanel;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JScrollPane overviewScrollPane;
    private javax.swing.JTable overviewTable;
    private javax.swing.JButton restoreItemButton;
    private javax.swing.JScrollPane restoreItemsScrollpane;
    private javax.swing.JPanel restorePanel;
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
