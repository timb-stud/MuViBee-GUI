package util.detailsList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import muvibee.media.Media;

@SuppressWarnings("serial")
public class DetailsListEntry extends JPanel{
	
	private String special;
	private String title;
        private int ySize = 50;
	
	public DetailsListEntry(Media media, String special) {
		this.special = special;
		this.title = media.getTitle();	
		
		// -----------------
		// |    Titel       |
		// |________________|
		// |    Info 1      |
		// |________________|
                
		setLayout(new GridLayout(2,1, +10, -10));
		setPreferredSize(new Dimension(10, ySize));
                setBackground(Color.white);

                add(new JLabel(title));
		add(new JLabel(special));
	}
	

	public String getSpecial() {
		return special;
	}

	public String getTitle() {
		return title;
	}
	
	public void setSpecial(String text) {
		this.special = text;
	}

        public int getySize() {
            return ySize;
        }
        
}
