/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.lists;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import muvibee.media.Book;
import muvibee.media.Media;

/**
 *
 * @author bline
 */
public class BookList extends MediaList {

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

    public void sortByAuthor() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Book b1 = (Book) o1;
                Book b2 = (Book) o2;
                return b1.getAuthor().compareTo(b2.getAuthor());
            }
        });
        sortedBy = sortedBy.AUTHOR;
        this.setChanged();
        this.notifyObservers();
    }

    public void sortByLanguage() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Book b1 = (Book) o1;
                Book b2 = (Book) o2;
                return (b1.getLanguage()).compareTo(b2.getLanguage());
            }
        });
        sortedBy = sortedBy.LANGUAGE;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public boolean resort() {
        if (super.resort()) {
            switch (sortedBy) {
                case AUTHOR:
                    sortByAuthor();
                    break;
                case LANGUAGE:
                    sortByLanguage();
                    break;
                default:
                    sortByTitle();
                    return true;
            }
        }
        return false;
    }
}
