package group4.feedapp.RESTproto.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import group4.feedapp.RESTproto.model.IoTDevice;
import group4.feedapp.RESTproto.model.Poll;

@Repository
public class IoTDeviceDAOImplementation implements IoTDeviceDAO{
	private EntityManagerFactory emf;
	private static final String PERSISTENCE_UNIT_NAME = "feedapp-RESTproto-group4";
	
	public IoTDeviceDAOImplementation() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	@Override
	public IoTDevice createIoTDevice(IoTDevice device) {
		EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;
     
		try {
			tx.begin();
			em.persist(device);
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
		
		if(!success) {
			return null;
		}
		return device;
	}

	@Override
	public IoTDevice createIoTDevice(String name, Poll linkedPoll) {
		EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        boolean success = true;
        IoTDevice device = new IoTDevice(name, linkedPoll);

		try {
			tx.begin();
			em.persist(device);
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
		
		if(!success) {
			return null;
		}
		return device;
	}

	@Override
	public IoTDevice readIoTDevice(Long id) {
		EntityManager em = emf.createEntityManager();        
		IoTDevice device = null;

		try {
			device = em.find(IoTDevice.class, id);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}		
		return device;
	}

	@Override
	public Collection<IoTDevice> readIoTDevices() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<IoTDevice> query = em.createQuery(
					"SELECT d FROM IoTDevice d", IoTDevice.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public IoTDevice updateIoTDevice(Long id, IoTDevice updatedDevice) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		IoTDevice device = null;
        boolean success = true;
        
		try {
			tx.begin();
			device = em.find(IoTDevice.class, id);
			if(device != null && updatedDevice != null) {
				device.setLinkedPoll(updatedDevice.getLinkedPoll());
				device.setName(updatedDevice.getName());
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
		
		if(!success) {
			return null;
		}
		return device;
	}

	@Override
	public IoTDevice deleteIoTDevice(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
        IoTDevice device = null;
        boolean success = true;
        
		try {
			tx.begin();
			device = em.find(IoTDevice.class, id);
			if(device != null) {
				em.remove(device);
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
		
		if(!success) {
			return null;
		}
		return device;
	}

}
