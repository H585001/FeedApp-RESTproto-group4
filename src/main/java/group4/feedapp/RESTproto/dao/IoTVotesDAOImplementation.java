package group4.feedapp.RESTproto.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import group4.feedapp.RESTproto.model.IoTDevice;
import group4.feedapp.RESTproto.model.IoTVotes;
import group4.feedapp.RESTproto.model.Poll;

@Repository
public class IoTVotesDAOImplementation implements IoTVotesDAO{
	private EntityManagerFactory emf;
	private static final String PERSISTENCE_UNIT_NAME = "feedapp-RESTproto-group4";
	
	public IoTVotesDAOImplementation() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Override
	public IoTVotes createIoTVotes(IoTDevice device, Poll votePoll, int noCount, int yesCount) {
		EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;
        IoTVotes votes = new IoTVotes(noCount, yesCount, device, votePoll);

		try {
			tx.begin();
			em.persist(votes);
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
		return votes;
	}

	@Override
	public IoTVotes createIoTVotes(IoTVotes votes) {
		EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;

		try {
			tx.begin();
			em.persist(votes);
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
		return votes;
	}

	@Override
	public IoTVotes readIoTVotes(Long id) {
		EntityManager em = emf.createEntityManager();        
        IoTVotes votes = null;

		try {
			votes = em.find(IoTVotes.class, id);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}		
		return votes;
	}

	@Override
	public Collection<IoTVotes> findIoTVotes(Long pollId, Long deviceId) {
		EntityManager em = emf.createEntityManager();        
		try {
			TypedQuery<IoTVotes> q = em.createQuery("SELECT v from IOTVOTES v WHERE v.iotdevice_id = :deviceId AND v.poll_id = :pollId", IoTVotes.class);
			q.setParameter("deviceId", deviceId);
			q.setParameter("pollId", pollId);
			return q.getResultList();
		} catch (NoResultException e) {
			
		} finally {
			em.close();
		}		
		return null;
	}

	@Override
	public Collection<IoTVotes> readIoTVotes() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<IoTVotes> query = em.createQuery("SELECT v FROM IOTVOTES v", IoTVotes.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public IoTVotes updateIoTVotes(Long id, IoTVotes updatedIoTVotes) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
        IoTVotes votes = null;
        boolean success = true;
        
		try {
			tx.begin();
			votes = em.find(IoTVotes.class, id);
			if(votes != null && updatedIoTVotes != null) {
				votes.setNoCount(updatedIoTVotes.getNoCount());
				votes.setYesCount(updatedIoTVotes.getYesCount());
				votes.setIotVotePoll(updatedIoTVotes.getIotVotePoll());
				votes.setDevice(updatedIoTVotes.getDevice());
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
		return votes;
	}

	@Override
	public IoTVotes deleteIoTVotes(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
        IoTVotes votes = null;
        boolean success = true;
        
		try {
			tx.begin();
			votes = em.find(IoTVotes.class, id);
			if(votes != null) {
				em.remove(votes);
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
		return votes;
	}

}
