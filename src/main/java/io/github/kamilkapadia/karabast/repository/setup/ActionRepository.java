package io.github.kamilkapadia.karabast.repository.setup;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.github.kamilkapadia.karabast.dao.setup.ActionDAO;
import io.github.kamilkapadia.karabast.dto.setup.Action;

@Repository
public class ActionRepository implements ActionDAO {

	private EntityManager entityManager;
	
	public ActionRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Action> findAll() {
		TypedQuery<Action> theQuery = entityManager.createQuery("from Action", Action.class);
		return theQuery.getResultList(); 
	}

	@Override
	public Action findById(long theId) {
		return entityManager.find(Action.class, theId);
	}
	
	@Override
	public List<Action> findByJobId(long theId) {
		TypedQuery<Action> theQuery = entityManager.createQuery("from Action where job_id =: jobId"
				, Action.class);
		
		theQuery.setParameter("jobId", theId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(Action theAction) {
		theAction.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		Action dbAction = entityManager.merge(theAction);
		theAction.setId(dbAction.getId());
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from Action where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
	}
}

