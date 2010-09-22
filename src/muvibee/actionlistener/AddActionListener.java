/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import muvibee.MuViBee;
import muvibee.ean.EanBol;
import muvibee.gui.StatusBarModel;
import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;



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
            int decision = mvb.showSelfEANDecisionFrame();
            if (decision >= 0) {
                mvb.resetSearch();
                if(button.getName().equals("add book button")){
                    Book book;
                    if(decision == 0){
                        String ean = mvb.showEanInputFrame();
                        if(ean == null){
                            return ;
                        }
                        try {
                            book = EanBol.getBookData(ean);
                            StatusBarModel.getInstance().setSuccessMessage("EAN/ISBN found");
                        } catch (IOException ex) {
                            StatusBarModel.getInstance().setFailMessage("Connection error!");
                            return;
                        }
                    }else{
                        book = new Book();
                    }
                    mvb.setCurrentBook(book);
                }else{
                    if(button.getName().equals("add music button")){
                        Music music;
                        if(decision == 0){
                            String ean = mvb.showEanInputFrame();
                            if(ean == null){
                                return;
                            }
                            try {
                                music = EanBol.getMusicData(ean);
                                StatusBarModel.getInstance().setSuccessMessage("EAN found");
                            } catch (IOException ex) {
                                StatusBarModel.getInstance().setFailMessage("Connection error!");
                                return;
                            }
                        }else{
                            music = new Music();
                        }
                        mvb.setCurrentMusic(music);
                    }else{
                        if(button.getName().equals("add video button")){
                            Video video;
                            if(decision == 0){
                                String ean = mvb.showEanInputFrame();
                                if (ean == null) {
                                    return;
                                }
                                try {
                                    video = EanBol.getVideoData(ean);
                                    StatusBarModel.getInstance().setSuccessMessage("EAN/ISBN found");
                                } catch (IOException ex) {
                                    StatusBarModel.getInstance().setFailMessage("Connection error!");
                                    return;
                                }
                            }else{
                                video = new Video();
                            }
                            mvb.setCurrentVideo(video);
                        }
                    }
                }
            }
        }
    }

}
