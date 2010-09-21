/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import muvibee.media.Media;

/**
 * Knoten welche in PrioTree aufgenommen wird
 * @author Christian Rech
 */
public class Node extends DefaultMutableTreeNode{

    private Media media;

    public Node(Object o, Media m) {
        super(o);
        this.media = m;
    }

    public Media getMedia() {
        return media;
    }

    public boolean equals(Node node){
        return (this.getUserObject().equals(node.getUserObject()))?true:false;
    }
}