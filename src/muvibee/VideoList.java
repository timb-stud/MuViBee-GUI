/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Video;

/**
 *
 * @author christian
 */
public class VideoList extends MediaList{

     public boolean add(Video v) {
        this.setChanged();
        this.notifyObservers();
        return list.add(v);
    }

    public void sortByFormat(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return v1.getFormat().compareTo(v2.getFormat());
            }

        });
    }

        public void sortByDirector(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return (v1.getDirector().compareTo(v2.getDirector()));
            }

        });
    }

    public void sortByactor(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return v1.getActors().compareTo(v2.getActors());
            }

        });
    }

}
