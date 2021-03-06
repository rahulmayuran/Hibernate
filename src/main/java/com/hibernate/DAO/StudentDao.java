package com.hibernate.DAO;

import java.util.List;

import com.hibernate.Entity.Student;
import com.hibernate.Exception.StudentException;
import com.hibernate.Util.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
   Why to create transactions in main method if you have DAO layer.
 */
public class StudentDao {

    //It should be void type for inserting records to DB
    public void persistME(Student s) {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(s);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateME(Student s) {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(s);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void getStudentWithID(int id){


        try {
            Session session = HibernateUtility.getSessionFactory().openSession();
            // start a transaction
            Transaction transaction = session.beginTransaction();
            // get Student entity using load() method
            session.load(Student.class, id);
            // commit transaction
            transaction.commit();
            throw new StudentException("Object not found");

        } catch (StudentException e) {
           System.out.println(e);
        }

    }

    public void deleteME(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a persistent object using load method
            Student student = session.load(Student.class, id);
            if (student != null) {
                session.delete(student);
                System.out.println("student :"+id+" is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Only this method has return type as List
    public List<Student> getStudents() {
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
}