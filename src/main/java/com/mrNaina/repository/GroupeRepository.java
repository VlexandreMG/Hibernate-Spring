package com.mrNaina.repository;

import com.mrNaina.models.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Integer> {
    // Repository minimal pour persister des Groupes
}
