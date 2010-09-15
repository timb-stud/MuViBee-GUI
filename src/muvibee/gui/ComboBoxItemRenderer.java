/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author bline
 */
public class ComboBoxItemRenderer extends JLabel implements ListCellRenderer {
    private  DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public ComboBoxItemRenderer() {
        
    }
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) defaultRenderer
        .getListCellRendererComponent(list, value, index, isSelected,
            cellHasFocus);
        if (value instanceof JScrollPane) {
            if (((JScrollPane)value).getName().equals("Tree")) {
                renderer.setText("Baum");
            } else {
                if (((JScrollPane)value).getName().equals("cover")) {
                    renderer.setText("Cover");
                } else {
                    if (((JScrollPane)value).getName().equals("cover details")) {
                        renderer.setText("Cover Details");
                    } else {
                        if (((JScrollPane)value).getName().equals("details")) {
                            renderer.setText("Details");
                        }
                    }
                }
            }
        }
        return renderer;
    }
}
