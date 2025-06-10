package com.example.coaapp.dao;

import com.example.coaapp.config.HibernateUtil;
import com.example.coaapp.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query; // Import yang benar

import java.util.List;
import java.util.Optional;

public class AccountDaoHibernateImpl implements AccountDao {

    @Override
    public void save(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(account); // Gunakan persist untuk objek baru
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(Account.class, id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Account> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Account> query = session.createQuery("FROM Account", Account.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void update(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(account); // Gunakan merge untuk update
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}