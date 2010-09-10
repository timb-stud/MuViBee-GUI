/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author bline
 */
public class AddActionListener implements ActionListener {
    private JScrollPane itemBooksPanel, itemMusicPanel, itemVideoPanel;
    private JButton addBooksButton, addMusicButton, addVideoButton;
    private SetEANFrame setEANFrame;
    private Object[] options = {"EAN", "Selbst"};

    public AddActionListener(JScrollPane itemBooksPanel, JScrollPane itemMusicPanel, JScrollPane itemVideoPanel, JButton addBooksButton, JButton addMusicButton, JButton addVideoButton) {
        this.itemBooksPanel = itemBooksPanel;
        this.itemMusicPanel = itemMusicPanel;
        this.itemVideoPanel = itemVideoPanel;
        this.addBooksButton = addBooksButton;
        this.addMusicButton = addMusicButton;
        this.addVideoButton = addVideoButton;
        setEANFrame = new SetEANFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JScrollPane p = null;
            JButton b = (JButton) e.getSource();
            if (b == addBooksButton) {
                p = itemBooksPanel;
            } else {
                if (b == addMusicButton) {
                    p = itemMusicPanel;
                } else {
                    if (b == addVideoButton) {
                        p = itemVideoPanel;
                    }
                }
            }

            setEANFrame.setJScrollPane(p);
            int n = JOptionPane.showOptionDialog(p,
                "Wollen Sie das Medium selbst anlegen oder per EAN ?",
                "Bitte wählen",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
             if (n == 0) {
                setEANFrame.setVisible(true);
             } else {
                if (n > 0) {
                    p.setVisible(true);
                    p.getParent().validate();
                 }
             }

        }



    }
}
