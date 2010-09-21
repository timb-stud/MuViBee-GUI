/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import muvibee.MuViBee;

/**
 *
 * @author bline
 */
public class SearchActionListener implements ActionListener{
    private MuViBee mvb;

    public SearchActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o instanceof JButton){
            JButton button = (JButton)o;
            if(button.getName().equals("searchButton")){
                mvb.search();
            }else{
                if(button.getName().equals("advancedSearchButton")){
                    mvb.showAdvancedSearchDialog();
                }
            }
            mvb.showDeleteSearchButton(true);
            mvb.setListsColor(Color.YELLOW);
        }
    }

}
