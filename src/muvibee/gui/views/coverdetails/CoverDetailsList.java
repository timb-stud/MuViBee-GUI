package muvibee.gui.views.coverdetails;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import muvibee.MuViBee;
import muvibee.lists.MediaList;

import muvibee.media.*;

/**
 * Erstellt und verwaltet die Cover Details Ansicht
 * @author Christian Rech
 */
@SuppressWarnings("serial")
public class CoverDetailsList extends JList implements Observer {

    DefaultListModel listModel;
    CoverDetailsListRenderer lcr;

    /**
     * Erstellt eine Liste, setz darauf ein ListModel und erstellt für die Liste ein SelectionListener
     * @param muvibee - Ausgewähltes Objekt wird an die Klasse MuViBee üergeben
     */
    public CoverDetailsList(final MuViBee muvibee) {
        listModel = new DefaultListModel();
        lcr = new CoverDetailsListRenderer();

        setModel(listModel);
        setCellRenderer(lcr);
        setPrototypeCellValue(new CoverDetailsListEntry(new Book(), TOOL_TIP_TEXT_KEY));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLayoutOrientation(JList.VERTICAL);
        setVisibleRowCount(10);

        addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting()) {
                    Object object = listModel.getElementAt(getSelectedIndex());
                    if (object instanceof CoverDetailsListEntryBook) {
                        muvibee.setCurrentBook(((CoverDetailsListEntryBook) object).getBook());
                    } else if (object instanceof CoverDetailsListEntryMusic) {
                        muvibee.setCurrentMusic(((CoverDetailsListEntryMusic) object).getMusic());
                    } else if (object instanceof CoverDetailsListEntryVideo) {
                        muvibee.setCurrentVideo(((CoverDetailsListEntryVideo) object).getVideo());

                    }
                }
            }
        });
    }

    /**
     * Dient zum Hinzufügen eines Elements in die Liste
     * @param entry - Hinzuzufügendes Element
     */
    private void listAdd(CoverDetailsListEntry entry) {
        listModel.addElement(entry);
    }

    /**
     * Observer Pattern: Löscht komplette Liste und erzeugt neue
     * @param list - neue Liste mit Elementen
     * @param o - ist immer NULL. Wird nicht verwendet
     */
    @Override
    public void update(Observable list, Object o) {
        LinkedList<Media> mList = ((MediaList) list).getList();
        listModel.clear();

        for (Media m : mList) {
            if (m instanceof Book) {
                listAdd(new CoverDetailsListEntryBook((Book) m));
            } else if (m instanceof Video) {
                listAdd(new CoverDetailsListEntryVideo((Video) m));
            } else if (m instanceof Music) {
                listAdd(new CoverDetailsListEntryMusic((Music) m));
            } else {
                System.out.println("nix von allem");
            }
        }
    }
}
