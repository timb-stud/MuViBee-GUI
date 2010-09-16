/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import muvibee.lists.MediaList;
import muvibee.media.Media;

/**
 *
 * @author christian
 */
public class PrioTree {

    public PrioTree() {

    }

    public void createTree(MediaList mediaList, int[] sortBy){


        sortList(mediaList, sortBy[0]);

//        for (Media m : mediaList.getList()){
//            if (m.)
//        }

    }

    private String sortList(MediaList mediaList, int sortedBy){
			switch (sortedBy){
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
