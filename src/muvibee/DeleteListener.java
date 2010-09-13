/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
                mvb.removeCurrentBookFromBookList();
                mvb.showBookItem(false);
            } else {
                if (button.getName().equals("deleteMusic")) {
                    mvb.removeCurrentMusicFromMusicList();
                    mvb.showMusicItem(false);
                } else {
                    if (button.getName().equals("deleteVideo")) {
                        mvb.removeCurrentVideoFromVideoList();
                        mvb.showVideoItem(false);
                    }
                }
            }
        }
    }

}
