/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JButton;
import muvibee.MuViBee;
import muvibee.ean.EAN;
import muvibee.ean.EanBol;
import muvibee.gui.StatusBarModel;
import muvibee.media.Book;
import muvibee.media.Music;
import muvibee.media.Video;

/**
 *
 * @author bline
 */
public class AddActionListener implements ActionListener {

    private MuViBee mvb;

    public AddActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }

    public void actionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            int decision = mvb.showSelfEANDecisionFrame();
            if (decision >= 0) {
                mvb.resetSearch();
                if (button.getName().equals("add book button")) {
                    Book book;
                    if (decision == 0) {
                        String ean = mvb.showEanInputFrame();
                        if (ean == null) {
                            return;
                        }
                        try {
                            EanBol.setProxy("www-proxy.htw-saarland.de", "3128");
                            book = muvibee.ean.EAN.getBookData(ean);
                            StatusBarModel.getInstance().setSuccessMessage(bundle.getString("ean_found"));
                        } catch (muvibee.ean.NoResultException ex) {
                            StatusBarModel.getInstance().setFailMessage(bundle.getString("ean_not_found_retry"));
                            return;
                        } catch (muvibee.ean.MoreThanOneResultException ex) {
                            StatusBarModel.getInstance().setFailMessage(bundle.getString("many_eans_found_retry"));
                            return;
                        } catch (IOException ioex) {
                            StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                            return;
                        }
                    } else {
                        book = new Book();
                    }
                    mvb.setCurrentBook(book);
                } else {
                    if (button.getName().equals("add music button")) {
                        Music music;
                        if (decision == 0) {
                            String ean = mvb.showEanInputFrame();
                            if (ean == null) {
                                return;
                            }
                            try {
                                EanBol.setProxy("www-proxy.htw-saarland.de", "3128");
                                music = muvibee.ean.EAN.getMusicData(ean);
                                StatusBarModel.getInstance().setSuccessMessage(bundle.getString("ean_found"));
                            } catch (muvibee.ean.NoResultException ex) {
                                StatusBarModel.getInstance().setFailMessage(bundle.getString("ean_not_found_retry"));
                                return;
                            } catch (muvibee.ean.MoreThanOneResultException ex) {
                                StatusBarModel.getInstance().setFailMessage(bundle.getString("many_eans_found_retry"));
                                return;
                            } catch (IOException ex) {
                                StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                return;
                            }
                        } else {
                            music = new Music();
                        }
                        mvb.setCurrentMusic(music);
                    } else {
                        if (button.getName().equals("add video button")) {
                            Video video;
                            if (decision == 0) {
                                String ean = mvb.showEanInputFrame();
                                if (ean == null) {
                                    return;
                                }
                                try {
                                    EanBol.setProxy("www-proxy.htw-saarland.de", "3128");
                                    video = EAN.getVideoData(ean);
                                    StatusBarModel.getInstance().setSuccessMessage(bundle.getString("ean_found"));
                                } catch (muvibee.ean.NoResultException ex) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("ean_not_found_retry"));
                                    return;
                                } catch (muvibee.ean.MoreThanOneResultException ex) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("many_eans_found_retry"));
                                    return;
                                } catch (IOException ex2) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                    return;
                                }
                            } else {
                                video = new Video();
                            }
                            mvb.setCurrentVideo(video);
                        }
                    }
                }
            }
        }
    }
}
