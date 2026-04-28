package com.mrNaina.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    // ManyToMany inverse side: un Projet a plusieurs Olona participants
    @ManyToMany(mappedBy = "projets", fetch = jakarta.persistence.FetchType.EAGER)
    private Set<Olona> participants = new HashSet<>();

    public Projet() {}

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

    public Set<Olona> getParticipants() {
        return participants;
    }
    public void setParticipants(Set<Olona> participants) {
        this.participants = participants;
    }

    // Helper to maintain both sides
    public void addParticipant(Olona o) {
        participants.add(o);
        o.getProjets().add(this);
    }
    public void removeParticipant(Olona o) {
        participants.remove(o);
        o.getProjets().remove(this);
    }
}
