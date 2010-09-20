/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import muvibee.MuViBee;

/**
 *
 * @author bline
 */
public class RestoreListener implements ActionListener {
    private MuViBee mvb;
    public RestoreListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
           JButton b = (JButton) e.getSource();
           if (b.getName().equals("restore everything")) {
               int[] indices = new int[mvb.getDeletedList().getModel().getSize()];
               for (int i = 0; i < mvb.getDeletedList().getModel().getSize(); i++) {
                   indices[i] = i;
               }
               mvb.getDeletedList().setSelectedIndices(indices);
               mvb.fillCurrentDeletedMedia(mvb.getDeletedList());
           }
           mvb.restoreCurrentDeletedMedia();
           mvb.setOverviewInformation();
        }
    }

}
