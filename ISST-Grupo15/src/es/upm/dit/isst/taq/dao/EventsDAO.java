package es.upm.dit.isst.taq.dao;

import java.util.List;

import es.upm.dit.isst.taq.model.Events;

public interface EventsDAO {

	public void create(Events event);
	public Events read(Integer id);
	public void update(Events event);
	public void delete(Events event);
	public List<Events> readAll();
	
}
