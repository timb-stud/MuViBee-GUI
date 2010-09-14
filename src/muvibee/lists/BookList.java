/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.lists;

import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Book;

/**
 *
 * @author bline
 */
public class BookList extends MediaList{

    public boolean add(Book b) {
        boolean succ = list.add(b);
        resort();
        this.setChanged();
        this.notifyObservers();
        return succ;
    }
}
