/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.tree;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import muvibee.lists.MediaList;
import muvibee.media.Media;
import muvibee.utils.SortTypes;

/**
 *
 * @author christian
 */
public class PrioTree extends JTree implements Observer {

    private final String OTHER = "sonstige";

    DefaultMutableTreeNode root ;
    DefaultMutableTreeNode lastAdded;
    TreeModel treeModel;

    public PrioTree() {
         super();

//         root = (DefaultMutableTreeNode) getModel().getRoot();
        expandRow(1);

        //tree.addTreeSelectionListener(new TreeSelectionListener() {
        addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                if (node.isLeaf()) {
                    System.out.println("You selected: " + node);
                }
            }
        });
    }


    private boolean containsChild (DefaultMutableTreeNode child){
        int to = lastAdded.getChildCount();
        for (int i = 0; i < to; i++)
               if (lastAdded.getChildAt(i).equals(child))
                   return true;

        return false;
    }


    public void createTree(MediaList mediaList, SortTypes[] sortedBy) {
        Object o = null;
        DefaultMutableTreeNode child;

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
                    child = new DefaultMutableTreeNode(OTHER);
                else
                    child = new DefaultMutableTreeNode(o);

                if (!(containsChild(child))){
                    lastAdded.add(child);
                    lastAdded = child;
                }
            }
        }
    }

    @Override
    public void update(Observable list, Object o) {
        root = new DefaultMutableTreeNode("Root");
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
