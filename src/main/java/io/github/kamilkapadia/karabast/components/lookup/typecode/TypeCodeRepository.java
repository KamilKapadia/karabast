package io.github.kamilkapadia.karabast.components.lookup.typecode;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class TypeCodeRepository implements TypeCodeDAO {

	private EntityManager entityManager;
	
	public TypeCodeRepository(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<TypeCode> findAll() {
		TypedQuery<TypeCode> theQuery = entityManager.createQuery("from TypeCode", TypeCode.class);
		return theQuery.getResultList(); 
	}

	@Override
	public TypeCode findById(int theId) {
		return entityManager.find(TypeCode.class, theId);
	}

}
