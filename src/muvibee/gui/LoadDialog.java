/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import muvibee.MuViBee;
import muvibee.db.DBSelector;

/**
 *
 * @author bline
 */
public class LoadDialog extends JDialog {
    JProgressBar sb;

    public LoadDialog(MainFrame mainFrame, String string, boolean b, final MuViBee mvb) {
        super(mainFrame, string, b);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setSize(100, 30);
        sb = new JProgressBar(0, 11);
        getContentPane().add(sb, BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        (new Thread() {
            @Override
            public void run() {
                doWork(mvb);
            }
        }).start();
        setVisible(true);
    }

    public void incBar() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                sb.setValue(sb.getValue() + 1);
            }
        });

    }

    private void doWork(MuViBee mvb) {
        incBar();
        mvb.setBookList(DBSelector.getBookList(false, null));
        incBar();
        mvb.setMusicList(DBSelector.getMusicList(false, null));
        incBar();
        mvb.setVideoList(DBSelector.getVideoList(false, null));
        incBar();
        mvb.getFilterBookList().addAll(mvb.getBookList());
        incBar();
        mvb.getFilterMusicList().addAll(mvb.getMusicList());
        incBar();
        mvb.getFilterVideoList().addAll(mvb.getVideoList());
        incBar();
        mvb.getDeletedMediaList().addAll(DBSelector.getBookList(true, null));
        incBar();
        mvb.getDeletedMediaList().addAll(DBSelector.getMusicList(true, null));
        incBar();
        mvb.getDeletedMediaList().addAll(DBSelector.getVideoList(true, null));
        incBar();
        mvb.setOverviewInformation();
        incBar();
        setVisible(false);
    }
}
