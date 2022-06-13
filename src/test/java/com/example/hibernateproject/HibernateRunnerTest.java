package com.example.hibernateproject;

import com.example.hibernateproject.entity.*;
import com.example.hibernateproject.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

class HibernateRunnerTest {

    @Test
    void checkManyToMany() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = session.get(User.class, 7L);
            Chat chat = session.get(Chat.class, 1L);

            UserChat userChat = UserChat.builder()
                    .createdAt(Instant.now())
                    .createdBy(user.getUsername())
                    .build();
            userChat.setUser(user);
            userChat.setChat(chat);

            session.save(userChat);

            session.getTransaction().commit();
        }
    }


    @Test
    void checkOneToOne() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = User.builder()
                    .username("Test5@gmail.com")
                    .build();

            Profile profile = Profile.builder()
                    .language("by")
                    .street("Novaya")
                    .build();

            profile.setUser(user);

            session.save(user);
//            session.save(user);
//            profile.setUser(user);
//            session.save(profile);


            session.getTransaction().commit();
        }
    }

    @Test
    void checkOrphanRemove() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();

            Company company = session.getReference(Company.class, 1L);
            company.getUsers().removeIf(user -> user.getId().equals(4L));

            session.getTransaction().commit();
        }
    }

    @Test
    void checkLazyInitialization() {
        Company company = null;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();

            company = session.getReference(Company.class, 1L);

            session.getTransaction().commit();
        }

        Set<User> users = company.getUsers();
        System.out.println(users.size());
    }

    @Test
    void deleteCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 2L);
        session.delete(company);

        session.getTransaction().commit();
    }

    @Test
    void addUserToNewCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Facebook")
                .build();

        User user = User.builder()
                .username("sveta@mail.by")
                .build();
        company.addUser(user);

        session.save(company);

        session.getTransaction().commit();
    }


}