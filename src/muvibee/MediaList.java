/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Observable;
import muvibee.media.Media;


/**
 *
 * @author bline
 */
public class MediaList extends Observable{
    LinkedList<Media> list;

    public MediaList() {
        list = new LinkedList<Media>();
    }

    public boolean add(Media m) {
        this.setChanged();
        this.notifyObservers();
        return list.add(m);
    }

    public LinkedList<Media> getList() {
        return list;
    }

    public void sortByTitle(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getTitle().compareTo(m2.getTitle());
            }

        });
    }
}
