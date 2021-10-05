package io.github.kamilkapadia.karabast.components.lookup.typecode;

import java.util.List;

public interface TypeCodeDAO {
	
	public List<TypeCode> findAll();
	
	public TypeCode findById(int theId);
}
