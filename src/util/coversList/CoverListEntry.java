package util.coversList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import muvibee.media.Media;
import muvibee.utils.ResizeImageIcon;

/**
 * Erstellt ein Label und fügt diesem ein Cover hinzu
 * @author Lucian Schneider, Christian Rech
 */
@SuppressWarnings("serial")
public class CoverListEntry extends JLabel {

	private ImageIcon icon;
        private Media media;
        private int ySize = 100;

        public CoverListEntry(Media media) {
            this.media = media;
            if (media.getCover() != null)
                this.icon = ResizeImageIcon.resizeIcon(100, 120, media.getCover());
            else
                this.icon = null;
	}

  //------(Getter & Setter )----->
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
