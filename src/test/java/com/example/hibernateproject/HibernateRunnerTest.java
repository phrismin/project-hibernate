package com.example.hibernateproject;

import com.example.hibernateproject.entity.Company;
import com.example.hibernateproject.entity.Profile;
import com.example.hibernateproject.entity.User;
import com.example.hibernateproject.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.util.Set;

class HibernateRunnerTest {

    @Test
    void checkOneToOne() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = session.get(User.class, 1L);
            System.out.println(user);

//            User user = User.builder()
//                    .username("Test@gmail.com")
//                    .build();
//
//            Profile profile = Profile.builder()
//                    .language("by")
//                    .street("Novaya")
//                    .build();
//
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