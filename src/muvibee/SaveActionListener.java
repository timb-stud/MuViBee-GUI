/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import muvibee.media.Book;

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
                mvb.setTmpBookItemInformation();
                mvb.updateBookList();
            } else {
                if(button.getName().equals("save music button")){

                } else {
                    if(button.getName().equals("save video button")){
                        mvb.setTmpVideoItemInformation();
                        mvb.updateVideoList();
                    }
                }
            }
        }
    }


}
