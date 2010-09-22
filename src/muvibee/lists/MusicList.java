/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.lists;

import java.util.Collection;
import muvibee.media.Media;
import muvibee.media.Music;

public class MusicList extends MediaList {
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
            add((Music)o);
        }
        updateObserver();
    }
}
