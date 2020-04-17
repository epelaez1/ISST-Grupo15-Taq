package es.upm.dit.isst.taq.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.taq.model.PaymentMethods;

public class PaymentMethodsDAOImpl implements PaymentMethodsDAO{

	private static PaymentMethodsDAOImpl instancia = null;
	private PaymentMethodsDAOImpl() {
	}
	
	public static PaymentMethodsDAOImpl getInstance() {
	    if( null == instancia ) 
	      instancia = new PaymentMethodsDAOImpl();
	    return instancia;
	  }


	@Override
	public void create(PaymentMethods a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public PaymentMethods read(Integer id) {
		 Session session = SessionFactoryService.get().openSession();
		 session.beginTransaction();
		 PaymentMethods event = session.get(PaymentMethods.class, id);
		 session.getTransaction().commit();
		 session.close();
		 return event;
	}


	@Override
	public void update(PaymentMethods a) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();	
	}


	@Override
	public void delete(PaymentMethods a) {
		
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	public List<PaymentMethods> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<PaymentMethods> lista = session.createQuery("from PaymentMethods").list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}


}