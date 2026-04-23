package com.mrNaina.repository;

import com.mrNaina.models.Olona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OlonaRepository extends JpaRepository<Olona, Integer> {
    // C'EST TOUT. 
    // Tu as déjà : save(), findById(), findAll(), delete(), etc.
}
