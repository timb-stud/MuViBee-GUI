/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.lists;

import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Music;


public class MusicList extends MediaList{

    public boolean add(Music m) {
        boolean succ = list.add(m);
        resort();
        this.setChanged();
        this.notifyObservers();
        return succ;
    }



    public void sortByFormat(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getFormat().compareTo(m2.getFormat());
            }
        });
        sortedBy = "format";
    }

    public void sortByInterpretert(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getInterpreter().compareTo(m2.getInterpreter());
            }

        });
        sortedBy = "interpreter";
    }

    public void sortByType(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getType().compareTo(m2.getType());
            }
        });
        sortedBy = "type";
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
