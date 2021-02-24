package io.github.kamilkapadia.karabast.repository.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.github.kamilkapadia.karabast.dao.data.HistoricalDataDAO;
import io.github.kamilkapadia.karabast.dto.data.HistoricalData;

@Repository
public class HistoricalDataRepository implements HistoricalDataDAO {

private EntityManager entityManager;
	
	public HistoricalDataRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<HistoricalData> findAll() {
		TypedQuery<HistoricalData> theQuery = entityManager.createQuery("from HistoricalData", HistoricalData.class);
		return theQuery.getResultList(); 
	}

	@Override
	public HistoricalData findById(long theId) {
		return entityManager.find(HistoricalData.class, theId);
	}

	@Override
	public List<HistoricalData> findByResultId(long resultId) {
		TypedQuery<HistoricalData> theQuery = entityManager.createQuery("from HistoricalData where result_id =: resultId"
				, HistoricalData.class);
		
		theQuery.setParameter("resultId", resultId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(HistoricalData theHistoricalData) {
		HistoricalData dbResult = entityManager.merge(theHistoricalData);
		theHistoricalData.setId(dbResult.getId());
		
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from HistoricalData where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
	}
}
