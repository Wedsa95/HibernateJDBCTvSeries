package com.jonas.connection;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jonas.entity.Categories;
import com.jonas.entity.Episodes;
import com.jonas.entity.Series;
//https://stackoverflow.com/questions/20265406/hibernate-no-currentsessioncontext-configured
public class SessionFactorySingelton
{
	
	private static SessionFactorySingelton instance = new SessionFactorySingelton();
	private SessionFactory sessionFactory;
	
		private SessionFactorySingelton(){
			this.sessionFactory = buildSessionFactory();
	    }
		
	    private SessionFactory buildSessionFactory() {
	        return new Configuration().configure()
	        		.addAnnotatedClass(Categories.class)
	        		.addAnnotatedClass(Series.class)
	        		.addAnnotatedClass(Episodes.class)
	        		.buildSessionFactory();
	    }
	
	    public static SessionFactorySingelton getInstance() {
	        if(instance == null){
	            return new SessionFactorySingelton();
	        }
	        return instance;
	    }
	
	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    
}
 