/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bline
 */
public class StatusBar extends JPanel implements Observer{
    private JLabel messageLabel;
    private JLabel iconLabel;
    private JPanel pRight, pLeft;

    public StatusBar(StatusBarModel model) {
        model.addObserver(this);
        setLayout(new BorderLayout());
        pLeft = new JPanel();
        pRight = new JPanel();
        messageLabel = new JLabel();
        iconLabel = new JLabel();
        pRight.add(messageLabel);
        pLeft.add(iconLabel);
        add(pLeft, BorderLayout.WEST);
        add(pRight, BorderLayout.CENTER);
    }

    public void update(Observable o, Object arg) {
        pRight.setBackground(((StatusBarModel)o).getColor());
        pLeft.setBackground(((StatusBarModel)o).getColor());
        iconLabel.setIcon(((StatusBarModel)o).getIcon());
        messageLabel.setText(((StatusBarModel)o).getMessage());
    }
}
