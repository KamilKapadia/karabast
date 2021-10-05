package io.github.kamilkapadia.karabast.components.setup.historicalname;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class HistoricalNameRepository implements HistoricalNameDAO {

	private EntityManager entityManager;
	
	public HistoricalNameRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<HistoricalName> findAll() {
		TypedQuery<HistoricalName> theQuery = entityManager.createQuery("from HistoricalName", HistoricalName.class);
		return theQuery.getResultList(); 
	}

	@Override
	public HistoricalName findById(long theId) {
		return entityManager.find(HistoricalName.class, theId);
	}
	
	@Override
	public List<HistoricalName> findByJobId(long theId) {
		TypedQuery<HistoricalName> theQuery = entityManager.createQuery("from HistoricalName where job_id =: jobId"
				, HistoricalName.class);
		
		theQuery.setParameter("jobId", theId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(HistoricalName historicalName) {
		historicalName.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		HistoricalName dbHistoricalName = entityManager.merge(historicalName);
		historicalName.setId(dbHistoricalName.getId());
		
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from HistoricalName where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
		
	}
}

