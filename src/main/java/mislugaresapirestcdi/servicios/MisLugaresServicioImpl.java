package mislugaresapirestcdi.servicios;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import mislugaresapirestcdi.modelo.Lugar;


@Stateless
public class MisLugaresServicioImpl implements MisLugaresServicio {
	@PersistenceContext(unitName = "JPATomcat10")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPATomcat10");
		EntityManager manager = factory.createEntityManager();
		return manager;
	}

	public MisLugaresServicioImpl() {
		//entityManager = getEntityManager();
	}

	@Override
	public Lugar createLugar(Lugar lugar) throws PersistenceException{
		// TODO Auto-generated method stub

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(lugar);

		transaction.commit();
		return lugar;
	}
	
	@Override
	public int borrarLugar(int id) throws PersistenceException{

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createQuery("DELETE FROM Lugar l WHERE l.id = :id");
		query.setParameter("id", id);
		int rowsDeleted = query.executeUpdate();
		transaction.commit();
		return rowsDeleted;

	}


	@Override
	public Lugar updateLugar(Lugar lugar) throws PersistenceException {
		// TODO Auto-generated method stub
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(lugar);
		transaction.commit();
		return lugar;
	}

	@Override
	public Lugar findLugarById(int id) throws PersistenceException{
		// TODO Auto-generated method stub
		return entityManager.find(Lugar.class, id);
	}

	@Override
	public Lugar findLugarByName(String name) throws PersistenceException {
		// TODO Auto-generated method stub
		return entityManager.createQuery("Select l from Lugar l where l.nombre LIKE '%" + name + "%'", Lugar.class)
				.getSingleResult();

	}

	@Override
	public List<Lugar> getLugares() throws PersistenceException {
		// TODO Auto-generated method stub

	//	return entityManager.createQuery("Select l from Lugar l", Lugar.class).getResultList();

		 return entityManager.createNativeQuery("Select * from lugares",
		 Lugar.class).getResultList();

	}


}
