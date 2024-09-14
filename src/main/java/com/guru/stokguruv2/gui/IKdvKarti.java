/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.gui;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public interface IKdvKarti {
 
     JPanel getNavPanel();
 
      
      String getKdvAdi();

     void setKdvAdi(String kdvAdi);
     
     String getKdvKodu();

     void setKdvKodu(String kdvKodu);
      
     String getKdvOrani();

     void setKdvOrani(String kdvOrani);
     
     JComboBox getKdvComboBox();
    
}
