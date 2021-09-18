package io.github.kamilkapadia.karabast.service.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.data.ContentResultDAO;
import io.github.kamilkapadia.karabast.dto.data.Content;
import io.github.kamilkapadia.karabast.dto.data.ContentResult;

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
