/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.controller;

import com.guru.stokguruv2.entitie.model.KdvTipKarti;
import com.guru.stokguruv2.entitie.model.Stok;
import com.guru.stokguruv2.gui.IKdvKarti;
import com.guru.stokguruv2.service.serviceDao.KdvServiceDao;
import com.guru.stokguruv2.service.serviceImp.KdvServiceDaoImp;
import com.guru.stokguruv2.view.KdvTipKartiNavigationPanel;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class KdvKartiController {

    IKdvKarti kdvKarti;
    private KdvTipKartiNavigationPanel navigationPanel;
    KdvServiceDaoImp kdvService = new KdvServiceDaoImp();

    String kdvAdi;
    String kdvKodu;
    double kdvOrani;

    public KdvKartiController(IKdvKarti kdvKarti) {
        this.kdvKarti = kdvKarti;

        navigationPanel = new KdvTipKartiNavigationPanel(this);

        kdvKarti.getNavPanel().add(navigationPanel, BorderLayout.SOUTH);

        kdvKarti.getNavPanel().revalidate();
        kdvKarti.getNavPanel().repaint();
    }

    private void getKdvFromFields() {
        kdvAdi = kdvKarti.getKdvAdi();
        kdvKodu = kdvKarti.getKdvKodu();
        kdvOrani = Double.parseDouble(kdvKarti.getKdvOrani());
    }

    private void updateFormFields(KdvTipKarti kdvTip) {
        kdvKarti.setKdvKodu(kdvTip.getKodu());
        kdvKarti.setKdvAdi(kdvTip.getAdi());
        kdvKarti.setKdvOrani(kdvTip.getOrani().toString());
    }

    public void saveKdv() {
        getKdvFromFields();

        if (kdvKodu == null || kdvKodu.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "KDV Kodu boş olamaz!");
        } else if (kdvAdi == null || kdvAdi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "KDV Adı boş olamaz!");
        } else {

            try {

                KdvTipKarti kdvTipKarti = new KdvTipKarti(kdvKodu, kdvAdi, kdvOrani);
                String response = kdvService.addKdvTipKarti(kdvTipKarti);
                JOptionPane.showMessageDialog(null, response);

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public void deleteKdv() {
        getKdvFromFields();

        KdvTipKarti kdvTip = kdvService.getKdvTipKartiByKodu(kdvKodu);

        if (kdvKodu != null && kdvTip != null) {
            try {
                // KDV Tip Kartını sil
                String response = kdvService.removeKdvTipKarti(kdvKodu);
                JOptionPane.showMessageDialog(null, response);

                // Bir önceki kaydı getir
                KdvTipKarti previousKdv = kdvService.previousKdv(kdvTip.getId());

                if (previousKdv != null) {
                    updateFormFields(previousKdv); // Bir önceki kaydı göster
                } else {
                    // Eğer önceki kayıt yoksa, bir sonraki kaydı getir
                    KdvTipKarti nextKdv = kdvService.nextKdv(kdvTip.getId());

                    if (nextKdv != null) {
                        updateFormFields(nextKdv); // Bir sonraki kaydı göster
                    } else {
                        // Eğer ne önceki ne de sonraki kayıt varsa, stok kalmadı mesajı göster
                        JOptionPane.showMessageDialog(null, "KDV Tip Kartı kalmadı.");
                        clearFormFields(); // Form alanlarını temizleyin
                    }
                }
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seçili KDV Tip Kartı bulunamadı.");
        }
    }

    private void clearFormFields() {
        kdvKarti.setKdvKodu("");
        kdvKarti.setKdvAdi("");
        kdvKarti.setKdvOrani(null);
    }

    public void firstKdv() {

        try {
            KdvTipKarti kdvTip = kdvService.firstKdv();
            if (kdvTip != null) {
                updateFormFields(kdvTip);
            } else {
                JOptionPane.showMessageDialog(null, "Kdv Kartı Bulunamadı");
            }
        } catch (Exception e) {
        }

    }

    public void nextKdv() {
        getKdvFromFields();
        if (kdvKodu == null && kdvKodu.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Kdv Kodu Giriniz");
        } else {

            KdvTipKarti currentKdvTipKarti = kdvService.getKdvTipKartiByKodu(kdvKodu);

            KdvTipKarti kdvTip = kdvService.nextKdv(currentKdvTipKarti.getId());
            if (kdvTip != null) {
                updateFormFields(kdvTip);
            } else {
                JOptionPane.showMessageDialog(null, "Son Eklenen Kdv Kartı");
            }

        }

    }

    public void previousKdv() {
        getKdvFromFields();
        if (kdvKodu != null && !kdvKodu.isEmpty()) {
            KdvTipKarti currentKdv = kdvService.getKdvTipKartiByKodu(kdvKodu);
            KdvTipKarti kdvTip = kdvService.previousKdv(currentKdv.getId());
            if (kdvTip != null) {
                updateFormFields(kdvTip);
            } else {
                JOptionPane.showMessageDialog(null, "İlk Eklenen Kdv Kartı");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Kdv Kodu Giriniz");
        }

    }

    public void lastKdv() {
        try {
            KdvTipKarti kdvTip = kdvService.lastKdv();
            if (kdvTip != null) {
                updateFormFields(kdvTip);
            } else {
                JOptionPane.showMessageDialog(null, "Kdv Kartı Bulunamadı");
            }
        } catch (Exception e) {
        }

    }

    public void updateKdv() {
        getKdvFromFields();
        if (kdvKodu != null) {
            try {

                KdvTipKarti kdvTip = new KdvTipKarti(kdvKodu, kdvAdi, kdvOrani);
                String guncellemeCevabi = kdvService.updateKdvTipKarti(kdvTip);
                JOptionPane.showMessageDialog(null, guncellemeCevabi);

            } catch (RuntimeException es) {
                JOptionPane.showMessageDialog(null, es.getMessage());
            }

        } else {

        }
    }
}
