/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.controller;

import com.guru.stokguruv2.entitie.model.KdvItem;
import com.guru.stokguruv2.entitie.model.KdvTipKarti;
import com.guru.stokguruv2.entitie.model.Stok;
import com.guru.stokguruv2.gui.IMainFrame;
import com.guru.stokguruv2.gui.IStokKarti;
import com.guru.stokguruv2.service.serviceImp.KdvServiceDaoImp;
import com.guru.stokguruv2.service.serviceImp.StokServiceDaoImp;
import com.guru.stokguruv2.view.StokKartiNavigationPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class StokKartiController {

    private IMainFrame mainFrame;
    private IStokKarti stokKarti;
    StokServiceDaoImp stokService = new StokServiceDaoImp();
    KdvServiceDaoImp kdvService = new KdvServiceDaoImp();
    String stokKodu;
    String stokBarkodu;
    String stokAdi;
    int stokTipi;
    int kdvTipi;
    String stokBirimi;
    String aciklama;
    Timestamp timestamp;

    List<KdvTipKarti> kdvTipleriListesi;

    private StokKartiNavigationPanel navigationPanel;

    public StokKartiController(IStokKarti stokKarti, IMainFrame mainFrame) {
        this.stokKarti = stokKarti;
        this.mainFrame = mainFrame;
        initController();

        navigationPanel = new StokKartiNavigationPanel(this);

        stokKarti.getNavPanel().add(navigationPanel, BorderLayout.SOUTH);

        stokKarti.getNavPanel().revalidate();
        stokKarti.getNavPanel().repaint();
    }

    private void initController() {
        kdvleriCek();
        LocalDateTime now = LocalDateTime.now();
        timestamp = Timestamp.valueOf(now);
        stokKarti.setTarih(timestamp);
        aramaYap();

    }

    private void getStokFromFields() {
        LocalDateTime now = LocalDateTime.now();
        timestamp = Timestamp.valueOf(now);

        stokKodu = stokKarti.getStokKodu();
        stokBarkodu = stokKarti.getStokBarkodu();
        stokAdi = stokKarti.getStokAdi();
        stokTipi = stokKarti.getStokTipi();

        KdvItem selectedItem = (KdvItem) stokKarti.getKdvComboBox().getSelectedItem();
        if (selectedItem != null) {
            kdvTipi = selectedItem.getId();
        } else {
            kdvTipi = 0;
        }

        stokBirimi = stokKarti.getStokBirimi();
        aciklama = stokKarti.getAciklama();
    }

    public void updateFormFields(Stok stok) {
        stokKarti.setStokKodu(stok.getStokKodu());
        stokKarti.setStokBarkodu(stok.getBarkodu());
        stokKarti.setStokAdi(stok.getStokAdi());
        stokKarti.setAciklama(stok.getAciklama());

        KdvItem selectedItem = null;
        for (int i = 0; i < stokKarti.getKdvComboBox().getItemCount(); i++) {
            KdvItem item = (KdvItem) stokKarti.getKdvComboBox().getItemAt(i);
            if (item.getId() == stok.getKdvTipi()) {
                selectedItem = item;
                break;
            }
        }
        stokKarti.getKdvComboBox().setSelectedItem(selectedItem);

        String stokTipiStr = Integer.toString(stok.getStokTipi());
        stokKarti.setStokTipi(stokTipiStr);

        if (stok.getOlusturmaZamani() != null) {
            LocalDateTime olusturmaZamani = stok.getOlusturmaZamani().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            Timestamp timestamp = Timestamp.valueOf(olusturmaZamani);

            stokKarti.setTarih(timestamp);
        } else {
            stokKarti.setTarih(null);
        }
        stokKarti.setStokBirimi(stok.getBirimi());
    }

    public void saveStok() {

        String stokKodu = stokKarti.getStokKodu();
        if (stokKodu == null || stokKodu.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stok Kodu boş olamaz!");
        } else {

            getStokFromFields();

            if (stokKodu == null || stokKodu.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Stok Kodu girilmelidir!");
            } else if (stokBarkodu == null || stokBarkodu.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Stok Barkodu girilmelidir!");
            } else if (stokAdi == null || stokAdi.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Stok Adı girilmelidir!");
            } else if (stokBirimi == null || stokBirimi.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Stok Birimi girilmelidir!");
            } else if (kdvTipi == 0 || stokTipi == 0) {
                JOptionPane.showMessageDialog(null, "KDV tipi ve Stok Tipleri Girilmelidir!");
            } else {

                try {
                    Stok stokkarti = new Stok(stokKodu, stokAdi, stokTipi, stokBirimi, stokBarkodu, kdvTipi, aciklama, timestamp);
                    String a = stokService.addStokKarti(stokkarti);
                    MainFrameController.getInstance(mainFrame).getStokListesiController().tabloyuDoldur();
                    JOptionPane.showMessageDialog(null, a);

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

            System.out.println("Stok Kaydedildi: " + stokKodu);
        }
    }

    private void aramaYap() {
        stokKarti.buttonAra(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stokKodu = stokKarti.getStokKodu();
                if (!stokKodu.isEmpty()) {
                    try {
                        List<Stok> stoklar = stokService.searchStokByKodu(stokKodu);

                        if (stoklar.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Stok bulunamadı.");
                        } else if (stoklar.size() == 1) {

                            Stok stok = stoklar.get(0);
                            JOptionPane.showMessageDialog(null,
                                    "Stok Kodu: " + stok.getStokKodu()
                                    + "\nStok Adı: " + stok.getStokAdi()
                                    + "\nStok Tipi: " + stok.getStokTipi()
                                    + "\nStok Birimi: " + stok.getBirimi()
                                    + "\nStok Barkodu: " + stok.getBarkodu()
                                    + "\nKDV Tipi: " + stok.getKdvTipi()
                                    + "\nAçıklama: " + stok.getAciklama()
                                    + "\nTarih: " + stok.getOlusturmaZamani()
                            );
                            updateFormFields(stok);

                        } else {

                            String[] options = new String[stoklar.size()];
                            for (int i = 0; i < stoklar.size(); i++) {
                                options[i] = stoklar.get(i).getStokKodu() + " - " + stoklar.get(i).getStokAdi();
                            }

                            String selectedOption = (String) JOptionPane.showInputDialog(
                                    null,
                                    "Bir stok seçin:",
                                    "Stok Seçimi",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]
                            );

                            if (selectedOption != null) {

                                String selectedStokKodu = selectedOption.split(" - ")[0];
                                Stok stok = stokService.getStokbyStokKoduStokKarti(stokKodu);
                                updateFormFields(stok);

                            }
                        }
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Stok Kodu Girin!");
                }
            }
        });
    }

    public void deleteStok() {
        getStokFromFields();

        Stok stok = stokService.getStokbyStokKoduStokKarti(stokKodu);
        if (stokKodu != null && stok != null) {

            try {

                String response = stokService.removeStokKarti(stokKodu);

                JOptionPane.showMessageDialog(null, response);
                MainFrameController.getInstance(mainFrame).getStokListesiController().tabloyuDoldur();

                Stok previousStok = stokService.previousStok(stok.getId());

                if (previousStok != null) {
                    updateFormFields(previousStok);
                } else {

                    Stok nextStok = stokService.nextStok(stok.getId());

                    if (nextStok != null) {
                        updateFormFields(nextStok);
                    } else {

                        JOptionPane.showMessageDialog(null, "Stok kartı kalmadı.");
                        clearFormFields();
                    }
                }
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seçili stok bulunamadı.");
        }
    }

    private void clearFormFields() {
        stokKarti.setStokKodu("");
        stokKarti.setStokAdi("");
        stokKarti.setStokBirimi("");
        stokKarti.setStokBarkodu("");
        stokKarti.setAciklama("");
        kdvTipi = 0;

    }

    public void firstStok() {
        try {

            Stok stok = stokService.firsStok();
            if (stok != null) {
                updateFormFields(stok);

            } else {
                JOptionPane.showMessageDialog(null, "Stok Bulunmamakta!");
            }

        } catch (Exception e) {

        }

    }

    public void nextStok() {

        getStokFromFields();
        if (stokKodu != null && !stokKodu.isEmpty()) {
            Stok currentStok = stokService.getStokbyStokKoduStokKarti(stokKodu);
            Stok stok = stokService.nextStok(currentStok.getId());
            if (stok != null) {
                updateFormFields(stok);
            } else {
                JOptionPane.showMessageDialog(null, "Son Eklenen Stok");
            }

        } else {

            JOptionPane.showMessageDialog(null, "Stok Kodu Giriniz");
        }

    }

    public void previousStok() {
        getStokFromFields();
        if (stokKodu != null && !stokKodu.isEmpty()) {
            Stok currentStok = stokService.getStokbyStokKoduStokKarti(stokKodu);
            Stok stok = stokService.previousStok(currentStok.getId());
            if (stok != null) {
                updateFormFields(stok);
            } else {
                JOptionPane.showMessageDialog(null, "İlk Eklenen Stok");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Stok Kodu Giriniz");
        }

    }

    public void lastStok() {

        try {

            Stok stok = stokService.lastStok();
            if (stok != null) {
                updateFormFields(stok);

            } else {
                JOptionPane.showMessageDialog(null, "Stok Bulunmamakta!");
            }

        } catch (Exception e) {

        }

    }

    public void updateStok() {
        getStokFromFields();
        if (stokKodu != null) {
            try {

                Stok stok = new Stok(stokKodu, stokAdi, stokTipi, stokBirimi, stokBarkodu, kdvTipi, aciklama, timestamp);
                String guncellemeCevabi = stokService.updateStokKarti(stok);
                JOptionPane.showMessageDialog(null, guncellemeCevabi);
                MainFrameController.getInstance(mainFrame).getStokListesiController().tabloyuDoldur();
            } catch (RuntimeException es) {
                JOptionPane.showMessageDialog(null, es.getMessage());
            }

        } else {

        }
    }

    public void kdvleriCek() {
        stokKarti.getKdvComboBox().removeAllItems();
        try {

            kdvTipleriListesi = kdvService.getAllKdvTypes();
            for (KdvTipKarti kdvTipi : kdvTipleriListesi) {
                stokKarti.getKdvComboBox().addItem(new KdvItem(kdvTipi.getId(), kdvTipi.getAdi()));
              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
