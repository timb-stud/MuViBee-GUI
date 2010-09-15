package util.cover;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;


public class CoverList extends JPanel implements Observer{

	JList list;
	DefaultListModel listModel;

	
	public CoverList(final MuViBee muvibee) {

		setLayout(new BorderLayout());
		listModel = new DefaultListModel();
	    
	   	list = new JList();
	   	list.setModel(listModel);
	   	list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	   	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	   	
		CoverListRenderer cr = new CoverListRenderer(CoverListRenderer.rendererType.ICON_LIST);
		cr.setPreferredSize(new Dimension(100, 120));
		list.setCellRenderer(cr);
		list.setVisibleRowCount(-1);

                list.addListSelectionListener(new ListSelectionListener() {
	        	public void valueChanged(ListSelectionEvent evt){
                            if(evt.getValueIsAdjusting()){
                                Media media = ((CoverListEntry) listModel.getElementAt(list.getSelectedIndex())).getMedia();

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
                add(list, BorderLayout.CENTER);
	}
	
	private void listAdd(CoverListEntry entry){
		listModel.addElement(entry);
		list.validate();
                getParent().getParent().getParent().repaint();
                list.setPreferredSize(new Dimension(150, entry.getySize()*listModel.getSize()));
	}


        @Override
	public void update(Observable list, Object o) {
            LinkedList<Media> mList = ((MediaList) list).getList();
            listModel.clear();

            for (Media m : mList){
                listAdd(new CoverListEntry(m));
            }
	}
}
