package group4.feedapp.RESTproto.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import group4.feedapp.RESTproto.model.FAUser;


@Repository
public class FAUserDAOImplementation implements FAUserDAO {
	private EntityManagerFactory emf;
	private static final String PERSISTENCE_UNIT_NAME = "feedapp-RESTproto-group4";
	
	public FAUserDAOImplementation() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	@Override
	public FAUser createUser(FAUser user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;

		try {
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			success = false;
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}	
		
		if(!success){
			return null;
		};
		return user;
	}

	@Override
	public FAUser createUser(String email, String password, String name, boolean isAdmin) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;
        FAUser user = new FAUser(email, password, name, isAdmin);

		try {
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			success = false;
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}	
		
		if(!success){
			return null;
		};
		return user;
	}

	@Override
	public FAUser readUser(Long id) {
		EntityManager em = emf.createEntityManager();        
        FAUser user = null;
		try {
			user = em.find(FAUser.class, id);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}		
		return user;
	}
	
	@Override
	public Collection<FAUser> readUsers() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<FAUser> query = em.createQuery(
					"SELECT u FROM FAUser u", FAUser.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@Override
	public FAUser updateUser(Long id, FAUser updatedUser) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
        FAUser user = null;
        boolean success = true;
        
		try {
			tx.begin();
			user = em.find(FAUser.class, id);
			if(user != null && updatedUser != null) {
				user.setEmail(updatedUser.getEmail());
				user.setAdmin(updatedUser.isAdmin());
				user.setName(updatedUser.getName());
				//user.setCreatedPolls(updatedUser.getCreatedPolls());
				user.setPassword(updatedUser.getPassword());
				user.setVotes(updatedUser.getVotes());
			}
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			success = false;
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}		
		
		if(!success){
			return null;
		};
		return user;
	}

	@Override
	public FAUser deleteUser(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
        FAUser user = null;
        boolean success = true;
        
		try {
			tx.begin();
			user = em.find(FAUser.class, id);
			if(user != null) {
				em.remove(user);
			}
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			success = false;
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}	
		
		if(!success){
			return null;
		};
		return user;
	}	
	
}
