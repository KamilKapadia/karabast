package io.github.kamilkapadia.karabast.components.data.content;

import java.util.List;

public interface ContentDAO {

	public List<Content> findAll();
	
	public Content findById(long theId);
	
	public Content findbyNameAndChecksums(String name, long crc32, long adler32, String md5, String sha512);
	
	public void save(Content theContent);
	
	public void deleteById(long theId);
}
