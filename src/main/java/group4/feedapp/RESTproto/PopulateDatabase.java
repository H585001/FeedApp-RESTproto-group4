package group4.feedapp.RESTproto;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import group4.feedapp.RESTproto.dao.FAUserDAO;
import group4.feedapp.RESTproto.dao.FAUserDAOImplementation;
import group4.feedapp.RESTproto.model.FAUser;
import group4.feedapp.RESTproto.model.IoTDevice;
import group4.feedapp.RESTproto.model.IoTVotes;
import group4.feedapp.RESTproto.model.Poll;
import group4.feedapp.RESTproto.model.Vote;

public class PopulateDatabase {
	private static final String PERSISTENCE_UNIT_NAME = "feedapp-RESTproto-group4";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {        
        populateDatabase();
        
        FAUserDAO userDAO = new FAUserDAOImplementation();
        userDAO.createUser("jens@ymail.com", "hei", "Jens", false);
        
        Collection<FAUser> users = userDAO.readUsers();
        users.forEach(u -> System.out.println(u));
	}

	private static void populateDatabase() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        em.getTransaction().begin();
        
        // Test data
		FAUser user = new FAUser("simon@gmail.com", "test", "Simon", true);
		Poll poll1 = new Poll("Are you sick and tired?", 0, 0, null, null, true, 0, "#sick", user);
		Poll poll2 = new Poll("Do you like America?", 0, 0, null, null, false, 0, "#america", user);
		IoTDevice iot1 = new IoTDevice("IoT Device 1", poll1);
		IoTDevice iot2 = new IoTDevice("IoT Device 2", poll1);
		
		Vote votePoll1 = new Vote(user, poll1, true);
		Vote votePoll2 = new Vote(user, poll2, false);
		
		user.getVotes().add(votePoll1);
		user.getVotes().add(votePoll2);
		
		poll1.getUserVotes().add(votePoll1);
		poll1.setYesCount(poll1.getYesCount() + 1);
		poll2.getUserVotes().add(votePoll2);
		poll2.setNoCount(poll2.getNoCount() + 1);
		
		IoTVotes iot1Votes = new IoTVotes(3, 4, iot1, poll1);
		poll1.getIotVotes().add(iot1Votes);
		poll1.setYesCount(poll1.getYesCount() + iot1Votes.getYesCount());
		poll1.setNoCount(poll1.getNoCount() + iot1Votes.getNoCount());
		
		// Persisting
        em.persist(user);
        em.persist(poll1);
        em.persist(poll2);
        em.persist(iot1);
        em.persist(iot2);
        em.persist(votePoll1);
        em.persist(votePoll2);
        em.persist(iot1Votes);
        em.getTransaction().commit();
        
        em.close();
	}

}
