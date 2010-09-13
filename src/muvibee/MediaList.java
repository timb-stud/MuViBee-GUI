/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.LinkedList;
import java.util.Observable;


/**
 *
 * @author bline
 */
public class MediaList <E> extends Observable{
    private LinkedList<E> list;

    public MediaList() { }

    public boolean add(E e) {
        this.setChanged();
        this.notifyObservers();
        return list.add(e);
    }

    public LinkedList<E> getList() {
        return list;
    }
}
