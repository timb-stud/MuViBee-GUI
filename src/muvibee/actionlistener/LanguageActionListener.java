/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JComboBox;
import muvibee.MuViBee;

/**
 *
 * @author tim
 */
public class LanguageActionListener implements ActionListener{

    MuViBee mvb;

    public LanguageActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }



    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o instanceof JComboBox){
            JComboBox cb = (JComboBox)o;
            String lang = cb.getSelectedItem().toString();
            if(lang.equals("en")){
                Locale.setDefault(Locale.ENGLISH);
            }else{
                if(lang.equals("de")){
                    Locale.setDefault(Locale.GERMAN);
                }else{
                    if(lang.equals("ru")){
                        Locale.setDefault(new Locale("ru"));
                    }else{
                        if(lang.equals("tr")){
                            Locale.setDefault(new Locale("tr"));
                        }

                    }
                }
            }
            mvb.reloadLabels();
        }
    }

}