package io.github.kamilkapadia.karabast.dao.lookup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.lookup.TypeCode;

public interface TypeCodeDAO {
	
	public List<TypeCode> findAll();
	
	public TypeCode findById(int theId);
}
