package com.mrNaina.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myAppPU");

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public void save(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity); // C'est ici que Hibernate génère l'INSERT
            em.getTransaction().commit();
            System.out.println("Saving entity: " + entity);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override 
    public T findById(Class<T> clazz, int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM "+ clazz.getSimpleName() + " e";
            // le e natao allias parce que hibernate a pense en collection d'objets et pas en table relationnelle
            TypedQuery<T> query = em.createQuery(jpql, clazz);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}