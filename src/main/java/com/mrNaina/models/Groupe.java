package com.mrNaina.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    // OneToMany side: un Groupe a plusieurs Olona (membres)
    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Olona> membres = new ArrayList<>();

    public Groupe() {}

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

    // Gestion des membres: utilitaire pour maintenir les deux côtés de la relation
    public void addMembre(Olona o) {
        membres.add(o);
        o.setGroupe(this);
    }
    public void removeMembre(Olona o) {
        membres.remove(o);
        o.setGroupe(null);
    }

    public List<Olona> getMembres() {
        return membres;
    }
    public void setMembres(List<Olona> membres) {
        this.membres = membres;
    }
}
