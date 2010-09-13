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

    public void sortByAuthor(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Book b1 = (Book)o1;
                Book b2 = (Book)o2;
                return b1.getAuthor().compareTo(b2.getAuthor());
            }

        });
        sortedBy = "author";
    }


   public void sortByLanguage(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Book b1 = (Book)o1;
                Book b2 = (Book)o2;
                return (b1.getLanguage()).compareTo(b2.getLanguage());
            }

        });
        sortedBy = "language";
    }

    @Override
    void resort() {
       if (sortedBy.equals("title")) {
           sortByTitle();
       } else {
           if (sortedBy.equals("EAN")) {
               //TODO sortByEan();
           } else {
               //TODOif ()
           }
       }
    }
}
