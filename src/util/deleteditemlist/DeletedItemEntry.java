package util.deleteditemlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import muvibee.media.Media;
import muvibee.utils.ResizeImageIcon;

@SuppressWarnings("serial")
public class DeletedItemEntry extends JPanel{
    private Media media;
    private int ySize = 80;

	public DeletedItemEntry(Media media) {
            this.media = media;
            ImageIcon icon;
            if (media.getCover() != null)
               icon = ResizeImageIcon.resizeIcon(70, 80, media.getCover());
            else
               icon = null;

            // ----------------------
            // |       |             |
            // | Icon  | Titel, usw  |
            // |_______|_____________|
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(300, ySize));
            setBackground(Color.yellow);
            
            JLabel label = new JLabel();
            label.setIcon(icon);
            add(label, BorderLayout.WEST);

            JLabel text = new JLabel(" " + "title" + ": " + media.getTitle() + ", Genre: " + media.getGenre() + ", Jahr: " + media.getLendYear() + ", und so weiter");
            add(text, BorderLayout.CENTER);
	}

    public Media getMedia() {
        return media;
    }

    public int getySize() {
        return ySize;
    }
        
        

}
