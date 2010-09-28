package muvibee.gui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import muvibee.MuViBee;

/**
 * Erstellt Help Dialog und ladet HTML Datei mit Daten
 * @author Christian Rech, Stanislav Tartakowski
 */
public class HelpDialog extends JDialog {

    public HelpDialog(JFrame parent) {
        super(parent, ResourceBundle.getBundle(MuViBee.mainBundlePath).getString("helpDialog"), true);
        final JDialog dialog = this;
        setSize(900, 900);

        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        String close = bundle.getString("close");

        JPanel panel = new JPanel(new BorderLayout());
        JButton b = new JButton(close);
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
            if(Locale.getDefault() == Locale.GERMAN)
                ep.setPage(getClass().getResource("resources/help/help_de.html"));
            else
                ep.setPage(getClass().getResource("resources/help/help_en.htm"));
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
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
