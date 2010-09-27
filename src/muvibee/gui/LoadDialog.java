/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.multi.MultiProgressBarUI;
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
            public void windowOpened(WindowEvent e) {
                startLoading(mvb);
            }
        });
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

    private void startLoading(final MuViBee mvb) {
        (new Thread() {
            @Override
            public void run() {
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
                setVisible(false);
            }
        }).start();
    }
}
