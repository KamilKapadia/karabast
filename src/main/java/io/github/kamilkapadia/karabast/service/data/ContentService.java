package io.github.kamilkapadia.karabast.service.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.data.ContentDAO;
import io.github.kamilkapadia.karabast.dto.data.Content;

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
	public void save(Content theContent) {
		contentDAO.save(theContent);
	}

	@Transactional
	public void deleteById(long theId) {
		contentDAO.deleteById(theId);
	}
}
