/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.entitie.model;

/**
 *
 * @author User
 */
public class KdvItem {
    private int id;
    private String ad;

    public KdvItem(int id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return ad; // JComboBox'da görünen değer
    }
}