package muvibee.gui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ResourceBundle;
import muvibee.MuViBee;

/**
 *
 * @author Christian
 */
public class HelpDialog extends JDialog {

    public HelpDialog(JFrame parent) {
        super(parent, "Help Dialog", true);
        final JDialog dialog = this;
        setSize(500, 500);
//        setLocationRelativeTo(null);

//        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.getMainBundlePath());
//        String close = bundle.getString("close");

        JPanel panel = new JPanel(new BorderLayout());
        JButton b = new JButton("close");
        final JEditorPane ep = new JEditorPane();
        JScrollPane sb = new JScrollPane(ep);

        ep.setEditable(false);
        getContentPane().add(panel);
        panel.add(b, BorderLayout.SOUTH);
        panel.add(sb);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setVisible(false);

            }
        });

        try {
            ep.setPage(getClass().getResource("/Resources/HTML/help.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ep.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent arg0) {
                if (arg0.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        ep.setPage(arg0.getURL());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
