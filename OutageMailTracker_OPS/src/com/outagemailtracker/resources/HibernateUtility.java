package com.outagemailtracker.resources;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility 
{
	//Please don't consider the syso statements in this class as PMD violation
	//It is given to make them understand when the sessionFactory is closed
	private static final String CONFIGURATION_LOCATION = "com/outagemailtracker/resources/hibernate.cfg.xml";
	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry;

	public synchronized static SessionFactory createSessionFactory() {
		if (sessionFactory == null) 
		{
			try {
				// Step1 : Loading the configuration details from
				// hibernate.cfg.xml
				
				Configuration configuration = new Configuration()
						.configure(CONFIGURATION_LOCATION);

				// Step2 : Creating ServiceRegistry using the
				// StandardServiceRegistryBuilder and Configuration defined in
				// Step 1
				
				serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
			
			
				// Step3 : Creating the SessionFactory using the Configuration
				// and serviceRegistry.
				
				sessionFactory = configuration
						.buildSessionFactory(serviceRegistry);
				System.out.println("sessionFactory created...");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				OmtLogger.logError("HibernateUtility",
						"createSessionFactory", e.getMessage());
			}
		}
		
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		
		
		if (sessionFactory!=null && sessionFactory.isClosed()==false) 
		{
			sessionFactory.close();
			
			System.out.println("sessionFactory closed...");
		}
	}
}
