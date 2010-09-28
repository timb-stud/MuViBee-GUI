package muvibee.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import muvibee.MuViBee;

/**
 * Erstellt About Dialog und ladet HTML Datei mit Daten
 * @author Stanislav Tartakowski, Christian Rech
 */
public class AboutDialog extends JDialog{

    public AboutDialog(JFrame parent) {
        super(parent, ResourceBundle.getBundle(MuViBee.mainBundlePath).getString("aboutDialog"), true);

        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        String close = bundle.getString("close");

        final JDialog dialog = this;
        setSize(700, 500);

        JPanel panel = new JPanel(new BorderLayout());
        JButton b = new JButton(close);


        final JEditorPane htmlPane = new JEditorPane();
        htmlPane.setEditorKit(new HTMLEditorKit());
        htmlPane.setEditable(false);

        getContentPane().add(panel);
        panel.add(b, BorderLayout.SOUTH);
        panel.add(new JScrollPane(htmlPane));

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setVisible(false);

            }
        });

        try {
            htmlPane.setPage(getClass().getResource("resources/about/about.html"));
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
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
