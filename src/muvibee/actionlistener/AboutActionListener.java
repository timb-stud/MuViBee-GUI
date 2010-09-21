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
 * @author Christian
 */
public class AboutActionListener implements ActionListener{
    private MuViBee mvb;

    public AboutActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
            mvb.showAboutDialog();
    }

}
