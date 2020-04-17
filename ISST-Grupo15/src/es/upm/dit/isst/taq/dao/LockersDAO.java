package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.Lockers;

public interface LockersDAO {

	public void create(Lockers locker);
	public Lockers read(Integer id);
	public void update(Lockers locker);
	public void delete(Lockers locker);
	public Collection<Lockers> readAll();
	
}
