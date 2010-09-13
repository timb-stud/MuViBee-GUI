/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee;

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
        color = Color.green;
        this.notifyObservers();
    }

    public void setFailMessage(String message) {
        this.message = message;
        this.setChanged();
        color = Color.red;
        this.notifyObservers();
    }

    public void reset() {
        this.message = "";
        this.setChanged();
        color = Color.gray; //TODO system color
        this.notifyObservers();
    }

    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }

 
}
