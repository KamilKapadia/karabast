package io.github.kamilkapadia.karabast.repository.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.github.kamilkapadia.karabast.dao.data.ResultDAO;
import io.github.kamilkapadia.karabast.dto.data.Result;

@Repository
public class ResultRepository implements ResultDAO {

	private EntityManager entityManager;
	
	public ResultRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Result> findAll() {
		TypedQuery<Result> theQuery = entityManager.createQuery("from Result", Result.class);
		return theQuery.getResultList(); 
	}

	@Override
	public Result findById(long theId) {
		return entityManager.find(Result.class, theId);
	}
	
	@Override
	public List<Result> findByJobId(long theId) {
		TypedQuery<Result> theQuery = entityManager.createQuery("from Result where job_id =: jobId order by run desc"
				, Result.class);
		
		theQuery.setParameter("jobId", theId);
		
		return theQuery.getResultList();
	}
	
	@Override
	public Result findByJobIdAndRunId(long jobId, long run) {
		TypedQuery<Result> theQuery = entityManager.createQuery("from Result where job_id =: jobId and run =: run"
				, Result.class);
		
		theQuery.setParameter("jobId", jobId);
		theQuery.setParameter("run", run);
		
		return theQuery.getSingleResult();
	}

	@Override
	public void save(Result theResult) {
		Result dbResult = entityManager.merge(theResult);
		theResult.setId(dbResult.getId());
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from Result where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
	}
}

