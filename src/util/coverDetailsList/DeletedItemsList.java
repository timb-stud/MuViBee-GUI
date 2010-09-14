
package util.coverDetailsList;
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

@SuppressWarnings("serial")
public class DeletedItemsList extends JList implements Observer{
	DefaultListModel  listModel;
	CoverDetailsListRenderer lcr;


	public DeletedItemsList(final MuViBee muvibee) {
		listModel = new DefaultListModel();
		lcr = new CoverDetailsListRenderer();
 	        setCellRenderer(lcr);
 	        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 	        setLayoutOrientation(JList.VERTICAL);
 	        setVisibleRowCount(10);
                setModel(listModel);

	        addListSelectionListener(new ListSelectionListener() {
	        	public void valueChanged(ListSelectionEvent evt){
                            if(evt.getValueIsAdjusting()){
                                Object object = listModel.getElementAt(getSelectedIndex());
                                muvibee.setCurrentDeletedMedia(((DeletedItemEntry) object).getMedia());
                            }
	        	}
	        });

	        setPreferredSize(new Dimension(150,600));
	}


	private void listAdd(DeletedItemEntry entry){
            listModel.addElement(entry);
            validate();
            getParent().getParent().getParent().repaint();
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

