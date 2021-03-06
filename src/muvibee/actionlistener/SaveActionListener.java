/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import muvibee.MuViBee;
import muvibee.gui.StatusBarModel;

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
                try {
                    if (!mvb.setCurrentBookItemInformation()) return;
                } catch (Exception ex) {
                    StatusBarModel.getInstance().setFailMessage(ex.getMessage());
                    return;
                }
                mvb.addCurrentBookToBookLists();
            } else {
                if(button.getName().equals("save music button")){
                    try {
                        if (!mvb.setCurrentMusicItemInformation()) return;
                    } catch (Exception ex) {
                        StatusBarModel.getInstance().setFailMessage(ex.getMessage());
                        return;
                    }
		    mvb.addCurrentMusicToMusicLists();
                } else {
                    if(button.getName().equals("save video button")){
                        try {
                            if (!mvb.setCurrentVideoItemInformation()) return;
                        } catch (Exception ex) {
                            StatusBarModel.getInstance().setFailMessage(ex.getMessage());
                            return;
                        }
                        mvb.addCurrentVideoToVideoLists();
                    }
                }
            }
            mvb.setOverviewInformation();
        }
    }


}
