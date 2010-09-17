/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author christian
 */
public class ResizeImageIcon {

    	public static ImageIcon resizeIcon(int width, int height, BufferedImage image) {
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

}
