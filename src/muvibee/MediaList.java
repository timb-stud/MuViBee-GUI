/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Observable;
import muvibee.media.Media;


/**
 *
 * @author bline
 */
public class MediaList extends Observable{
    LinkedList<Media> list;
    String sortedBy = "title";

    public MediaList() {
        list = new LinkedList<Media>();
    }

    public boolean add(Media m) {
        boolean succ = list.add(m);
        resort();
        this.setChanged();
        this.notifyObservers();
        return succ;
    }

    public boolean contains(Media m){
        return list.contains(m);
    }

    public LinkedList<Media> getList() {
        return list;
    }

    public boolean remove(Media m){
        boolean succ = list.remove(m);
        resort();
        this.setChanged();
        this.notifyObservers();
        return succ;
    }

    public String getSortedBy() {
        return sortedBy;
    }
    
    public void sortByTitle(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getTitle().compareTo(m2.getTitle());
            }

        });
        sortedBy = "title";
    }



    public void sortByGenre(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getGenre().compareTo(m2.getGenre());
            }

        });
        sortedBy = "genre";
    }

    public void sortByReleaseYear(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return compareInt(m1.getReleaseYear(), (m2.getReleaseYear()));
            }

        });
        sortedBy = "release year";
    }

    public void sortByLocation(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getLocation().compareTo(m2.getLocation());
            }

        });
        sortedBy = "location";
    }


    public void sortByLendTo(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getLendTo().compareTo(m2.getLendTo());
            }

        });
        sortedBy = "lendto";
    }

   public void sortByRating(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return compareInt(m1.getRating(), (m2.getRating()));
            }

        });
        sortedBy = "rating";
    }


    private static int compareInt(int a, int b){
		if (a == b)
			return 0;
		else if (a > b)
			return 1;
		else
			return -1;
	}

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
