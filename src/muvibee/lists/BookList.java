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
import muvibee.utils.SortTypes;

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
        updateObserver();
        return succ;
    }

    @Override
    public void addAll(Collection c) {
        list.addAll(c);
        resort();
        updateObserver();
    }

    public void sortByAuthor() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Book b1 = (Book) o1;
                Book b2 = (Book) o2;
                return b1.getAuthor().compareTo(b2.getAuthor());
            }
        });
        if (!sortedBy.contains(SortTypes.AUTHOR))
            sortedBy.add(SortTypes.AUTHOR);
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
        if (!sortedBy.contains(SortTypes.LANGUAGE))
            sortedBy.add(SortTypes.LANGUAGE);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public boolean resort() {
        if (super.resort()) {
            for (SortTypes st : sortedBy) {
                switch (st) {
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
        }
        return false;
    }
}
