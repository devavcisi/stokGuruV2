/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.service.serviceDao;

import com.guru.stokguruv2.entitie.model.Stok;
import com.guru.stokguruv2.entitie.model.StokKdvDTO;
import java.util.List;

/**
 *
 * @author User
 */
public interface StokServiceDao {

    String addStokKarti(Stok stokKarti);

    String removeStokKarti(String stokKodu);

    String updateStokKarti(Stok stokKarti);

    List<Stok> getListAllStokKarti();

    Stok getStokbyStokKoduStokKarti(String stokKodu);

    List<Stok> searchStokByKodu(String stokKodu);
    
    Stok nextStok(int id);

    Stok previousStok(int id);
    
    Stok firsStok();
    
    Stok lastStok();
    
    
    List<StokKdvDTO> getListStokKartiByKdvDTO();

}
