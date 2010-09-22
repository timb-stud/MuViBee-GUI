/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import muvibee.MuViBee;

/**
 *
 * @author bline
 */
public class SortActionListener implements ActionListener{
    private MuViBee mvb;

    public SortActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JToggleButton){
            JToggleButton button = (JToggleButton)source;
            if(button.getName().equals("sort book title")){
                mvb.sortBooksByTitle();
                mvb.sortedByBook();
            } else {
                if (button.getName().equals("sort book genre")) {
                    mvb.sortBooksByGenre();
                    mvb.sortedByBook();
                } else {
                    if (button.getName().equals("sort book rating")) {
                        mvb.sortBooksByRating();
                        mvb.sortedByBook();
                    } else {
                        if (button.getName().equals("sort music title")) {
                            mvb.sortMusicByTitle();
                        } else {
                            if (button.getName().equals("sort music genre")) {
                                mvb.sortMusicByGenre();
                            } else {
                                if (button.getName().equals("sort music rating")) {
                                    mvb.sortMusicByRating();
                                } else {
                                    if (button.getName().equals("sort video title")) {
                                        mvb.sortVideoByTitle();
                                    } else {
                                        if (button.getName().equals("sort video genre")) {
                                            mvb.sortVideoByGenre();
                                        } else {
                                            if (button.getName().equals("sort video rating")) {
                                                mvb.sortVideoByRating();
                                            } else {
                                                if (button.getName().equals("sort book author")) {
                                                    mvb.sortBookByAuthor();
                                                } else {
                                                    if (button.getName().equals("sort book language")) {
                                                        mvb.sortBookByLanguage();
                                                    } else {
                                                        if (button.getName().equals("sort music type")) {
                                                            mvb.sortMusicByType();
                                                        } else {
                                                            if (button.getName().equals("sort music interpreter")) {
                                                                mvb.sortMusicByInterpreter();
                                                            } else {
                                                                if (button.getName().equals("sort video regisseur")) {
                                                                    mvb.sortVideoByRegisseur();
                                                                } else {
                                                                    if (button.getName().equals("sort video actors")) {
                                                                        mvb.sortVideoByActors();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
