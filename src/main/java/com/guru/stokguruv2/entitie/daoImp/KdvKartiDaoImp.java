/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.entitie.daoImp;

import com.guru.stokguruv2.HibernateUtil;
import com.guru.stokguruv2.entitie.dao.KdvKartiDao;
import com.guru.stokguruv2.entitie.model.KdvTipKarti;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author User
 */
public class KdvKartiDaoImp implements KdvKartiDao {

    @Override
    public List<KdvTipKarti> getAllKdvTypes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM KdvTipKarti";
        List<KdvTipKarti> kdvler = session.createQuery(hql, KdvTipKarti.class).list();
        session.getTransaction().commit();
        session.close();
        return kdvler;

    }

    @Override
    public String addKdvTipKarti(KdvTipKarti kdvTipKarti) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String kdvKodu = kdvTipKarti.getKodu();
            String hql = "FROM KdvTipKarti WHERE kodu = :kdvKodu";
            Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
            query.setParameter("kdvKodu", kdvKodu);

            KdvTipKarti existingKdv = query.uniqueResult();

            if (existingKdv == null) {
                session.save(kdvTipKarti);
                transaction.commit();
                return "KDV Tip Kartı başarıyla eklendi.";
            } else {
                transaction.rollback();
                return "Hata: Aynı KDV koduna sahip bir kart zaten mevcut.";
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return "Ekleme işlemi sırasında bir hata oluştu: " + e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public String removeKdvTipKarti(String kdvKodu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM KdvTipKarti WHERE kodu = :kdvKodu");
            query.setParameter("kdvKodu", kdvKodu);
            int result = query.executeUpdate();

            tx.commit();
            return "KDV Tip Kartı başarıyla silindi.";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return "Kart silinemedi!" + e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public String updateKdvTipKarti(KdvTipKarti kdvTipKarti) {
        try {
            if (kdvTipKarti == null) {
                return "Hata! Güncellenecek KDV Tip Kartı bulunamadı.";
            }

            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();

            String hql = "FROM KdvTipKarti WHERE kodu = :kdvKodu";
            KdvTipKarti mevcutKdvTipKarti = (KdvTipKarti) s.createQuery(hql)
                    .setParameter("kdvKodu", kdvTipKarti.getKodu())
                    .uniqueResult();

            if (mevcutKdvTipKarti == null) {
                return "Hata! Veritabanında bu KDV Tip Kartı bulunamadı.";
            }

            boolean degisiklikVar = false;

         
            if (!mevcutKdvTipKarti.getAdi().equals(kdvTipKarti.getAdi())) {
                mevcutKdvTipKarti.setAdi(kdvTipKarti.getAdi());
                degisiklikVar = true;
            }

          
            if (Math.abs(mevcutKdvTipKarti.getOrani() - kdvTipKarti.getOrani()) > 0.0001) {
                mevcutKdvTipKarti.setOrani(kdvTipKarti.getOrani());
                degisiklikVar = true;
            }

           
            if (!mevcutKdvTipKarti.getKodu().equals(kdvTipKarti.getKodu())) {
                mevcutKdvTipKarti.setKodu(kdvTipKarti.getKodu());
                degisiklikVar = true;
            }

            if (!degisiklikVar) {
                return "Değişiklik yapılmadı, güncelleme işlemi gerçekleşmedi.";
            }

            s.update(mevcutKdvTipKarti);
            s.getTransaction().commit();
            return "KDV Tip Kartı başarıyla güncellendi.";
        } catch (Exception e) {
            return "Hata! " + e.getMessage();
        }
    }

    @Override
    public KdvTipKarti getKdvTipKartiByKodu(String kdvKodu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        KdvTipKarti kdvTipKarti = null;
        try {
            String hql = "FROM KdvTipKarti WHERE kodu = :kdvKodu";
            Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
            query.setParameter("kdvKodu", kdvKodu);
            kdvTipKarti = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return kdvTipKarti;
    }
    
     @Override
    public KdvTipKarti getKdvTipKartiById (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        KdvTipKarti kdvTipKarti = null;
        try {
            String hql = "FROM KdvTipKarti WHERE id = :id";
            Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
            query.setParameter("id", id);
            kdvTipKarti = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return kdvTipKarti;
    }

    @Override
    public List<KdvTipKarti> searchKdvTipKartiByKodu(String kdvKodu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM KdvTipKarti WHERE kodu LIKE :kdvKodu";
        Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
        query.setParameter("kdvKodu", "%" + kdvKodu + "%");
        List<KdvTipKarti> result = query.list();
        session.close();
        return result;
    }

    @Override
    public KdvTipKarti nextKdv(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM KdvTipKarti WHERE id > :id ORDER BY id ASC";
            Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
            query.setParameter("id", id);
            query.setMaxResults(1); // Sadece bir sonraki kaydı getir
            KdvTipKarti kdvTip = query.uniqueResult();
            session.getTransaction().commit();
            return kdvTip;
        } catch (Exception e) {
            e.printStackTrace();
            throw e; 
        }
    }

    @Override
    public KdvTipKarti previousKdv(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM KdvTipKarti WHERE id < :id ORDER BY id DESC";
            Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
            query.setParameter("id", id);
            query.setMaxResults(1); 
            KdvTipKarti kdvTip = query.uniqueResult();
            session.getTransaction().commit();
            return kdvTip;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public KdvTipKarti firstKdv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM KdvTipKarti ORDER BY id ASC";
            Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
            query.setMaxResults(1); 
            KdvTipKarti kdvTip = query.uniqueResult();
            session.getTransaction().commit();
            return kdvTip;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public KdvTipKarti lastKdv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM KdvTipKarti ORDER BY id DESC";
            Query<KdvTipKarti> query = session.createQuery(hql, KdvTipKarti.class);
            query.setMaxResults(1); 
            KdvTipKarti kdvTip = query.uniqueResult();
            session.getTransaction().commit();
            return kdvTip;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
