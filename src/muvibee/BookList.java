/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.LinkedList;
import muvibee.media.Book;

/**
 *
 * @author bline
 */
public class BookList extends MediaList{

    public boolean add(Book b) {
        this.setChanged();
        this.notifyObservers();
        return list.add(b);
    }

}
