package util.coversList;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import muvibee.media.Media;
import muvibee.utils.ResizeImageIcon;

@SuppressWarnings("serial")
public class CoverListEntry extends JLabel {

	private ImageIcon icon;
        private Media media;
        private int ySize = 100;

        public CoverListEntry(Media media) {
            this.media = media;
            if (media.getCover() != null)
                this.icon = ResizeImageIcon.resizeIcon(90, 100, media.getCover());
            else
                this.icon = null;
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
