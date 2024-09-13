package com.guru.stokguruv2.view;

import com.guru.stokguruv2.controller.KdvKartiController;

public class KdvTipKartiNavigationPanel extends AbstractNavigationPanel {

    private KdvKartiController controller;

    public KdvTipKartiNavigationPanel(KdvKartiController kdvKartiController) {

        this.controller = kdvKartiController;

        // Kaydet butonuna listener ekle
        btnSave.addActionListener(e -> controller.saveKdv());

        // Sil butonuna listener ekle
        btnDelete.addActionListener(e -> controller.deleteKdv());

        // Kaydet butonuna listener ekle
        btnFirst.addActionListener(e -> controller.firstKdv());

        // Sil butonuna listener ekle
        btnNext.addActionListener(e -> controller.nextKdv());

        // Kaydet butonuna listener ekle
        btnPrevious.addActionListener(e -> controller.previousKdv());

        // Sil butonuna listener ekle
        btnLast.addActionListener(e -> controller.lastKdv());

        btnUpdate.addActionListener((e) -> controller.updateKdv());
    }

    @Override
    protected void firstRecord() {
       
    }

    @Override
    protected void previousRecord() {

    }

    @Override
    protected void nextRecord() {

    }

    @Override
    protected void lastRecord() {

    }

    @Override
    protected void saveRecord() {

    }

    @Override
    protected void deleteRecord() {

    }

    @Override
    protected void updateRecord() {

    }
}
