package io.github.kamilkapadia.karabast.components.lookup.rulecode;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

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

