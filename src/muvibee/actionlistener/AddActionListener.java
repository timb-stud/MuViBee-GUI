/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import muvibee.EAN;
import muvibee.MuViBee;
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
            int decision = mvb.showDecisionFrame();
            if(button.getName().equals("add book button")){
                Book book;
                if(decision == 0){
                    String ean = mvb.showEanInputFrame();
		    if(ean == null){
			return ;
		    }
                    book = (Book)EAN.searchEan(ean); //TODO getBook(ean);
                }else{
                    book = new Book();
                }
                mvb.showBookItem(true);
                mvb.setCurrentBook(book);
                mvb.setBookItem();
            }else{
                if(button.getName().equals("add music button")){
                    Music music;
                    if(decision == 0){
                        String ean = mvb.showEanInputFrame();
			if(ean == null){
			    return ;
			}
                        music = (Music)EAN.searchEan(ean);
                    }else{
                        music = new Music();
                    }
                    mvb.showMusicItem(true);
                    mvb.setCurrentMusic(music);
                    mvb.setMusicItem();
                }else{
                    if(button.getName().equals("add video button")){
                        Video video;
                        if(decision == 0){
                            String ean = mvb.showEanInputFrame();
			    if(ean == null){
				return ;
			    }
                            video = (Video)EAN.searchEan(ean);
                        }else{
                            video = new Video();
                        }
                        mvb.showVideoItem(true);
                        mvb.setCurrentVideo(video);
                        mvb.setVideoItem();
                    }
                }
            }
        }
    }

}
