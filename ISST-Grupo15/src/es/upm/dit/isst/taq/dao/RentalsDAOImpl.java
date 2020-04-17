package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.Rentals;

public class RentalsDAOImpl implements RentalsDAO{

	private static RentalsDAOImpl instancia = null;
	private RentalsDAOImpl() {
	}
	
	public static RentalsDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new RentalsDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(Rentals a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public Rentals read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 Rentals event = session.get(Rentals.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(Rentals a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(Rentals a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<Rentals> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Rentals> lista = session.createQuery("from Rentals").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}