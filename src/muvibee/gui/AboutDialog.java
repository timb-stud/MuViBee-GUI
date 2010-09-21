/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muvibee.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import muvibee.MuViBee;

/**
 *
 * @author Christian
 */
public class AboutDialog extends JDialog{

    public AboutDialog(JFrame parent) {
        super(parent, ResourceBundle.getBundle(MuViBee.mainBundlePath).getString("aboutDialog"), true);

        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        String close = bundle.getString("close");

        final JDialog dialog = this;
        setSize(300, 300);
//        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JButton b = new JButton(close);
//        JScrollPane sb = new JScrollPane(ep);


        final JEditorPane htmlPane = new JEditorPane();
        htmlPane.setEditorKit(new HTMLEditorKit());
        htmlPane.setEditable(false);

        getContentPane().add(panel);
        panel.add(b, BorderLayout.SOUTH);
        panel.add(htmlPane);
//        panel.add(sb);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setVisible(false);

            }
        });

        try {
            htmlPane.setPage(getClass().getResource("../../muvibee/resources/HTML/about.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        htmlPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent arg0) {
                if (arg0.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        htmlPane.setPage(arg0.getURL());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });
    }



//    public AboutDialog(JFrame parent) {
//        super(parent, "About Dialog", true);
//        getContentPane().setLayout(new BorderLayout());
//
//        Box b = Box.createVerticalBox();
//        b.add(Box.createGlue());
//        b.add(new JLabel("Entwickler von MuViBee:"));
//        b.add(new JLabel("- Tim Bartsch"));
//        b.add(new JLabel("- Stanislav Tartakovski"));
//        b.add(new JLabel("- Christian Rech"));
//        b.add(new JLabel("- Lucian Schneider"));
//        b.add(new JLabel("- Thomas Altmeyer"));
//        b.add(new JLabel("- Tobias Lana"));
//        b.add(new JLabel("- Volkan Goekayya"));
//        b.add(new JLabel("- Dominik Janßen"));
//        b.add(new JLabel("- Yassir Klose"));
//        b.add(Box.createGlue());
//        getContentPane().add(b, BorderLayout.CENTER);
//
//
//    JPanel p2 = new JPanel();
//    JButton ok = new JButton("Ok");
//    p2.add(ok);
//    getContentPane().add(p2, "South");
//
//    ok.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        setVisible(false);
//      }
//    });
//
//    setSize(250, 250);
//    }
//


}
