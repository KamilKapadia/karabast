package io.github.kamilkapadia.karabast.components.setup.job;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JobRepository implements JobDAO {

	private EntityManager entityManager;
	
	public JobRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Job> findAll() {
		TypedQuery<Job> theQuery = entityManager.createQuery("from Job", Job.class);
		return theQuery.getResultList(); 
	}

	@Override
	public Job findById(long theId) {
		return entityManager.find(Job.class, theId);
	}
	
	@Override
	public List<Job> findByName(String name) {
		TypedQuery<Job> theQuery = entityManager.createQuery("from Job where name =: theName", Job.class);
		
		theQuery.setParameter("theName", name);
		
		return theQuery.getResultList();
	}
	
	@Override
	public void save(Job theJob) {
		theJob.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		Job dbJob = entityManager.merge(theJob);
		theJob.setId(dbJob.getId());
	}
	
	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from Job where id=:jobId");
		theQuery.setParameter("jobId", theId);
		theQuery.executeUpdate();
	}
}
