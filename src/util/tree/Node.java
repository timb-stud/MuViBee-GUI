/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import muvibee.media.Media;

/**
 *
 * @author Christian
 */
public class Node extends DefaultMutableTreeNode{

    private Media media;

    public Node(Object o, Media m) {
        super(o);

    }

    public Media getMedia() {
        return media;
    }
}
