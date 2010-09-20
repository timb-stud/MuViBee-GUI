/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import muvibee.media.Media;
import muvibee.utils.SortTypes;

/**
 *
 * @author bline
 */
public class MediaList extends Observable implements Observer {

    LinkedList<Media> list;
    ArrayList<SortTypes> sortedBy;

    public MediaList() {
        sortedBy = new ArrayList<SortTypes>();
        list = new LinkedList<Media>();
        if (!sortedBy.contains(SortTypes.TITLE))
            sortedBy.add(SortTypes.TITLE);
    }

    public boolean add(Media m) {
        boolean succ = list.add(m);
        resort();
        m.addObserver(this);
        updateObserver();
        return succ;
    }

    public void addAll(Collection c) {
        list.addAll(c);
        resort();
        updateObserver();
    }

    public void update(Observable o, Object arg) {
        resort();
        updateObserver();
    }

    public boolean contains(Media m) {
        return list.contains(m);
    }

    public LinkedList<Media> getList() {
        return list;
    }

    public boolean remove(Media m) {
        boolean succ = list.remove(m);
        updateObserver();
        return succ;
    }

    public void sortByTitle() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Media m1 = (Media) o1;
                Media m2 = (Media) o2;
                return m1.getTitle().compareTo(m2.getTitle());
            }
        });
        if (!sortedBy.contains(SortTypes.TITLE)) {
            sortedBy.add(SortTypes.TITLE);
        }
    }

    public void sortByGenre() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Media m1 = (Media) o1;
                Media m2 = (Media) o2;
                return m1.getGenre().compareTo(m2.getGenre());
            }
        });
        if (!sortedBy.contains(SortTypes.GENRE)) {
            sortedBy.add(SortTypes.GENRE);
        }
    }

    public void sortByReleaseYear() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Media m1 = (Media) o1;
                Media m2 = (Media) o2;
                return compareInt(m1.getReleaseYear(), (m2.getReleaseYear()));
            }
        });
        if (!sortedBy.contains(SortTypes.YEAR)) {
            sortedBy.add(SortTypes.YEAR);
        }
    }

    public void sortByLocation() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Media m1 = (Media) o1;
                Media m2 = (Media) o2;
                return m1.getLocation().compareTo(m2.getLocation());
            }
        });
        if (!sortedBy.contains(SortTypes.LOCATION)) {
            sortedBy.add(SortTypes.LOCATION);
        }
    }

    public void sortByLentTo() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Media m1 = (Media) o1;
                Media m2 = (Media) o2;
                return m1.getLentTo().compareTo(m2.getLentTo());
            }
        });
        if (!sortedBy.contains(SortTypes.LENTTO)) {
            sortedBy.add(SortTypes.LENTTO);
        }
    }

    public void sortByRating() {
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Media m1 = (Media) o1;
                Media m2 = (Media) o2;
                return compareInt(m1.getRating(), (m2.getRating()));
            }
        });
        if (!sortedBy.contains(SortTypes.RATING)) {
            sortedBy.add(SortTypes.RATING);
        }
    }

    private static int compareInt(int a, int b) {
        if (a == b) {
            return 0;
        } else if (a > b) {
            return 1;
        } else {
            return -1;
        }
    }

    public void updateObserver() {
        setChanged();
        notifyObservers();
    }

    boolean resort() {
        for (SortTypes st : sortedBy) {
            switch (st) {
                case TITLE:
                    sortByTitle();
                    break;
                case YEAR:
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
        }
        return false;
    }

    public void removeSort(SortTypes sortTypes) {
        switch (sortTypes) {
            case TITLE:
                sortedBy.remove(sortTypes.TITLE);
                break;
            case YEAR:
                sortedBy.remove(sortTypes.YEAR);
                break;
            case GENRE:
                sortedBy.remove(sortTypes.GENRE);
                break;
            case RATING:
                sortedBy.remove(sortTypes.RATING);
                break;
            case LOCATION:
                sortedBy.remove(sortTypes.LOCATION);
                break;
            case LENTTO:
                sortedBy.remove(sortTypes.LENTTO);
                break;
        }
    }

    public ArrayList<SortTypes> getSortedBy() {
        return sortedBy;
    }
    
}
