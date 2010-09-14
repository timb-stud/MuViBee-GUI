/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.lists;

import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Music;


public class MusicList extends MediaList{
    public boolean add(Music m) {
        boolean succ = list.add(m);
        m.addObserver(this);
        resort();
        this.setChanged();
        this.notifyObservers();
        return succ;
    }
}
