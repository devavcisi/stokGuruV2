/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.controller;

import com.guru.stokguruv2.gui.IMainFrame;
import com.guru.stokguruv2.view.KdvKarti;
import com.guru.stokguruv2.view.StokKarti;
import com.guru.stokguruv2.view.StokListesi;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author User
 */
public class MainFrameController {

    private IMainFrame mainFrame;
    private StokKarti stokKartiFrame = null;
    private StokListesi stokListesiFrame = null;
    private KdvKartiController kdvKartiController = null;
    private KdvKarti kdvKartiFrame = null;
    private StokKartiController stokKartiController = null;
    private static MainFrameController instance;
    private StokListesiController stokListesiController = null;

    public MainFrameController(IMainFrame mainFrame) {
        this.mainFrame = mainFrame;

        initController();

    }

    public static MainFrameController getInstance(IMainFrame mainFrame) {
        if (instance == null) {
            instance = new MainFrameController(mainFrame);
        }
        return instance;
    }

    private void initController() {

        URL iconURLAdd = getClass().getResource("/images/plus.png");
        URL iconURLtable = getClass().getResource("/images/table.png");
        URL iconURLkdv = getClass().getResource("/images/paying.png");

        ImageIcon AddIcon = new ImageIcon(iconURLAdd);
        Image img = AddIcon.getImage();
        Image scaledImgAdd = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIconAdd = new ImageIcon(scaledImgAdd);

        ImageIcon TableIcon = new ImageIcon(iconURLtable);
        Image imgt = TableIcon.getImage();
        Image scaledImgTable = imgt.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIconTable = new ImageIcon(scaledImgTable);

        ImageIcon KdvIcon = new ImageIcon(iconURLkdv);
        Image imgk = KdvIcon.getImage();
        Image scaledImgKdv = imgk.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIconKdv = new ImageIcon(scaledImgKdv);

        JButton btnStokKarti = new JButton();
        JButton btnStokListesi = new JButton();
        JButton btnKdvKarti = new JButton();

        btnStokKarti.setIcon(scaledIconAdd);
        btnStokListesi.setIcon(scaledIconTable);
        btnKdvKarti.setIcon(scaledIconKdv);

        btnStokKarti.setFocusable(false);

        btnStokListesi.setFocusable(false);
        btnKdvKarti.setFocusable(false);
        mainFrame.getMainToolBar().setFloatable(false);

        mainFrame.getMainToolBar().add(btnStokKarti);
        mainFrame.getMainToolBar().add(btnStokListesi);
        mainFrame.getMainToolBar().add(btnKdvKarti);

        JMenu stokMenu = new JMenu("Stok");
        JMenu KDVMenu = new JMenu("Kdv");
        JMenuItem stokKarti = new JMenuItem("Stok Kartı");
        JMenuItem stokListesi = new JMenuItem("Stok Listesi");
        JMenuItem kdvTipi = new JMenuItem("Kdv Tip Kartı");

        stokKarti.setIcon(scaledIconAdd);
        stokListesi.setIcon(scaledIconTable);
        kdvTipi.setIcon(scaledIconKdv);

        stokMenu.add(stokKarti);
        stokMenu.add(stokListesi);
        KDVMenu.add(kdvTipi);

        mainFrame.getMainMenuBar().add(stokMenu);
        mainFrame.getMainMenuBar().add(KDVMenu);

        stokListesi.addActionListener(e -> openStokListesi());
        stokKarti.addActionListener(e -> openStokKarti());
        kdvTipi.addActionListener(e -> openKdvKarti());

        btnStokKarti.addActionListener(e -> openStokKarti());
        btnStokListesi.addActionListener(e -> openStokListesi());
        btnKdvKarti.addActionListener(e -> openKdvKarti());

        mainFrame.getMainToolBar().revalidate();
        mainFrame.getMainMenuBar().revalidate();
    }

    private void openKdvKarti() {
        if (kdvKartiFrame == null || kdvKartiFrame.isClosed()) {
            kdvKartiFrame = new KdvKarti();
            mainFrame.getDesktopPaneControl().add(kdvKartiFrame);
            kdvKartiController = new KdvKartiController(kdvKartiFrame, mainFrame);
        }
        kdvKartiFrame.setVisible(true);
        kdvKartiFrame.toFront();
    }

    public StokKarti getStokKartiFrame() {
        return stokKartiFrame;
    }

    public StokKartiController getStokKartiController() {
        return stokKartiController;
    }

    public StokListesiController getStokListesiController() {
        return stokListesiController;
    }

    public KdvKartiController getKdvKartiController() {
        return kdvKartiController;
    }

    public void openStokKarti() {
        if (stokKartiFrame == null || stokKartiFrame.isClosed()) {
            stokKartiFrame = new StokKarti();
            stokKartiController = new StokKartiController(stokKartiFrame, mainFrame);
            mainFrame.getDesktopPaneControl().add(stokKartiFrame);
        }
        stokKartiFrame.setVisible(true);
        stokKartiFrame.toFront();
    }

    private void openStokListesi() {
        if (stokListesiFrame == null || stokListesiFrame.isClosed()) {
            stokListesiFrame = new StokListesi();
            mainFrame.getDesktopPaneControl().add(stokListesiFrame);
            stokListesiController = new StokListesiController(stokListesiFrame, mainFrame);
        }
        stokListesiFrame.setVisible(true);
        stokListesiFrame.toFront();
    }

}
