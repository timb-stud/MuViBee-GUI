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

public class VideoList extends MediaList {
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
    public void sortByDirector() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Video v1 = (Video) o1;
                Video v2 = (Video) o2;
                return (v1.getDirector().compareTo(v2.getDirector()));
            }
        });
        sortedBy = sortedBy.DIRECTOR;
    }

    public void sortByActors() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Video v1 = (Video) o1;
                Video v2 = (Video) o2;
                return v1.getActors().compareTo(v2.getActors());
            }
        });
        sortedBy = sortedBy.ACTORS;
    }

    public void sortByFormat() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Video m1 = (Video) o1;
                Video m2 = (Video) o2;
                return m1.getFormat().compareTo(m2.getFormat());
            }
        });
        sortedBy = sortedBy.FORMAT;
    }

    @Override
    public boolean resort() {
        if (super.resort()) {
            switch (sortedBy) {
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
        return false;
    }
}
