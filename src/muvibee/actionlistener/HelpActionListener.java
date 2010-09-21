/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import muvibee.MuViBee;

/**
 * Actionlisterner für Hilfe Knopf
 * @author Christian Rech
 */
public class HelpActionListener implements ActionListener{
    private MuViBee mvb;

    public HelpActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
            mvb.showHelpDialog();
    }
}
