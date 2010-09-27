package muvibee.gui.views.coverdetails;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;


/**
 * CellRenderer für CoverDetailsList
 * @author Christian Rech
 */
public class CoverDetailsListRenderer implements ListCellRenderer {
		private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 225);
		Border selectedBorder = BorderFactory.createLineBorder(HIGHLIGHT_COLOR,2);
		Border normalBorder = BorderFactory.createEmptyBorder();
	    
	    
	    public Component getListCellRendererComponent(
                                                        JList list,
                                                        Object value,
                                                        int index,
                                                        boolean isSelected,
                                                        boolean hasFocus)
	    {
	    	JComponent component;
	    	
	    	if(value instanceof Component)
	    		component = (JComponent)value;
	    	else
	    		component = new JPanel();
	    	
	    	if(isSelected) 
	    		component.setBorder(selectedBorder);
			else 
				component.setBorder(normalBorder);
		    
		    return component;
	    }
}


