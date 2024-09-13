package com.guru.stokguruv2.view;

import com.guru.stokguruv2.controller.StokKartiController;

public class StokKartiNavigationPanel extends AbstractNavigationPanel {
    
  
    private StokKartiController controller;

    public StokKartiNavigationPanel(StokKartiController controller) {
        this.controller = controller;

        // Kaydet butonuna listener ekle
        btnSave.addActionListener(e -> controller.saveStok());

        // Sil butonuna listener ekle
        btnDelete.addActionListener(e -> controller.deleteStok());
        
        // Kaydet butonuna listener ekle
        btnFirst.addActionListener(e -> controller.firstStok());

        // Sil butonuna listener ekle
        btnNext.addActionListener(e -> controller.nextStok());
        
        // Kaydet butonuna listener ekle
        btnPrevious.addActionListener(e -> controller.previousStok());

        // Sil butonuna listener ekle
        btnLast.addActionListener(e -> controller.lastStok());
        
        btnUpdate.addActionListener((e) -> controller.updateStok());
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
