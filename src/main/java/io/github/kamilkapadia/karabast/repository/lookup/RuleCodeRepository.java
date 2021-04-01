package io.github.kamilkapadia.karabast.repository.lookup;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.github.kamilkapadia.karabast.dao.lookup.RuleCodeDAO;
import io.github.kamilkapadia.karabast.dto.lookup.RuleCode;

@Repository
public class RuleCodeRepository implements RuleCodeDAO {

	private EntityManager entityManager;
	
	public RuleCodeRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<RuleCode> findAll() {
		TypedQuery<RuleCode> theQuery = entityManager.createQuery("from RuleCode", RuleCode.class);
		return theQuery.getResultList(); 
	}

	@Override
	public RuleCode findById(int theId) {
		return entityManager.find(RuleCode.class, theId);
	}

}

