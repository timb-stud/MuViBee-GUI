/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.lists;

import muvibee.media.Video;


public class VideoList extends MediaList{
    public boolean add(Video v) {
        boolean succ = list.add(v);
        resort();
        v.addObserver(this);
        this.setChanged();
        this.notifyObservers();
        return succ;
    }
}
