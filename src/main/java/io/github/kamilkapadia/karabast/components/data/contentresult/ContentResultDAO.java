package io.github.kamilkapadia.karabast.components.data.contentresult;

import java.util.List;

public interface ContentResultDAO {

	public List<ContentResult> findAll();
	
	public ContentResult findById(long theId);
	
	public List<ContentResult> findByResultId(long theId);
	
	public void save(ContentResult contentResult);
	
	public void deleteById(long theId);
}

