/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package com.guru.stokguruv2.controller;

import com.guru.stokguruv2.entitie.model.Stok;
import com.guru.stokguruv2.entitie.model.StokKdvDTO;
import com.guru.stokguruv2.gui.IMainFrame;
import com.guru.stokguruv2.gui.IStokListesi;
import com.guru.stokguruv2.service.serviceImp.StokServiceDaoImp;
import com.guru.stokguruv2.view.StokListesi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class StokListesiController {

    private IMainFrame mainFrame;
    private IStokListesi stokListesi;
    private JTable stokTable;
    private StokServiceDaoImp stokService = new StokServiceDaoImp();
    private List<StokKdvDTO> stokList = new ArrayList<>();
    private static MainFrameController instance;

    public StokListesiController(IStokListesi stokListesi, IMainFrame mainFrame) {
        this.stokListesi = stokListesi;
        this.mainFrame = mainFrame;
        stokTable = stokListesi.getTableStokListesi();
        initComponents();
        initPopupMenu();
    }

    private void initComponents() {
        tabloyuDoldur();
    }

    private void initPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem stokKartiItem = new JMenuItem("Stok Kartı");
        popupMenu.add(stokKartiItem);

        stokTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() && stokTable.getSelectedRow() != -1) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        stokKartiItem.addActionListener(e -> {
            int selectedRow = stokTable.getSelectedRow();
            if (selectedRow != -1) {
                String stokKodu = (String) stokTable.getValueAt(selectedRow, 0);
                Stok stok = stokService.getStokbyStokKoduStokKarti(stokKodu);
                openStokKarti(stok);
            }
        });
    }

    private void openStokKarti(Stok stok) {
        MainFrameController controller = MainFrameController.getInstance(mainFrame);

        if (controller.getStokKartiFrame() != null && !controller.getStokKartiFrame().isClosed()) {
            controller.getStokKartiFrame().setVisible(true);
            controller.getStokKartiFrame().toFront();
        } else {
            controller.openStokKarti();
        }

        StokKartiController stokKartiController = controller.getStokKartiController();
        if (stokKartiController != null) {
            stokKartiController.updateFormFields(stok);
        } else {
            System.out.println("StokKartiController örneği oluşturulmadı.");
        }
    }

    public void tabloyuDoldur() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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
