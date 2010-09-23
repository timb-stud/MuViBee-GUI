package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JToggleButton;
import muvibee.MuViBee;
import muvibee.db.DBSelector;
import muvibee.lists.MediaList;
import muvibee.media.Book;
import muvibee.utils.SortTypes;

/**
 *
 * @author tim
 */
public class SortBookActionListener implements ActionListener{
    MuViBee mvb;
    ArrayList<SortTypes> orderList = new ArrayList<SortTypes>();

    public SortBookActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o instanceof JToggleButton){
            JToggleButton tb = (JToggleButton)o;
            if(tb.getName().equals("sort book title")){
                if(tb.isSelected()){
                    if(!orderList.contains(SortTypes.TITLE))
                        orderList.add(SortTypes.TITLE);
                }else{
                    orderList.remove(SortTypes.TITLE);
                }
            }else{
                if(tb.getName().equals("sort book genre")){
                    if (tb.isSelected()) {
                        if (!orderList.contains(SortTypes.GENRE)) {
                            orderList.add(SortTypes.GENRE);
                        }
                    } else {
                        orderList.remove(SortTypes.GENRE);
                    }
                }else{
                    if(tb.getName().equals("sort book rating")){
                        if (tb.isSelected()) {
                            if (!orderList.contains(SortTypes.RATING)) {
                                orderList.add(SortTypes.RATING);
                            }
                        } else {
                            orderList.remove(SortTypes.RATING);
                        }
                    }else{
                        if(tb.getName().equals("sort book author")){
                            if (tb.isSelected()) {
                                if (!orderList.contains(SortTypes.AUTHOR)) {
                                    orderList.add(SortTypes.AUTHOR);
                                }
                            } else {
                                orderList.remove(SortTypes.AUTHOR);
                            }
                        }else{
                            if(tb.getName().equals("sort book language")){
                                if (tb.isSelected()) {
                                    if (!orderList.contains(SortTypes.LANGUAGE)) {
                                        orderList.add(SortTypes.LANGUAGE);
                                    }
                                } else {
                                    orderList.remove(SortTypes.LANGUAGE);
                                }
                            }else{
                                if (tb.getName().equals("sort book release year")) {
                                    if (tb.isSelected()) {
                                        if (!orderList.contains(SortTypes.RELEASEYEAR)) {
                                            orderList.add(SortTypes.RELEASEYEAR);
                                        }
                                    } else {
                                        orderList.remove(SortTypes.RELEASEYEAR);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        MediaList ml = mvb.getBookList();
        ml.clear();
        LinkedList<Book> bookList = DBSelector.getBookList(false, orderList.toArray(new SortTypes[0]));
        ml.addAll((bookList));
        ml.updateObserver();
    }

}
