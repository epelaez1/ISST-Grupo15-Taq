package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.LockerStates;

public class LockerStatesDAOImpl implements LockerStatesDAO{

	private static LockerStatesDAOImpl instancia = null;
	private LockerStatesDAOImpl() {
	}
	
	public static LockerStatesDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new LockerStatesDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(LockerStates a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public LockerStates read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 LockerStates event = session.get(LockerStates.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(LockerStates a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(LockerStates a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<LockerStates> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<LockerStates> lista = session.createQuery("from LockerStates").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}