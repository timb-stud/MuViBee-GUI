/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.tree;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import muvibee.lists.MediaList;
import muvibee.media.Media;

/**
 *
 * @author christian
 */
public class PrioTree extends JPanel {

    DefaultMutableTreeNode root;
    DefaultMutableTreeNode stageOneChild;
    DefaultMutableTreeNode stageTwoChild;
    DefaultMutableTreeNode stageThreeChild;

    public PrioTree() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(150, 600));

        JTree tree = new JTree(root);
        tree.expandRow(1); // Expand children to illustrate leaf icons
        JScrollPane pane1 = new JScrollPane(tree);

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                if (node.isLeaf()) {
                    System.out.println("You selected: " + node);
                }
            }
        });

        add(pane1);
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


    private boolean containsChild (DefaultMutableTreeNode node, DefaultMutableTreeNode child){
        for (int i = 0; i < node.getChildCount(); i++)
            return (node.getChildAt(i).equals(child));

        return true;
    }


    public static void createTree(MediaList mediaList, int[] sortBy) {
        for (Media m : mediaList.getList()){
            for (int i : sortBy){
                switch (i) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        ;
                }
            }
        }
    }

    private String sortList(MediaList mediaList, int sortedBy) {
        switch (sortedBy) {
            case 1:

                mediaList.sortByReleaseYear();
                break;
            case 2:
                break;
            default:
                ;
        }
        return null;
    }
}
