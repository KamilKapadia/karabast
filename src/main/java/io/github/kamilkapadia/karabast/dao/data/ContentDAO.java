package io.github.kamilkapadia.karabast.dao.data;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.Content;

public interface ContentDAO {

	public List<Content> findAll();
	
	public Content findById(long theId);
	
	public void save(Content theContent);
	
	public void deleteById(long theId);
}
