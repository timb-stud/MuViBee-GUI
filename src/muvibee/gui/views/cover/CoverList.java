package muvibee.gui.views.cover;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import muvibee.MuViBee;
import muvibee.lists.MediaList;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 * Erstellt List für Cover Ansicht
 * @author Lucian Schneider, Christian Rech
 */
public class CoverList extends JList implements Observer{

	DefaultListModel listModel;

        /**
         * Kümmert sich um Aufbau der Liste und fügt SelectionListner hinzu
         * @param muvibee
         */
	public CoverList(final MuViBee muvibee) {
                setPrototypeCellValue("Index 1234567890");

		listModel = new DefaultListModel();
	   	setModel(listModel);
	   	setLayoutOrientation(JList.HORIZONTAL_WRAP);
	   	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		CoverListRenderer cr = new CoverListRenderer();
		cr.setPreferredSize(new Dimension(120, 140));
		setCellRenderer(cr);
		setVisibleRowCount(-1);

                addListSelectionListener(new ListSelectionListener() {
	        	public void valueChanged(ListSelectionEvent evt){
                            if(evt.getValueIsAdjusting()){
                                Media media = ((CoverListEntry) listModel.getElementAt(getSelectedIndex())).getMedia();

                                if (media instanceof Book) {
                                    muvibee.setCurrentBook((Book) media);
                                } else if (media instanceof Music) {
                                    muvibee.setCurrentMusic((Music) media);
                                } else if (media instanceof Video) {
                                    muvibee.setCurrentVideo((Video) media);
                                }
                            }
	        	}
	        });
	}

        /**
         * Kümmert sich ums hinzufügen eines Elements
         * @param entry
         */
	private void listAdd(CoverListEntry entry){
		listModel.addElement(entry);
		validate();
               //getParent().getParent().getParent().repaint();
	}


        /**
         * Observer Pattern: Wird aufgerufen sobald sich MediaList verändert.
         * Löscht liste und erzeugt neue
         * @param list - neue Liste
         * @param o - ist leer
         */
        @Override
	public void update(Observable list, Object o) {
            LinkedList<Media> mList = ((MediaList) list).getList();
            listModel.clear();

            for (Media m : mList){
                listAdd(new CoverListEntry(m));
            }
	}
}
