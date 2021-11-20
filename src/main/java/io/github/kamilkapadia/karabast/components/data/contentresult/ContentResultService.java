package io.github.kamilkapadia.karabast.components.data.contentresult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentResultService {

	// The "regular" JPA WAY
	
	private ContentResultDAO contentResultDAO;
	
	@Autowired
	public ContentResultService(ContentResultDAO theContentResultDAO) {
		contentResultDAO = theContentResultDAO;
	}
	
	@Transactional
	public List<ContentResult> findAll() {
		return contentResultDAO.findAll();
	}
	
	@Transactional
	public ContentResult findById(long theId) {
		return contentResultDAO.findById(theId);
	}
	
	@Transactional
	public List<ContentResult> findByResultId(long theId) {
		return contentResultDAO.findByResultId(theId);
	}
	
	@Transactional
	public void save(ContentResult theContentResult) {
		contentResultDAO.save(theContentResult);
	}
}