/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import muvibee.MuViBee;

/**
 *
 * @author bline
 */
public class ResetSearchActionListener implements ActionListener {
    private MuViBee mvb;
    public ResetSearchActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        mvb.resetSearch();
    }

}
