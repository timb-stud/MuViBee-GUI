/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Music;

/**
 *
 * @author bline
 */
public class MusicList extends MediaList{

    public boolean add(Music m) {
        boolean succ = list.add(m);
        resort();
        this.setChanged();
        this.notifyObservers();
        return succ;
    }

    public void sortByInterpreter(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getInterpreter().compareTo(m2.getInterpreter());
            }

        });
        sortedBy = "Interpreter";
    }

    @Override
    void resort() {
       if (sortedBy.equals("title")) {
           sortByTitle();
       } else {
           if (sortedBy.equals("EAN")) {
               //TODO sortByEan();
           } else {
               //TODOif ()
           }
       }
    }
}
