/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.detailsList;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import muvibee.lists.MediaList;
import muvibee.media.Media;

/**
 *
 * @author Christian
 */
public class DetailsTable extends JTable implements Observer{

    String[] columnNames = {"Title",
                        "Author",
                        "Genre",
                        "Release Year",
                        "Location"};
    DefaultTableModel dtm;

    public DetailsTable() {
        super();
        dtm = new DefaultTableModel();
        setModel(dtm);
      //TODO ActionListener
//        dtm.addColumn("Titel", columnNames);

//        setSelectionMode(SINGLE_SELECTION);
        
    }

    private void setColumnData(MediaList mediaList){
        String[] title = new String[mediaList.getList().size()];
        String[] genre = new String[mediaList.getList().size()];
        String[] location = new String[mediaList.getList().size()];
        String[] year = new String[mediaList.getList().size()];
        int i = 0;

        for (Media m : mediaList.getList()){
            title[i] = m.getTitle();
            genre[i] = m.getGenre();
            year[i] =  "" + m.getReleaseYear();
            location[i] = m.getLocation();
            i++;
        }
        dtm.addColumn("Title", title);
        dtm.addColumn("Genre", genre);
        dtm.addColumn("ReleaseYear", year);
        dtm.addColumn("Location", location);
    }

    private void setData(MediaList mediaList) {
        int i = 0;

        for (Media m : mediaList.getList()){
            for (int j = 0; j < columnNames.length; j++){
                dtm.setValueAt(m.getTitle(), i, j);
                dtm.setValueAt(m.getEan(), i, j);
                dtm.setValueAt(m.getGenre(), i, j);
                dtm.setValueAt(m.getReleaseYear(), i, j);
                dtm.setValueAt(m.getLocation(), i, j);
            }
            i++;
        }
    }

    public void update(Observable list, Object arg) {
        MediaList mediaList = ((MediaList) list);
        dtm = new DefaultTableModel();
        setModel(dtm);
        //        setData(mediaList);
        setColumnData(mediaList);
    }

}
