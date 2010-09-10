package util;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class CoverDetailsList extends JPanel{
	DefaultListModel  listModel;
	CoverDetailsListRenderer lcr;
	JList list;

	public CoverDetailsList() {
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
	        		System.out.println("Feld: " + list.getSelectedIndex() + " " + (evt.getValueIsAdjusting()?"PREP":"SEL"));
	        	}
	        });


	        list.setPreferredSize(new Dimension(150,600));

	        add(list, BorderLayout.NORTH);
	}


	public void listAdd(CoverDetailsListEntry entry){
		listModel.addElement(entry);
		list.validate();
	}
}

