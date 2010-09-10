package util;



import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CoverDetailsListEntry extends JPanel{

	private ImageIcon icon;
	private String author;
	private String title;

	public CoverDetailsListEntry(String cover, String author, String title) {
		this.icon = resizeIcon(70, 70, "resources/icons/" + cover);
		this.author=author;
		this.title=title;

		// -----------------
		// |       | Author |
		// | Cover |________|
		// |       | Titel  |
		// |_______|________|
		setLayout(new GridLayout(1,2, -40,0));
		setPreferredSize(new Dimension(10, 80));

		JLabel label = new JLabel();
		label.setIcon(icon);
		add(label);

		JPanel author_title = new JPanel(new GridLayout(2,1,0,-20));
		JLabel authorLbl = new JLabel(author);
		JLabel titleLbl = new JLabel(title);
		author_title.add(authorLbl);
		author_title.add(titleLbl);
		add(author_title);
	}

	private ImageIcon resizeIcon(int width, int height, String path) {
		ImageIcon result = null;
		try {
			BufferedImage src = ImageIO.read(new File(path));
			BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			AffineTransform trans = AffineTransform.getScaleInstance((double)width/src.getWidth(), (double)height/src.getHeight());

			Graphics2D g2d = (Graphics2D) dest.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2d.drawImage(src, trans, null);
			g2d.dispose();

			result = new ImageIcon(dest);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}


}
