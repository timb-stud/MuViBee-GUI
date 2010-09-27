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
        reset();
        this.message = message;
        this.setChanged();
        color = Color.green; //green  //Transparenz führt zu Fehlern
        this.notifyObservers();
    }

    public void setFailMessage(String message) {
        reset();
        this.message = message;
        this.setChanged();
        color = Color.red; //red //Transparenz führt zu Fehlern
        this.notifyObservers();
    }

    public void reset() {
        this.message = "";
        this.setChanged();
        color = (Color.GRAY); //gray - TODO System Color
        this.notifyObservers();
    }

    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }

}
