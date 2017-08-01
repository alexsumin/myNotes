package ru.alexsumin.notes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by alex on 31.07.17.
 */
@Configuration
@ComponentScan(basePackages = "ru.alexsumin.notes")
public class TestConfiguration {


    @Bean
    public EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public EntityManager getEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }
}
