package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.Locations;

public interface LocationsDAO {

	public void create(Locations location);
	public Locations read(Integer id);
	public void update(Locations location);
	public void delete(Locations location);
	public Collection<Locations> readAll();
	
}
