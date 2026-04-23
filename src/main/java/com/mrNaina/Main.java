package com.mrNaina;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mrNaina.models.Olona;
import com.mrNaina.config.HibernateConfig;
import com.mrNaina.dao.GenericDao;
import com.mrNaina.dao.GenericDaoImpl;

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
        
        // On demande le Bean à Spring
        GenericDao<Olona> dao = context.getBean(GenericDao.class);
        
        Olona o = new Olona();
        o.setNom("Spring Olona");
        dao.save(o);
        
        System.out.println("Sauvegarde réussie avec Spring !");
    }
}
