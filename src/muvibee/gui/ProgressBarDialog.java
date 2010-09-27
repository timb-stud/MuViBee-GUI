/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 *
 * @author bline
 */
public class ProgressBarDialog extends JDialog {
    JProgressBar sb;
    Runnable runnableCode;

    public ProgressBarDialog(MainFrame mainFrame, String string, boolean b) {
        super(mainFrame, string, b);
        setUndecorated(true);
        getContentPane().setLayout(new BorderLayout());
        sb = new JProgressBar(0, 11);
        sb.setStringPainted(true);
        sb.setPreferredSize(new Dimension(500, 100));
        getContentPane().add(sb, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setLocation(getLocation().x - 250, getLocation().y - 50);
        pack();
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {
                runCode();
            }
        });
    }

    public void incBar() {
        sb.setValue(sb.getValue() + 1);
    }
    
    private void runCode() {
        new Thread(runnableCode).start();
    }

    public void stopProgressBar() {
        sb.setValue(0);
        setVisible(false);
    }

    public void startProgressBar(Runnable runnableCode, int max) {
        this.runnableCode = runnableCode;
        sb.setIndeterminate(false);
        sb.setStringPainted(true);
        if (max == 0) max = 1;
        sb.setMaximum(max);
        sb.setMinimum(0);
        setVisible(true);
    }

    public void startProgressBar(Runnable runnableCode) {
        this.runnableCode = runnableCode;
        sb.setStringPainted(false);
        sb.setIndeterminate(true);
        setVisible(true);
    }
}
