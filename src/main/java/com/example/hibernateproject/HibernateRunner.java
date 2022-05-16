package com.example.hibernateproject;

import com.example.hibernateproject.entity.*;
import com.example.hibernateproject.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        Company yahoo = Company.builder()
                .name("Yahoo")
                .build();

        User user = User.builder()
                .username("aaaaa@vvvvv.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Diare")
                        .lastname("Mukhara")
                        .birthDate(new Birthday(LocalDate.of(1990, 1, 10)))
                        .build())
                .company(yahoo)
                .age(new Birthday(LocalDate.of(1990, 1, 10)).getAge())
                .role(Role.ADMIN)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                Transaction transaction = session.beginTransaction();

                session.save(yahoo);
                session.saveOrUpdate(user);

                session.getTransaction().commit();
            }
        }
    }
}