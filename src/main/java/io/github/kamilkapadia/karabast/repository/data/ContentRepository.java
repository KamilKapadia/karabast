package io.github.kamilkapadia.karabast.repository.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.github.kamilkapadia.karabast.dao.data.ContentDAO;
import io.github.kamilkapadia.karabast.dto.data.Content;

@Repository
public class ContentRepository implements ContentDAO {

	private EntityManager entityManager;

	public ContentRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Content> findAll() {
		TypedQuery<Content> theQuery = entityManager.createQuery("from Content", Content.class);
		return theQuery.getResultList(); 
	}

	@Override
	public Content findById(long theId) {
		return entityManager.find(Content.class, theId);
	}

	@Override
	public void save(Content theContent) {
		Content dbContent = entityManager.merge(theContent);
		theContent.setId(dbContent.getId());
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from Content where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
	}
}

