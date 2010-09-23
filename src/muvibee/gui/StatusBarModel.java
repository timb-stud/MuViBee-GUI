/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.Color;
import java.util.Observable;
import javax.swing.ImageIcon;

/**
 *
 * @author bline
 */
public class StatusBarModel extends Observable{
    private static StatusBarModel uniqueInstance;
    private String message;
    private Color color;
    private ImageIcon ii;

    private StatusBarModel(){};

    public static StatusBarModel getInstance() {
        if (uniqueInstance == null) {
            return uniqueInstance = new StatusBarModel();
        }
        return uniqueInstance;
    }

    public void setSuccessMessage(String message) {
        reset();
        ii = MainFrame.createImageIcon("gui/resources/icons/success.png");
        this.message = message;
        this.setChanged();
        color = new Color(0, 255, 20, 100); //green
        this.notifyObservers();
    }

    public void setFailMessage(String message) {
        reset();
        ii = MainFrame.createImageIcon("gui/resources/icons/error.png");
        this.message = message;
        this.setChanged();
        color = new Color(255, 0, 20, 100); //red
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

    public ImageIcon getIcon() {
        return ii;
    }

}
