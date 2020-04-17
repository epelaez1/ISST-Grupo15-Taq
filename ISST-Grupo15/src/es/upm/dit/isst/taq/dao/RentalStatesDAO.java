package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.RentalStates;

public interface RentalStatesDAO {

	public void create(RentalStates rentalStates);
	public RentalStates read(Integer id);
	public void update(RentalStates rentalStates);
	public void delete(RentalStates rentalStates);
	public Collection<RentalStates> readAll();
	
}
