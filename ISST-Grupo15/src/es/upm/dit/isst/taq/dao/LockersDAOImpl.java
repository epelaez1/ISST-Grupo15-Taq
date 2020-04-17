package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.Lockers;

public class LockersDAOImpl implements LockersDAO{

	private static LockersDAOImpl instancia = null;
	private LockersDAOImpl() {
	}
	
	public static LockersDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new LockersDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(Lockers a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public Lockers read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 Lockers event = session.get(Lockers.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(Lockers a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(Lockers a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<Lockers> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Lockers> lista = session.createQuery("from Lockers").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}