package muvibee.lists;

import java.util.ArrayList;
import java.util.Collection;
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

    public MediaList() {
//        sortedBy = new ArrayList<SortTypes>();
        list = new LinkedList<Media>();
    }

    public boolean add(Media m) {
        boolean succ = list.add(m);
        m.addObserver(this);
        updateObserver();
        return succ;
    }

    public void addAll(Collection c) {
        for (Object o : c) {
            add((Media)o);
        }
        updateObserver();
    }

    public void update(Observable o, Object arg) {
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

    public void clear(){
        list.clear();
        updateObserver();
    }

    public void updateObserver() {
        setChanged();
        notifyObservers();
    }
}
