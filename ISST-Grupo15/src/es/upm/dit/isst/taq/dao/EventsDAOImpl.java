package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.Events;

public class EventsDAOImpl implements EventsDAO{

	private static EventsDAOImpl instancia = null;
	private EventsDAOImpl() {
	}
	
	public static EventsDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new EventsDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(Events events) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(events);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public Events read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 Events event = session.get(Events.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(Events events) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(events);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(Events events) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(events);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<Events> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Events> lista = session.createQuery("from Events").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}
