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
import muvibee.utils.NonValidYearException;

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
                    if(!mvb.setCurrentBookItemInformation()) return;
               } catch (NonValidYearException ex) {
                   StatusBarModel.getInstance().setFailMessage("Ungüliges Jahr");
                   return;
               }
                mvb.addCurrentBookToBookLists();
            } else {
                if(button.getName().equals("save music button")){
		try {
                    if(!mvb.setCurrentMusicItemInformation()) return;
                } catch (NonValidYearException ex) {
                   StatusBarModel.getInstance().setFailMessage("Ungüliges Jahr");
                   return;
                }
		    mvb.addCurrentMusicToMusicLists();
                } else {
                    if(button.getName().equals("save video button")){
                        try {
                            if(!mvb.setCurrentVideoItemInformation()) return;
                        } catch (NonValidYearException ex) {
                            StatusBarModel.getInstance().setFailMessage("Ungüliges Jahr");
                            return;
                        }
                        mvb.addCurrentVideoToVideoLists();
                    }
                }
            }
        }
    }


}
