package muvibee.gui.views.coverdetails;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import muvibee.media.Media;
import muvibee.utils.ResizeImageIcon;

/**
 * Erstellt Panel welches in die CoverDetailsList hinzugefügt wird
 * @author Christian Rech
 */
@SuppressWarnings("serial")
public class CoverDetailsListEntry extends JPanel{
	
	private ImageIcon icon;
	private String special;
	private String title;
        private int ySize = 80;

        /**
         * Erstellt default Ansicht
         * @param media
         * @param special
         */
	public CoverDetailsListEntry(Media media, String special) {
                setOpaque(false);
                if (media.getCover() != null)
                    this.icon = ResizeImageIcon.resizeIcon(70, 80, media.getCover());
                else
                    this.icon = null;
		this.special = special;
		this.title = media.getTitle();	
		
		// -----------------
		// |       | Titel  |
		// | Cover |________|
		// |       | Info 1 |
		// |_______|________|
                
                setLayout(new GridLayout(1,2, -360,0)); //antatt setLayout(new GridLayout(1,2, 0,0)); was is besser ?
		setPreferredSize(new Dimension(10, ySize));
                setBackground(Color.white);
		
		JLabel label = new JLabel();
		label.setIcon(icon);
		add(label);
		
		JPanel info1_title = new JPanel(new GridLayout(2,1,0,-20));
		JLabel info1Lbl = new JLabel(special);
		JLabel titleLbl = new JLabel(title);

                info1Lbl.setFont(new Font("Colibri", Font.PLAIN, 12));
                titleLbl.setFont(new Font("Colibri", Font.BOLD, 15));

		info1_title.add(titleLbl);
                info1_title.add(info1Lbl);
                info1_title.setOpaque(false);
		add(info1_title);
	}
	

 //--------( Getter & Setter )------>

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
