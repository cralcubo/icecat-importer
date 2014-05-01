package com.christian.icecat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.connectable.Connectable;

/**
 * Class that will open a session with the 
 * database to persist data in it or to 
 * do any other action that needs direct
 * actions against the database.
 * 
 * @author christian
 *
 */
public class HibernateConnector implements Connectable {
	private Logger LOG = LoggerFactory.getLogger(HibernateConnector.class);
	private String user;
	private String password;
	private String dbHost;
	private String dbName;
	private String dbPort;

	private Session session;
	private SessionFactory sessionFactory;

	public HibernateConnector(String user, String password, String dbHost,
			String dbPort, String dbName) {
		this.user = user;
		this.password = password;
		this.dbHost = dbHost;
		this.dbPort = dbPort;
		this.dbName = dbName;
	}

	public HibernateConnector(HibernateConnector connector) {
		this(connector.user, connector.password, connector.dbHost, connector.dbPort, connector.dbName);
	}
	
	public void open() {
		LOG.debug("Openning connection to Database");
		if (sessionFactory == null) {
			setProperties();
		}

		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
		}
		LOG.debug("Session created: {}", session);
	}
	
	public void connect(){
		session.beginTransaction().commit();
	}

	@Override
	public void close() {
		if (session != null) {
			session.close();
		}
		if(sessionFactory != null) {
			sessionFactory.close();
		}
		
		LOG.debug("Session closed");

	}

	private void setProperties() {
		Configuration conf = new Configuration();
		conf.setProperty("hibernate.connection.url",
				String.format("jdbc:mysql://%s:%s/%s", dbHost, dbPort, dbName));
		conf.setProperty("hibernate.connection.username", user);
		conf.setProperty("hibernate.connection.password", password);
		sessionFactory = conf.configure().buildSessionFactory();
		LOG.debug("Session factory configured: {}", sessionFactory);
	}

	public void persistObject(Object obj) {
		Transaction transaction = session.beginTransaction();
		session.merge(obj);
		session.flush();
		session.clear();
		transaction.commit();
	}

	public Session getSession() {
		return session;
	}

}
