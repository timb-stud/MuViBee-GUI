/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;

/**
 *
 * @author bline
 */
public class ComboBoxItemRenderer extends JLabel implements ListCellRenderer {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) defaultRenderer
        .getListCellRendererComponent(list, value, index, isSelected,
            cellHasFocus);
        if (value instanceof JScrollPane) {
            if (((JViewport) (((JScrollPane)value).getComponents()[0])).getComponent(0) instanceof JTree) {
                renderer.setText("Baum");
            } else {
                renderer.setText("Liste");
            }
    }
        return renderer;
    }

}
