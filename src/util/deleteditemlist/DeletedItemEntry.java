package util.deleteditemlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import muvibee.MuViBee;

import muvibee.media.Media;
import muvibee.utils.ResizeImageIcon;

@SuppressWarnings("serial")
public class DeletedItemEntry extends JPanel{
    private Media media;
    private int ySize = 80;

	public DeletedItemEntry(Media media) {
            ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
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
            setBackground(Color.lightGray);
            
            JLabel label = new JLabel();
            label.setIcon(icon);
            add(label);

             JLabel text = new JLabel(" " + bundle.getString("titleLabel") + ": " + media.getTitle() + ", " + bundle.getString("genreLabel") + ": " + media.getGenre() + ", " + bundle.getString("yearComboBox") + ": " + media.getLentYear());
            add(text);
	}

    public Media getMedia() {
        return media;
    }

    public int getySize() {
        return ySize;
    }
        
        

}
