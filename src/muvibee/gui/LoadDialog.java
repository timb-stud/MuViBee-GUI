/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author bline
 */
public class LoadDialog extends JDialog {
    JProgressBar sb;

    public LoadDialog() {
        setLayout(new BorderLayout());
        setSize(100, 30);
        sb = new JProgressBar(0, 11);
        add(sb, BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
    }

    public void incBar() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                sb.setValue(sb.getValue() + 1);
            }
        });

    }

//    public static void main(String[] args) {
//        LoadDialog l = new LoadDialog();
//        l.setVisible(true);
//    }
}
