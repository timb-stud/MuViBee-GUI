
package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JToggleButton;
import muvibee.MuViBee;
import muvibee.db.DBSelector;
import muvibee.lists.MediaList;
import muvibee.media.Video;
import muvibee.utils.SortTypes;

/**
 *
 * @author tim
 */
public class SortVideoActionListener implements ActionListener{
    MuViBee mvb;
    ArrayList<SortTypes> orderList = new ArrayList<SortTypes>();

    public SortVideoActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

     public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o instanceof JToggleButton){
            JToggleButton tb = (JToggleButton)o;
            if(tb.getName().equals("sort video title")){
                if(tb.isSelected()){
                    if(!orderList.contains(SortTypes.TITLE))
                        orderList.add(SortTypes.TITLE);
                }else{
                    orderList.remove(SortTypes.TITLE);
                }
            }else{
                if(tb.getName().equals("sort video genre")){
                    if (tb.isSelected()) {
                        if (!orderList.contains(SortTypes.GENRE)) {
                            orderList.add(SortTypes.GENRE);
                        }
                    } else {
                        orderList.remove(SortTypes.GENRE);
                    }
                }else{
                    if(tb.getName().equals("sort video rating")){
                        if (tb.isSelected()) {
                            if (!orderList.contains(SortTypes.RATING)) {
                                orderList.add(SortTypes.RATING);
                            }
                        } else {
                            orderList.remove(SortTypes.RATING);
                        }
                    }else{
                        if(tb.getName().equals("sort video regisseur")){
                            if (tb.isSelected()) {
                                if (!orderList.contains(SortTypes.DIRECTOR)) {
                                    orderList.add(SortTypes.DIRECTOR);
                                }
                            } else {
                                orderList.remove(SortTypes.DIRECTOR);
                            }
                        }else{
                            if(tb.getName().equals("sort video actors")){
                                if (tb.isSelected()) {
                                    if (!orderList.contains(SortTypes.ACTORS)) {
                                        orderList.add(SortTypes.ACTORS);
                                    }
                                } else {
                                    orderList.remove(SortTypes.ACTORS);
                                }
                            }
                        }
                    }
                }
            }
        }
//        MediaList ml = mvb.getVideoList();
//        ml.clear();
//        LinkedList<Video> bookList = DBSelector.getBookList(false, orderList.toArray(new SortTypes[0]));
//        ml.addAll((bookList));
//        ml.updateObserver();
    }
}
