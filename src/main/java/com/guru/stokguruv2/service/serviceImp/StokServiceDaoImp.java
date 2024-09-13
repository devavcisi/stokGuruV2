/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.service.serviceImp;

import com.guru.stokguruv2.entitie.dao.StokKartiDao;
import com.guru.stokguruv2.entitie.daoImp.StokKartiDaoImp;
import com.guru.stokguruv2.entitie.model.Stok;
import com.guru.stokguruv2.entitie.model.StokKdvDTO;
import com.guru.stokguruv2.service.serviceDao.StokServiceDao;
import java.util.List;

/**
 *
 * @author User
 */
public class StokServiceDaoImp implements StokServiceDao {

    private StokKartiDaoImp stokService = new StokKartiDaoImp();

    @Override
    public String addStokKarti(Stok stokKarti) {
        return stokService.addStokKarti(stokKarti);
    }

    @Override
    public String removeStokKarti(String stokKodu) {
        return stokService.removeStokKarti(stokKodu);
    }

    @Override
    public String updateStokKarti(Stok stokKarti) {
        return stokService.updateStokKarti(stokKarti);
    }

    @Override
    public List<Stok> getListAllStokKarti() {
        return stokService.getListAllStokKarti();
    }

    @Override
    public Stok getStokbyStokKoduStokKarti(String stokKodu) {
        return stokService.getStokbyStokKoduStokKarti(stokKodu);
    }

    @Override
    public List<Stok> searchStokByKodu(String stokKodu) {
        return stokService.searchStokByKodu(stokKodu);
    }

    @Override
    public Stok nextStok(int id) {
       return stokService.nextStok(id);
    }

    @Override
    public Stok previousStok(int id) {
        return stokService.previousStok(id);
    }

    @Override
    public Stok firsStok() {
        return stokService.firsStok();
    }

    @Override
    public Stok lastStok() {
        return stokService.lastStok();
    }

    @Override
    public List<StokKdvDTO> getListStokKartiByKdvDTO() {
       return stokService.getListStokKartiByKdvDTO();
    }

}
