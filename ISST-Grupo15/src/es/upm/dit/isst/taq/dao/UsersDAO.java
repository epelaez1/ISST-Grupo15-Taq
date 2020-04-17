package es.upm.dit.isst.taq.dao;

import java.util.Collection;

import es.upm.dit.isst.taq.model.Users;

public interface UsersDAO {

	public void create(Users user);
	public Users read(Integer id);
	public void update(Users user);
	public void delete(Users user);
	public Collection<Users> readAll();
	
}
