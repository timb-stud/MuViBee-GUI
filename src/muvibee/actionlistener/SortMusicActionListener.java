
package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JToggleButton;
import muvibee.MuViBee;
import muvibee.db.DBSelector;
import muvibee.lists.MediaList;
import muvibee.media.Music;
import muvibee.utils.SortTypes;

/**
 *
 * @author tim
 */
public class SortMusicActionListener implements ActionListener{
    MuViBee mvb;
    ArrayList<SortTypes> orderList = new ArrayList<SortTypes>();

    public SortMusicActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

        public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o instanceof JToggleButton){
            JToggleButton tb = (JToggleButton)o;
            if(tb.getName().equals("sort music title")){
                if(tb.isSelected()){
                    if(!orderList.contains(SortTypes.TITLE))
                        orderList.add(SortTypes.TITLE);
                }else{
                    orderList.remove(SortTypes.TITLE);
                }
            }else{
                if(tb.getName().equals("sort music genre")){
                    if (tb.isSelected()) {
                        if (!orderList.contains(SortTypes.GENRE)) {
                            orderList.add(SortTypes.GENRE);
                        }
                    } else {
                        orderList.remove(SortTypes.GENRE);
                    }
                }else{
                    if(tb.getName().equals("sort music rating")){
                        if (tb.isSelected()) {
                            if (!orderList.contains(SortTypes.RATING)) {
                                orderList.add(SortTypes.RATING);
                            }
                        } else {
                            orderList.remove(SortTypes.RATING);
                        }
                    }else{
                        if(tb.getName().equals("sort music type")){
                            if (tb.isSelected()) {
                                if (!orderList.contains(SortTypes.TYPE)) {
                                    orderList.add(SortTypes.TYPE);
                                }
                            } else {
                                orderList.remove(SortTypes.TYPE);
                            }
                        }else{
                            if(tb.getName().equals("sort music interpreter")){
                                if (tb.isSelected()) {
                                    if (!orderList.contains(SortTypes.INTERPRETER)) {
                                        orderList.add(SortTypes.INTERPRETER);
                                    }
                                } else {
                                    orderList.remove(SortTypes.INTERPRETER);
                                }
                            }
                        }
                    }
                }
            }
        }
//        MediaList ml = mvb.getMusicList();
//        ml.clear();
//        LinkedList<Music> bookList = DBSelector.getBookList(false, orderList.toArray(new SortTypes[0]));
//        ml.addAll((bookList));
//        ml.updateObserver();
    }

}
