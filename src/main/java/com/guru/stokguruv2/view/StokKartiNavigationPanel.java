package com.guru.stokguruv2.view;

import com.guru.stokguruv2.controller.StokKartiController;

public class StokKartiNavigationPanel extends AbstractNavigationPanel {
    
  
    private StokKartiController controller;

    public StokKartiNavigationPanel(StokKartiController controller) {
        this.controller = controller;

    }

    @Override
    protected void firstRecord() {
       controller.firstStok();
    }

    @Override
    protected void previousRecord() {
       controller.previousStok();
    }

    @Override
    protected void nextRecord() {
      controller.nextStok();
    }

    @Override
    protected void lastRecord() {
        controller.lastStok();
    }

    @Override
    protected void saveRecord() {
        controller.saveStok();
    }

    @Override
    protected void deleteRecord() {
        controller.deleteStok();
    }

    @Override
    protected void updateRecord() {
      controller.updateStok();
    }
}
