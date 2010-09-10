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
public class AddActionListener implements ActionListener {
    private MuViBee mvb;
    
    public AddActionListener(MuViBee mvb) {
        this.mvb = mvb; 
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton){
            JButton button = (JButton)source;
            int decision = mvb.showDecisionFrame();
            if(button.getName().equals("add book button")){
                Book book;
                if(decision == 0){
                    String ean = mvb.showSetEANFrame();
                    book = new Book("Test", "bla"); //TODO getBook(ean);
                }else{
                    book = new Book();
                }
                mvb.showBookItem(book);
                mvb.setTmpBook(book);
            }else{
                if(button.getName().equals("add music button")){

                }else{
                    if(button.getName().equals("add video button")){

                    }
                }
            }
        }
    }

}
