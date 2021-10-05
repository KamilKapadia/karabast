package io.github.kamilkapadia.karabast.components.setup.contentpath;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentPathService {

	// The "regular" JPA WAY

	private ContentPathDAO contentPathDAO;

	@Autowired
	public ContentPathService(ContentPathDAO theContentPathDAO) {
		contentPathDAO = theContentPathDAO;
	}

	@Transactional
	public List<ContentPath> findAll() {
		return contentPathDAO.findAll();
	}

	@Transactional
	public ContentPath findById(long theId) {
		return contentPathDAO.findById(theId);
	}

	@Transactional
	public List<ContentPath> findByJobId(long theId) {
		return contentPathDAO.findByJobId(theId);
	}
	
	@Transactional
	public void save(ContentPath theContentPath) {
		contentPathDAO.save(theContentPath);
	}

	@Transactional
	public void deleteById(long theId) {
		contentPathDAO.deleteById(theId);
	}
}

