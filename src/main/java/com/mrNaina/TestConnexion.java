package com.mrNaina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConnexion {
    public static void main(String[] args) {
        // 1. On tente de créer la Factory en lisant le persistence.xml
        // Remplace "myAppPU" par le nom exact défini dans ton XML
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Tentative de connexion à la base de données...");
            emf = Persistence.createEntityManagerFactory("myAppPU");
            em = emf.createEntityManager();

            System.out.println("CONNEXION RÉUSSIE !");
            
            // Si hibernate.hbm2ddl.auto est à "update", 
            // la table 'Olona' devrait être créée à ce moment précis.
            
        } catch (Exception e) {
            System.err.println("ÉCHEC DE LA CONNEXION !");
            e.printStackTrace();
        } finally {
            // Toujours fermer les ressources
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}