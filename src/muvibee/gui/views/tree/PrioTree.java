/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.gui.views.tree;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import muvibee.MuViBee;
import muvibee.lists.BookList;
import muvibee.lists.MediaList;
import muvibee.lists.MusicList;
import muvibee.media.Book;
import muvibee.media.Media;
import muvibee.media.Music;
import muvibee.media.Video;
import muvibee.utils.SortTypes;

/**
 * Erstellt Baum Ansicht
 * @author Christian Rech
 */
public class PrioTree extends JTree implements Observer {

    Node root ;
    Node lastAdded;
    TreeModel treeModel;
    MuViBee mvb;

    /**
     * Erstellt Tree mit TreeModel und fügt Selectionlistner hinzu
     * @param muvibee
     */
    public PrioTree(final MuViBee muvibee) {
        super();
        this.mvb = muvibee;
        root = new Node("Root", null);
        treeModel = new DefaultTreeModel(root);
        setModel(treeModel);

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

    /**
     * Prüft ob Kind bereits auf auf aktueller Ebene vorhanden ist
     * @param child
     * @return
     */
    private boolean containsChild (Node child){
        int to = lastAdded.getChildCount();
        for (int i = 0; i < to; i++){
               if (((Node)(lastAdded.getChildAt(i))).equals(child)){
                   lastAdded = (Node) lastAdded.getChildAt(i);
                   return true;
              }
        }
        return false;
    }


    /**
     * Erstellt Baum nach vorgegebeber Sortierung
     * @param mediaList
     * @param sortedBy
     */
    public void createTree(MediaList mediaList,ArrayList<SortTypes> sortedBy) {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        final String OTHER = bundle.getString("other_PrioTree");
        Object o = null;
        Node child;

        for (Media m : mediaList.getList()){
            lastAdded = root;
            for (SortTypes sortBy : sortedBy){
                switch (sortBy){
                    case TITLE :
                        o = m.getTitle();
                        break;
                    case RELEASEYEAR :
                        o = m.getReleaseYear();
                        break;
                    case GENRE:
                        o = m.getGenre();
                        break;
                    case RATING:
                        o = m.getRating();
                        break;
//                    case LOCATION:
//                        o = m.getLocation();
//                        break;
//                    case LENTTO:
//                        o = m.getLentTo();
//                        break;
                    case LANGUAGE:
                        o = ((Book) m).getLanguage();
                        break;
                    case AUTHOR:
                        o = ((Book) m).getAuthor();
                        break;
                    case FORMAT:
                        if (m instanceof Music)
                            o = ((Music) m).getFormat();
                        else
                            o = ((Video) m).getFormat();
                        break;
                    case DIRECTOR:
                        o = ((Video)m).getDirector();
                        break;
                    case ARTIST:
                        o = ((Music)m).getInterpreter();
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
                    lastAdded = child;
                }
            }
        }
        expandPath(new TreePath(root.getPath()));
    }

    /**
     * Observer Pattern: Löscht aktuellen Baum und erstellt neuen
     * @param list
     * @param o
     */
    @Override
    public void update(Observable list, Object o) {
        ArrayList<SortTypes> sortedBy;
        root = new Node("Root", null);
        treeModel = new DefaultTreeModel(root);
        setModel(treeModel);

        setRootVisible(false);
        
        MediaList mediaList = ((MediaList) list);
        sortedBy = mediaList.getSortedBy();

        createTree(mediaList,sortedBy);
    }
}