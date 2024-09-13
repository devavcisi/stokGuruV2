/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2;



import com.guru.stokguruv2.entitie.model.KdvTipKarti;
import com.guru.stokguruv2.entitie.model.StokKdvDTO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Hibernate config dosyasını yükleyin
            Configuration config = new Configuration().configure("hibernate.cfg.xml");
            config.addAnnotatedClass(KdvTipKarti.class);
            config.addAnnotatedClass(StokKdvDTO.class);
            // SessionFactory oluşturuluyor
            sessionFactory = config.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("SessionFactory oluşturulurken hata oluştu: " + e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
