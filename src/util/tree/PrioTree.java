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
import muvibee.lists.MediaList;
import muvibee.media.Media;
import muvibee.utils.SortTypes;

/**
 *
 * @author christian
 */
public class PrioTree extends JTree implements Observer {

    private final String OTHER = "sonstige";

    DefaultMutableTreeNode root;
    DefaultMutableTreeNode lastAdded = root;
//    DefaultMutableTreeNode stageOneChild;
//    DefaultMutableTreeNode stageTwoChild;
//    DefaultMutableTreeNode stageThreeChild;

    public PrioTree() {
         root =  new DefaultMutableTreeNode("Root");
//        stageOneChild = new DefaultMutableTreeNode();
//        stageTwoChild = new DefaultMutableTreeNode();
//        stageThreeChild = new DefaultMutableTreeNode();;

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


    private boolean containsChild (int level, DefaultMutableTreeNode child){
        System.out.println("neuer Knoten: " + child);
        System.out.println("einfuegen in: " + lastAdded);
        int to = lastAdded.getChildCount();
        for (int i = 0; i <= to; i++)
            if (to != 0)
               if (lastAdded.getChildAt(i).equals(child))
                   return false;

        return true;
    }


//    private DefaultMutableTreeNode stage(int i){
//        switch (i){
//            case 0:
//                return root;
//            case 1:
//                return root.getNextNode();
//            case 2:
//                return root.getNextNode().getNextNode();
//            case 3:
//                return root.getNextNode().getNextNode().getNextNode();
//        }
//        return null;
//    }


    public void createTree(MediaList mediaList, SortTypes[] sortedBy) {
        Object o = null;
        DefaultMutableTreeNode newNode;
        int level = 0;

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

                if (o.equals(null))
                    newNode = new DefaultMutableTreeNode(OTHER);
                else
                    newNode = new DefaultMutableTreeNode(o);

                if (!(containsChild(level, newNode))){
                    lastAdded.add(newNode);
                    lastAdded = newNode;
                }

                level++;
            }
        }
    }

    @Override
    public void update(Observable list, Object o) {
        SortTypes[] sortedBy;
        if (o == null)
           sortedBy = new SortTypes[]{SortTypes.GENRE, SortTypes.YEAR, SortTypes.TITLE };
        else
           sortedBy =  (SortTypes[]) o;

        MediaList mediaList = ((MediaList) list);
        createTree(mediaList,sortedBy);
    }
}
