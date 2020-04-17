package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.PaymentMethods;

public interface PaymentMethodsDAO {

	public void create(PaymentMethods paymentMethods);
	public PaymentMethods read(Integer id);
	public void update(PaymentMethods paymentMethods);
	public void delete(PaymentMethods paymentMethods);
	public Collection<PaymentMethods> readAll();
	
}
