/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muvibee.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import muvibee.MuViBee;
import muvibee.ean.EAN;
import muvibee.ean.MoreThanOneResultException;
import muvibee.ean.NoResultException;
import muvibee.ean.WrongArticleTypeException;
import muvibee.gui.ProgressBarDialog;
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
    private Book book;
    private Music music;
    private Video video;

    public AddActionListener(MuViBee mvb) {
        this.mvb = mvb;
    }


    public void actionPerformed(ActionEvent e) {
        final ResourceBundle bundle = ResourceBundle.getBundle(MuViBee.mainBundlePath);
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            int decision = mvb.showSelfEANDecisionFrame();
            if (decision >= 0) {
                mvb.resetSearch();
                if (button.getName().equals("add book button")) {
                    book = null;
                    if (decision == 0) {
                        final String ean = mvb.showEanInputFrame();
                        if (ean == null) {
                            return;
                        }
                        Runnable runCode = new Runnable() {

                            public void run() {
                                try {
                                    book = EAN.getBookData(ean);
                                    StatusBarModel.getInstance().setSuccessMessage(bundle.getString("ean_found"));
                                } catch (muvibee.ean.NoResultException ex) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("ean_not_found_retry"));
                                    return;
                                } catch (muvibee.ean.MoreThanOneResultException ex) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("many_eans_found_retry"));
                                } catch (muvibee.ean.WrongArticleTypeException ex) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("fales_ean_type"));
                                    return;
                                } catch (IOException ioex) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                    return;
                                } catch (Exception exc) {
                                    StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                    return;
                                }
                            }

                        };
                        ProgressBarDialog.getInstance().startProgressBar(runCode);
                        if (book == null) return;
                    } else {
                        book = new Book();
                    }
                    mvb.setCurrentBook(book);
                } else {
                    if (button.getName().equals("add music button")) {
                        music = null;
                        if (decision == 0) {
                            final String ean = mvb.showEanInputFrame();
                            if (ean == null) {
                                return;
                            }
                            Runnable runCode = new Runnable() {

                                public void run() {
                                    try {
                                        music = EAN.getMusicData(ean);
                                        StatusBarModel.getInstance().setSuccessMessage(bundle.getString("ean_found"));
                                    } catch (muvibee.ean.NoResultException ex) {
                                        StatusBarModel.getInstance().setFailMessage(bundle.getString("ean_not_found_retry"));
                                        return;
                                    } catch (muvibee.ean.MoreThanOneResultException ex) {
                                        StatusBarModel.getInstance().setFailMessage(bundle.getString("many_eans_found_retry"));
                                    } catch (muvibee.ean.WrongArticleTypeException ex) {
                                        StatusBarModel.getInstance().setFailMessage(bundle.getString("fales_ean_type"));
                                        return;
                                    } catch (IOException ioex) {
                                        StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                        return;
                                    } catch (Exception exc) {
                                        StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                        return;
                                    }
                                }

                            };
                            ProgressBarDialog.getInstance().startProgressBar(runCode);
                            if (music == null) return;
                        } else {
                            music = new Music();
                        }
                        mvb.setCurrentMusic(music);
                    } else {
                        if (button.getName().equals("add video button")) {
                            video = null;
                            if (decision == 0) {
                                final String ean = mvb.showEanInputFrame();
                                if (ean == null) {
                                    return;
                                }
                                Runnable runCode = new Runnable() {

                                    public void run() {
                                        try {
                                            video = EAN.getVideoData(ean);
                                            StatusBarModel.getInstance().setSuccessMessage(bundle.getString("ean_found"));
                                        } catch (muvibee.ean.NoResultException ex) {
                                            StatusBarModel.getInstance().setFailMessage(bundle.getString("ean_not_found_retry"));
                                            return;
                                        } catch (muvibee.ean.MoreThanOneResultException ex) {
                                            StatusBarModel.getInstance().setFailMessage(bundle.getString("many_eans_found_retry"));
                                        } catch (muvibee.ean.WrongArticleTypeException ex) {
                                            StatusBarModel.getInstance().setFailMessage(bundle.getString("fales_ean_type"));
                                            return;
                                        } catch (IOException ioex) {
                                            StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                            return;
                                        } catch (Exception exc) {
                                            StatusBarModel.getInstance().setFailMessage(bundle.getString("connectionError"));
                                            return;
                                        }
                                    }
                                };
                                ProgressBarDialog.getInstance().startProgressBar(runCode);
                                if (video == null) return;
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
