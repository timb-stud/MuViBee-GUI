/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Video;


public class VideoList extends MediaList{
    public boolean add(Video v) {
        boolean succ = list.add(v);
        resort();
        this.setChanged();
        this.notifyObservers();
        return succ;
    }

    public void sortByFormat(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return v1.getFormat().compareTo(v2.getFormat());
            }
        });
        sortedBy = "format";
    }

        public void sortByDirector(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return (v1.getDirector().compareTo(v2.getDirector()));
            }
        });
        sortedBy = "director";
    }

    public void sortByActors(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return v1.getActors().compareTo(v2.getActors());
            }

        });
        sortedBy = "actors";
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
