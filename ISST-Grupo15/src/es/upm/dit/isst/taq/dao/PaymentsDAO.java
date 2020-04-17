package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.Payments;

public interface PaymentsDAO {

	public void create(Payments payment);
	public Payments read(Integer id);
	public void update(Payments payment);
	public void delete(Payments payment);
	public Collection<Payments> readAll();
	
}
