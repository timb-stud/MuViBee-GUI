/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.tree;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import muvibee.MuViBee;
import muvibee.lists.MediaList;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.utils.SortTypes;

/**
 *
 * @author christian
 */
public class PrioTree extends JTree implements Observer {

    private final String OTHER = "sonstige";

    Node root ;
    Node lastAdded;
    TreeModel treeModel;

    public PrioTree(final MuViBee muvibee) {
        super();
        root = new Node("Root", null);
        treeModel = new DefaultTreeModel(root);
        setModel(treeModel);
        expandRow(1);

        addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                Node node = (Node) e.getPath().getLastPathComponent();
                if (node.isLeaf()) {
                    if ((node.getMedia()) instanceof Book) {
                        muvibee.setCurrentBook((Book) node.getMedia());
                    } else if (node.getMedia() instanceof Music) {
                        muvibee.setCurrentMusic((Music) node.getMedia());
                    } else if (node.getMedia() instanceof Video) {
                        muvibee.setCurrentVideo((Video) node.getMedia());
                    }
                }
            }
        });
    }


    private boolean containsChild (Node child){
        int to = lastAdded.getChildCount();
        for (int i = 0; i < to; i++){
               if (((Node)lastAdded.getChildAt(i)).equals(child)){
                   return true;
            }
        }
        return false;
    }


    public void createTree(MediaList mediaList, SortTypes[] sortedBy) {
        Object o = null;
        Node child;

        for (Media m : mediaList.getList()){
            lastAdded = root;
            for (SortTypes sortBy : sortedBy){
                switch (sortBy){
                    case TITLE :
                        o = m.getTitle();
                        break;
                    case YEAR :
                        o = m.getReleaseYear();
                        break;
                    case GENRE:
                        o = m.getGenre();
                        break;
                    case RATING:
                        o = m.getRating();
                        break;
                    case LOCATION:
                        o = m.getLocation();
                        break;
                    case LENTTO:
                        o = m.getLentTo();
                        break;
                    default:
                       o = m.getTitle();
                }

                if (o.equals("") || o.equals(-1))
                    child = new Node(OTHER, m);
                else
                    child = new Node(o, m);

                if (!(containsChild(child))){
                    lastAdded.add(child);
                }
                lastAdded = child;
            }
        }
    }

    @Override
    public void update(Observable list, Object o) {
        root = new Node("Root", null);
        treeModel = new DefaultTreeModel(root);
        setModel(treeModel);

        SortTypes[] sortedBy;
        if (o == null)
           sortedBy = new SortTypes[]{SortTypes.GENRE, SortTypes.YEAR, SortTypes.TITLE };
        else
           sortedBy =  (SortTypes[]) o;

        MediaList mediaList = ((MediaList) list);
        createTree(mediaList,sortedBy);
    }
}