/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdvancedSearchDialog.java
 *
 * Created on Sep 16, 2010, 10:39:25 PM
 */

package muvibee.gui;

import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import muvibee.IllegalYearException;
import muvibee.MuViBee;
import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 *
 * @author tim
 */
public class AdvancedSearchDialog extends javax.swing.JDialog {
    /** A return status code - returned if Cancel button has been pressed */
    public static final int RET_CANCEL = 0;
    /** A return status code - returned if OK button has been pressed */
    public static final int RET_OK = 1;
    public static final int RET_ILLEGAL_YEAR = -1;

    private Book book;
    private Music music;
    private Video video;
    private MuViBee mvb;

    public AdvancedSearchDialog(java.awt.Frame parent,String title, boolean modal, MuViBee mvb){
        super(parent, title, modal);
        this.mvb = mvb;
        initComponents();

        String[] days = new String[32];
        days[0] = "";
	for(int i=1; i<32;i++)
	    days[i] = String.valueOf(i);
	lentDayComboBox.setModel(new DefaultComboBoxModel(days));
        lentUntilDayComboBox.setModel(new DefaultComboBoxModel(days));
        String[] emptyArray = {""};
        lentMonthComboBox.setModel(new DefaultComboBoxModel(emptyArray));
        lentUntilMonthComboBox.setModel(new DefaultComboBoxModel(emptyArray));
        String[] years = new String[63];
        for(int i=0;i<years.length;i++)
            years[i] = String.valueOf(2012 - i);
        releaseYearComboBox.setModel(new DefaultComboBoxModel(years));
        releaseYearComboBox.setSelectedIndex(-1);
        lentYearComboBox.setModel(new DefaultComboBoxModel(years));
        lentYearComboBox.setSelectedIndex(-1);
        lentUntilYearComboBox.setModel(new DefaultComboBoxModel(years));
        lentUntilYearComboBox.setSelectedIndex(-1);

        setLentFieldsEnabled(false);
        
        reloadLabels();
    }

    /** @return the return status of this dialog - one of RET_OK or RET_CANCEL */
    public int getReturnStatus() {
        return returnStatus;
    }

    public Book getBook() {
        return book;
    }

    public Music getMusic() {
        return music;
    }

    public Video getVideo() {
        return video;
    }

    public final void reloadLabels(){
        ResourceBundle bundle = ResourceBundle.getBundle(mvb.getMainBundlePath());

        mediaPanel.setBorder(BorderFactory.createTitledBorder(bundle.getString("mediaPanelASD")));
        bookPanel.setBorder(BorderFactory.createTitledBorder(bundle.getString("bookPanelASD")));
        musicPanel.setBorder(BorderFactory.createTitledBorder(bundle.getString("musicPanelASD")));
        videoPanel.setBorder(BorderFactory.createTitledBorder(bundle.getString("videoPanelASD")));
        
        titleLabel.setText(bundle.getString("titleLabel"));
        ratingLabel.setText(bundle.getString("ratingLabel"));
        eanLabel.setText(bundle.getString("eanLabel"));
        genreLabel.setText(bundle.getString("genreLabel"));
        releaseYearLabel.setText(bundle.getString("releaseYearLabel"));
        isLentLabel.setText(bundle.getString("isLentLabel"));
        lentToLabel.setText(bundle.getString("lentToLabel"));
        lentDateLabel.setText(bundle.getString("lentDateLabel"));
        lentUntilDateLabel.setText(bundle.getString("lentUntilDateLabel"));
        locationLabel.setText(bundle.getString("locationLabel"));
        descriptionLabel.setText(bundle.getString("descriptionLabel"));
        annotationLabel.setText(bundle.getString("annotationLabel"));
        authorLabel.setText(bundle.getString("authorLabel"));
        isbnLabel.setText(bundle.getString("isbnLabel"));
        languageLabel.setText(bundle.getString("languageLabel"));
        artistLabel.setText(bundle.getString("artistLabel"));
        typeLabel.setText(bundle.getString("typeLabel"));
        musicFormatLabel.setText(bundle.getString("formatLabel"));
        directorLabel.setText(bundle.getString("directorLabel"));
        actorsLabel.setText(bundle.getString("actorsLabel"));
        videoFormatLabel.setText(bundle.getString("formatLabel"));

        ratingNoneRadioButton.setSelected(true);
        ratingNoneRadioButton.setText(bundle.getString("noRating"));

        String day = bundle.getString("dayComboBox");
        updateComboBoxLabel(lentDayComboBox, day);
        updateComboBoxLabel(lentUntilDayComboBox, day);

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

        updateComboBoxLabels(lentMonthComboBox, months);
	updateComboBoxLabels(lentUntilMonthComboBox, months);

        String[] types = new String[3];
        types[0] = bundle.getString("album");
        types[1] = bundle.getString("sampler");
        types[2] = bundle.getString("single");
        updateComboBoxLabels(typeComboBox, types);

	String[] musicFormats = new String[3];
        musicFormats[0] = bundle.getString("cd");
        musicFormats[1] = bundle.getString("lp");
        musicFormats[2] = bundle.getString("cassette");
	String[] videoFormats = new String[3];
        videoFormats[0] = bundle.getString("cd/dvd");
        videoFormats[1] = bundle.getString("blu-ray");
        videoFormats[2] = bundle.getString("vhs");
	updateComboBoxLabels(musicFormatComboBox, musicFormats);
	updateComboBoxLabels(videoFormatComboBox, videoFormats);
    }

    public void updateComboBoxLabel(JComboBox cb, String label){
        int selectedIndex = cb.getSelectedIndex();
        cb.removeItemAt(0);
        cb.insertItemAt(label, 0);
        cb.setSelectedIndex(selectedIndex);
    }

    public void updateComboBoxLabels(JComboBox cb, String[] labels){
        int selectedIndex = cb.getSelectedIndex();
        cb.removeAllItems();
        for(int i=0; i<labels.length; i++)
            cb.addItem(labels[i]);
        cb.setSelectedIndex(selectedIndex);
    }

    public void setLentFieldsEnabled(boolean b){
        lentToLabel.setEnabled(b);
        lentToTextField.setEnabled(b);
        lentDateLabel.setEnabled(b);
        lentDayComboBox.setEnabled(b);
        lentMonthComboBox.setEnabled(b);
        lentYearComboBox.setEnabled(b);
        lentUntilDateLabel.setEnabled(b);
        lentUntilDayComboBox.setEnabled(b);
        lentUntilMonthComboBox.setEnabled(b);
        lentUntilYearComboBox.setEnabled(b);
    }

        public int getYear(JComboBox cb) throws IllegalYearException {
        if(cb.getSelectedIndex() == 0)
            return -1;
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ratingButtonGroup = new javax.swing.ButtonGroup();
        mediaPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        eanLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        releaseYearLabel = new javax.swing.JLabel();
        isLentLabel = new javax.swing.JLabel();
        lentToLabel = new javax.swing.JLabel();
        lentDateLabel = new javax.swing.JLabel();
        lentUntilDateLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        annotationLabel = new javax.swing.JLabel();
        isLentCheckBox = new javax.swing.JCheckBox();
        ratingOneRadioButton = new javax.swing.JRadioButton();
        ratingTwoRadioButton = new javax.swing.JRadioButton();
        ratingThreeRadioButton = new javax.swing.JRadioButton();
        ratingNoneRadioButton = new javax.swing.JRadioButton();
        titleTextField = new javax.swing.JTextField();
        eanTextField = new javax.swing.JTextField();
        genreTextField = new javax.swing.JTextField();
        lentToTextField = new javax.swing.JTextField();
        locationTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        annotationTextField = new javax.swing.JTextField();
        releaseYearComboBox = new javax.swing.JComboBox();
        lentDayComboBox = new javax.swing.JComboBox();
        lentMonthComboBox = new javax.swing.JComboBox();
        lentYearComboBox = new javax.swing.JComboBox();
        lentUntilDayComboBox = new javax.swing.JComboBox();
        lentUntilMonthComboBox = new javax.swing.JComboBox();
        lentUntilYearComboBox = new javax.swing.JComboBox();
        bookPanel = new javax.swing.JPanel();
        authorLabel = new javax.swing.JLabel();
        languageLabel = new javax.swing.JLabel();
        isbnLabel = new javax.swing.JLabel();
        authorTextField = new javax.swing.JTextField();
        languageTextField = new javax.swing.JTextField();
        isbnTextField = new javax.swing.JTextField();
        musicPanel = new javax.swing.JPanel();
        artistLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        musicFormatLabel = new javax.swing.JLabel();
        artistTextField = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox();
        musicFormatComboBox = new javax.swing.JComboBox();
        videoPanel = new javax.swing.JPanel();
        directorLabel = new javax.swing.JLabel();
        actorsLabel = new javax.swing.JLabel();
        videoFormatLabel = new javax.swing.JLabel();
        directorTextField = new javax.swing.JTextField();
        actorsTextField = new javax.swing.JTextField();
        videoFormatComboBox = new javax.swing.JComboBox();
        searchButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        mediaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Allgmeine Daten"));

        titleLabel.setText("Titel");

        ratingLabel.setText("Bewertung");

        eanLabel.setText("EAN");

        genreLabel.setText("Genre");

        releaseYearLabel.setText("Erscheinungsjahr");

        isLentLabel.setText("Verliehen");

        lentToLabel.setText("Verliehen an");

        lentDateLabel.setText("Verleihdatum");

        lentUntilDateLabel.setText("Verliehen bis");

        locationLabel.setText("Standort");

        descriptionLabel.setText("Beschreibung");

        annotationLabel.setText("Kommentar");

        isLentCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isLentCheckBoxActionPerformed(evt);
            }
        });

        ratingButtonGroup.add(ratingOneRadioButton);
        ratingOneRadioButton.setText("1");

        ratingButtonGroup.add(ratingTwoRadioButton);
        ratingTwoRadioButton.setText("2");

        ratingButtonGroup.add(ratingThreeRadioButton);
        ratingThreeRadioButton.setText("3");

        ratingButtonGroup.add(ratingNoneRadioButton);
        ratingNoneRadioButton.setText("keine");

        releaseYearComboBox.setEditable(true);

        lentYearComboBox.setEditable(true);

        lentUntilYearComboBox.setEditable(true);

        javax.swing.GroupLayout mediaPanelLayout = new javax.swing.GroupLayout(mediaPanel);
        mediaPanel.setLayout(mediaPanelLayout);
        mediaPanelLayout.setHorizontalGroup(
            mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mediaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel)
                    .addComponent(ratingLabel)
                    .addComponent(eanLabel)
                    .addComponent(genreLabel)
                    .addComponent(releaseYearLabel)
                    .addComponent(locationLabel))
                .addGap(13, 13, 13)
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mediaPanelLayout.createSequentialGroup()
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(genreTextField)
                            .addComponent(eanTextField)
                            .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mediaPanelLayout.createSequentialGroup()
                        .addComponent(ratingOneRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratingTwoRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratingThreeRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratingNoneRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mediaPanelLayout.createSequentialGroup()
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(locationTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(releaseYearComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 223, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(annotationLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mediaPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mediaPanelLayout.createSequentialGroup()
                                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lentToLabel)
                                    .addComponent(lentDateLabel)
                                    .addComponent(lentUntilDateLabel)
                                    .addComponent(descriptionLabel))
                                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mediaPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentMonthComboBox, 0, 181, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lentYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mediaPanelLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(lentToTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mediaPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(descriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                            .addGroup(mediaPanelLayout.createSequentialGroup()
                                                .addComponent(lentUntilDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lentUntilMonthComboBox, 0, 181, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lentUntilYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(annotationTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)))))
                            .addGroup(mediaPanelLayout.createSequentialGroup()
                                .addComponent(isLentLabel)
                                .addGap(37, 37, 37)
                                .addComponent(isLentCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        mediaPanelLayout.setVerticalGroup(
            mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mediaPanelLayout.createSequentialGroup()
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isLentLabel)
                    .addComponent(isLentCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mediaPanelLayout.createSequentialGroup()
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ratingOneRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ratingTwoRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ratingThreeRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ratingNoneRadioButton))
                            .addComponent(ratingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eanLabel)
                            .addComponent(eanTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genreLabel)))
                    .addGroup(mediaPanelLayout.createSequentialGroup()
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lentToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lentToLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lentDateLabel)
                            .addComponent(lentDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lentMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lentYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lentUntilDateLabel)
                            .addComponent(lentUntilDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lentUntilMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lentUntilYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(releaseYearLabel)
                    .addComponent(releaseYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mediaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationLabel)
                    .addComponent(annotationLabel)
                    .addComponent(annotationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        bookPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bücher"));

        authorLabel.setText("Autor");

        languageLabel.setText("Sprache");

        isbnLabel.setText("ISBN");

        javax.swing.GroupLayout bookPanelLayout = new javax.swing.GroupLayout(bookPanel);
        bookPanel.setLayout(bookPanelLayout);
        bookPanelLayout.setHorizontalGroup(
            bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(authorLabel)
                    .addComponent(languageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(isbnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(languageTextField)
                    .addComponent(isbnTextField)
                    .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bookPanelLayout.setVerticalGroup(
            bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookPanelLayout.createSequentialGroup()
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorLabel)
                    .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(languageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(isbnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isbnLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        musicPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Musik"));

        artistLabel.setText("Interpret");

        typeLabel.setText("Typ");

        musicFormatLabel.setText("Format");

        typeComboBox.setEditable(true);

        musicFormatComboBox.setEditable(true);

        javax.swing.GroupLayout musicPanelLayout = new javax.swing.GroupLayout(musicPanel);
        musicPanel.setLayout(musicPanelLayout);
        musicPanelLayout.setHorizontalGroup(
            musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistLabel)
                    .addComponent(typeLabel)
                    .addComponent(musicFormatLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeComboBox, 0, 160, Short.MAX_VALUE)
                    .addComponent(artistTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(musicFormatComboBox, 0, 160, Short.MAX_VALUE))
                .addContainerGap())
        );
        musicPanelLayout.setVerticalGroup(
            musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(musicPanelLayout.createSequentialGroup()
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(artistLabel)
                    .addComponent(artistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(musicFormatLabel)
                    .addComponent(musicFormatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        videoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Videos"));

        directorLabel.setText("Regisseur");

        actorsLabel.setText("Schauspieler");

        videoFormatLabel.setText("Format");

        videoFormatComboBox.setEditable(true);

        javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(videoPanel);
        videoPanel.setLayout(videoPanelLayout);
        videoPanelLayout.setHorizontalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(videoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(videoFormatLabel)
                    .addComponent(actorsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(directorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actorsTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(directorTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(videoFormatComboBox, 0, 171, Short.MAX_VALUE))
                .addContainerGap())
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(videoPanelLayout.createSequentialGroup()
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directorLabel)
                    .addComponent(directorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actorsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actorsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(videoFormatLabel)
                    .addComponent(videoFormatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchButton.setText("Suchen");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Abbrechen");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mediaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(musicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(videoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mediaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(musicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(videoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        int returnCode = RET_OK;

        String title = titleTextField.getText().trim();
        String ean = eanTextField.getText().trim();	    //TODO Ueberpruefen!?!?
        String genre = genreTextField.getText().trim();
        String location = locationTextField.getText().trim();
        boolean isLent = isLentCheckBox.isSelected();
        String lendTo = lentToTextField.getText().trim();
        int lendDay = lentDayComboBox.getSelectedIndex();
        int lendMonth = lentMonthComboBox.getSelectedIndex();
        int lendUntilDay = lentUntilDayComboBox.getSelectedIndex();
        int lendUntilMonth = lentUntilMonthComboBox.getSelectedIndex();
        String description = descriptionTextField.getText().trim();
        String annotation = annotationTextField.getText().trim();

        //Rating
        int rating = 0;
        if (ratingOneRadioButton.isSelected()) {
            rating = 1;
        } else if (ratingOneRadioButton.isSelected()) {
            rating = 2;
        } else if (ratingOneRadioButton.isSelected()) {
            rating = 3;
        }

        String author = authorTextField.getText().trim();
        String language = languageTextField.getText().trim();
        String isbn = isbnTextField.getText().trim();   //TODO Ueberpruefen!?!?

        String artist = artistTextField.getText().trim();
        Object selectedType = typeComboBox.getSelectedItem();
        String type = "";
        if (selectedType != null) {
            type = selectedType.toString().trim();
        }
        Object selectedMusicFormat = musicFormatComboBox.getSelectedItem();
        String musicFormat = "";
        if (selectedMusicFormat != null) {
            musicFormat = selectedMusicFormat.toString().trim();
        }

        String director = directorTextField.getText().trim();
        String actors = actorsTextField.getText().trim();
        Object selectedVideoFormat = videoFormatComboBox.getSelectedItem();
        String videoFormat = "";
        if (selectedVideoFormat != null) {
            videoFormat = selectedVideoFormat.toString().trim();
        }

        int releaseYear = -1;
        int lentYear = -1;
        int lentUntilYear = -1;

        try {
            releaseYear = getYear(releaseYearComboBox);
            lentYear = getYear(lentYearComboBox);
            lentUntilYear = getYear(lentUntilYearComboBox);
        } catch (IllegalYearException e) {
            returnCode = RET_ILLEGAL_YEAR;
        }
        book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setLanguage(language);
        book.setIsbn(isbn);
        book.setEan(ean);
        book.setGenre(genre);
        book.setReleaseYear(releaseYear);
        book.setLocation(location);
        book.setIsLent(isLent);
        book.setLendTo(lendTo);
        book.setLendDay(lendDay);
        book.setLendMonth(lendMonth);
        book.setLendYear(lentYear);
        book.setLendUntilDay(lendUntilDay);
        book.setLendUntilMonth(lendUntilMonth);
        book.setLendUntilYear(lentUntilYear);
        book.setRating(rating);
        book.setDescription(description);
        book.setComment(annotation);

        music = new Music();
        music.setTitle(title);
        music.setInterpreter(artist);
        music.setType(type);
        music.setFormat(musicFormat);
        music.setEan(ean);
        music.setGenre(genre);
        music.setReleaseYear(releaseYear);
        music.setLocation(location);
        music.setIsLent(isLent);
        music.setLendTo(lendTo);
        music.setLendDay(lendDay);
        music.setLendMonth(lendMonth);
        music.setLendYear(lentYear);
        music.setLendUntilDay(lendUntilDay);
        music.setLendUntilMonth(lendUntilMonth);
        music.setLendUntilYear(lentUntilYear);
        music.setRating(rating);
        music.setDescription(description);
        music.setComment(annotation);

        video = new Video();
        video.setTitle(title);
        video.setDirector(director);
        video.setActors(actors);
        video.setFormat(videoFormat);
        video.setEan(ean);
        video.setGenre(genre);
        video.setReleaseYear(releaseYear);
        video.setLocation(location);
        video.setIsLent(isLent);
        video.setLendTo(lendTo);
        video.setLendDay(lendDay);
        video.setLendMonth(lendMonth);
        video.setLendYear(lentUntilYear);
        video.setLendUntilDay(lendUntilDay);
        video.setLendUntilMonth(lendUntilMonth);
        video.setLendUntilYear(lentUntilYear);
        video.setRating(rating);
        video.setDescription(description);
        video.setComment(annotation);

        doClose(returnCode);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void isLentCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isLentCheckBoxActionPerformed
        Object o = evt.getSource();
        if(o instanceof JCheckBox){
            JCheckBox checkBox = (JCheckBox)o;
            setLentFieldsEnabled(checkBox.isSelected());
        }
    }//GEN-LAST:event_isLentCheckBoxActionPerformed

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actorsLabel;
    private javax.swing.JTextField actorsTextField;
    private javax.swing.JLabel annotationLabel;
    private javax.swing.JTextField annotationTextField;
    private javax.swing.JLabel artistLabel;
    private javax.swing.JTextField artistTextField;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JTextField authorTextField;
    private javax.swing.JPanel bookPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JLabel directorLabel;
    private javax.swing.JTextField directorTextField;
    private javax.swing.JLabel eanLabel;
    private javax.swing.JTextField eanTextField;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JTextField genreTextField;
    private javax.swing.JCheckBox isLentCheckBox;
    private javax.swing.JLabel isLentLabel;
    private javax.swing.JLabel isbnLabel;
    private javax.swing.JTextField isbnTextField;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JTextField languageTextField;
    private javax.swing.JLabel lentDateLabel;
    private javax.swing.JComboBox lentDayComboBox;
    private javax.swing.JComboBox lentMonthComboBox;
    private javax.swing.JLabel lentToLabel;
    private javax.swing.JTextField lentToTextField;
    private javax.swing.JLabel lentUntilDateLabel;
    private javax.swing.JComboBox lentUntilDayComboBox;
    private javax.swing.JComboBox lentUntilMonthComboBox;
    private javax.swing.JComboBox lentUntilYearComboBox;
    private javax.swing.JComboBox lentYearComboBox;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JTextField locationTextField;
    private javax.swing.JPanel mediaPanel;
    private javax.swing.JComboBox musicFormatComboBox;
    private javax.swing.JLabel musicFormatLabel;
    private javax.swing.JPanel musicPanel;
    private javax.swing.ButtonGroup ratingButtonGroup;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JRadioButton ratingNoneRadioButton;
    private javax.swing.JRadioButton ratingOneRadioButton;
    private javax.swing.JRadioButton ratingThreeRadioButton;
    private javax.swing.JRadioButton ratingTwoRadioButton;
    private javax.swing.JComboBox releaseYearComboBox;
    private javax.swing.JLabel releaseYearLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JComboBox videoFormatComboBox;
    private javax.swing.JLabel videoFormatLabel;
    private javax.swing.JPanel videoPanel;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
