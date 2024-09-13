    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package com.guru.stokguruv2.controller;

    import com.guru.stokguruv2.entitie.model.Stok;
    import com.guru.stokguruv2.entitie.model.StokKdvDTO;
    import com.guru.stokguruv2.gui.IStokListesi;
    import com.guru.stokguruv2.service.serviceImp.StokServiceDaoImp;
    import java.util.ArrayList;
    import java.util.List;
    import javax.swing.JTable;
    import javax.swing.table.DefaultTableModel;

    /**
     *
     * @author User
     */
    public class StokListesiController {
        private IStokListesi stokListesi;
          private JTable stokTable;
         StokServiceDaoImp stokService = new StokServiceDaoImp();
            private List<StokKdvDTO> stokList = new ArrayList<>();

         public StokListesiController(IStokListesi stokListesi){
            this.stokListesi=stokListesi;
                stokTable = stokListesi.getTableStokListesi();
                 initComponents();
        }

        private void initComponents() {
            tabloyuDoldur();
        }
       private void tabloyuDoldur() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Kolon isimlerini ayarlayın
        Object[] columnsName = new Object[10];
        columnsName[0] = "Stok Kodu";
        columnsName[1] = "Stok Adı";
        columnsName[2] = "Stok Tipi";
        columnsName[3] = "Stok Birimi";
        columnsName[4] = "Stok Barkodu";
        columnsName[5] = "Kdv Tip Kodu";
        columnsName[6] = "Kdv Tip Adı";
        columnsName[7] = "Kdv Tip Oranı";
        columnsName[8] = "Aciklama";
        columnsName[9] = "Tarih";

        model.setColumnIdentifiers(columnsName);
        stokTable.setModel(model);

        stokList.clear();

        List<StokKdvDTO> stoklar = null;
        try {
            stoklar = stokService.getListStokKartiByKdvDTO();
        } catch (RuntimeException e) {
            System.out.println("Stoklar Çekilemedi: " + e.getMessage());
        }

        if (stoklar != null && !stoklar.isEmpty()) {
            for (StokKdvDTO stok : stoklar) {
                Object[] rowData = new Object[10];
                rowData[0] = stok.getStokKodu();
                rowData[1] = stok.getStokAdi();
                rowData[2] = stok.getStokTipi();
                rowData[3] = stok.getBirimi();
                rowData[4] = stok.getBarkodu();
                rowData[5] = stok.getKdvTipKodu();
                rowData[6] = stok.getKdvTipAdi();
                rowData[7] = stok.getKdvTipOrani();
                rowData[8] = stok.getAciklama();
                rowData[9] = stok.getOlusturmaZamani();

                model.addRow(rowData);
                stokList.add(stok);
            }
        }
    }


    }
