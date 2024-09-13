/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.entitie.dao;

import com.guru.stokguruv2.entitie.model.KdvTipKarti;
import java.util.List;

/**
 *
 * @author User
 */
public interface KdvKartiDao {
    
    List<KdvTipKarti> getAllKdvTypes();
    String addKdvTipKarti(KdvTipKarti kdvTipKarti);
    String removeKdvTipKarti(String kdvKodu);
    String updateKdvTipKarti(KdvTipKarti kdvTipKarti);
     KdvTipKarti getKdvTipKartiByKodu(String kdvKodu);
     List<KdvTipKarti> searchKdvTipKartiByKodu(String kdvKodu);
     
      KdvTipKarti nextKdv(int id);

    KdvTipKarti previousKdv(int id);
    
    KdvTipKarti firstKdv();
    
    KdvTipKarti lastKdv();
}
