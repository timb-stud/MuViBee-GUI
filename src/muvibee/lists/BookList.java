package muvibee.lists;

import java.util.Collection;
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
        m.addObserver(this);
        updateObserver();
        return succ;
    }

    @Override
    public void addAll(Collection c) {
        for (Object o : c) {
            add((Book)o);
        }
        updateObserver();
    }
}
