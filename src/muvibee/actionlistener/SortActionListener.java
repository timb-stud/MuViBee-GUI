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
            } else {
                if (button.getName().equals("sort book genre")) {
                } else {
                    if (button.getName().equals("sort book rating")) {
                    } else {
                        if (button.getName().equals("sort music title")) {
                        } else {
                            if (button.getName().equals("sort music genre")) {
                            } else {
                                if (button.getName().equals("sort music rating")) {
                                } else {
                                    if (button.getName().equals("sort video title")) {
                                    } else {
                                        if (button.getName().equals("sort video genre")) {
                                        } else {
                                            if (button.getName().equals("sort video rating")) {
                                            } else {
                                                if (button.getName().equals("sort book author")) {
                                                } else {
                                                    if (button.getName().equals("sort book language")) {
                                                    } else {
                                                        if (button.getName().equals("sort music type")) {
                                                        } else {
                                                            if (button.getName().equals("sort music interpreter")) {
                                                            } else {
                                                                if (button.getName().equals("sort video regisseur")) {
                                                                } else {
                                                                    if (button.getName().equals("sort video actors")) {
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
            if (button.getName().contains("book"))
                    mvb.sortedByBook();
            else if (button.getName().contains("music"))
                    mvb.sortedByMusic();
            else if (button.getName().contains("video"))
                    mvb.sortedByVideo();
        }
    }


}
