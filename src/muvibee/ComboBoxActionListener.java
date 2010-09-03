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
    private JLayeredPane lp;

    public ComboBoxActionListener(JLayeredPane lp) {
        this.lp = lp;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            if (cb.getName().equals("ViewComboBox")) {
                if (cb.getSelectedItem().equals("Tree")) {
                    for (Component c : lp.getComponents()) {
                        if (c.getName().equals("Tree")) {
                            lp.setPosition(c, 0);
                        }
                    }
                } else {
                    if (cb.getSelectedItem().equals("Cover Details")) {
                        for (Component c : lp.getComponents()) {
                            if (c.getName().equals("Cover Details")) {
                                lp.setPosition(c, 0);
                            }
                        }
                    } else {
                        if (cb.getSelectedItem().equals("Cover")) {
                            for (Component c : lp.getComponents()) {
                                if (c.getName().equals("Cover")) {
                                    lp.setPosition(c, 0);
                                }
                             }
                        } else {
                            if (cb.getSelectedItem().equals("Details")) {
                                for (Component c : lp.getComponents()) {
                                    if (c.getName().equals("Details")) {
                                        lp.setPosition(c, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                //Langauges
            }
        }
    }

}
