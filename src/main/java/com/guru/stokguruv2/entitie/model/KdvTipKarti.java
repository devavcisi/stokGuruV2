

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.guru.stokguruv2.entitie.model;

import javax.persistence.*;

@Entity
@Table(name = "kdv_tip_karti")
public class KdvTipKarti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "kodu", nullable = false)
    private String kodu;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "orani", nullable = false)
    private Double orani;

    public KdvTipKarti() {
    }

    public KdvTipKarti(String kodu, String adi, Double orani) {
        this.kodu = kodu;
        this.adi = adi;
        this.orani = orani;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodu() {
        return kodu;
    }

    public void setKodu(String kodu) {
        this.kodu = kodu;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public Double getOrani() {
        return orani;
    }

    public void setOrani(Double orani) {
        this.orani = orani;
    }
}
