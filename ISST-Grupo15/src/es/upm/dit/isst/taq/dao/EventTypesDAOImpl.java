package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.EventTypes;

public class EventTypesDAOImpl implements EventTypesDAO{

	private static EventTypesDAOImpl instancia = null;
	private EventTypesDAOImpl() {
	}
	
	public static EventTypesDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new EventTypesDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(EventTypes a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public EventTypes read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 EventTypes event = session.get(EventTypes.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(EventTypes a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(EventTypes a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<EventTypes> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<EventTypes> lista = session.createQuery("from EventTypes").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}