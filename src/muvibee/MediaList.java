/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

import java.util.Collection;
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

    public MediaList() {
        list = new LinkedList<Media>();
    }

    public boolean add(Media m) {
        this.setChanged();
        this.notifyObservers();
        return list.add(m);
    }

    public boolean contains(Media m){
        return list.contains(m);
    }

    public LinkedList<Media> getList() {
        return list;
    }

    public boolean remove(Media m){
        return list.remove(m);
    }

    public void sortByTitle(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getTitle().compareTo(m2.getTitle());
            }

        });
    }

    public void sortByGenre(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getGenre().compareTo(m2.getGenre());
            }

        });
    }

    public void sortByReleaseYear(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return compareInt(m1.getReleaseYear(), (m2.getReleaseYear()));
            }

        });
    }

    public void sortByLocation(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getLocation().compareTo(m2.getLocation());
            }

        });
    }


    public void sortByLendTo(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getLendTo().compareTo(m2.getLendTo());
            }

        });
    }

   public void sortByRating(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return compareInt(m1.getRating(), (m2.getRating()));
            }

        });
    }


    private static int compareInt(int a, int b){
		if (a == b)
			return 0;
		else if (a > b)
			return 1;
		else
			return -1;
	}

}
