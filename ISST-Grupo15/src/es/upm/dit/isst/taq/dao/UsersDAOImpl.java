package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.Users;

public class UsersDAOImpl implements UsersDAO{

	private static UsersDAOImpl instancia = null;
	private UsersDAOImpl() {
	}
	
	public static UsersDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new UsersDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(Users a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public Users read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 Users event = session.get(Users.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(Users a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(Users a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<Users> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Users> lista = session.createQuery("from Rentals").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}