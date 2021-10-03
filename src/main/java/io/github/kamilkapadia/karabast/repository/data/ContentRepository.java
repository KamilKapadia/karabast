package io.github.kamilkapadia.karabast.repository.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	public Content findbyNameAndChecksums(String name, long crc32, long adler32, String md5, String sha512) {
		TypedQuery<Content> theQuery = entityManager.createQuery("from Content where name =: name and crc32 =: crc32 "
				+ "and adler32 =: adler32 and md5 =: md5 and sha512 =: sha512"
				, Content.class);
		
		theQuery.setParameter("name", name);
		theQuery.setParameter("crc32", crc32);
		theQuery.setParameter("adler32", adler32);
		theQuery.setParameter("md5", md5);
		theQuery.setParameter("sha512", sha512);
		
		Content content = null; 
		
		try {
			content = theQuery.getSingleResult();
		} catch (NoResultException nre) {
			// TODO
		}
			
		return content;
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

