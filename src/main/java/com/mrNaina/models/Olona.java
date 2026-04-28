package com.mrNaina.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Olona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name= "nom")
    String nom;

    // ManyToOne side: chaque Olona appartient à un Groupe
    @ManyToOne
    @JoinColumn(name = "groupe_id")
    Groupe groupe;

    // ManyToMany: Olona peut participer à plusieurs Projets
    @ManyToMany(cascade = {jakarta.persistence.CascadeType.MERGE})
    @JoinTable(name = "olona_projet",
        joinColumns = @JoinColumn(name = "olona_id"),
        inverseJoinColumns = @JoinColumn(name = "projet_id")
    )
    private Set<Projet> projets = new HashSet<>();

    public Olona() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter/Setter pour la relation ManyToOne
    public Groupe getGroupe() {
        return groupe;
    }
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    // ManyToMany helpers
    public Set<Projet> getProjets() {
        return projets;
    }
    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }
    public void addProjet(Projet p) {
        projets.add(p);
        p.getParticipants().add(this);
    }
    public void removeProjet(Projet p) {
        projets.remove(p);
        p.getParticipants().remove(this);
    }

}