package io.github.kamilkapadia.karabast.repository.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.github.kamilkapadia.karabast.dao.data.LastRunDAO;
import io.github.kamilkapadia.karabast.dto.data.LastRun;

@Repository
public class LastRunRepository implements LastRunDAO {

	private EntityManager entityManager;

	public LastRunRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<LastRun> findAll() {
		TypedQuery<LastRun> theQuery = entityManager.createQuery("from LastRun", LastRun.class);
		return theQuery.getResultList(); 
	}

	@Override
	public LastRun findById(long theId) {
		return entityManager.find(LastRun.class, theId);
	}

	@Override
	public LastRun findByJobId(long theId) {
		TypedQuery<LastRun> theQuery = entityManager.createQuery("from LastRun where job_id =: jobId "
				, LastRun.class);
		
		theQuery.setParameter("jobId", theId);
		
		return theQuery.getSingleResult();
	}

	@Override
	public void save(LastRun theLastRun) {
		LastRun dbLastRun = entityManager.merge(theLastRun);
		theLastRun.setId(dbLastRun.getId());
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from LastRun where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
	}
}

