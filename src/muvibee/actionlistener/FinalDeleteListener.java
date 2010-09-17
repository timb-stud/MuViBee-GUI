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
public class FinalDeleteListener implements ActionListener{
    private MuViBee mvb;

    public FinalDeleteListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        mvb.removeCurrentDeletedMediaFromDeletedList();
        mvb.setOverviewInformation();
    }

}
