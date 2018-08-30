package com.apm.core;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
import com.apm.core.Employee;

public class Dao
{

  public static Session startSession() {
    Session session = DaoConfiguration.getSessionFactory().openSession();
    if ( !session.getTransaction().isActive() ) {
      session.beginTransaction();
    }
    return session;
  }

  public <T> void save ( final T t ) {
    Session session = startSession();
    session.save(t);
    session.getTransaction().commit();
    session.close();
  }

  public <T> String delete ( final T t ) {
    Session session = startSession();
    session.delete ( t );
    session.getTransaction().commit();
    session.close();
    return "Successfully Deleted! ";
  }

  public <T> T getSpecific ( final long id, final Class<T> type ) {
		Session session = startSession();
		T t = ( T ) session.get ( type, id );
		session.close();
		return t;
	}

  public Employee getSpecificWithContacts ( final long id, final Class<Employee> type ) {
		Session session = startSession();
		Employee employee = ( Employee ) session.get ( type, id );
    Hibernate.initialize(employee.getContacts());
    session.close();
		return employee;
	}

  public Employee getSpecificWithRoles ( final long id, final Class<Employee> type ) {
		Session session = startSession();
		Employee employee = ( Employee ) session.get ( type, id );
    Hibernate.initialize(employee.getRoles());
    session.close();
		return employee;
	}

  public void closeSession() {
    Session session = DaoConfiguration.getSessionFactory().getCurrentSession();
    session.close();
  }

  public <T> T get ( T t ) {
		Session session = startSession();
		List<T> list = session.createCriteria ( t.getClass() ).list();
		session.getTransaction().commit();
		session.close();
		return (T) list.get ( list.indexOf ( (T) t) );
	}

  public <T> List<T> getAll ( final Class<T> type ) {
	   	Session session = startSession();
	   	List<T> list = session.createCriteria ( type ).list();
	   	session.close();
	   	return list;
 	}

  public <T> void update ( final T t ) {
	  	Session session = startSession();
	   	session.saveOrUpdate ( t );
	   	session.getTransaction().commit();
	   	session.close();
	}

  public <T> List<T> getByQuery ( String query, final Class<T> type ) {
	  	Session session = startSession();
	   	List<T> list = session.createQuery(query).list();
	   	session.close();
	   	return list;
  	}

}
