package muvibee.gui;

import java.awt.Component;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class LookAndFeelChanger {
	
	private LookAndFeelChanger() {}
	
	public static void changeLookAndFeel(String lnfName, Component component){
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if(lnfName.equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(component);
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public static String[] getLNFNames(){
		LookAndFeelInfo[] lafInfos = UIManager.getInstalledLookAndFeels();
		String[] lafS = new String[lafInfos.length];
		for (int i = 0; i < lafInfos.length; i++) {
			lafS[i] = lafInfos[i].getName();
		}
		return lafS;
	}

}
