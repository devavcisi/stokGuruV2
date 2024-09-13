/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.gui;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public interface IStokKarti {
    
    JPanel getNavPanel();
    
    String getTarih();

    void setTarih(Timestamp time);

    String getStokKodu();

    void setStokKodu(String stokKodu);

    String getStokBarkodu();

    void setStokBarkodu(String stokBarkodu);

    String getStokAdi();

    void setStokAdi(String stokAdi);
    
    int getStokTipi();
   
    void setStokTipi(String stokTipi);
     
    String getStokBirimi();

    void setStokBirimi(String stokBirimi);
    
   
     String getAciklama();
     
     void setAciklama(String aciklama);
      void buttonAra(ActionListener actionListener);
      
      JComboBox getKdvComboBox();
}
