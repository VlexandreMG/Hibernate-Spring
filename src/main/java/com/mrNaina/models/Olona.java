package com.mrNaina.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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

}