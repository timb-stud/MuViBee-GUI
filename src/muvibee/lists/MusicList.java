/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.lists;

import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Music;
import muvibee.media.Video;

public class MusicList extends MediaList {

    public void sortByType() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getType().compareTo(m2.getType());
            }
        });
        sortedBy = sortedBy.TYPE;
    }

    public void sortByInterpreter() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getInterpreter().compareTo(m2.getInterpreter());
            }
        });
        sortedBy = sortedBy.INTERPRETER;
    }

    public void sortByFormat() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Music m1 = (Music) o1;
                Music m2 = (Music) o2;
                return m1.getFormat().compareTo(m2.getFormat());
            }
        });
        sortedBy = sortedBy.FORMAT;
    }



    @Override
    public boolean resort() {
        if (super.resort()) {
            switch (sortedBy) {
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
        return false;
    }
}
