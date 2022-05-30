package com.example.hibernateproject;

import com.example.hibernateproject.entity.Company;
import com.example.hibernateproject.entity.User;
import com.example.hibernateproject.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

class HibernateRunnerTest {

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