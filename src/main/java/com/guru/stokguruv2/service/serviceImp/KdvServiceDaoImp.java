/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.service.serviceImp;

import com.guru.stokguruv2.entitie.daoImp.KdvKartiDaoImp;
import com.guru.stokguruv2.entitie.model.KdvTipKarti;
import com.guru.stokguruv2.service.serviceDao.KdvServiceDao;
import java.util.List;

/**
 *
 * @author User
 */
public class KdvServiceDaoImp implements KdvServiceDao{
    
    KdvKartiDaoImp kdvservice = new KdvKartiDaoImp();
    
    @Override
    public List<KdvTipKarti> getAllKdvTypes() {
       return kdvservice.getAllKdvTypes();
    }

    @Override
    public String addKdvTipKarti(KdvTipKarti kdvTipKarti) {
        return kdvservice.addKdvTipKarti(kdvTipKarti);
    }

    @Override
    public String removeKdvTipKarti(String kdvKodu) {
        return kdvservice.removeKdvTipKarti(kdvKodu);
    }

    @Override
    public String updateKdvTipKarti(KdvTipKarti kdvTipKarti) {
        return kdvservice.updateKdvTipKarti(kdvTipKarti);
    }

    @Override
    public KdvTipKarti getKdvTipKartiByKodu(String kdvKodu) {
        return kdvservice.getKdvTipKartiByKodu(kdvKodu);
    }

    @Override
    public List<KdvTipKarti> searchKdvTipKartiByKodu(String kdvKodu) {
        return kdvservice.searchKdvTipKartiByKodu(kdvKodu);
    }

    @Override
    public KdvTipKarti nextKdv(int id) {
       return kdvservice.nextKdv(id);
    }

    @Override
    public KdvTipKarti previousKdv(int id) {
       return kdvservice.previousKdv(id);
    }

    @Override
    public KdvTipKarti firstKdv() {
        return kdvservice.firstKdv();
    }

    @Override
    public KdvTipKarti lastKdv() {
        return kdvservice.lastKdv();
    }

    @Override
    public KdvTipKarti getKdvTipKartiById(int id) {
        return kdvservice.getKdvTipKartiById(id);
    }
    
    
    
}
