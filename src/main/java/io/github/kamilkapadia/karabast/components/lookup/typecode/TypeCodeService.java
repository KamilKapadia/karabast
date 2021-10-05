package io.github.kamilkapadia.karabast.components.lookup.typecode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeCodeService {

private TypeCodeDAO typeCodeDAO;
	
	@Autowired
	public TypeCodeService(TypeCodeDAO theTypeCodeDAO) {
		typeCodeDAO = theTypeCodeDAO;
	}

	@Transactional
	public List<TypeCode> findAll() {
		return typeCodeDAO.findAll();
	}

	@Transactional
	public TypeCode findById(int theId) {
		return typeCodeDAO.findById(theId);
	}
}