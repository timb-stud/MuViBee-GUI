/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author bline
 */
public class ProgressBarDialog extends JDialog  {
    private JProgressBar sb;
    private Runnable runnableCode;
    private Task task;
    private int progress;
    private static ProgressBarDialog uniqueInstance;

    public static ProgressBarDialog getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProgressBarDialog();
        }
        return uniqueInstance;
    }

     private class Task extends SwingWorker<Void, Void> implements PropertyChangeListener{

         public Task() {
             addPropertyChangeListener(this);
         }
         
        @Override
        protected Void doInBackground() throws Exception {
            runnableCode.run();
            return null;
        }

        @Override
        protected void done () {
            progress = 0;
            sb.setValue(0);
            setVisible(false);
            task = new Task();
        }

        public void propertyChange(PropertyChangeEvent evt) {
            if ("progress".equals(evt.getPropertyName())) {
                sb.setValue(progress);
            }
         }

        public void incProgress() {
            setProgress(++progress);
        }

     }

    private ProgressBarDialog() {
        setLocation(new Point(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2));
        setModal(true);
        setUndecorated(true);
        getContentPane().setLayout(new BorderLayout());
        sb = new JProgressBar(0, 11);
        sb.setStringPainted(true);
        sb.setPreferredSize(new Dimension(500, 100));
        getContentPane().add(sb, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setLocation(getLocation().x - 250, getLocation().y - 50);
        task = new Task();
        pack();
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {
                runCode();
            }
        });
    }

    public void incBar() {
       task.incProgress();
    }
    
    private void runCode() {
       task.execute();
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
