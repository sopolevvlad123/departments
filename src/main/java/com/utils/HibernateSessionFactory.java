package com.utils;

import com.bean.Department;
import com.bean.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactory {


    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        SessionFactory sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }

}
   /* private static SessionFactory buildSessionFactory(){
        SessionFactory sessionFactory = null;

        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure( "/hibernate.cfg.xml" )
                    .build();

            Metadata metadata = new MetadataSources( standardRegistry )
                    .addAnnotatedClass(Department.class )
                    // You can add more entity classes here like above
                    .addResource( "/mapping.hbm.xml" )
                    .getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
                    .build();

           sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        return sessionFactory;
    }
*/


