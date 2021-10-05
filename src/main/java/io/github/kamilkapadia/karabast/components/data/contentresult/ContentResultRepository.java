package io.github.kamilkapadia.karabast.components.data.contentresult;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class ContentResultRepository implements ContentResultDAO {

	private EntityManager entityManager;

	public ContentResultRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<ContentResult> findAll() {
		TypedQuery<ContentResult> theQuery = entityManager.createQuery("from ContentResult", ContentResult.class);
		return theQuery.getResultList(); 
	}

	@Override
	public ContentResult findById(long theId) {
		return entityManager.find(ContentResult.class, theId);
	}

	@Override
	public List<ContentResult> findByResultId(long theId) {
		TypedQuery<ContentResult> theQuery = entityManager.createQuery("from ContentResult where result_id =: resultId"
				, ContentResult.class);
		
		theQuery.setParameter("resultId", theId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(ContentResult contentResult) {
		ContentResult dbContentResult = entityManager.merge(contentResult);
		contentResult.setId(dbContentResult.getId());
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from ContentResult where id=:id");
		theQuery.setParameter("id", theId);
		theQuery.executeUpdate();
	}
}

