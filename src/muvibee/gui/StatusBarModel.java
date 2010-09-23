/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.Color;
import java.util.Observable;

/**
 *
 * @author bline
 */
public class StatusBarModel extends Observable{
    private static StatusBarModel uniqueInstance;
    private String message;
    private Color color;

    private StatusBarModel(){};

    public static StatusBarModel getInstance() {
        if (uniqueInstance == null) {
            return uniqueInstance = new StatusBarModel();
        }
        return uniqueInstance;
    }

    public void setSuccessMessage(String message) {
        this.message = message;
        this.setChanged();
        color = new Color(0, 255, 20, 100); //green
        this.notifyObservers();
    }

    public void setFailMessage(String message) {
        this.message = message;
        this.setChanged();
        color = new Color(255, 0, 20, 100); //red
        this.notifyObservers();
    }

    public void reset() {
        this.message = "";
        this.setChanged();
        color = (Color.GRAY); //gray - TODO System Color
//        color = new Color(0.6f, 0.6f, 0.6f, 0.2f); //gray - TODO System Color
        this.notifyObservers();
    }

    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }

 
}
