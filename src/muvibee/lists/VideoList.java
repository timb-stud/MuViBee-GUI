/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.lists;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Media;
import muvibee.media.Video;
import muvibee.utils.SortTypes;

public class VideoList extends MediaList {
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
    public void sortByDirector() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Video v1 = (Video) o1;
                Video v2 = (Video) o2;
                return (v1.getDirector().compareTo(v2.getDirector()));
            }
        });
<<<<<<< HEAD
        if (!sortedBy.contains(SortTypes.DIRECTOR))
            sortedBy.add(SortTypes.DIRECTOR);
=======
        if (!sortedBy.contains(SortTypes.DIRECTOR)) {
            sortedBy.add(SortTypes.DIRECTOR);
        }
>>>>>>> c0c0f1c9c79aa7d3a285aaa57f8625caa7e9bb45
    }

    public void sortByActors() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Video v1 = (Video) o1;
                Video v2 = (Video) o2;
                return v1.getActors().compareTo(v2.getActors());
            }
        });
<<<<<<< HEAD
        if (!sortedBy.contains(SortTypes.ACTORS))
            sortedBy.add(SortTypes.ACTORS);
=======
        if (!sortedBy.contains(SortTypes.ACTORS)) {
            sortedBy.add(SortTypes.ACTORS);
        }
>>>>>>> c0c0f1c9c79aa7d3a285aaa57f8625caa7e9bb45
    }

    public void sortByFormat() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Video m1 = (Video) o1;
                Video m2 = (Video) o2;
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
                    case DIRECTOR:
                        sortByDirector();
                        break;
                    case ACTORS:
                        sortByActors();
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
