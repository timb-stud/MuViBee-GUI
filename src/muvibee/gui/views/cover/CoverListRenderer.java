package muvibee.gui.views.cover;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

/**
 * CellRenderer für CoverList Ansicht
 * @author Lucian Schneider
 */

@SuppressWarnings("serial")
public class CoverListRenderer extends JLabel implements ListCellRenderer {

        /**
         * Setzt Boder für Darstellung
         */
	public CoverListRenderer() {
		setOpaque(true);
	        Border compound = BorderFactory.createCompoundBorder(
	        		BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
	        Border iconGap = BorderFactory.createCompoundBorder(
	        		BorderFactory.createLineBorder(Color.WHITE, 5), compound);
	        this.setBorder(iconGap);
	}
		

        /**
         *  Setzt verhalten der einzelnen Elemente fest
         */
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
