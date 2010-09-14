
package util.coverDetailsList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import muvibee.lists.BookList;
import muvibee.lists.MediaList;
import muvibee.lists.MusicList;
import muvibee.lists.VideoList;

import muvibee.media.*;

@SuppressWarnings("serial")
public class CoverDetailsList extends JPanel implements Observer{
	DefaultListModel  listModel;
	CoverDetailsListRenderer lcr;
	JList list;
	
	public CoverDetailsList(MediaList mediaList) {
                setLayout(new BorderLayout());
		
		listModel = new DefaultListModel();
		lcr = new CoverDetailsListRenderer();
 	        list = new JList(listModel);
 	        list.setCellRenderer(lcr);
 	        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 	        list.setLayoutOrientation(JList.VERTICAL);
 	        list.setVisibleRowCount(10);
 	        
	        list.addListSelectionListener(new ListSelectionListener() {
	        	public void valueChanged(ListSelectionEvent evt){
                            if(evt.getValueIsAdjusting())
                                mediaList.getList().get(list.getSelectedIndex());
//	        		System.out.println("Feld: " + list.getSelectedIndex() + " " + (evt.getValueIsAdjusting()?"PREP":"SEL"));
	        	}
	        });
 	        
	        list.setPreferredSize(new Dimension(150,600));
	        
	        add(list, BorderLayout.NORTH);
	}
	
	
	private void listAdd(CoverDetailsListEntry entry){
		listModel.addElement(entry);
		list.validate();
	}


        @Override
	public void update(Observable list, Object media) {
            LinkedList<Media> mList = ((MediaList) list).getList();
            listModel.clear();

            for (Media m : mList){
                if (media instanceof Book)
			listAdd(new CoverDetailsListEntryBook((Book)media));
		else if (media instanceof Video)
			listAdd(new CoverDetailsListEntryVideo((Video) media));
		else if (media instanceof Music)
			listAdd(new CoverDetailsListEntryMusic((Music) media));
            }
	}
}

