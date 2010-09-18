/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.detailsList;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import muvibee.MuViBee;
import muvibee.lists.MediaList;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 *
 * @author Christian
 */
public class DetailsTable extends JTable implements Observer{

//    String[] columnNames = {"Title",
//                        "Author",
//                        "Genre",
//                        "Release Year",
//                        "Location"};
    DefaultTableModel dtm;
    MediaList mediaList;

    public DetailsTable(final MuViBee muvibee) {
        super();
        dtm = new DefaultTableModel();
        setModel(dtm);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setEnabled(true);

        getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()){
                    if (getSelectedRow() >= 0) { //hier wird selectedrow auf -1 gesetzt was zu nehm fehler führt. das passiert immer, wenn man eine zelle anklickt und dann zb auf hinzufügen drückt. dann verliert die zelle den focus und dieser listener wird mit -1 ausgelöst(man siehts am hellblauen rand der zelle).
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

    private void setColumnData(MediaList mediaList){
        String[] title = new String[mediaList.getList().size()];
        String[] mediaSpecial = new String[mediaList.getList().size()];
        String[] genre = new String[mediaList.getList().size()];
        String[] location = new String[mediaList.getList().size()];
        String[] year = new String[mediaList.getList().size()];
        int i = 0;

        for (Media m : mediaList.getList()){
            title[i] = m.getTitle();
            mediaSpecial[i] = getMediaSpecial(m);
            genre[i] = m.getGenre();
            year[i] =  "" + m.getReleaseYear();
            location[i] = m.getLocation();
            i++;
        }
        dtm.addColumn("Title", title);
        dtm.addColumn("Name fehlt", title);
        dtm.addColumn("Genre", genre);
        dtm.addColumn("ReleaseYear", year);
        dtm.addColumn("Location", location);
    }

    private String getMediaSpecial(Media m) {
        if (m instanceof Book) {
            return ((Book) m).getAuthor();
        } else if (m instanceof Music) {
            return ((Music) m).getInterpreter();
        } else if (m instanceof Video) {
            return ((Video) m).getActors();
        }
        return null;
    }


//    private void setData(MediaList mediaList) {
//        int i = 0;
//
//        for (Media m : mediaList.getList()){
//            for (int j = 0; j < columnNames.length; j++){
//                dtm.setValueAt(m.getTitle(), i, j);
//                dtm.setValueAt(m.getEan(), i, j);
//                dtm.setValueAt(m.getGenre(), i, j);
//                dtm.setValueAt(m.getReleaseYear(), i, j);
//                dtm.setValueAt(m.getLocation(), i, j);
//            }
//            i++;
//        }
//    }

    public void update(Observable list, Object arg) {
        mediaList = ((MediaList) list);
        dtm = new DefaultTableModel();
        setModel(dtm);
        setColumnData(mediaList);
    }

}
