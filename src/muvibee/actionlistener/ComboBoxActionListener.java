/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.actionlistener;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author bline
 */
public class ComboBoxActionListener implements ActionListener {

    private JPanel bookCardPanel;
    private JPanel musicCardPanel;
    private JPanel videoCardPanel;
    private JComboBox viewBookComboBox;
    private JComboBox viewMusicComboBox;
    private JComboBox viewVideoComboBox;

    public ComboBoxActionListener(JPanel bookCardPanel, JPanel musicCardPanel, JPanel videoCardPanel, JComboBox viewBookComboBox, JComboBox viewMusicComboBox, JComboBox viewVideoComboBox) {
        this.bookCardPanel = bookCardPanel;
        this.musicCardPanel = musicCardPanel;
        this.videoCardPanel = videoCardPanel;
        this.viewBookComboBox = viewBookComboBox;
        this.viewMusicComboBox = viewMusicComboBox;
        this.viewVideoComboBox = viewVideoComboBox;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            if (cb == viewBookComboBox) {
                ((CardLayout)bookCardPanel.getLayout()).show(bookCardPanel, ((Component)cb.getSelectedItem()).getName());
            } else {
                if (cb == viewMusicComboBox) {
                    ((CardLayout)musicCardPanel.getLayout()).show(musicCardPanel, ((Component)cb.getSelectedItem()).getName());
                } else {
                    if (cb == viewVideoComboBox) {
                        ((CardLayout)videoCardPanel.getLayout()).show(videoCardPanel, ((Component)cb.getSelectedItem()).getName());
                    }
                }
            }
        }
    }
}
