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
    DefaultMutableTreeNode stageOneChild;
    DefaultMutableTreeNode stageTwoChild;
    DefaultMutableTreeNode stageThreeChild;

    public PrioTree() {
        //setLayout(new FlowLayout());
        //setPreferredSize(new Dimension(150, 600));

        //JTree tree = new JTree(root);
        //tree.expandRow(1); // Expand children to illustrate leaf icons
        expandRow(1);
        //JScrollPane pane1 = new JScrollPane(tree);

        //tree.addTreeSelectionListener(new TreeSelectionListener() {
        addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                if (node.isLeaf()) {
                    System.out.println("You selected: " + node);
                }
            }
        });

        //add(pane1);
        // pane1.setBorder(BorderFactory.createTitledBorder("TreeTitle"));
    }


    public void newRoot(DefaultMutableTreeNode child) {
        root.add(child);
    }

    public void newStageOneChild(DefaultMutableTreeNode child) {
        stageOneChild.add(child);
    }

    public void newStageTwoChild(DefaultMutableTreeNode child) {
        stageTwoChild.add(child);
    }

    public void newStageThreeChild(DefaultMutableTreeNode child) {
        stageThreeChild.add(child);
    }


    private boolean containsChild (int level, DefaultMutableTreeNode child){
        for (int i = 0; i < stage(level).getChildCount(); i++)
            if (stage(level).getChildAt(i).equals(child))
                return false;

        return true;
    }


    private DefaultMutableTreeNode stage(int i){
        switch (i){
            case 0:
                return root;
            case 1:
                return stageOneChild;
            case 2:
                return stageTwoChild;
            case 3:
                return stageThreeChild;
        }
        return null;
    }


    public void createTree(MediaList mediaList, SortTypes[] sortBy) {
        Object o = null;
        DefaultMutableTreeNode newNode;
        int level = 0;

        for (Media m : mediaList.getList()){
            for (SortTypes sortedBy : sortBy){
                switch (sortedBy){
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

                if (!(containsChild(level, newNode)))
                    stage(level).add(newNode);

                level++;
            }
        }
    }

    @Override
    public void update(Observable list, Object sortBy) {
        MediaList mediaList = ((MediaList) list);
        root =  new DefaultMutableTreeNode("Root");
        createTree(mediaList,(SortTypes[]) sortBy);
    }
}
