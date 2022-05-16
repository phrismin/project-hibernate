package com.example.hibernateproject.util;

import com.example.hibernateproject.converter.BirthdayConverter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAttributeConverter(BirthdayConverter.class);
        configuration.configure();

        return configuration.buildSessionFactory();
    }

}
