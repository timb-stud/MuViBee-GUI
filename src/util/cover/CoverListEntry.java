package util.cover;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import muvibee.media.Media;

@SuppressWarnings("serial")
public class CoverListEntry extends JLabel {

	private ImageIcon icon;
        private Media media;
        private int ySize = 80;

        public CoverListEntry(Media media) {
            this.media = media;
            if (media.getCover() != null)
		this.icon = resizeIcon(90,100, media.getCover());
            else
                this.icon = null;
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

        public Media getMedia() {
            return media;
        }

        public int getySize() {
            return ySize;
        }

        public ImageIcon getIcon() {
            return this.icon;
        }
}
