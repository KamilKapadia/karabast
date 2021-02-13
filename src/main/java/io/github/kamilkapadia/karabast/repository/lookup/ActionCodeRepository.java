package io.github.kamilkapadia.karabast.repository.lookup;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.github.kamilkapadia.karabast.dao.lookup.ActionCodeDAO;
import io.github.kamilkapadia.karabast.dto.lookup.ActionCode;

@Repository
public class ActionCodeRepository implements ActionCodeDAO {

	private EntityManager entityManager;
	
	public ActionCodeRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<ActionCode> findAll() {
		TypedQuery<ActionCode> theQuery = entityManager.createQuery("from ActionCode", ActionCode.class);
		return theQuery.getResultList(); 
	}

	@Override
	public ActionCode findById(int theId) {
		return entityManager.find(ActionCode.class, theId);
	}
}
