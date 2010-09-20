/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Christian
 */
public class AboutDialog extends JDialog{

    public AboutDialog(JFrame parent) {
        super(parent, "About Dialog", true);
        getContentPane().setLayout(new BorderLayout());

        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.add(new JLabel("Entwickler von MuViBee:"));
        b.add(new JLabel("- Tim Bartsch"));
        b.add(new JLabel("- Stanislav Tartakovski"));
        b.add(new JLabel("- Christian Rech"));
        b.add(new JLabel("- Lucian Schneider"));
        b.add(new JLabel("- Thomas Altmeyer"));
        b.add(new JLabel("- Tobias Lana"));
        b.add(new JLabel("- Volkan Goekayya"));
        b.add(new JLabel("- Dominik Janßen"));
        b.add(new JLabel("- Yassir Klose"));
        b.add(Box.createGlue());
        getContentPane().add(b, BorderLayout.CENTER);


    JPanel p2 = new JPanel();
    JButton ok = new JButton("Ok");
    p2.add(ok);
    getContentPane().add(p2, "South");

    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        setVisible(false);
      }
    });

    setSize(250, 250);
    }



}
