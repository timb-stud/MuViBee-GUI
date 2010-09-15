package util.coversList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class CoverListRenderer extends JLabel implements ListCellRenderer {

	
	protected static enum rendererType {SIMPLE_LIST, ICON_LIST, DETAIL_ICON_LIST, TREE_VIEW};
		
	
	public CoverListRenderer(rendererType type) {
		
		switch (type) {
		
		case SIMPLE_LIST :
			setOpaque(true);
	        Border listGap = BorderFactory.createLineBorder(Color.GREEN, 1);
	        this.setBorder(listGap);
			break;
			
		case ICON_LIST :
			setOpaque(true);
	        Border compound = BorderFactory.createCompoundBorder(
	        		BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
	        Border iconGap = BorderFactory.createCompoundBorder(
	        		BorderFactory.createLineBorder(Color.WHITE, 5), compound);
	        this.setBorder(iconGap);
			break;
		
		case DETAIL_ICON_LIST :
			setOpaque(true);
			final Color HIGHLIGHT_COLOR = new Color(200, 0, 225);
			final Border selectedBorder = BorderFactory.createLineBorder(HIGHLIGHT_COLOR, 2);
			final Border normalBorder = BorderFactory.createEmptyBorder();
//			this.
			break;
				
		}
		
	}

	@Override
	public Component getListCellRendererComponent(JList list,
	        Object value,
	        int index,
	        boolean isSelected,
	        boolean cellHasFocus) 
	{
		
		// Simple Icon List
		if (value instanceof CoverListEntry) {
                    CoverListEntry entry = (CoverListEntry) value;
                    if (entry != null) {
                            setIcon(entry.getIcon());
                    }
		}
		
//		// Simple List
//		if (value instanceof Entry) {
//                    Entry entry = (Entry) value;
//                    if (entry != null) {
//                            setText(entry.getTitle() + " | " + entry.getAuthor());
//                            setFont(list.getFont().deriveFont(Font.BOLD, 14));
//                    }
//		}
//
		// Detailed Icon List
//		if (value instanceof ListEntry) {
//			Component le = (ListEntry) value;
//	    	le = (JComponent) value;
	    	
//	    	if(isSelected) 
//	    		le.setBorder(selectedBorder);
//			else 
//				le.setBorder(normalBorder);
//		    
//		    return le;
//		}
		
            if (isSelected) {
              setBackground(Color.cyan);
              setForeground(Color.white);
            } else {
              setBackground(Color.white);
              setForeground(Color.black);
            }

            return this;
	}
	
	
}
