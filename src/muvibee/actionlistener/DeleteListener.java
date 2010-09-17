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
public class DeleteListener implements ActionListener{
    private MuViBee mvb;

    public DeleteListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton){
            JButton button = (JButton)source;
            if(button.getName().equals("deleteBook")){
                mvb.removeCurrentBookFromBookLists();
            } else {
                if (button.getName().equals("deleteMusic")) {
                    mvb.removeCurrentMusicFromMusicLists();
                } else {
                    if (button.getName().equals("deleteVideo")) {
                        mvb.removeCurrentVideoFromVideoLists();
                    }
                }
            }
            mvb.setOverviewInformation();
        }
    }

}
