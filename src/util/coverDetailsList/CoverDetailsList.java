
package util.coverDetailsList;

import java.awt.BorderLayout;
import java.awt.Component;
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
import muvibee.MuViBee;
import muvibee.lists.MediaList;

import muvibee.media.*;

@SuppressWarnings("serial")
public class CoverDetailsList extends JPanel implements Observer{
	DefaultListModel  listModel;
	CoverDetailsListRenderer lcr;
	JList list;


	public CoverDetailsList(final MuViBee muvibee) {

//           
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
                            if(evt.getValueIsAdjusting()){
                                Object object = listModel.getElementAt(list.getSelectedIndex());
                                if (object instanceof CoverDetailsListEntryBook) {
                                    muvibee.setCurrentBook(((CoverDetailsListEntryBook) object).getBook());
                                } else if (object instanceof CoverDetailsListEntryMusic) {
                                    muvibee.setCurrentMusic(((CoverDetailsListEntryMusic) object).getMusic());
                                } else if ( object instanceof CoverDetailsListEntryVideo) {
                                    muvibee.setCurrentVideo(((CoverDetailsListEntryVideo) object).getVideo());

                                }
                            }
	        	}
	        });
 	        
	        list.setPreferredSize(new Dimension(150,600));
	        
	        add(list, BorderLayout.NORTH);
	}
	
	
	private void listAdd(CoverDetailsListEntry entry){
		listModel.addElement(entry);
		list.validate();
                getParent().getParent().getParent().repaint();
	}


        @Override
	public void update(Observable list, Object o) {
            LinkedList<Media> mList = ((MediaList) list).getList();
            listModel.clear();

            for (Media m : mList){
                if (m instanceof Book)
			listAdd(new CoverDetailsListEntryBook((Book)m));
		else if (m instanceof Video)
			listAdd(new CoverDetailsListEntryVideo((Video) m));
		else if (m instanceof Music)
			listAdd(new CoverDetailsListEntryMusic((Music) m));
                else
                    System.out.println("nix von allem");
            }
	}
}

