package com.guru.stokguruv2.view;

import com.guru.stokguruv2.controller.KdvKartiController;

public class KdvTipKartiNavigationPanel extends AbstractNavigationPanel {

    private KdvKartiController controller;

    public KdvTipKartiNavigationPanel(KdvKartiController kdvKartiController) {

        this.controller = kdvKartiController;

       
    }

    @Override
    protected void firstRecord() {
    controller.firstKdv();
    }

    @Override
    protected void previousRecord() {
         controller.previousKdv();
    }

    @Override
    protected void nextRecord() {
         controller.nextKdv();
    }

    @Override
    protected void lastRecord() {
        controller.lastKdv();
    }

    @Override
    protected void saveRecord() {
        controller.saveKdv();
    }

    @Override
    protected void deleteRecord() {
        controller.deleteKdv();
    }

    @Override
    protected void updateRecord() {
        controller.updateKdv();
    }
}
