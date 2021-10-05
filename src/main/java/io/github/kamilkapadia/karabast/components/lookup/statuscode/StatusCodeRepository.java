package io.github.kamilkapadia.karabast.components.lookup.statuscode;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class StatusCodeRepository implements StatusCodeDAO {

	private EntityManager entityManager;
	
	public StatusCodeRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<StatusCode> findAll() {
		TypedQuery<StatusCode> theQuery = entityManager.createQuery("from StatusCode", StatusCode.class);
		return theQuery.getResultList(); 
	}

	@Override
	public StatusCode findById(int theId) {
		return entityManager.find(StatusCode.class, theId);
	}

}
