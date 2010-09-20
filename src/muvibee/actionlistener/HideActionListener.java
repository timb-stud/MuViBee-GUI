/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import muvibee.MuViBee;

/**
 *
 * @author bline
 */
public class HideActionListener implements ActionListener{
    private MuViBee mvb;
    
    public HideActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton){
            JButton button = (JButton)source;
            mvb.unselectLists();
            if(button.getName().equals("hide book")){
                mvb.showBookItem(false);
            } else {
                if (button.getName().equals("hide music")) {
                    mvb.showMusicItem(false);
                } else {
                    if (button.getName().equals("hide video")) {
                        mvb.showVideoItem(false);
                    }
                }
            }
        }
    }

}
