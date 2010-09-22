package util.deleteditemlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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
            setLayout(new GridLayout(1,1, 0,0));
            setPreferredSize(new Dimension(300, ySize));
            setBackground(Color.yellow);
            
            JLabel label = new JLabel();
            label.setIcon(icon);
            add(label);

            JLabel text = new JLabel(" " + "title" + ": " + media.getTitle() + ", Genre: " + media.getGenre() + ", Jahr: " + media.getReleaseYear() + ", und so weiter");
            add(text);
	}

    public Media getMedia() {
        return media;
    }

    public int getySize() {
        return ySize;
    }
        
        

}
