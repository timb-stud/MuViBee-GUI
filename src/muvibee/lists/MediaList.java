/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.lists;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;


/**
 *
 * @author bline
 */
public class MediaList extends Observable implements Observer{

    enum SortTypes { DIRECTOR, LANGUAGE, TITLE, FORMAT, INTERPRETER, TYPE, ISBN, EAN, ACTORS, REGISSEUR, YEAR, GENRE, LOCATION, LENTTO, RATING, AUTHOR };
    LinkedList<Media> list;
    SortTypes sortedBy;

    public MediaList() {
        list = new LinkedList<Media>();
        sortedBy = sortedBy.TITLE;
    }

    public boolean add(Media m) {
        boolean succ = list.add(m);
        resort();
        m.addObserver(this);
        this.setChanged();
        this.notifyObservers();
        return succ;
    }

    public void addAll(Collection c) {
        list.addAll(c);
        resort();
        this.setChanged();
        this.notifyObservers();
    }

    public void update(Observable o, Object arg) {
        resort();
        setChanged();
        notifyObservers();
    }

    public boolean contains(Media m){
        return list.contains(m);
    }

    public LinkedList<Media> getList() {
        return list;
    }

    public boolean remove(Media m){
        boolean succ = list.remove(m);
        this.setChanged();
        this.notifyObservers();
        return succ;
    }

    public void sortByTitle(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getTitle().compareTo(m2.getTitle());
            }

        });
        sortedBy = sortedBy.TITLE;
        this.setChanged();
        this.notifyObservers();
    }



    public void sortByGenre(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getGenre().compareTo(m2.getGenre());
            }

        });
        sortedBy = sortedBy.GENRE;
        this.setChanged();
        this.notifyObservers();
    }

    public void sortByReleaseYear(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return compareInt(m1.getReleaseYear(), (m2.getReleaseYear()));
            }

        });
        sortedBy = sortedBy.YEAR;
        this.setChanged();
        this.notifyObservers();
    }

    public void sortByLocation(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getLocation().compareTo(m2.getLocation());
            }

        });
        sortedBy = sortedBy.LOCATION;
        this.setChanged();
        this.notifyObservers();
    }


    public void sortByLentTo(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return m1.getLentTo().compareTo(m2.getLentTo());
            }

        });
        sortedBy = sortedBy.LENTTO;
        this.setChanged();
        this.notifyObservers();
    }

   public void sortByRating(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Media m1 = (Media)o1;
                Media m2 = (Media)o2;
                return compareInt(m1.getRating(), (m2.getRating()));
            }

        });
        sortedBy = sortedBy.RATING;
        this.setChanged();
        this.notifyObservers();
    }

    private static int compareInt(int a, int b){
		if (a == b)
			return 0;
		else if (a > b)
			return 1;
		else
			return -1;
	}

        boolean resort() {
        switch (sortedBy){
            case TITLE :
                sortByTitle();
                break;
            case YEAR :
                sortByReleaseYear();
                break;
            case GENRE:
                sortByGenre();
                break;
            case RATING:
                sortByRating();
                break;
            case LOCATION:
                sortByLocation();
                break;
            case LENTTO:
                sortByLentTo();
                break;
            default:
               return true;
        }
        return false;
    }
}
