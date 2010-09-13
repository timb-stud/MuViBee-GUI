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
 * @author christian
 */
public class MusicList extends MediaList{

    public boolean add(Music m) {
        this.setChanged();
        this.notifyObservers();
        return list.add(m);
    }

    public void sortByFormat(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getFormat().compareTo(m2.getFormat());
            }

        });
    }

    public void sortByInterpretert(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getInterpreter().compareTo(m2.getInterpreter());
            }

        });
    }

    public void sortByType(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getType().compareTo(m2.getType());
            }

        });
    }

}
