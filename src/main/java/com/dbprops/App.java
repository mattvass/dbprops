package com.dbprops;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;;


public class App {
	
	private static SessionFactory buildSessionFactory() {
        try {
             return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
              
        Session session = null;
        Transaction tx=null;
         
        try {
            session = buildSessionFactory().openSession();
            tx = session.beginTransaction();
             
            // Creating Environments entity that will be save to the sqlite database
            Environments environment = new Environments(1, "qa2", "transite.login.url", "http://qa2.360.com/login");
            Environments environment1 = new Environments(2, "qa8", "transite.login.url", "http://qa8.360.com/login");
             
            // Saving to the database
            session.save(environment);
            session.save(environment1);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
             
            // Fetching saved data
            List<Environments> envList = session.createQuery("from Environments").list();
             
            for (Environments env : envList) {
                System.out.println("Id: " + env.getId() + " | Environment:" + env.getEnv()  + " | Name:"  + env.getName() + " | Value:" + env.getValue());
            }
             
        } catch (Exception ex) {
            ex.printStackTrace();
             
            // Rolling back the changes to make the data consistent in case of any failure 
            // in between multiple database write operations.
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
    }
}