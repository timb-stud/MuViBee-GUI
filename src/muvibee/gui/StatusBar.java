/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bline
 */
public class StatusBar extends JPanel implements Observer{
    private JLabel label;

    public StatusBar(StatusBarModel model) {
        model.addObserver(this);
        label = new JLabel();
        add(label);
    }

    public void update(Observable o, Object arg) {
        this.setBackground(((StatusBarModel)o).getColor());
        label.setText(((StatusBarModel)o).getInstance().getMessage());
    }
}
