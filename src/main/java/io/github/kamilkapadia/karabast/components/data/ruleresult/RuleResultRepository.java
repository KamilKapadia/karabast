package io.github.kamilkapadia.karabast.components.data.ruleresult;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class RuleResultRepository implements RuleResultDAO {

	private EntityManager entityManager;
	
	public RuleResultRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<RuleResult> findAll() {
		TypedQuery<RuleResult> theQuery = entityManager.createQuery("from RuleResult", RuleResult.class);
		return theQuery.getResultList(); 
	}

	@Override
	public RuleResult findById(long theId) {
		return entityManager.find(RuleResult.class, theId);
	}

	@Override
	public List<RuleResult> findByResultId(long theId) {
		TypedQuery<RuleResult> theQuery = entityManager.createQuery("from RuleResult where result_id =: resultId "
				, RuleResult.class);
		
		theQuery.setParameter("resultId", theId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(RuleResult theRuleResult) {
		RuleResult dbRuleResult = entityManager.merge(theRuleResult);
		theRuleResult.setId(dbRuleResult.getId());
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from RuleResult where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
	}
}

