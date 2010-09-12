/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 03.09.2010, 08:57:34
 */

package muvibee;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;
import util.CoverDetailsList;



/**
 *
 * @author bline
 */
public class MainFrame extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public MainFrame(MuViBee mvb) {
        initComponents();

	//init dayComboBoxes
	String[] days = new String[32];
	days[0] = "Tag";
	for(int i=1; i<32;i++)
	    days[i] = String.valueOf(i);
	borrowDayBookComboBox.setModel(new DefaultComboBoxModel(days));
	borrowedUntilDayBookComboBox.setModel(new DefaultComboBoxModel(days));
	borrowDayMusicComboBox.setModel(new DefaultComboBoxModel(days));
	borrowedUntilDayMusicComboBox.setModel(new DefaultComboBoxModel(days));
	borrowDayVideoComboBox.setModel(new DefaultComboBoxModel(days));
	borrowedUntilDayVideoComboBox.setModel(new DefaultComboBoxModel(days));

	//init monthComboBoxes
	String[] months = {"Monat", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
	borrowMonthBookComboBox.setModel(new DefaultComboBoxModel(months));
	borrowedUntilMonthBookComboBox.setModel(new DefaultComboBoxModel(months));
	borrowMonthMusicComboBox.setModel(new DefaultComboBoxModel(months));
	borrowedUntilMonthMusicComboBox.setModel(new DefaultComboBoxModel(months));
	borrowMonthVideoComboBox.setModel(new DefaultComboBoxModel(months));
	borrowedUntilMonthVideoComboBox.setModel(new DefaultComboBoxModel(months));

	//init typeComboBox
	String[] types = {"", "Album", "Single", "Sampler"};
	typeMusicComboBox.setModel(new DefaultComboBoxModel(types));

	//init formatComboBoxes
	String[] musicFormats = {"", "CD", "LP", "Kassette"};
	String[] videoFormats = {"", "CD/DVD", "BlueRay", "VHS"};
	formatMusicComboBox.setModel(new DefaultComboBoxModel(musicFormats));
	formatVideoComboBox.setModel(new DefaultComboBoxModel(videoFormats));
	
        CoverDetailsList coverDetailsBookList = new CoverDetailsList();
        CoverDetailsList coverDetailsMusicList = new CoverDetailsList();
        CoverDetailsList coverDetailsVideoList = new CoverDetailsList();

        coverDetailsListBookScrollPane.setViewportView(coverDetailsBookList);
        coverDetailsListMusicScrollPane.setViewportView(coverDetailsMusicList);
        coverDetailsListVideoScrollPane.setViewportView(coverDetailsVideoList);


        
        itemBookScrollPane.setVisible(false);
        itemMusicScrollPane.setVisible(false);
        itemVideoScrollPane.setVisible(false);

        DefaultComboBoxModel cbBooksModel = new DefaultComboBoxModel();
        cbBooksModel.addElement(treeBookScrollPane);// eigene toString
        cbBooksModel.addElement(coverListBookScrollPane);// eigene toString
        cbBooksModel.addElement(coverDetailsListBookScrollPane);
        viewBookComboBox.setModel(cbBooksModel);
        

        DefaultComboBoxModel cbMusicModel = new DefaultComboBoxModel();
        cbMusicModel.addElement(treeMusicScrollPane);// eigene toString
        cbMusicModel.addElement(coverListMusicScrollPane); // eigene toString
        cbMusicModel.addElement(coverDetailsListMusicScrollPane);
        viewMusicComboBox.setModel(cbMusicModel);
        

        DefaultComboBoxModel cbVideoModel = new DefaultComboBoxModel();
        cbVideoModel.addElement(treeVideoScrollPane);// eigene toString
        cbVideoModel.addElement(coverListVideoScrollPane);// eigene toString
        cbVideoModel.addElement(coverDetailsListVideoScrollPane);
        viewVideoComboBox.setModel(cbVideoModel);
        

        ComboBoxItemRenderer cbRenderer = new ComboBoxItemRenderer();
        viewBookComboBox.setRenderer(cbRenderer);
        viewMusicComboBox.setRenderer(cbRenderer);
        viewVideoComboBox.setRenderer(cbRenderer);
        
        ComboBoxActionListener cbal = new ComboBoxActionListener(viewBookLayeredPane, viewMusicLayeredPane, viewVideoLayeredPane, viewBookComboBox, viewMusicComboBox, viewVideoComboBox);
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

    }

    public void setBookItem(Book book){
        titleBookTextField.setText(book.getTitle());
        authorBookTextField.setText(book.getAuthor());
	languageBookTextField.setText(book.getLanguage());
	isbnBookTextField.setText(book.getIsbn());
	eanBookTextField.setText(book.getEan());
	genreBookTextField.setText(book.getGenre());
	releaseYearBookTextField.setText(String.valueOf(book.getReleaseYear()));
	locationBookTextField.setText(book.getLocation());
	borrowedToBookTextField.setText(book.getLendTo());
	borrowDayBookComboBox.setSelectedIndex(book.getLendDay());
	borrowMonthBookComboBox.setSelectedIndex(book.getLendMonth());
	borrowYearBookTextField.setText(String.valueOf(book.getLendYear()));
	borrowedUntilDayBookComboBox.setSelectedIndex(book.getLendUntilDay());
	borrowedUntilMonthBookComboBox.setSelectedIndex(book.getLendUntilMonth());
	borrowedUntilYearBookTextField.setText(String.valueOf(book.getLendUntilYear()));
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
        titleMusicTextField.setText(music.getTitle());
        artistMusicTextField.setText(music.getInterpreter());

	String type = music.getType();
	if(type != null && !type.isEmpty()){
	    int itemNumber = typeMusicComboBox.getItemCount();
	    boolean found = false;
	    for(int i=0; i < itemNumber; i++){
		if(typeMusicComboBox.getItemAt(i).equals(type)){
		    typeMusicComboBox.setSelectedIndex(i);
		    found = true;
		    break;
		}
	    }
	    if(!found)
		typeMusicComboBox.addItem(type);
	}

	String format = music.getFormat();
	if(format != null && !format.isEmpty()){
	    int itemNumber = formatMusicComboBox.getItemCount();
	    boolean found = false;
	    for(int i=0; i < itemNumber; i++){
		if(formatMusicComboBox.getItemAt(i).equals(format)){
		    formatMusicComboBox.setSelectedIndex(i);
		    found = true;
		    break;
		}
	    }
	    if(!found)
		formatMusicComboBox.addItem(format);
	}
	eanMusicTextField.setText(music.getEan());
	genreMusicTextField.setText(music.getGenre());
	releaseYearMusicTextField.setText(String.valueOf(music.getReleaseYear()));
	locationMusicTextField.setText(music.getLocation());
	borrowedToMusicTextField.setText(music.getLendTo());
	borrowDayMusicComboBox.setSelectedIndex(music.getLendDay());
	borrowMonthMusicComboBox.setSelectedIndex(music.getLendMonth());
	borrowYearMusicTextField.setText(String.valueOf(music.getLendYear()));
	borrowedUntilDayMusicComboBox.setSelectedIndex(music.getLendUntilDay());
	borrowedUntilMonthMusicComboBox.setSelectedIndex(music.getLendUntilMonth());
	borrowedUntilYearMusicTextField.setText(String.valueOf(music.getLendUntilYear()));
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
        titleVideoTextField.setText(video.getTitle());
        directorVideoTextField.setText(video.getDirector());
	actorsVideoTextField.setText(video.getActors());

	String format = video.getFormat();
	if(format != null && !format.isEmpty()){
	    int itemNumber = formatVideoComboBox.getItemCount();
	    boolean found = false;
	    for(int i=0; i < itemNumber; i++){
		if(formatVideoComboBox.getItemAt(i).equals(format)){
		    formatVideoComboBox.setSelectedIndex(i);
		    found = true;
		    break;
		}
	    }
	    if(!found)
		formatVideoComboBox.addItem(format);
	}

	eanVideoTextField.setText(video.getEan());
	genreVideoTextField.setText(video.getGenre());
	releaseYearVideoTextField.setText(String.valueOf(video.getReleaseYear()));
	locationVideoTextField.setText(video.getLocation());
	borrowedToVideoTextField.setText(video.getLendTo());
	borrowDayVideoComboBox.setSelectedIndex(video.getLendDay());
	borrowMonthVideoComboBox.setSelectedIndex(video.getLendMonth());
	borrowYearVideoTextField.setText(String.valueOf(video.getLendYear()));
	borrowedUntilDayVideoComboBox.setSelectedIndex(video.getLendUntilDay());
	borrowedUntilMonthVideoComboBox.setSelectedIndex(video.getLendUntilMonth());
	borrowedUntilYearVideoTextField.setText(String.valueOf(video.getLendUntilYear()));
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


    public void setBookItemInformation(Book book) {
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
	try {
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
	} catch (NonValidYearException ex) {
	    //TODO Fehlerausgabe
	    statusLabel.setText("Ungueltiges Datum");
	}
    }

    public void setMusicItemInformation(Music music){
	String title = titleMusicTextField.getText().trim();
	String artist = artistMusicTextField.getText().trim();
	String type = typeMusicComboBox.getSelectedItem().toString().trim();
	String format = formatMusicComboBox.getSelectedItem().toString().trim();
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
	try {
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
	} catch (NonValidYearException ex) {
	    //TODO Fehlerausgabe
	    statusLabel.setText("Ungueltiges Datum");
	}
    }

    public void setVideoItemInformation(Video video) {
        String title = titleMusicTextField.getText().trim();
	String director = directorVideoTextField.getText().trim();
	String actors = actorsVideoTextField.getText().trim();
	String format = formatVideoComboBox.getSelectedItem().toString().trim();
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
	try {
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
	} catch (NonValidYearException ex) {
	    //TODO Fehlerausgabe
	    statusLabel.setText("Ungueltiges Datum");
	}
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
        jButton1 = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
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
        viewBookLayeredPane = new javax.swing.JLayeredPane();
        treeBookScrollPane = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        coverListBookScrollPane = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        coverDetailsListBookScrollPane = new javax.swing.JScrollPane();
        detailsListBookScrollPane = new javax.swing.JScrollPane();
        viewBookComboBox = new javax.swing.JComboBox();
        addBookButton = new javax.swing.JButton();
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
        viewMusicLayeredPane = new javax.swing.JLayeredPane();
        treeMusicScrollPane = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        coverListMusicScrollPane = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        coverDetailsListMusicScrollPane = new javax.swing.JScrollPane();
        detailsListMusicScrollPane = new javax.swing.JScrollPane();
        viewMusicComboBox = new javax.swing.JComboBox();
        addMusicButton = new javax.swing.JButton();
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
        viewVideoLayeredPane = new javax.swing.JLayeredPane();
        treeVideoScrollPane = new javax.swing.JScrollPane();
        jTree3 = new javax.swing.JTree();
        coverListVideoScrollPane = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        coverDetailsListVideoScrollPane = new javax.swing.JScrollPane();
        detailsListVideoScrollPane = new javax.swing.JScrollPane();
        viewVideoComboBox = new javax.swing.JComboBox();
        addVideoButton = new javax.swing.JButton();
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
        restoreItemsScrollPane = new javax.swing.JScrollPane();
        restoreItemsList = new javax.swing.JList();
        restoreItemButton = new javax.swing.JButton();
        deleteItemButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MuViBee");

        aboutButton.setText("Über");

        helpButton.setText("Hilfe");

        languagesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        languagesComboBox.setName("LangaugesComboBox"); // NOI18N

        searchTextField.setText("jTextField1");

        searchButton.setText("Suchen");

        advancedSearchButton.setText("Erweiterte Suche");

        jButton1.setText("X");

        javax.swing.GroupLayout headPanelLayout = new javax.swing.GroupLayout(headPanel);
        headPanel.setLayout(headPanelLayout);
        headPanelLayout.setHorizontalGroup(
            headPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(advancedSearchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(advancedSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        statusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        statusLabel.setText("jLabel1");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap(738, Short.MAX_VALUE))
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

        treeBookScrollPane.setName("Tree"); // NOI18N
        treeBookScrollPane.setPreferredSize(new java.awt.Dimension(74, 322));
        treeBookScrollPane.setViewportView(jTree1);

        treeBookScrollPane.setBounds(0, 0, 217, 430);
        viewBookLayeredPane.add(treeBookScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        coverListBookScrollPane.setName("Cover"); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        coverListBookScrollPane.setViewportView(jList1);

        coverListBookScrollPane.setBounds(0, 0, 217, 430);
        viewBookLayeredPane.add(coverListBookScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        coverDetailsListBookScrollPane.setName("Cover Details"); // NOI18N
        coverDetailsListBookScrollPane.setBounds(0, 0, 217, 430);
        viewBookLayeredPane.add(coverDetailsListBookScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        detailsListBookScrollPane.setName("Details"); // NOI18N
        detailsListBookScrollPane.setBounds(0, 0, 217, 430);
        viewBookLayeredPane.add(detailsListBookScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        viewBookComboBox.setName("ViewComboBox"); // NOI18N

        addBookButton.setText("Hinzufügen");
        addBookButton.setName("add book button"); // NOI18N
        addBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewBookPanelLayout = new javax.swing.GroupLayout(viewBookPanel);
        viewBookPanel.setLayout(viewBookPanelLayout);
        viewBookPanelLayout.setHorizontalGroup(
            viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewBookLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(viewBookPanelLayout.createSequentialGroup()
                        .addComponent(viewBookComboBox, 0, 113, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBookButton)))
                .addContainerGap())
        );
        viewBookPanelLayout.setVerticalGroup(
            viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookPanelLayout.createSequentialGroup()
                .addComponent(viewBookLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addBookButton)
                    .addComponent(viewBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemBookPanel.setPreferredSize(new java.awt.Dimension(518, 837));

        coverBookLabel.setText("jLabel2");

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
                            .addComponent(authorBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(languageBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(isbnBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(eanBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(genreBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(releaseYearBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(locationBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(borrowedToBookTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemBookPanelLayout.createSequentialGroup()
                                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                                        .addComponent(borrowDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowMonthBookComboBox, 0, 160, Short.MAX_VALUE))
                                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                                        .addComponent(borrowedUntilDayBookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowedUntilMonthBookComboBox, 0, 160, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrowedUntilYearBookTextField, 0, 0, Short.MAX_VALUE)
                                    .addComponent(borrowYearBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))))
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(titleTextBookLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleBookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                    .addGroup(itemBookPanelLayout.createSequentialGroup()
                        .addComponent(ratingBookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                        .addComponent(editBookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBookButton))
                    .addComponent(descriptionBookScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(annotationBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
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
            .addGroup(bookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewBookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        bookPanelLayout.setVerticalGroup(
            bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemBookScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(viewBookPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Bücher", bookPanel);

        viewMusicPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        treeMusicScrollPane.setName("Tree"); // NOI18N
        treeMusicScrollPane.setViewportView(jTree2);

        treeMusicScrollPane.setBounds(0, 0, 217, 430);
        viewMusicLayeredPane.add(treeMusicScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        coverListMusicScrollPane.setName("Cover"); // NOI18N

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        coverListMusicScrollPane.setViewportView(jList2);

        coverListMusicScrollPane.setBounds(0, 0, 217, 430);
        viewMusicLayeredPane.add(coverListMusicScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        coverDetailsListMusicScrollPane.setName("Cover Details"); // NOI18N
        coverDetailsListMusicScrollPane.setBounds(0, 0, 217, 430);
        viewMusicLayeredPane.add(coverDetailsListMusicScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        detailsListMusicScrollPane.setName("Details"); // NOI18N
        detailsListMusicScrollPane.setBounds(0, 0, 217, 430);
        viewMusicLayeredPane.add(detailsListMusicScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        viewMusicComboBox.setName("ViewComboBox"); // NOI18N

        addMusicButton.setText("Hinzufügen");
        addMusicButton.setName("add music button"); // NOI18N

        javax.swing.GroupLayout viewMusicPanelLayout = new javax.swing.GroupLayout(viewMusicPanel);
        viewMusicPanel.setLayout(viewMusicPanelLayout);
        viewMusicPanelLayout.setHorizontalGroup(
            viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMusicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewMusicLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(viewMusicPanelLayout.createSequentialGroup()
                        .addComponent(viewMusicComboBox, 0, 113, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMusicButton)))
                .addContainerGap())
        );
        viewMusicPanelLayout.setVerticalGroup(
            viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewMusicPanelLayout.createSequentialGroup()
                .addComponent(viewMusicLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addMusicButton)
                    .addComponent(viewMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemMusicPanel.setPreferredSize(new java.awt.Dimension(518, 837));

        coverMusicLabel.setText("jLabel2");

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
                            .addComponent(artistMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(eanMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(genreMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(releaseYearMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(locationMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(borrowedToMusicTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemMusicPanelLayout.createSequentialGroup()
                                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                        .addComponent(borrowDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowMonthMusicComboBox, 0, 151, Short.MAX_VALUE))
                                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                                        .addComponent(borrowedUntilDayMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowedUntilMonthMusicComboBox, 0, 151, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrowedUntilYearMusicTextField, 0, 0, Short.MAX_VALUE)
                                    .addComponent(borrowYearMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
                            .addComponent(typeMusicComboBox, 0, 251, Short.MAX_VALUE)
                            .addComponent(formatMusicComboBox, 0, 251, Short.MAX_VALUE)))
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(titleMusicLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleMusicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                    .addGroup(itemMusicPanelLayout.createSequentialGroup()
                        .addComponent(ratingMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                        .addComponent(editMusicButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMusicButton))
                    .addComponent(descriptionMusicScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(annotationMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
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
            .addGroup(musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        musicPanelLayout.setVerticalGroup(
            musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemMusicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(viewMusicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Musik", musicPanel);

        viewVideoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        treeVideoScrollPane.setName("Tree"); // NOI18N
        treeVideoScrollPane.setViewportView(jTree3);

        treeVideoScrollPane.setBounds(0, 0, 217, 430);
        viewVideoLayeredPane.add(treeVideoScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        coverListVideoScrollPane.setName("Cover"); // NOI18N

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        coverListVideoScrollPane.setViewportView(jList3);

        coverListVideoScrollPane.setBounds(0, 0, 217, 430);
        viewVideoLayeredPane.add(coverListVideoScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        coverDetailsListVideoScrollPane.setName("Cover Details"); // NOI18N
        coverDetailsListVideoScrollPane.setBounds(0, 0, 217, 430);
        viewVideoLayeredPane.add(coverDetailsListVideoScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        detailsListVideoScrollPane.setName("Details"); // NOI18N
        detailsListVideoScrollPane.setBounds(0, 0, 217, 430);
        viewVideoLayeredPane.add(detailsListVideoScrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        addVideoButton.setText("Hinzufügen");
        addVideoButton.setName("add video button"); // NOI18N

        javax.swing.GroupLayout viewVideoPanelLayout = new javax.swing.GroupLayout(viewVideoPanel);
        viewVideoPanel.setLayout(viewVideoPanelLayout);
        viewVideoPanelLayout.setHorizontalGroup(
            viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewVideoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viewVideoLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(viewVideoPanelLayout.createSequentialGroup()
                        .addComponent(viewVideoComboBox, 0, 113, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addVideoButton)))
                .addContainerGap())
        );
        viewVideoPanelLayout.setVerticalGroup(
            viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewVideoPanelLayout.createSequentialGroup()
                .addComponent(viewVideoLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addVideoButton)
                    .addComponent(viewVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemVideoPanel.setPreferredSize(new java.awt.Dimension(518, 837));

        coverVideoLabel.setText("jLabel2");

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
                            .addComponent(directorVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(actorsVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(eanVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(genreVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(releaseYearVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(locationVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(borrowedToVideoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemVideoPanelLayout.createSequentialGroup()
                                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                        .addComponent(borrowDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowMonthVideoComboBox, 0, 151, Short.MAX_VALUE))
                                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                                        .addComponent(borrowedUntilDayVideoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(borrowedUntilMonthVideoComboBox, 0, 151, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(itemVideoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrowedUntilYearVideoTextField, 0, 0, Short.MAX_VALUE)
                                    .addComponent(borrowYearVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
                            .addComponent(formatVideoComboBox, 0, 251, Short.MAX_VALUE)))
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(titleVideoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleVideoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                    .addGroup(itemVideoPanelLayout.createSequentialGroup()
                        .addComponent(ratingVideoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                        .addComponent(editVideoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteVideoButton))
                    .addComponent(descriptionVideoScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(annotationVideoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
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
            .addGroup(videoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewVideoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemVideoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(videoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemVideoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(viewVideoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Videos", videoPanel);

        restoreItemsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        restoreItemsScrollPane.setViewportView(restoreItemsList);

        restoreItemButton.setText("Wiederhestellen");

        deleteItemButton.setText("Endgültig löschen");

        javax.swing.GroupLayout restorePanelLayout = new javax.swing.GroupLayout(restorePanel);
        restorePanel.setLayout(restorePanelLayout);
        restorePanelLayout.setHorizontalGroup(
            restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restorePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restoreItemsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addGroup(restorePanelLayout.createSequentialGroup()
                        .addComponent(restoreItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteItemButton)))
                .addContainerGap())
        );
        restorePanelLayout.setVerticalGroup(
            restorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restorePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(restoreItemsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane coverDetailsListBookScrollPane;
    private javax.swing.JScrollPane coverDetailsListMusicScrollPane;
    private javax.swing.JScrollPane coverDetailsListVideoScrollPane;
    private javax.swing.JScrollPane coverListBookScrollPane;
    private javax.swing.JScrollPane coverListMusicScrollPane;
    private javax.swing.JScrollPane coverListVideoScrollPane;
    private javax.swing.JLabel coverMusicLabel;
    private javax.swing.JLabel coverVideoLabel;
    private javax.swing.JButton deleteBookButton;
    private javax.swing.JButton deleteItemButton;
    private javax.swing.JButton deleteMusicButton;
    private javax.swing.JButton deleteVideoButton;
    private javax.swing.JScrollPane descriptionBookScrollPane;
    private javax.swing.JTextArea descriptionBookTextArea;
    private javax.swing.JScrollPane descriptionMusicScrollPane;
    private javax.swing.JTextArea descriptionMusicTextArea;
    private javax.swing.JScrollPane descriptionVideoScrollPane;
    private javax.swing.JTextArea descriptionVideoTextArea;
    private javax.swing.JScrollPane detailsListBookScrollPane;
    private javax.swing.JScrollPane detailsListMusicScrollPane;
    private javax.swing.JScrollPane detailsListVideoScrollPane;
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
    private javax.swing.JButton jButton1;
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
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    private javax.swing.JTree jTree3;
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
    private javax.swing.JList restoreItemsList;
    private javax.swing.JScrollPane restoreItemsScrollPane;
    private javax.swing.JPanel restorePanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel statusLabel;
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
    private javax.swing.JScrollPane treeBookScrollPane;
    private javax.swing.JScrollPane treeMusicScrollPane;
    private javax.swing.JScrollPane treeVideoScrollPane;
    private javax.swing.JRadioButton twoRatingpointsBookRadioButton;
    private javax.swing.JRadioButton twoRatingpointsMusicRadioButton;
    private javax.swing.JRadioButton twoRatingpointsVideoRadioButton;
    private javax.swing.JComboBox typeMusicComboBox;
    private javax.swing.JLabel typeMusicLabel;
    private javax.swing.JPanel videoPanel;
    private javax.swing.JComboBox viewBookComboBox;
    private javax.swing.JLayeredPane viewBookLayeredPane;
    private javax.swing.JPanel viewBookPanel;
    private javax.swing.JComboBox viewMusicComboBox;
    private javax.swing.JLayeredPane viewMusicLayeredPane;
    private javax.swing.JPanel viewMusicPanel;
    private javax.swing.JComboBox viewVideoComboBox;
    private javax.swing.JLayeredPane viewVideoLayeredPane;
    private javax.swing.JPanel viewVideoPanel;
    // End of variables declaration//GEN-END:variables




}
