package io.github.kamilkapadia.karabast.components.setup.contentpath;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class ContentPathRepository implements ContentPathDAO {

	private EntityManager entityManager;
	
	public ContentPathRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<ContentPath> findAll() {
		TypedQuery<ContentPath> theQuery = entityManager.createQuery("from ContentPath", ContentPath.class);
		return theQuery.getResultList(); 
	}

	@Override
	public ContentPath findById(long theId) {
		return entityManager.find(ContentPath.class, theId);
	}

	@Override
	public List<ContentPath> findByJobId(long theId) {
		TypedQuery<ContentPath> theQuery = entityManager.createQuery("from ContentPath where job_id =: jobId"
				, ContentPath.class);
		
		theQuery.setParameter("jobId", theId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(ContentPath contentPath) {
		contentPath.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		ContentPath dbContentPath = entityManager.merge(contentPath);
		contentPath.setId(dbContentPath.getId());
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from ContentPath where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
	}
}

