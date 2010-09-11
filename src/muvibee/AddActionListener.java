/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
                    book = new Book("Test", "bla"); //TODO getBook(ean);
                }else{
                    book = new Book();
                }
                mvb.showBookItem(book);
                mvb.setTmpBook(book);
            }else{
                if(button.getName().equals("add music button")){
                    Music music;
                    if(decision == 0){
                        String ean = mvb.showEanInputFrame();
                        music = new Music("Test", "bla"); //TODO getBook(ean);
                    }else{
                        music = new Music();
                    }
                    mvb.showMusicItem(music);
                    mvb.setTmpMusic(music);
                }else{
                    if(button.getName().equals("add video button")){
                        Video video;
                        if(decision == 0){
                            String ean = mvb.showEanInputFrame();
                            video = new Video("Test", "bla"); //TODO getBook(ean);
                        }else{
                            video = new Video();
                        }
                        mvb.showVideoItem(video);
                        mvb.setTmpVideo(video);
                    }
                }
            }
        }
    }

}
