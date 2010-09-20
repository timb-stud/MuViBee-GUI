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
        updateObserver();
        return succ;
    }

    @Override
    public void addAll(Collection c) {
        list.addAll(c);
        resort();
        updateObserver();
    }
    public void sortByType() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getType().compareTo(m2.getType());
            }
        });
<<<<<<< HEAD
        if (!sortedBy.contains(SortTypes.TYPE))
            sortedBy.add(SortTypes.TYPE);
=======
        if (!sortedBy.contains(SortTypes.TYPE)) {
            sortedBy.add(SortTypes.TYPE);
        }
>>>>>>> c0c0f1c9c79aa7d3a285aaa57f8625caa7e9bb45
    }

    public void sortByInterpreter() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getInterpreter().compareTo(m2.getInterpreter());
            }
        });
<<<<<<< HEAD
        if (!sortedBy.contains(SortTypes.INTERPRETER))
            sortedBy.add(SortTypes.INTERPRETER);
=======
        if (!sortedBy.contains(SortTypes.INTERPRETER)) {
            sortedBy.add(SortTypes.INTERPRETER);
        }
>>>>>>> c0c0f1c9c79aa7d3a285aaa57f8625caa7e9bb45
    }

    public void sortByFormat() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getFormat().compareTo(m2.getFormat());
            }
        });
<<<<<<< HEAD
        if (!sortedBy.contains(SortTypes.FORMAT))
            sortedBy.add(SortTypes.FORMAT);
=======
        if (!sortedBy.contains(SortTypes.FORMAT)) {
            sortedBy.add(SortTypes.FORMAT);
        }
>>>>>>> c0c0f1c9c79aa7d3a285aaa57f8625caa7e9bb45
    }




    @Override
    public boolean resort() {
        if (super.resort()) {
            for (SortTypes st : sortedBy) {
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
