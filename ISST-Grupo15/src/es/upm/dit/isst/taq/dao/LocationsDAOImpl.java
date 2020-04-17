package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.Locations;

public class LocationsDAOImpl implements LocationsDAO{

	private static LocationsDAOImpl instancia = null;
	private LocationsDAOImpl() {
	}
	
	public static LocationsDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new LocationsDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(Locations a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public Locations read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 Locations event = session.get(Locations.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(Locations a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(Locations a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<Locations> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Locations> lista = session.createQuery("from Locations").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}