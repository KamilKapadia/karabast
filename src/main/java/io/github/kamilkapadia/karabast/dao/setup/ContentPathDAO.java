package io.github.kamilkapadia.karabast.dao.setup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.setup.ContentPath;

public interface ContentPathDAO {

	public List<ContentPath> findAll();
	
	public ContentPath findById(long theId);
	
	public List<ContentPath> findByJobId(long theId);
	
	public void save(ContentPath contentPath);
	
	public void deleteById(long theId);
}
