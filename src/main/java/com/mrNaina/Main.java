package com.mrNaina;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mrNaina.models.Olona;
import com.mrNaina.models.Groupe;
import com.mrNaina.models.Projet;
import com.mrNaina.repository.OlonaRepository;
import com.mrNaina.repository.GroupeRepository;
import com.mrNaina.repository.ProjetRepository;
import com.mrNaina.config.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello World!");
        // GenericDao<Olona> olonadao = new GenericDaoImpl<>();

        // Save
        // Olona olona = new Olona();
        // olona.setNom("test");
        // olonadao.save(olona);

        // FindById
        // Olona foundOlona = olonadao.findById(Olona.class, 14);
        // if (foundOlona != null) {
        // System.out.println("Found Olona: " + foundOlona.getNom());
        // } else {
        // System.out.println("Olona not found.");
        // }

        // Update
        // foundOlona.setNom("updated name");
        // olonadao.update(foundOlona);

        // Delete
        // olonadao.delete(foundOlona);

        // FindAll
        // List<Olona> olonas = olonadao.findAll(Olona.class);
        // for (Olona o : olonas) {
        //     System.out.println("Olona: " + o.getNom());
        // }

        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        
        // On demande le Repository (l'interface) et non plus l'implémentation
        OlonaRepository repo = context.getBean(OlonaRepository.class);
        GroupeRepository groupeRepo = context.getBean(GroupeRepository.class);
        ProjetRepository projetRepo = context.getBean(ProjetRepository.class);
        
        // // Exemple de test: créer un Groupe avec des Olona et persister
        // Groupe g = new Groupe();
        // g.setNom("Groupe A");

        // Olona o1 = new Olona();
        // o1.setNom("Alice");

        // Olona o2 = new Olona();
        // o2.setNom("Bob");

        // // Utiliser les helpers pour maintenir les deux côtés de la relation
        // g.addMembre(o1);
        // g.addMembre(o2);

        // // Persister le groupe: cascade persistera aussi les Olona
        // groupeRepo.save(g);

        // // Afficher les Olona en base (via OlonaRepository)
        // repo.findAll().forEach(o -> System.out.println("Olona: " + o.getNom() + " (Groupe: " + (o.getGroupe()!=null?o.getGroupe().getNom():"null") + ")"));

        // --- Test ManyToMany : Olona <-> Projet ---
        Olona m1 = new Olona();
        m1.setNom("Charlie");
        Olona m2 = new Olona();
        m2.setNom("Diana");

        Projet p1 = new Projet();
        p1.setNom("Projet X");
        Projet p2 = new Projet();
        p2.setNom("Projet Y");

        // Sauvegarder les Projets d'abord
        projetRepo.save(p1);
        projetRepo.save(p2);

        // Lier via les helpers (maintient les deux côtés)
        m1.addProjet(p1);
        m1.addProjet(p2);
        m2.addProjet(p1);

        // Persister les Olona
        repo.save(m1);
        repo.save(m2);

        // Afficher les projets et leurs participants
        projetRepo.findAll().forEach(p -> {
            System.out.println("Projet: " + p.getNom());
            p.getParticipants().forEach(part -> System.out.println(" - " + part.getNom()));
        });
    }
}
