package es.upm.dit.isst.taq.dao;

import java.util.List;

import es.upm.dit.isst.taq.model.EventTypes;

public interface EventTypesDAO {

	public void create(EventTypes eventType);
	public EventTypes read(Integer id);
	public void update(EventTypes eventType);
	public void delete(EventTypes eventType);
	public List<EventTypes> readAll();
	
}
