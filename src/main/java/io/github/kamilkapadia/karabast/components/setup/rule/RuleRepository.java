package io.github.kamilkapadia.karabast.components.setup.rule;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class RuleRepository implements RuleDAO {

	private EntityManager entityManager;
	
	public RuleRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Rule> findAll() {
		TypedQuery<Rule> theQuery = entityManager.createQuery("from Rule", Rule.class);
		return theQuery.getResultList(); 
	}

	@Override
	public Rule findById(long theId) {
		return entityManager.find(Rule.class, theId);
	}

	@Override
	public List<Rule> findByJobId(long theId) {
		TypedQuery<Rule> theQuery = entityManager.createQuery("from Rule where job_id =: jobId"
				, Rule.class);
		
		theQuery.setParameter("jobId", theId);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(Rule theRule) {
		theRule.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		Rule dbJob = entityManager.merge(theRule);
		theRule.setId(dbJob.getId());
		
	}

	@Override
	public void deleteById(long theId) {
		Query theQuery = entityManager.createQuery("delete from Rule where id=:theId");
		theQuery.setParameter("theId", theId);
		theQuery.executeUpdate();
		
	}

}

