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
import muvibee.MuViBee;
import muvibee.actionlistener.ResetSearchActionListener;
import muvibee.actionlistener.LanguageActionListener;
import muvibee.actionlistener.SearchActionListener;
import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.utils.ResizeImageIcon;
import util.coversList.CoverList;
import util.coverDetailsList.*;
import util.deleteditemlist.DeletedItemsList;
import util.detailsList.DetailsList;



/**
 *
 * @author bline
 */
public class MainFrame extends javax.swing.JFrame {

    private void createCoverList(MuViBee mvb){
        CoverList coverListBook = new CoverList(mvb);
        mvb.getBookList().addObserver(coverListBook);
        CoverList coverListMusic = new CoverList(mvb);
        mvb.getMusicList().addObserver(coverListMusic);
        CoverList coverListVideo = new CoverList(mvb);
        mvb.getVideoList().addObserver(coverListVideo);

        coverListBookScrollPane.setViewportView(coverListBook);
        coverListMusicScrollPane.setViewportView(coverListMusic);
        coverListVideoScrollPane.setViewportView(coverListVideo);
    }

    private void createDetailsList(MuViBee mvb){
        DetailsList detailsListBook = new DetailsList(mvb);
        mvb.getBookList().addObserver(detailsListBook);
        DetailsList detailsListMusic = new DetailsList(mvb);
        mvb.getMusicList().addObserver(detailsListMusic);
        DetailsList detailsListVideo = new DetailsList(mvb);
        mvb.getVideoList().addObserver(detailsListVideo);

        detailsListBookScrollPane.setViewportView(detailsListBook);
        detailsListMusicScrollPane.setViewportView(detailsListMusic);
        detailsListVideoScrollPane.setViewportView(detailsListVideo);
    }


    private void createCoverDetailsList(MuViBee mvb){
        CoverDetailsList coverDetailsBookList = new CoverDetailsList(mvb);
        mvb.getBookList().addObserver(coverDetailsBookList);
        CoverDetailsList coverDetailsMusicList = new CoverDetailsList(mvb);
        mvb.getMusicList().addObserver(coverDetailsMusicList);
        CoverDetailsList coverDetailsVideoList = new CoverDetailsList(mvb);
        mvb.getVideoList().addObserver(coverDetailsVideoList);

        coverDetailsListBookScrollPane.setViewportView(coverDetailsBookList);
        coverDetailsListMusicScrollPane.setViewportView(coverDetailsMusicList);
        coverDetailsListVideoScrollPane.setViewportView(coverDetailsVideoList);
    }


    /** Creates new form MainFrame */
    public MainFrame(MuViBee mvb) {
        initComponents();
        coverDetailsListBookScrollPane = new JScrollPane();
        detailsListBookScrollPane = new JScrollPane();
        coverListBookScrollPane = new JScrollPane();
        coverDetailsListMusicScrollPane = new JScrollPane();
        detailsListMusicScrollPane = new JScrollPane();
        coverListMusicScrollPane = new JScrollPane();
        coverDetailsListVideoScrollPane = new JScrollPane();
        detailsListVideoScrollPane = new JScrollPane();
        coverListVideoScrollPane = new JScrollPane();

        coverDetailsListBookScrollPane.setName("cover details");
        detailsListBookScrollPane.setName("details");
        coverListBookScrollPane.setName("cover");
        
        coverDetailsListMusicScrollPane.setName("cover details");
        detailsListMusicScrollPane.setName("details");
        coverListMusicScrollPane.setName("cover");
        
        coverDetailsListVideoScrollPane.setName("cover details");
        detailsListVideoScrollPane.setName("details");
        coverListVideoScrollPane.setName("cover");

        bookCardPanel.add(coverDetailsListBookScrollPane, "cover details");
        bookCardPanel.add(detailsListBookScrollPane, "details");
        bookCardPanel.add(coverListBookScrollPane, "cover");

        musicCardPanel.add(coverDetailsListMusicScrollPane, "cover details");
        musicCardPanel.add(detailsListMusicScrollPane, "details");
        musicCardPanel.add(coverListMusicScrollPane, "cover");

        videoCardPanel.add(coverDetailsListVideoScrollPane, "cover details");
        videoCardPanel.add(detailsListVideoScrollPane, "details");
        videoCardPanel.add(coverListVideoScrollPane, "cover");

        createDetailsList(mvb);
        createCoverDetailsList(mvb);
        createCoverList(mvb);

        itemBookScrollPane.setVisible(false);
        itemMusicScrollPane.setVisible(false);
        itemVideoScrollPane.setVisible(false);

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
        viewBookComboBox.setModel(cbBooksModel);
        

        DefaultComboBoxModel cbMusicModel = new DefaultComboBoxModel();
        cbMusicModel.addElement(coverDetailsListMusicScrollPane);
        cbMusicModel.addElement(coverListMusicScrollPane);
        cbMusicModel.addElement(detailsListMusicScrollPane);
        viewMusicComboBox.setModel(cbMusicModel);
        

        DefaultComboBoxModel cbVideoModel = new DefaultComboBoxModel();
        cbVideoModel.addElement(coverDetailsListVideoScrollPane);
        cbVideoModel.addElement(coverListVideoScrollPane);
        cbVideoModel.addElement(detailsListVideoScrollPane);
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

        searchButton.addActionListener(new SearchActionListener(mvb));
        
        deleteSearchButton.addActionListener(new ResetSearchActionListener(mvb));
        
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
	if(cover != null)
	    coverBookLabel.setIcon(ResizeImageIcon.resizeIcon(140, 160, cover));
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
	if(cover != null)
	    coverMusicLabel.setIcon(new ImageIcon(cover));
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
	if(cover != null)
	    coverVideoLabel.setIcon(new ImageIcon(cover));
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
            book.setCover(null);
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
	
    }

    
    public void bookItemSetVisible(boolean b) {
        itemBookScrollPane.setVisible(b);
        itemBookScrollPane.getParent().validate();
    }

    public void musicItemSetVisible(boolean b) {
        itemMusicScrollPane.setVisible(b);
        itemMusicScrollPane.getParent().validate();
    }


   public void videoItemSetVisible(boolean b) {
        itemVideoScrollPane.setVisible(b);
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
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        bookPanel = new javax.swing.JPanel();
        viewBookPanel = new javax.swing.JPanel();
        viewBookComboBox = new javax.swing.JComboBox();
        addBookButton = new javax.swing.JButton();
        bookCardPanel = new javax.swing.JPanel();
        itemBookScrollPane = new javax.swing.JScrollPane();
        itemBookPanel = new javax.swing.JPanel();
        coverBookLabel = new javax.swing.JLabel();
        titleTextBookLabel = new javax.swing.JLabel();
        authorTextBookLabel = new javax.swing.JLabel();
        languageTextBookLabel = new javax.swing.JLabel();
        isbnTextBookLabel = new javax.swing.JLabel();
        genreTextBookLabel = new javax.swing.JLabel();
        locationTextBookLabel = new javax.swing.JLabel();
        eanTextBookLabel = new javax.swing.JLabel();
        releaseYearTextBookLabel = new javax.swing.JLabel();
        borrowedToTextBookLabel = new javax.swing.JLabel();
        borrowDateTextBookLabel = new javax.swing.JLabel();
        borrowedUntilTextBookLabel = new javax.swing.JLabel();
        ratingBookPanel = new javax.swing.JPanel();
        oneRatingpointBookRadioButton = new javax.swing.JRadioButton();
        twoRatingpointsBookRadioButton = new javax.swing.JRadioButton();
        threeRatingpointsBookRadioButton = new javax.swing.JRadioButton();
        descriptionBookScrollPane = new javax.swing.JScrollPane();
        descriptionBookTextArea = new javax.swing.JTextArea();
        annotationBookScrollPane = new javax.swing.JScrollPane();
        annotationBookTextArea = new javax.swing.JTextArea();
        editBookButton = new javax.swing.JButton();
        deleteBookButton = new javax.swing.JButton();
        titleBookTextField = new javax.swing.JTextField();
        authorBookTextField = new javax.swing.JTextField();
        languageBookTextField = new javax.swing.JTextField();
        isbnBookTextField = new javax.swing.JTextField();
        eanBookTextField = new javax.swing.JTextField();
        genreBookTextField = new javax.swing.JTextField();
        releaseYearBookTextField = new javax.swing.JTextField();
        locationBookTextField = new javax.swing.JTextField();
        borrowedToBookTextField = new javax.swing.JTextField();
        borrowDayBookComboBox = new javax.swing.JComboBox();
        borrowedUntilDayBookComboBox = new javax.swing.JComboBox();
        borrowMonthBookComboBox = new javax.swing.JComboBox();
        borrowedUntilMonthBookComboBox = new javax.swing.JComboBox();
        borrowYearBookTextField = new javax.swing.JTextField();
        borrowedUntilYearBookTextField = new javax.swing.JTextField();
        musicPanel = new javax.swing.JPanel();
        viewMusicPanel = new javax.swing.JPanel();
        viewMusicComboBox = new javax.swing.JComboBox();
        addMusicButton = new javax.swing.JButton();
        musicCardPanel = new javax.swing.JPanel();
        itemMusicScrollPane = new javax.swing.JScrollPane();
        itemMusicPanel = new javax.swing.JPanel();
        coverMusicLabel = new javax.swing.JLabel();
        titleMusicLabel = new javax.swing.JLabel();
        artistMusicLabel = new javax.swing.JLabel();
        typeMusicLabel = new javax.swing.JLabel();
        formatMusicLabel = new javax.swing.JLabel();
        genreMusicLabel = new javax.swing.JLabel();
        locationMusicLabel = new javax.swing.JLabel();
        eanMusicLabel = new javax.swing.JLabel();
        releaseYearMusicLabel = new javax.swing.JLabel();
        borrowedToMusicLabel = new javax.swing.JLabel();
        borrowDateMusicLabel = new javax.swing.JLabel();
        borrowedUntilMusicLabel = new javax.swing.JLabel();
        ratingMusicPanel = new javax.swing.JPanel();
        oneRatingpointMusicRadioButton = new javax.swing.JRadioButton();
        twoRatingpointsMusicRadioButton = new javax.swing.JRadioButton();
        threeRatingpointsMusicRadioButton = new javax.swing.JRadioButton();
        descriptionMusicScrollPane = new javax.swing.JScrollPane();
        descriptionMusicTextArea = new javax.swing.JTextArea();
        annotationMusicScrollPane = new javax.swing.JScrollPane();
        annotationMusicTextArea = new javax.swing.JTextArea();
        editMusicButton = new javax.swing.JButton();
        deleteMusicButton = new javax.swing.JButton();
        titleMusicTextField = new javax.swing.JTextField();
        artistMusicTextField = new javax.swing.JTextField();
        eanMusicTextField = new javax.swing.JTextField();
        genreMusicTextField = new javax.swing.JTextField();
        releaseYearMusicTextField = new javax.swing.JTextField();
        locationMusicTextField = new javax.swing.JTextField();
        borrowedToMusicTextField = new javax.swing.JTextField();
        borrowDayMusicComboBox = new javax.swing.JComboBox();
        borrowedUntilDayMusicComboBox = new javax.swing.JComboBox();
        borrowMonthMusicComboBox = new javax.swing.JComboBox();
        borrowedUntilMonthMusicComboBox = new javax.swing.JComboBox();
        borrowYearMusicTextField = new javax.swing.JTextField();
        borrowedUntilYearMusicTextField = new javax.swing.JTextField();
        typeMusicComboBox = new javax.swing.JComboBox();
        formatMusicComboBox = new javax.swing.JComboBox();
        videoPanel = new javax.swing.JPanel();
        viewVideoPanel = new javax.swing.JPanel();
        viewVideoComboBox = new javax.swing.JComboBox();
        addVideoButton = new javax.swing.JButton();
        videoCardPanel = new javax.swing.JPanel();
        itemVideoScrollPane = new javax.swing.JScrollPane();
        itemVideoPanel = new javax.swing.JPanel();
        coverVideoLabel = new javax.swing.JLabel();
        titleVideoLabel = new javax.swing.JLabel();
        authorVideoLabel = new javax.swing.JLabel();
        languageVideoLabel = new javax.swing.JLabel();
        isbnVideoLabel = new javax.swing.JLabel();
        genreVideoLabel = new javax.swing.JLabel();
        locationVideoLabel = new javax.swing.JLabel();
        eanVideoLabel = new javax.swing.JLabel();
        releaseYearVideoLabel = new javax.swing.JLabel();
        borrowedToVideoLabel = new javax.swing.JLabel();
        borrowDateVideoLabel = new javax.swing.JLabel();
        borrowedUntilVideoLabel = new javax.swing.JLabel();
        ratingVideoPanel = new javax.swing.JPanel();
        oneRatingpointVideoRadioButton = new javax.swing.JRadioButton();
        twoRatingpointsVideoRadioButton = new javax.swing.JRadioButton();
        threeRatingpointsVideoRadioButton = new javax.swing.JRadioButton();
        descriptionVideoScrollPane = new javax.swing.JScrollPane();
        descriptionVideoTextArea = new javax.swing.JTextArea();
        annotationVideoScrollPane = new javax.swing.JScrollPane();
        annotationVideoTextArea = new javax.swing.JTextArea();
        editVideoButton = new javax.swing.JButton();
        deleteVideoButton = new javax.swing.JButton();
        titleVideoTextField = new javax.swing.JTextField();
        directorVideoTextField = new javax.swing.JTextField();
        actorsVideoTextField = new javax.swing.JTextField();
        eanVideoTextField = new javax.swing.JTextField();
        genreVideoTextField = new javax.swing.JTextField();
        releaseYearVideoTextField = new javax.swing.JTextField();
        locationVideoTextField = new javax.swing.JTextField();
        borrowedToVideoTextField = new javax.swing.JTextField();
        borrowDayVideoComboBox = new javax.swing.JComboBox();
        borrowedUntilDayVideoComboBox = new javax.swing.JComboBox();
        borrowMonthVideoComboBox = new javax.swing.JComboBox();
        borrowedUntilMonthVideoComboBox = new javax.swing.JComboBox();
        borrowYearVideoTextField = new javax.swing.JTextField();
        borrowedUntilYearVideoTextField = new javax.swing.JTextField();
        formatVideoComboBox = new javax.swing.JComboBox();
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

        advancedSearchButton.setText("Erweiterte Suche");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 464, Short.MAX_VALUE)
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
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        tabbedPane.setName("add video button"); // NOI18N

        jLabel35.setText("jLabel35");

        jLabel36.setText("jLabel36");

        jLabel37.setText("jLabel37");

        jLabel38.setText("jLabel38");

        jLabel39.setText("jLabel39");

        jLabel40.setText("jLabel40");

        jLabel41.setText("jLabel41");

        jLabel42.setText("jLabel42");

        jLabel43.setText("jLabel43");

        jLabel44.setText("jLabel44");

        jLabel45.setText("jLabel45");

        jLabel46.setText("jLabel46");

        javax.swing.GroupLayout overviewPanelLayout = new javax.swing.GroupLayout(overviewPanel);
        overviewPanel.setLayout(overviewPanelLayout);
        overviewPanelLayout.setHorizontalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36)))
                    .addComponent(jLabel38)
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39)))
                    .addComponent(jLabel41)
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel42)))
                    .addComponent(jLabel44)
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel45))))
                .addContainerGap(1037, Short.MAX_VALUE))
        );
        overviewPanelLayout.setVerticalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Übersicht", overviewPanel);

        viewBookPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        bookCardPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        bookCardPanel.setPreferredSize(new java.awt.Dimension(218, 361));
        bookCardPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout viewBookPanelLayout = new javax.swing.GroupLayout(viewBookPanel);
        viewBookPanel.setLayout(viewBookPanelLayout);
        viewBookPanelLayout.setHorizontalGroup(
            viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bookCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(viewBookPanelLayout.createSequentialGroup()
                        .addComponent(viewBookComboBox, 0, 385, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBookButton)))
                .addContainerGap())
        );
        viewBookPanelLayout.setVerticalGroup(
            viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addBookButton)
                    .addComponent(viewBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemBookPanel.setPreferredSize(new java.awt.Dimension(518, 837));

        titleTextBookLabel.setText("Titel");

        authorTextBookLabel.setText("Autor");

        languageTextBookLabel.setText("Sprache");

        isbnTextBookLabel.setText("ISBN");

        genreTextBookLabel.setText("Genre");

        locationTextBookLabel.setText("Standort");

        eanTextBookLabel.setText("EAN");

        releaseYearTextBookLabel.setText("Erscheinungsjahr");

        borrowedToTextBookLabel.setText("Verliehen an");

        borrowDateTextBookLabel.setText("Verleihdatum");

        borrowedUntilTextBookLabel.setText("Verliehen bis");

        ratingBookPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bewertung"));

        buttonGroup1.add(oneRatingpointBookRadioButton);
        oneRatingpointBookRadioButton.setText("1");
        oneRatingpointBookRadioButton.setActionCommand("jRadioButton1");

        buttonGroup1.add(twoRatingpointsBookRadioButton);
        twoRatingpointsBookRadioButton.setText("2");

        buttonGroup1.add(threeRatingpointsBookRadioButton);
        threeRatingpointsBookRadioButton.setText("3");

        javax.swing.GroupLayout ratingBookPanelLayout = new javax.swing.GroupLayout(ratingBookPanel);
        ratingBookPanel.setLayout(ratingBookPanelLayout);
        ratingBookPanelLayout.setHorizontalGroup(
            ratingBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oneRatingpointBookRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(twoRatingpointsBookRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(threeRatingpointsBookRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ratingBookPanelLayout.setVerticalGroup(
            ratingBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ratingBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneRatingpointBookRadioButton)
                    .addComponent(twoRatingpointsBookRadioButton)
                    .addComponent(threeRatingpointsBookRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        descriptionBookScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Beschreibung"));

        descriptionBookTextArea.setColumns(20);
        descriptionBookTextArea.setRows(5);
        descriptionBookScrollPane.setViewportView(descriptionBookTextArea);

        annotationBookScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Kommentar"));

        annotationBookTextArea.setColumns(20);
        annotationBookTextArea.setRows(5);
        annotationBookScrollPane.setViewportView(annotationBookTextArea);

        editBookButton.setText("Speichern");
        editBookButton.setName("save book button"); // NOI18N

        deleteBookButton.setText("Löschen");
        deleteBookButton.setName("deleteBook"); // NOI18N

        titleBookTextField.setText("jTextField7");

        authorBookTextField.setText("jTextField6");

        languageBookTextField.setText("jTextField5");

        isbnBookTextField.setText("jTextField4");

        eanBookTextField.setText("jTextField3");

        genreBookTextField.setText("jTextField2");

        releaseYearBookTextField.setText("jTextField1");

        locationBookTextField.setText("jTextField8");

        borrowedToBookTextField.setText("jTextField9");

        borrowDayBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        borrowDayBookComboBox.setAutoscrolls(true);
        borrowDayBookComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        borrowedUntilDayBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        borrowMonthBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        borrowedUntilMonthBookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        borrowYearBookTextField.setText("2010");

        borrowedUntilYearBookTextField.setText("jTextField2");

        javax.swing.GroupLayout itemBookPanelLayout = new javax.swing.GroupLayout(itemBookPanel);
        itemBookPanel.setLayout(itemBookPanelLayout);
        itemBookPanelLayout.setHorizontalGroup(
            itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(coverBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borrowedToTextBookLabel)
                            .addComponent(borrowDateTextBookLabel)
                            .addComponent(locationTextBookLabel)
                            .addComponent(releaseYearTextBookLabel)
                            .addComponent(borrowedUntilTextBookLabel)
                            .addComponent(genreTextBookLabel)
                            .addComponent(eanTextBookLabel)
                            .addComponent(isbnTextBookLabel)
                            .addComponent(languageTextBookLabel)
                            .addComponent(authorTextBookLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(authorBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(languageBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(isbnBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(eanBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(genreBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(releaseYearBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(locationBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(borrowedToBookTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanelLayout.createSequentialGroup()
                                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                                        .addComponent(borrowDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowMonthBookComboBox, 0, 166, Short.MAX_VALUE))
                                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                                        .addComponent(borrowedUntilDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowedUntilMonthBookComboBox, 0, 166, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrowedUntilYearBookTextField, 0, 0, Short.MAX_VALUE)
                                    .addComponent(borrowYearBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))))
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(titleTextBookLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(ratingBookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addComponent(editBookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBookButton))
                    .addComponent(descriptionBookScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(annotationBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap())
        );
        itemBookPanelLayout.setVerticalGroup(
            itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleTextBookLabel)
                    .addComponent(titleBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(authorTextBookLabel)
                            .addComponent(authorBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(languageTextBookLabel)
                            .addComponent(languageBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isbnTextBookLabel)
                            .addComponent(isbnBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eanTextBookLabel)
                            .addComponent(eanBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreTextBookLabel)
                            .addComponent(genreBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(releaseYearTextBookLabel)
                            .addComponent(releaseYearBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(locationTextBookLabel)
                            .addComponent(locationBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowedToTextBookLabel)
                            .addComponent(borrowedToBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowDateTextBookLabel)
                            .addComponent(borrowDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowMonthBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowYearBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowedUntilTextBookLabel)
                            .addComponent(borrowedUntilDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowedUntilMonthBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowedUntilYearBookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteBookButton)
                        .addComponent(editBookButton))
                    .addComponent(ratingBookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descriptionBookScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annotationBookScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemBookScrollPane.setViewportView(itemBookPanel);

        javax.swing.GroupLayout bookPanelLayout = new javax.swing.GroupLayout(bookPanel);
        bookPanel.setLayout(bookPanelLayout);
        bookPanelLayout.setHorizontalGroup(
            bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(itemBookScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bookPanelLayout.setVerticalGroup(
            bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viewBookPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemBookScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Bücher", bookPanel);

        viewMusicPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        viewMusicPanel.setMinimumSize(new java.awt.Dimension(350, 0));

        viewMusicComboBox.setName("ViewComboBox"); // NOI18N

        addMusicButton.setText("Hinzufügen");
        addMusicButton.setName("add music button"); // NOI18N

        musicCardPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout viewMusicPanelLayout = new javax.swing.GroupLayout(viewMusicPanel);
        viewMusicPanel.setLayout(viewMusicPanelLayout);
        viewMusicPanelLayout.setHorizontalGroup(
            viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(musicCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(viewMusicPanelLayout.createSequentialGroup()
                        .addComponent(viewMusicComboBox, 0, 385, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMusicButton)))
                .addContainerGap())
        );
        viewMusicPanelLayout.setVerticalGroup(
            viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(musicCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addMusicButton)
                    .addComponent(viewMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemMusicPanel.setPreferredSize(new java.awt.Dimension(518, 837));

        titleMusicLabel.setText("Titel");

        artistMusicLabel.setText("Interpret");

        typeMusicLabel.setText("Typ");

        formatMusicLabel.setText("Format");

        genreMusicLabel.setText("Genre");

        locationMusicLabel.setText("Standort");

        eanMusicLabel.setText("EAN");

        releaseYearMusicLabel.setText("Erscheinungsjahr");

        borrowedToMusicLabel.setText("Verliehen an");

        borrowDateMusicLabel.setText("Verleihdatum");

        borrowedUntilMusicLabel.setText("Verliehen bis");

        ratingMusicPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bewertung"));

        buttonGroup1.add(oneRatingpointMusicRadioButton);
        oneRatingpointMusicRadioButton.setText("1");
        oneRatingpointMusicRadioButton.setActionCommand("jRadioButton1");

        buttonGroup1.add(twoRatingpointsMusicRadioButton);
        twoRatingpointsMusicRadioButton.setText("2");

        buttonGroup1.add(threeRatingpointsMusicRadioButton);
        threeRatingpointsMusicRadioButton.setText("3");

        javax.swing.GroupLayout ratingMusicPanelLayout = new javax.swing.GroupLayout(ratingMusicPanel);
        ratingMusicPanel.setLayout(ratingMusicPanelLayout);
        ratingMusicPanelLayout.setHorizontalGroup(
            ratingMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oneRatingpointMusicRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(twoRatingpointsMusicRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(threeRatingpointsMusicRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ratingMusicPanelLayout.setVerticalGroup(
            ratingMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ratingMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneRatingpointMusicRadioButton)
                    .addComponent(twoRatingpointsMusicRadioButton)
                    .addComponent(threeRatingpointsMusicRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        descriptionMusicScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Beschreibung"));

        descriptionMusicTextArea.setColumns(20);
        descriptionMusicTextArea.setRows(5);
        descriptionMusicScrollPane.setViewportView(descriptionMusicTextArea);

        annotationMusicScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Kommentar"));

        annotationMusicTextArea.setColumns(20);
        annotationMusicTextArea.setRows(5);
        annotationMusicScrollPane.setViewportView(annotationMusicTextArea);

        editMusicButton.setText("Speichern");
        editMusicButton.setName("save music button"); // NOI18N

        deleteMusicButton.setText("Löschen");
        deleteMusicButton.setName("deleteMusic"); // NOI18N

        titleMusicTextField.setText("jTextField7");

        artistMusicTextField.setText("jTextField6");

        eanMusicTextField.setText("jTextField3");

        genreMusicTextField.setText("jTextField2");

        releaseYearMusicTextField.setText("jTextField1");

        locationMusicTextField.setText("jTextField8");

        borrowedToMusicTextField.setText("jTextField9");

        borrowDayMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        borrowDayMusicComboBox.setAutoscrolls(true);
        borrowDayMusicComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        borrowedUntilDayMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        borrowMonthMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        borrowedUntilMonthMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        borrowYearMusicTextField.setText("2010");

        borrowedUntilYearMusicTextField.setText("2010");

        typeMusicComboBox.setEditable(true);
        typeMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typeMusicComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeMusicComboBoxActionPerformed(evt);
            }
        });

        formatMusicComboBox.setEditable(true);
        formatMusicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout itemMusicPanelLayout = new javax.swing.GroupLayout(itemMusicPanel);
        itemMusicPanel.setLayout(itemMusicPanelLayout);
        itemMusicPanelLayout.setHorizontalGroup(
            itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(coverMusicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borrowedToMusicLabel)
                            .addComponent(borrowDateMusicLabel)
                            .addComponent(locationMusicLabel)
                            .addComponent(releaseYearMusicLabel)
                            .addComponent(borrowedUntilMusicLabel)
                            .addComponent(genreMusicLabel)
                            .addComponent(eanMusicLabel)
                            .addComponent(formatMusicLabel)
                            .addComponent(typeMusicLabel)
                            .addComponent(artistMusicLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artistMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(eanMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(genreMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(releaseYearMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(locationMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(borrowedToMusicTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemMusicPanelLayout.createSequentialGroup()
                                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                        .addComponent(borrowDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowMonthMusicComboBox, 0, 166, Short.MAX_VALUE))
                                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                        .addComponent(borrowedUntilDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowedUntilMonthMusicComboBox, 0, 166, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrowedUntilYearMusicTextField, 0, 0, Short.MAX_VALUE)
                                    .addComponent(borrowYearMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
                            .addComponent(typeMusicComboBox, 0, 266, Short.MAX_VALUE)
                            .addComponent(formatMusicComboBox, 0, 266, Short.MAX_VALUE)))
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(titleMusicLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(ratingMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addComponent(editMusicButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMusicButton))
                    .addComponent(descriptionMusicScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(annotationMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap())
        );
        itemMusicPanelLayout.setVerticalGroup(
            itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleMusicLabel)
                    .addComponent(titleMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverMusicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(artistMusicLabel)
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
                            .addComponent(releaseYearMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(locationMusicLabel)
                            .addComponent(locationMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowedToMusicLabel)
                            .addComponent(borrowedToMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowDateMusicLabel)
                            .addComponent(borrowDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowMonthMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowYearMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowedUntilMusicLabel)
                            .addComponent(borrowedUntilDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowedUntilMonthMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowedUntilYearMusicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteMusicButton)
                        .addComponent(editMusicButton))
                    .addComponent(ratingMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descriptionMusicScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annotationMusicScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemMusicScrollPane.setViewportView(itemMusicPanel);

        javax.swing.GroupLayout musicPanelLayout = new javax.swing.GroupLayout(musicPanel);
        musicPanel.setLayout(musicPanelLayout);
        musicPanelLayout.setHorizontalGroup(
            musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewMusicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(itemMusicScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        musicPanelLayout.setVerticalGroup(
            musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewMusicPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemMusicScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Musik", musicPanel);

        viewVideoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        viewVideoPanel.setMinimumSize(new java.awt.Dimension(350, 0));

        addVideoButton.setText("Hinzufügen");
        addVideoButton.setName("add video button"); // NOI18N

        videoCardPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout viewVideoPanelLayout = new javax.swing.GroupLayout(viewVideoPanel);
        viewVideoPanel.setLayout(viewVideoPanelLayout);
        viewVideoPanelLayout.setHorizontalGroup(
            viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(videoCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(viewVideoPanelLayout.createSequentialGroup()
                        .addComponent(viewVideoComboBox, 0, 385, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addVideoButton)))
                .addContainerGap())
        );
        viewVideoPanelLayout.setVerticalGroup(
            viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(videoCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addVideoButton)
                    .addComponent(viewVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemVideoPanel.setPreferredSize(new java.awt.Dimension(518, 837));

        titleVideoLabel.setText("Titel");

        authorVideoLabel.setText("Regisseur");

        languageVideoLabel.setText("Schauspieler");

        isbnVideoLabel.setText("Format");

        genreVideoLabel.setText("Genre");

        locationVideoLabel.setText("Standort");

        eanVideoLabel.setText("EAN");

        releaseYearVideoLabel.setText("Erscheinungsjahr");

        borrowedToVideoLabel.setText("Verliehen an");

        borrowDateVideoLabel.setText("Verleihdatum");

        borrowedUntilVideoLabel.setText("Verliehen bis");

        ratingVideoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bewertung"));

        buttonGroup1.add(oneRatingpointVideoRadioButton);
        oneRatingpointVideoRadioButton.setText("1");
        oneRatingpointVideoRadioButton.setActionCommand("jRadioButton1");

        buttonGroup1.add(twoRatingpointsVideoRadioButton);
        twoRatingpointsVideoRadioButton.setText("2");

        buttonGroup1.add(threeRatingpointsVideoRadioButton);
        threeRatingpointsVideoRadioButton.setText("3");

        javax.swing.GroupLayout ratingVideoPanelLayout = new javax.swing.GroupLayout(ratingVideoPanel);
        ratingVideoPanel.setLayout(ratingVideoPanelLayout);
        ratingVideoPanelLayout.setHorizontalGroup(
            ratingVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oneRatingpointVideoRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(twoRatingpointsVideoRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(threeRatingpointsVideoRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ratingVideoPanelLayout.setVerticalGroup(
            ratingVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ratingVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneRatingpointVideoRadioButton)
                    .addComponent(twoRatingpointsVideoRadioButton)
                    .addComponent(threeRatingpointsVideoRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        descriptionVideoScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Beschreibung"));

        descriptionVideoTextArea.setColumns(20);
        descriptionVideoTextArea.setRows(5);
        descriptionVideoScrollPane.setViewportView(descriptionVideoTextArea);

        annotationVideoScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Kommentar"));

        annotationVideoTextArea.setColumns(20);
        annotationVideoTextArea.setRows(5);
        annotationVideoScrollPane.setViewportView(annotationVideoTextArea);

        editVideoButton.setText("Speichern");
        editVideoButton.setName("save video button"); // NOI18N

        deleteVideoButton.setText("Löschen");
        deleteVideoButton.setName("deleteVideo"); // NOI18N

        titleVideoTextField.setText("jTextField7");

        directorVideoTextField.setText("jTextField6");

        actorsVideoTextField.setText("jTextField5");

        eanVideoTextField.setText("jTextField3");

        genreVideoTextField.setText("jTextField2");

        releaseYearVideoTextField.setText("jTextField1");

        locationVideoTextField.setText("jTextField8");

        borrowedToVideoTextField.setText("jTextField9");

        borrowDayVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        borrowDayVideoComboBox.setAutoscrolls(true);
        borrowDayVideoComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        borrowedUntilDayVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        borrowMonthVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        borrowedUntilMonthVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        borrowYearVideoTextField.setText("2010");

        borrowedUntilYearVideoTextField.setText("jTextField2");

        formatVideoComboBox.setEditable(true);
        formatVideoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout itemVideoPanelLayout = new javax.swing.GroupLayout(itemVideoPanel);
        itemVideoPanel.setLayout(itemVideoPanelLayout);
        itemVideoPanelLayout.setHorizontalGroup(
            itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(coverVideoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borrowedToVideoLabel)
                            .addComponent(borrowDateVideoLabel)
                            .addComponent(locationVideoLabel)
                            .addComponent(releaseYearVideoLabel)
                            .addComponent(borrowedUntilVideoLabel)
                            .addComponent(genreVideoLabel)
                            .addComponent(eanVideoLabel)
                            .addComponent(isbnVideoLabel)
                            .addComponent(languageVideoLabel)
                            .addComponent(authorVideoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(directorVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(actorsVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(eanVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(genreVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(releaseYearVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(locationVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(borrowedToVideoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemVideoPanelLayout.createSequentialGroup()
                                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                        .addComponent(borrowDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowMonthVideoComboBox, 0, 166, Short.MAX_VALUE))
                                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                        .addComponent(borrowedUntilDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowedUntilMonthVideoComboBox, 0, 166, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrowedUntilYearVideoTextField, 0, 0, Short.MAX_VALUE)
                                    .addComponent(borrowYearVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
                            .addComponent(formatVideoComboBox, 0, 266, Short.MAX_VALUE)))
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(titleVideoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(ratingVideoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addComponent(editVideoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteVideoButton))
                    .addComponent(descriptionVideoScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(annotationVideoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap())
        );
        itemVideoPanelLayout.setVerticalGroup(
            itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleVideoLabel)
                    .addComponent(titleVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverVideoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(authorVideoLabel)
                            .addComponent(directorVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(languageVideoLabel)
                            .addComponent(actorsVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isbnVideoLabel)
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
                            .addComponent(releaseYearVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(locationVideoLabel)
                            .addComponent(locationVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowedToVideoLabel)
                            .addComponent(borrowedToVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowDateVideoLabel)
                            .addComponent(borrowDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowMonthVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowYearVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borrowedUntilVideoLabel)
                            .addComponent(borrowedUntilDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowedUntilMonthVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowedUntilYearVideoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteVideoButton)
                        .addComponent(editVideoButton))
                    .addComponent(ratingVideoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descriptionVideoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annotationVideoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemVideoScrollPane.setViewportView(itemVideoPanel);

        javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(videoPanel);
        videoPanel.setLayout(videoPanelLayout);
        videoPanelLayout.setHorizontalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewVideoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(itemVideoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viewVideoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemVideoScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
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
                    .addComponent(restoreItemsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE)
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
                .addComponent(restoreItemsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
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
                    .addComponent(tabbedPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBookButtonActionPerformed

    private void typeMusicComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeMusicComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeMusicComboBoxActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JTextField actorsVideoTextField;
    private javax.swing.JButton addBookButton;
    private javax.swing.JButton addMusicButton;
    private javax.swing.JButton addVideoButton;
    private javax.swing.JButton advancedSearchButton;
    private javax.swing.JScrollPane annotationBookScrollPane;
    private javax.swing.JTextArea annotationBookTextArea;
    private javax.swing.JScrollPane annotationMusicScrollPane;
    private javax.swing.JTextArea annotationMusicTextArea;
    private javax.swing.JScrollPane annotationVideoScrollPane;
    private javax.swing.JTextArea annotationVideoTextArea;
    private javax.swing.JLabel artistMusicLabel;
    private javax.swing.JTextField artistMusicTextField;
    private javax.swing.JTextField authorBookTextField;
    private javax.swing.JLabel authorTextBookLabel;
    private javax.swing.JLabel authorVideoLabel;
    private javax.swing.JPanel bookCardPanel;
    private javax.swing.JPanel bookPanel;
    private javax.swing.JLabel borrowDateMusicLabel;
    private javax.swing.JLabel borrowDateTextBookLabel;
    private javax.swing.JLabel borrowDateVideoLabel;
    private javax.swing.JComboBox borrowDayBookComboBox;
    private javax.swing.JComboBox borrowDayMusicComboBox;
    private javax.swing.JComboBox borrowDayVideoComboBox;
    private javax.swing.JComboBox borrowMonthBookComboBox;
    private javax.swing.JComboBox borrowMonthMusicComboBox;
    private javax.swing.JComboBox borrowMonthVideoComboBox;
    private javax.swing.JTextField borrowYearBookTextField;
    private javax.swing.JTextField borrowYearMusicTextField;
    private javax.swing.JTextField borrowYearVideoTextField;
    private javax.swing.JTextField borrowedToBookTextField;
    private javax.swing.JLabel borrowedToMusicLabel;
    private javax.swing.JTextField borrowedToMusicTextField;
    private javax.swing.JLabel borrowedToTextBookLabel;
    private javax.swing.JLabel borrowedToVideoLabel;
    private javax.swing.JTextField borrowedToVideoTextField;
    private javax.swing.JComboBox borrowedUntilDayBookComboBox;
    private javax.swing.JComboBox borrowedUntilDayMusicComboBox;
    private javax.swing.JComboBox borrowedUntilDayVideoComboBox;
    private javax.swing.JComboBox borrowedUntilMonthBookComboBox;
    private javax.swing.JComboBox borrowedUntilMonthMusicComboBox;
    private javax.swing.JComboBox borrowedUntilMonthVideoComboBox;
    private javax.swing.JLabel borrowedUntilMusicLabel;
    private javax.swing.JLabel borrowedUntilTextBookLabel;
    private javax.swing.JLabel borrowedUntilVideoLabel;
    private javax.swing.JTextField borrowedUntilYearBookTextField;
    private javax.swing.JTextField borrowedUntilYearMusicTextField;
    private javax.swing.JTextField borrowedUntilYearVideoTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel coverBookLabel;
    private javax.swing.JLabel coverMusicLabel;
    private javax.swing.JLabel coverVideoLabel;
    private javax.swing.JButton deleteBookButton;
    private javax.swing.JButton deleteItemButton;
    private javax.swing.JButton deleteMusicButton;
    private javax.swing.JButton deleteSearchButton;
    private javax.swing.JButton deleteVideoButton;
    private javax.swing.JScrollPane descriptionBookScrollPane;
    private javax.swing.JTextArea descriptionBookTextArea;
    private javax.swing.JScrollPane descriptionMusicScrollPane;
    private javax.swing.JTextArea descriptionMusicTextArea;
    private javax.swing.JScrollPane descriptionVideoScrollPane;
    private javax.swing.JTextArea descriptionVideoTextArea;
    private javax.swing.JTextField directorVideoTextField;
    private javax.swing.JTextField eanBookTextField;
    private javax.swing.JLabel eanMusicLabel;
    private javax.swing.JTextField eanMusicTextField;
    private javax.swing.JLabel eanTextBookLabel;
    private javax.swing.JLabel eanVideoLabel;
    private javax.swing.JTextField eanVideoTextField;
    private javax.swing.JButton editBookButton;
    private javax.swing.JButton editMusicButton;
    private javax.swing.JButton editVideoButton;
    private javax.swing.JComboBox formatMusicComboBox;
    private javax.swing.JLabel formatMusicLabel;
    private javax.swing.JComboBox formatVideoComboBox;
    private javax.swing.JTextField genreBookTextField;
    private javax.swing.JLabel genreMusicLabel;
    private javax.swing.JTextField genreMusicTextField;
    private javax.swing.JLabel genreTextBookLabel;
    private javax.swing.JLabel genreVideoLabel;
    private javax.swing.JTextField genreVideoTextField;
    private javax.swing.JPanel headPanel;
    private javax.swing.JButton helpButton;
    private javax.swing.JTextField isbnBookTextField;
    private javax.swing.JLabel isbnTextBookLabel;
    private javax.swing.JLabel isbnVideoLabel;
    private javax.swing.JPanel itemBookPanel;
    private javax.swing.JScrollPane itemBookScrollPane;
    private javax.swing.JPanel itemMusicPanel;
    private javax.swing.JScrollPane itemMusicScrollPane;
    private javax.swing.JPanel itemVideoPanel;
    private javax.swing.JScrollPane itemVideoScrollPane;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JTextField languageBookTextField;
    private javax.swing.JLabel languageTextBookLabel;
    private javax.swing.JLabel languageVideoLabel;
    private javax.swing.JComboBox languagesComboBox;
    private javax.swing.JTextField locationBookTextField;
    private javax.swing.JLabel locationMusicLabel;
    private javax.swing.JTextField locationMusicTextField;
    private javax.swing.JLabel locationTextBookLabel;
    private javax.swing.JLabel locationVideoLabel;
    private javax.swing.JTextField locationVideoTextField;
    private javax.swing.JPanel musicCardPanel;
    private javax.swing.JPanel musicPanel;
    private javax.swing.JRadioButton oneRatingpointBookRadioButton;
    private javax.swing.JRadioButton oneRatingpointMusicRadioButton;
    private javax.swing.JRadioButton oneRatingpointVideoRadioButton;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JPanel ratingBookPanel;
    private javax.swing.JPanel ratingMusicPanel;
    private javax.swing.JPanel ratingVideoPanel;
    private javax.swing.JTextField releaseYearBookTextField;
    private javax.swing.JLabel releaseYearMusicLabel;
    private javax.swing.JTextField releaseYearMusicTextField;
    private javax.swing.JLabel releaseYearTextBookLabel;
    private javax.swing.JLabel releaseYearVideoLabel;
    private javax.swing.JTextField releaseYearVideoTextField;
    private javax.swing.JButton restoreItemButton;
    private javax.swing.JScrollPane restoreItemsScrollpane;
    private javax.swing.JPanel restorePanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JRadioButton threeRatingpointsBookRadioButton;
    private javax.swing.JRadioButton threeRatingpointsMusicRadioButton;
    private javax.swing.JRadioButton threeRatingpointsVideoRadioButton;
    private javax.swing.JTextField titleBookTextField;
    private javax.swing.JLabel titleMusicLabel;
    private javax.swing.JTextField titleMusicTextField;
    private javax.swing.JLabel titleTextBookLabel;
    private javax.swing.JLabel titleVideoLabel;
    private javax.swing.JTextField titleVideoTextField;
    private javax.swing.JRadioButton twoRatingpointsBookRadioButton;
    private javax.swing.JRadioButton twoRatingpointsMusicRadioButton;
    private javax.swing.JRadioButton twoRatingpointsVideoRadioButton;
    private javax.swing.JComboBox typeMusicComboBox;
    private javax.swing.JLabel typeMusicLabel;
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

}
