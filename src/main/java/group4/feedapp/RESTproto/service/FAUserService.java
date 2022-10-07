package group4.feedapp.RESTproto.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group4.feedapp.RESTproto.dao.FAUserDAO;
import group4.feedapp.RESTproto.model.FAUser;

@Service
public class FAUserService {
	private final FAUserDAO userDAO;
	
	@Autowired
	public FAUserService(FAUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public FAUser addUser(String email, String password, String name, boolean isAdmin) {
		return userDAO.createUser(email, password, name, isAdmin);
	}
	
	public Collection<FAUser> getAllUsers(){
		return userDAO.readUsers();
	}
	
	public FAUser getUser(Long id){
		return userDAO.readUser(id);
	}
	
	public FAUser deleteUser(Long id) {
		return userDAO.deleteUser(id);
	}
	
	public FAUser updateUser(Long id, FAUser newUser) {
		return userDAO.updateUser(id, newUser);
	}
}
