package com.guru.stokguruv2.view;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class AbstractNavigationPanel extends JPanel {

    protected JButton btnFirst, btnPrevious, btnNext, btnLast, btnSave, btnDelete,btnUpdate;

    public AbstractNavigationPanel() {
        // Butonları oluştur
        btnFirst = new JButton("İlk Kayıt");
        btnPrevious = new JButton("Geri");
        btnNext = new JButton("İleri");
        btnLast = new JButton("Son Kayıt");
        btnSave = new JButton("Kaydet");
        btnDelete = new JButton("Sil");
        btnUpdate = new JButton("Güncelle");

        // Butonları panele ekle
        add(btnFirst);
        add(btnPrevious);
        add(btnNext);
        add(btnLast);
        add(btnUpdate);
        add(btnSave);
        add(btnDelete);

        this.setPreferredSize(new Dimension(400, 50)); 
    
        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstRecord();
            }
        });
        
         btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousRecord();
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextRecord();
            }
        });

        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastRecord();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecord();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });
    }

    protected abstract void firstRecord();
    protected abstract void previousRecord();
    protected abstract void nextRecord();
    protected abstract void lastRecord();
    protected abstract void saveRecord();
    protected abstract void deleteRecord();
    protected abstract void updateRecord();
}
