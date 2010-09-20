/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.lists;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.utils.SortTypes;

public class MusicList extends MediaList {
    @Override
    public boolean add(Media m) {
        boolean succ = list.add(m);
        resort();
        m.addObserver(this);
        this.setChanged();
        this.notifyObservers();
        return succ;
    }

    @Override
    public void addAll(Collection c) {
        list.addAll(c);
        resort();
        this.setChanged();
        this.notifyObservers();
    }
    public void sortByType() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getType().compareTo(m2.getType());
            }
        });
        if (!sortedBy.contains(SortTypes.TYPE))
            sortedBy.add(SortTypes.TYPE);
    }

    public void sortByInterpreter() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getInterpreter().compareTo(m2.getInterpreter());
            }
        });
        if (!sortedBy.contains(SortTypes.INTERPRETER))
            sortedBy.add(SortTypes.INTERPRETER);
    }

    public void sortByFormat() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getFormat().compareTo(m2.getFormat());
            }
        });
        if (!sortedBy.contains(SortTypes.FORMAT))
            sortedBy.add(SortTypes.FORMAT);
    }




    @Override
    public boolean resort() {
        if (super.resort()) {
            for (SortTypes st : sortedBy.toArray(new SortTypes[0])) {
                switch (st) {
                    case TYPE:
                        sortByType();
                        break;
                    case INTERPRETER:
                        sortByInterpreter();
                        break;
                    case FORMAT:
                        sortByFormat();
                        break;
                    default:
                        sortByTitle();
                        return true;
                }
            }
        }
        return false;
    }
}
