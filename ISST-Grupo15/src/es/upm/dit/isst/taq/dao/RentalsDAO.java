package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.Rentals;

public interface RentalsDAO {

	public void create(Rentals rental);
	public Rentals read(Integer id);
	public void update(Rentals rental);
	public void delete(Rentals rental);
	public Collection<Rentals> readAll();
	
}
