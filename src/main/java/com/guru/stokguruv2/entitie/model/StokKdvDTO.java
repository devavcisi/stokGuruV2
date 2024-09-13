/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.entitie.model;



import java.util.Date;

public class StokKdvDTO {
    private String stokKodu;
    private String stokAdi;
    private int stokTipi;
    private String birimi;
    private String barkodu;
    private String kdvTipKodu;
    private String kdvTipAdi;
    private double kdvTipOrani;
    private String aciklama;
    private Date olusturmaZamani;

    public StokKdvDTO(String stokKodu, String stokAdi, int stokTipi, String birimi, String barkodu, 
                      String kdvTipKodu, String kdvTipAdi, double kdvTipOrani, String aciklama, Date olusturmaZamani) {
        this.stokKodu = stokKodu;
        this.stokAdi = stokAdi;
        this.stokTipi = stokTipi;
        this.birimi = birimi;
        this.barkodu = barkodu;
        this.kdvTipKodu = kdvTipKodu;
        this.kdvTipAdi = kdvTipAdi;
        this.kdvTipOrani = kdvTipOrani;
        this.aciklama = aciklama;
        this.olusturmaZamani = olusturmaZamani;
    }

    
public StokKdvDTO(){
    
}
    public String getStokKodu() {
        return stokKodu;
    }

    public void setStokKodu(String stokKodu) {
        this.stokKodu = stokKodu;
    }

    public String getStokAdi() {
        return stokAdi;
    }

    public void setStokAdi(String stokAdi) {
        this.stokAdi = stokAdi;
    }

    public int getStokTipi() {
        return stokTipi;
    }

    public void setStokTipi(int stokTipi) {
        this.stokTipi = stokTipi;
    }

    public String getBirimi() {
        return birimi;
    }

    public void setBirimi(String birimi) {
        this.birimi = birimi;
    }

    public String getBarkodu() {
        return barkodu;
    }

    public void setBarkodu(String barkodu) {
        this.barkodu = barkodu;
    }

    public String getKdvTipKodu() {
        return kdvTipKodu;
    }

    public void setKdvTipKodu(String kdvTipKodu) {
        this.kdvTipKodu = kdvTipKodu;
    }

    public String getKdvTipAdi() {
        return kdvTipAdi;
    }

    public void setKdvTipAdi(String kdvTipAdi) {
        this.kdvTipAdi = kdvTipAdi;
    }

    public double getKdvTipOrani() {
        return kdvTipOrani;
    }

    public void setKdvTipOrani(double kdvTipOrani) {
        this.kdvTipOrani = kdvTipOrani;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Date getOlusturmaZamani() {
        return olusturmaZamani;
    }

    public void setOlusturmaZamani(Date olusturmaZamani) {
        this.olusturmaZamani = olusturmaZamani;
    }

    @Override
    public String toString() {
        return "StokKdvDTO{" +
               
                ", stokKodu='" + stokKodu + '\'' +
                ", stokAdi='" + stokAdi + '\'' +
                ", stokTipi=" + stokTipi +
                ", birimi='" + birimi + '\'' +
                ", barkodu='" + barkodu + '\'' +
                ", kdvTipKodu='" + kdvTipKodu + '\'' +
                ", kdvTipAdi='" + kdvTipAdi + '\'' +
                ", kdvTipOrani=" + kdvTipOrani +
                ", aciklama='" + aciklama + '\'' +
                ", olusturmaZamani=" + olusturmaZamani +
                '}';
    }
}
