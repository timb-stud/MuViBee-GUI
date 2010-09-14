package util.coverDetailsList;

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
public class CoverDetailsListEntry extends JPanel{
	
	private ImageIcon icon;
	private String special;
	private String title;
	
	public CoverDetailsListEntry(Media media, String special) {
                if (media.getCover() != null)
                    this.icon = resizeIcon(70, 70, media.getCover());
                else
                    this.icon = null;
		this.special = special;
		this.title = media.getTitle();	
		
		// -----------------
		// |       | Info 1 |
		// | Cover |________|
		// |       | Titel  |
		// |_______|________|
		setLayout(new GridLayout(1,2, -40,0));
		setPreferredSize(new Dimension(10, 80));
		
		JLabel label = new JLabel();
		label.setIcon(icon);
		add(label);
		
		JPanel info1_title = new JPanel(new GridLayout(2,1,0,-20));
		JLabel info1Lbl = new JLabel(special);
		JLabel titleLbl = new JLabel(title);
		info1_title.add(info1Lbl);
		info1_title.add(titleLbl);		
		add(info1_title);
	}
	

	
	private ImageIcon resizeIcon(int width, int height, BufferedImage image) {
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
	
	public ImageIcon getIcon() {
		return icon;
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

}
