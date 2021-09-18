package io.github.kamilkapadia.karabast.dao.data;

import java.util.List;

import javax.persistence.Column;

import io.github.kamilkapadia.karabast.dto.data.Content;

public interface ContentDAO {

	public List<Content> findAll();
	
	public Content findById(long theId);
	
	public Content findbyNameAndChecksums(String name, long crc32, long adler32, String md5, String sha512);
	
	public void save(Content theContent);
	
	public void deleteById(long theId);
}
