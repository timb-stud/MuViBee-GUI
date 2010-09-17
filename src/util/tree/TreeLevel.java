/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.tree;

import java.util.LinkedList;

/**
 *
 * @author christian
 */
public class TreeLevel {

    private String name;
    private LinkedList<TreeLevel> list;

    public TreeLevel(String name, LinkedList<TreeLevel> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public LinkedList<TreeLevel> getList() {
        return list;
    }

}
