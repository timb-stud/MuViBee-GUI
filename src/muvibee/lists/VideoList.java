package muvibee.lists;

import java.util.Collection;
import muvibee.media.Media;
import muvibee.media.Video;

public class VideoList extends MediaList {
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
            add((Video)o);
        }
        updateObserver();
    }
}
