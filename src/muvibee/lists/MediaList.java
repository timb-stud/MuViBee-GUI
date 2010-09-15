/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.lists;

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
    }
       public void sortByAuthor(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Book b1 = (Book)o1;
                Book b2 = (Book)o2;
                return b1.getAuthor().compareTo(b2.getAuthor());
            }

        });
        sortedBy = sortedBy.AUTHOR;
    }


   public void sortByLanguage(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Book b1 = (Book)o1;
                Book b2 = (Book)o2;
                return (b1.getLanguage()).compareTo(b2.getLanguage());
            }

        });
        sortedBy = sortedBy.LANGUAGE;
    }

       public void sortByFormat(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getFormat().compareTo(m2.getFormat());
            }
        });
        sortedBy = sortedBy.FORMAT;
    }

    public void sortByInterpreter(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getInterpreter().compareTo(m2.getInterpreter());
            }

        });
        sortedBy = sortedBy.INTERPRETER;
    }

    public void sortByType(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Music m1 = (Music)o1;
                Music m2 = (Music)o2;
                return m1.getType().compareTo(m2.getType());
            }
        });
        sortedBy = sortedBy.TYPE;
    }

        public void sortByDirector(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return (v1.getDirector().compareTo(v2.getDirector()));
            }
        });
        sortedBy = sortedBy.DIRECTOR;
    }

    public void sortByActors(){
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                Video v1 = (Video)o1;
                Video v2 = (Video)o2;
                return v1.getActors().compareTo(v2.getActors());
            }

        });
        sortedBy = sortedBy.ACTORS;
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
            case AUTHOR:
                sortByAuthor();
                break;
            case LANGUAGE:
                sortByLanguage();
                break;
            case TYPE:
                sortByType();
            case INTERPRETER:
                sortByInterpreter();
            case DIRECTOR:
                sortByDirector();
            default:
               sortByTitle(); //default sortByTitle
        }
    }
}
