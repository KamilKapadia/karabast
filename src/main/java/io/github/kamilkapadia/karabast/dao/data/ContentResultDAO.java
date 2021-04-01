package io.github.kamilkapadia.karabast.dao.data;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.ContentResult;

public interface ContentResultDAO {

	public List<ContentResult> findAll();
	
	public ContentResult findById(long theId);
	
	public List<ContentResult> findByResultId(long theId);
	
	public void save(ContentResult contentResult);
	
	public void deleteById(long theId);
}

