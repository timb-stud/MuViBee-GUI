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
public class SaveActionListener implements ActionListener {
    private MuViBee mvb;

    public SaveActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton){
            JButton button = (JButton)source;
            if(button.getName().equals("save book button")){
                mvb.setCurrentBookItemInformation();
                mvb.addCurrentBookToBookList();
            } else {
                if(button.getName().equals("save music button")){
		    mvb.setCurrentMusicItemInformation();
		    mvb.addCurrentMusicToMusicList();
                } else {
                    if(button.getName().equals("save video button")){
                        mvb.setCurrentVideoItemInformation();
                        mvb.addCurrentVideoToVideoList();
                    }
                }
            }
        }
    }


}
