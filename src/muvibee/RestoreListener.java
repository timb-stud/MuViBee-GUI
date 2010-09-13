/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        mvb.restoreCurrentDeletedMedia();
    }

}
