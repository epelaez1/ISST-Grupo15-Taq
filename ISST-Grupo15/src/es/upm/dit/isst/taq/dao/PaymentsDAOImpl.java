package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.Payments;

public class PaymentsDAOImpl implements PaymentsDAO{

	private static PaymentsDAOImpl instancia = null;
	private PaymentsDAOImpl() {
	}
	
	public static PaymentsDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new PaymentsDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(Payments a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public Payments read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 Payments event = session.get(Payments.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(Payments a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(Payments a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<Payments> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Payments> lista = session.createQuery("from Payments").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}