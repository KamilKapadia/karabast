package io.github.kamilkapadia.karabast.components.setup.contentpath;

import java.util.List;

public interface ContentPathDAO {

	public List<ContentPath> findAll();
	
	public ContentPath findById(long theId);
	
	public List<ContentPath> findByJobId(long theId);
	
	public void save(ContentPath contentPath);
	
	public void deleteById(long theId);
}
