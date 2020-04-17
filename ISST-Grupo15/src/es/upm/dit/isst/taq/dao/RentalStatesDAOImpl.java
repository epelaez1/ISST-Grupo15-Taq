package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.RentalStates;

public class RentalStatesDAOImpl implements RentalStatesDAO{

	private static RentalStatesDAOImpl instancia = null;
	private RentalStatesDAOImpl() {
	}
	
	public static RentalStatesDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new RentalStatesDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(RentalStates a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public RentalStates read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 RentalStates event = session.get(RentalStates.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(RentalStates a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(RentalStates a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<RentalStates> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<RentalStates> lista = session.createQuery("from Rentals").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}