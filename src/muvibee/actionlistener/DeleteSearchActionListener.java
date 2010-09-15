/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import muvibee.MuViBee;

/**
 *
 * @author bline
 */
public class DeleteSearchActionListener implements ActionListener {
    private MuViBee mvb;
    public DeleteSearchActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        mvb.resetSearch();
    }

}
