package group4.feedapp.RESTproto.dao;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import group4.feedapp.RESTproto.model.FAUser;
import group4.feedapp.RESTproto.model.Poll;

@Repository
public class PollDAOImplementation implements PollDAO {
	private EntityManagerFactory emf;
	private static final String PERSISTENCE_UNIT_NAME = "feedapp-RESTproto-group4";
	
	public PollDAOImplementation() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	@Override
	public Poll createPoll(Poll newPoll) {
		EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;

		try {
			tx.begin();
			em.persist(newPoll);
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
		return newPoll;
	}

	@Override
	public Poll createPoll(String question, int noCount, int yesCount, LocalDateTime startTime,
			LocalDateTime endTime, boolean isPublic, int status, String accessCode, FAUser creator) {
		EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;
        Poll poll = new Poll(question, noCount, yesCount, startTime, endTime, isPublic, status, accessCode, creator);

		try {
			tx.begin();
			em.persist(poll);
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
		return poll;
	}

	@Override
	public Poll readPoll(Long id) {
		EntityManager em = emf.createEntityManager();        
        Poll poll = null;

		try {
			poll = em.find(Poll.class, id);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}		
		return poll;
	}

	@Override
	public Collection<Poll> readPolls() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Poll> query = em.createQuery(
					"SELECT p FROM Poll p", Poll.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Poll updatePoll(Long id, Poll updatedPoll) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
        Poll poll = null;
        boolean success = true;
        
		try {
			tx.begin();
			poll = em.find(Poll.class, id);
			if(poll != null && updatedPoll != null) {
				poll.setAccessCode(updatedPoll.getAccessCode());
				poll.setCreator(updatedPoll.getCreator());
				poll.setStartTime(updatedPoll.getStartTime());
				poll.setEndTime(updatedPoll.getStartTime());
				poll.setStatus(updatedPoll.getStatus());
				poll.setIotVotes(updatedPoll.getIotVotes());
				poll.setUserVotes(updatedPoll.getUserVotes());
				poll.setNoCount(updatedPoll.getNoCount());
				poll.setYesCount(updatedPoll.getYesCount());
				poll.setPublic(updatedPoll.isPublic());
				poll.setQuestion(updatedPoll.getQuestion());
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
		return poll;
	}

	@Override
	public Poll deletePoll(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
        Poll poll = null;
        boolean success = true;
        
		try {
			tx.begin();
			poll = em.find(Poll.class, id);
			if(poll != null) {
				em.remove(poll);
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
		return poll;
	}

}
