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
        m.addObserver(this);
        updateObserver();
        return succ;
    }

    @Override
    public void addAll(Collection c) {
        for (Object o : c) {
            add((Video)o);
        }
        updateObserver();
    }
}
