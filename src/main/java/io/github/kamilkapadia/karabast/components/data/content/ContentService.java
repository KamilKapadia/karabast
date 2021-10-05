package io.github.kamilkapadia.karabast.components.data.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentService {
	private ContentDAO contentDAO;

	@Autowired
	public ContentService(ContentDAO theContentDAO) {
		contentDAO = theContentDAO;
	}

	@Transactional
	public List<Content> findAll() {
		return contentDAO.findAll();
	}

	@Transactional
	public Content findById(long theId) {
		return contentDAO.findById(theId);
	}
	
	@Transactional
	public Content findbyNameAndChecksums(String name, long crc32, long adler32, String md5, String sha512) {
		return contentDAO.findbyNameAndChecksums(name, crc32, adler32, md5, sha512);
	}
	
	@Transactional
	public void save(Content theContent) {
		contentDAO.save(theContent);
	}

	@Transactional
	public void deleteById(long theId) {
		contentDAO.deleteById(theId);
	}
}
