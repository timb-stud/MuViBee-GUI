/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;



/**
 *
 * @author bline
 */
public class ComboBoxActionListener implements ActionListener {
    private JLayeredPane viewBookLayeredPane;
    private JLayeredPane viewMusicLayeredPane;
    private JLayeredPane viewVideoLayeredPane;
    private JComboBox viewBookComboBox;
    private JComboBox viewMusicComboBox;
    private JComboBox viewVideoComboBox;

    ComboBoxActionListener(JLayeredPane viewBookLayeredPane, JLayeredPane viewMusicLayeredPane, JLayeredPane viewVideoLayeredPane, JComboBox viewBookComboBox, JComboBox viewMusicComboBox, JComboBox viewVideoComboBox) {
        this.viewBookLayeredPane = viewBookLayeredPane;
        this.viewMusicLayeredPane = viewMusicLayeredPane;
        this.viewVideoLayeredPane = viewVideoLayeredPane;
        this.viewBookComboBox = viewBookComboBox;
        this.viewMusicComboBox = viewMusicComboBox;
        this.viewVideoComboBox = viewVideoComboBox;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            if (cb == viewBookComboBox) {
                viewBookLayeredPane.setPosition((Component)cb.getSelectedItem(), 0);
            } else {
                if (cb == viewMusicComboBox) {
                    viewMusicLayeredPane.setPosition((Component)cb.getSelectedItem(), 0);
                } else {
                    if (cb == viewVideoComboBox) {
                        viewVideoLayeredPane.setPosition((Component)cb.getSelectedItem(), 0);
                    }
                  }
                }
            }
        }
    }
