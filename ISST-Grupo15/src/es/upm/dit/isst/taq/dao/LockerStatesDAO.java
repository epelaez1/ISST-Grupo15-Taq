package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.LockerStates;

public interface LockerStatesDAO {

	public void create(LockerStates lockerState);
	public LockerStates read(Integer id);
	public void update(LockerStates lockerState);
	public void delete(LockerStates lockerState);
	public Collection<LockerStates> readAll();
	
}
