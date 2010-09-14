package util.coverDetailsList.deleteditemslist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import muvibee.media.Media;

@SuppressWarnings("serial")
public class DeletedItemEntry extends JPanel{
    private Media media;
    private int ySize = 80;

	public DeletedItemEntry(Media media) {
            this.media = media;
            	BufferedImage dest = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = (Graphics2D) dest.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setColor(Color.red);
                g2d.fillRect(0, 0, 80, 80);
                g2d.setColor(Color.white);
                g2d.drawString("Bild", 40, 40);
		g2d.dispose();

            ImageIcon icon = new ImageIcon(dest);

            // ----------------------
            // |       |             |
            // | Icon  | Titel, usw  |
            // |_______|_____________|
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(300, 80));

            JLabel label = new JLabel();
            label.setIcon(icon);
            add(label, BorderLayout.WEST);

            JLabel text = new JLabel(media.getTitle() + ", " + media.getLendYear() + ", und so weiter");
            add(text, BorderLayout.CENTER);
	}



	public ImageIcon resizeIcon(int width, int height, BufferedImage image) {
		ImageIcon result = null;
		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		AffineTransform trans = AffineTransform.getScaleInstance((double)width/image.getWidth(), (double)height/image.getHeight());

		Graphics2D g2d = (Graphics2D) dest.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(image, trans, null);
		g2d.dispose();

		result = new ImageIcon(dest);
		return result;
	}

    public Media getMedia() {
        return media;
    }

    public int getySize() {
        return ySize;
    }
        
        

}
