package com.hibernate.Controller;

import com.hibernate.Entity.Address;
import com.hibernate.Entity.City;
import com.hibernate.Util.HibernateUtility;
import org.hibernate.Session;

public class CityController {

    public static void main(String[] args) {

        City city = new City();
        city.setName("Mumbai");
        city.setPopulation("1.04 crores");
          Address addressObj = new Address();
          addressObj.setAddressLine1("122");
          addressObj.setAddressLine2("third cross street");
        city.setAddress(addressObj);


        /**
         * If you are not willing to use a Utility class that has static object
         * of SessionFactory method and setting the cfg file as a Map,
         * you can do this way.
         */

       /* Configuration configObj = new Configuration().configure().addAnnotatedClass(Entity.class);
        SessionFactory sessionfactoryObj = configObj.buildSessionFactory();
        Session sessionObj = sessionfactoryObj.openSession();

        Transaction transactionObj = sessionObj.beginTransaction();
        sessionObj.save(CitySetter(Entityobject));*/

        Session session = HibernateUtility.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();

        session.close();

    }



}
