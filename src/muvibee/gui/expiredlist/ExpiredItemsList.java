
package muvibee.gui.expiredlist;
import java.awt.Dimension;
import java.awt.event.ItemListener;
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
public class ExpiredItemsList extends JList implements Observer{
	DefaultListModel  listModel;
	CoverDetailsListRenderer lcr;


	public ExpiredItemsList(final MuViBee muvibee) {
		listModel = new DefaultListModel();
		lcr = new CoverDetailsListRenderer();
                setModel(listModel);
 	        setCellRenderer(lcr);
                setPrototypeCellValue(new ExpiredItemEntry(new Book()));
 	        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 	        setLayoutOrientation(JList.VERTICAL);
 	        setVisibleRowCount(10);


	        addListSelectionListener(new ListSelectionListener() {
	        	public void valueChanged(ListSelectionEvent evt){
                            if(evt.getValueIsAdjusting()){
                                if (getSelectedValue() != null) {
                                    muvibee.showSelectedMediaItem(((ExpiredItemEntry)getSelectedValue()).getMedia());
                                }
                                muvibee.getExpiredList().clearSelection();
                            }
	        	}
	        });

                getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}


	private void listAdd(ExpiredItemEntry entry){
            listModel.addElement(entry);
	}


        @Override
	public void update(Observable list, Object o) {
            LinkedList<Media> mList = ((MediaList) list).getList();
            listModel.clear();

            for (Media m : mList){
		listAdd(new ExpiredItemEntry(m));
            }
	}
}

