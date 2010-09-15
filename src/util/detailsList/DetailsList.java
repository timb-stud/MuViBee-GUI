
package util.detailsList;

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
import muvibee.MuViBee;
import muvibee.lists.MediaList;

import muvibee.media.*;

@SuppressWarnings("serial")
public class DetailsList extends JList implements Observer{
	DefaultListModel  listModel;
	DetailsListRenderer lcr;

	public DetailsList(final MuViBee muvibee) {

                setLayout(new BorderLayout());
		
		listModel = new DefaultListModel();
		lcr = new DetailsListRenderer();
 	        setCellRenderer(lcr);
 	        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 	        setLayoutOrientation(JList.VERTICAL);
 	        setVisibleRowCount(10);
                setModel(listModel);
 	        
	        addListSelectionListener(new ListSelectionListener() {
	        	public void valueChanged(ListSelectionEvent evt){
                            if(evt.getValueIsAdjusting()){
                                Object object = listModel.getElementAt(getSelectedIndex());
                                if (object instanceof DetailsListEntryBook) {
                                    muvibee.setCurrentBook(((DetailsListEntryBook) object).getBook());
                                } else if (object instanceof DetailsListEntryMusic) {
                                    muvibee.setCurrentMusic(((DetailsListEntryMusic) object).getMusic());
                                } else if ( object instanceof DetailsListEntryVideo) {
                                    muvibee.setCurrentVideo(((DetailsListEntryVideo) object).getVideo());

                                }
                            }
	        	}
	        });
	}
	
	
	private void listAdd(DetailsListEntry entry){
		listModel.addElement(entry);
		validate();
                getParent().getParent().getParent().repaint();
                setPreferredSize(new Dimension(150, entry.getySize()*listModel.getSize()));
	}


        @Override
	public void update(Observable list, Object o) {
            LinkedList<Media> mList = ((MediaList) list).getList();
            listModel.clear();

            for (Media m : mList){
                if (m instanceof Book)
			listAdd(new DetailsListEntryBook((Book)m));
		else if (m instanceof Video)
			listAdd(new DetailsListEntryVideo((Video) m));
		else if (m instanceof Music)
			listAdd(new DetailsListEntryMusic((Music) m));
                else
                    System.out.println("nix von allem");
            }
	}
}

