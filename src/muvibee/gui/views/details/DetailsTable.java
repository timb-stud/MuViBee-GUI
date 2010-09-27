/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui.views.details;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import muvibee.MuViBee;
import muvibee.lists.MediaList;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 * Erstellt DetailsTable Ansicht
 * @author Christian Rech
 */
public class DetailsTable extends JTable implements Observer{
    DefaultTableModel dtm;
    MediaList mediaList;

    /**
     * Kümmert sich um Tablemodel und fügt Selectionlistner hinzu
     * @param muvibee
     */
    public DetailsTable(final MuViBee muvibee) {
        super();
        dtm = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        setModel(dtm);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setEnabled(true);

        getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()){
                    if (getSelectedRow() != -1){
                        Media media = mediaList.getList().get(getSelectedRow());
                        if (media instanceof Book) {
                            muvibee.setCurrentBook((Book) media);
                        } else if (media instanceof Music) {
                            muvibee.setCurrentMusic((Music) media);
                        } else if (media instanceof Video) {
                            muvibee.setCurrentVideo((Video) media);
                        }
                    }
                }
            }
        });

    }

    /**
     * Sorgt dafür, dass Tabellenzellen nicht editirbar sind
     * @param row
     * @param col
     * @return liefert immer false
     */
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    /**
     * Fühlt die Tabelle mit den Daten von MediaList
     * @param mediaList
     */
    private void setColumnData(MediaList mediaList){
        String[] title = new String[mediaList.getList().size()];
        String[] mediaSpecial = new String[mediaList.getList().size()];
        String[] genre = new String[mediaList.getList().size()];
        String[] location = new String[mediaList.getList().size()];
        String[] year = new String[mediaList.getList().size()];
        String special = "n/a";
        int i = 0;

        for (Media m : mediaList.getList()){
            if (m instanceof Book) {
                mediaSpecial[i] = ((Book) m).getAuthor();
                special = "Author";
            } else if (m instanceof Music) {
                mediaSpecial[i] = ((Music) m).getInterpreter();
                special = "Interpreter";
            } else if (m instanceof Video) {
                mediaSpecial[i] = ((Video) m).getActors();
                special = "Actors";
            }

            title[i] = m.getTitle();
            genre[i] = m.getGenre();
            year[i] =  "" + m.getReleaseYear();
            location[i] = m.getLocation();
            i++;
        }
        dtm.addColumn("Title", title);
        dtm.addColumn(special, mediaSpecial);
        dtm.addColumn("Genre", genre);
        dtm.addColumn("ReleaseYear", year);
        dtm.addColumn("Location", location);
    }

    /**
     * Observer Pattern: Löscht aktuelle Liste und erstellt neue
     * @param list
     * @param arg
     */
    @Override
    public void update(Observable list, Object arg) {
        mediaList = ((MediaList) list);
        dtm = new DefaultTableModel();
        setModel(dtm);
        setColumnData(mediaList);
    }

}