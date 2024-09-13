/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.controller;

import com.guru.stokguruv2.gui.IMainFrame;
import com.guru.stokguruv2.view.KdvKarti;
import com.guru.stokguruv2.view.StokKarti;
import com.guru.stokguruv2.view.MainFrame;
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
    private KdvKarti kdvKartiFrame = null;
    
    public MainFrameController(IMainFrame mainFrame){
        this.mainFrame=mainFrame;
        
        initController(); 
        
    }

    private void initController() {
        
        URL iconURLAdd = getClass().getResource("/images/plus.png");
         URL iconURLtable = getClass().getResource("/images/table.png");

    // URL null olabilir, bu yüzden kontrol yapalım
    if (iconURLAdd == null) {
        System.err.println("Icon resource not found.");
        return;
    }
    
     if (iconURLtable == null) {
        System.err.println("Icon resource not found.");
        return;
    }
    
    // İkonu yükleyin ve yeniden boyutlandırın
    ImageIcon AddIcon = new ImageIcon(iconURLAdd);
    Image img = AddIcon.getImage(); // İkonu Image nesnesine çevirin
    Image scaledImgAdd = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH); // Boyutlandırma
    ImageIcon scaledIconAdd = new ImageIcon(scaledImgAdd);
    
    
     if (iconURLAdd == null) {
        System.err.println("Icon resource not found.");
        return;
    }
    
    // İkonu yükleyin ve yeniden boyutlandırın
    ImageIcon TableIcon = new ImageIcon(iconURLtable);
    Image imgt = TableIcon.getImage(); // İkonu Image nesnesine çevirin
    Image scaledImgTable = imgt.getScaledInstance(16, 16, Image.SCALE_SMOOTH); // Boyutlandırma
    ImageIcon scaledIconTable = new ImageIcon(scaledImgTable);
    
    
    // Toolbar'a dinamik olarak buton ekleme
    JButton btn1 = new JButton();
    JButton btn2 = new JButton();
    
    // Butonlara ikon ekleme
    btn1.setIcon(scaledIconAdd);
    btn2.setIcon(scaledIconTable);

    btn1.setFocusable(false);
    
    btn2.setFocusable(false);
    mainFrame.getMainToolBar().setFloatable(false); // Yer değiştirme özelliğini kapatır
    
    mainFrame.getMainToolBar().add(btn1);
    mainFrame.getMainToolBar().add(btn2);
    
    // Menü Bara dinamik olarak menü ve menü öğesi ekleme
    JMenu stokMenu = new JMenu("Stok");
    JMenuItem stokKarti = new JMenuItem("Stok Kartı");
    JMenuItem stokListesi = new JMenuItem("Stok Listesi");
    JMenuItem kdvTipi = new JMenuItem("Kdv Tip Kartı");
    
    // Menü öğelerine ikon ekleme
    stokKarti.setIcon(scaledIconAdd); // İkonu ekleyin
    stokListesi.setIcon(scaledIconTable);
   
    
    // Stok menüsüne öğeleri ekleme
    stokMenu.add(stokKarti);
    stokMenu.add(stokListesi);
    stokMenu.add(kdvTipi);
    
    // Ana Menü Bara menüyü ekleme
    mainFrame.getMainMenuBar().add(stokMenu);
    
    stokListesi.addActionListener(e -> openStokListesi());
    stokKarti.addActionListener(e -> openStokKarti());
    kdvTipi.addActionListener(e -> openKdvKarti());
    
    btn1.addActionListener(e -> openStokKarti());
    btn2.addActionListener(e -> openStokListesi());
    // Yeniden çizim ve boyut ayarlaması
    mainFrame.getMainToolBar().revalidate();
    mainFrame.getMainMenuBar().revalidate();
    }
    
     // StokKartiInternalFrame'i açan fonksiyon (tek örnek üzerinden)
    private void openKdvKarti() {
        if (kdvKartiFrame == null || kdvKartiFrame.isClosed()) {
            kdvKartiFrame = new KdvKarti();
            mainFrame.getDesktopPaneControl().add(kdvKartiFrame);
        }
        kdvKartiFrame.setVisible(true);
        kdvKartiFrame.toFront();
    }
  
    // StokKartiInternalFrame'i açan fonksiyon (tek örnek üzerinden)
    private void openStokKarti() {
        if (stokKartiFrame == null || stokKartiFrame.isClosed()) {
            stokKartiFrame = new StokKarti();
            mainFrame.getDesktopPaneControl().add(stokKartiFrame);
        }
        stokKartiFrame.setVisible(true);
        stokKartiFrame.toFront();
    }

    // StokListesiInternalFrame'i açan fonksiyon (tek örnek üzerinden)
    private void openStokListesi() {
        if (stokListesiFrame == null || stokListesiFrame.isClosed()) {
            stokListesiFrame = new StokListesi();
            mainFrame.getDesktopPaneControl().add(stokListesiFrame);
        }
        stokListesiFrame.setVisible(true);
        stokListesiFrame.toFront();
    }
    
    
    
}
