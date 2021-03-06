package com.hibernate.Util;

import com.hibernate.Entity.City;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtility {

	private static SessionFactory sessionFactory;
	private static final Logger LOGGER = Logger.getLogger(HibernateUtility.class.getName());

	static{

		LOGGER.info("Initializing the sessionFactory object in static block ->"+sessionFactory.getClass());
		if(sessionFactory==null)
		try {
			//It is an implementation class specially made to accept Properties Mapping and SessionFactory settings.
			Configuration configObj = new Configuration();

			// Properties is a Impl.Class that extends HashTable which is  Synchronized Map of <K,V>
			Properties settingcfg = new Properties();
			settingcfg.put(Environment.USER,"hibernator");
			settingcfg.put(Environment.PASS,"");
			settingcfg.put(Environment.URL,"jdbc:sqlserver://localhost:1433;databaseName=HibernateTrial");
			settingcfg.put(Environment.DRIVER,"com.microsoft.sqlserver.jdbc.SQLServerDriver");

			settingcfg.put(Environment.HBM2DDL_AUTO,"create-drop");
			settingcfg.put(Environment.SHOW_SQL,true);
			settingcfg.put(Environment.USE_SQL_COMMENTS, true);
				/*
					When you create a hibernate session using any of the sessionFactory.openSession(...)
					methods the session factory will 'bind' the session to the current context.
					The default context is 'thread' which means the sesion factory will bind the session to the thread
					from which openSession(...) is called.

					This is useful because you can later call sessionFactory.getCurrentSession()
					which will return the session that is bound to the currently running thread.
				*/
			settingcfg.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				/*
					c3p0 is a Java library that provides a convenient way for managing database connections.
					In short, it achieves this by creating a pool of connections.
					It also effectively handles the cleanup of Statements and ResultSets after use.
					This cleanup is necessary to ensure that resource usage is optimized and avoidable deadlocks do not occur.
				 */
				settingcfg.put(Environment.C3P0_MIN_SIZE, 5);         //Minimum size of pool
				settingcfg.put(Environment.C3P0_MAX_SIZE, 20);        //Maximum size of pool
				settingcfg.put(Environment.C3P0_ACQUIRE_INCREMENT, 1);//Number of connections acquired at a time when pool is exhausted
				settingcfg.put(Environment.C3P0_TIMEOUT, 1800);       //Connection idle time, seconds?
				settingcfg.put(Environment.C3P0_MAX_STATEMENTS, 150); //PreparedStatement cache size

				settingcfg.put(Environment.C3P0_CONFIG_PREFIX+".initialPoolSize", 5);


			configObj.setProperties(settingcfg);
			configObj.addAnnotatedClass(City.class);

			//An Interface , ...Builder is a Implementation class to apply the configurations
			//In 5.3.7 instead of ServiceRegistryBuilder we use StandardServiceRegistryBuilder()
			ServiceRegistry serviceRegistryObj =
					new StandardServiceRegistryBuilder()
							.applySettings(configObj.getProperties())
							.build();

			LOGGER.log(Level.INFO, "Service Registry object built with applied Configuration settings");

			sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	//Factory method
	public static SessionFactory getSessionFactory(){

		LOGGER.info("Inside Static method , calling session factory object");
		return sessionFactory;
	}
}

