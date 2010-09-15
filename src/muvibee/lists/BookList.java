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
public class BookList extends MediaList {

    public void sortByAuthor() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Book b1 = (Book) o1;
                Book b2 = (Book) o2;
                return b1.getAuthor().compareTo(b2.getAuthor());
            }
        });
        sortedBy = sortedBy.AUTHOR;
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
