/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.Component;
import java.util.ResourceBundle;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import muvibee.MuViBee;

/**
 *
 * @author bline
 */
public class ComboBoxItemRenderer extends JLabel implements ListCellRenderer {
    private  DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public ComboBoxItemRenderer() {
        
    }
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        JLabel renderer = (JLabel) defaultRenderer
        .getListCellRendererComponent(list, value, index, isSelected,
            cellHasFocus);
        if (value instanceof JScrollPane) {
            if (((JScrollPane)value).getName().equals("tree")) {
                renderer.setText(bundle.getString("tree"));
            } else {
                if (((JScrollPane)value).getName().equals("cover")) {
                    renderer.setText(bundle.getString("cover"));
                } else {
                    if (((JScrollPane)value).getName().equals("cover details")) {
                        renderer.setText(bundle.getString("coverDetails"));
                    } else {
                        if (((JScrollPane)value).getName().equals("details")) {
                            renderer.setText(bundle.getString("details"));
                        }
                    }
                }
            }
        }
        return renderer;
    }
}
