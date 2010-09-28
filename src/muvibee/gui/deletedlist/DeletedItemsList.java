
package muvibee.gui.deletedlist;
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

import muvibee.media.*;
import muvibee.gui.views.coverdetails.CoverDetailsListRenderer;

@SuppressWarnings("serial")
public class DeletedItemsList extends JList implements Observer{
	DefaultListModel  listModel;
	CoverDetailsListRenderer lcr;


	public DeletedItemsList(final MuViBee muvibee) {
		listModel = new DefaultListModel();
		lcr = new CoverDetailsListRenderer();
                setModel(listModel);
                setCellRenderer(lcr);
                setPrototypeCellValue(new DeletedItemEntry(new Book()));
 	        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 	        setLayoutOrientation(JList.VERTICAL);
 	        setVisibleRowCount(10);


	        addListSelectionListener(new ListSelectionListener() {
	        	public void valueChanged(ListSelectionEvent evt){
                            if(evt.getValueIsAdjusting()){
                                muvibee.fillCurrentDeletedMedia(muvibee.getDeletedList());
                            }
	        	}
	        });

                getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}


	private void listAdd(DeletedItemEntry entry){
            listModel.addElement(entry);
	}


        @Override
	public void update(Observable list, Object o) {
            LinkedList<Media> mList = ((MediaList) list).getList();
            listModel.clear();

            for (Media m : mList){
		listAdd(new DeletedItemEntry(m));
            }
	}
}

