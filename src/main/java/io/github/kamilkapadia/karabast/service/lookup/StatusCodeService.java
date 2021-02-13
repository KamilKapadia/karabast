package io.github.kamilkapadia.karabast.service.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.lookup.StatusCodeDAO;
import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;

@Service
public class StatusCodeService {

private StatusCodeDAO statusCodeDAO;
	
	@Autowired
	public StatusCodeService(StatusCodeDAO theStatusCodeDAO) {
		statusCodeDAO = theStatusCodeDAO;
	}

	@Transactional
	public List<StatusCode> findAll() {
		return statusCodeDAO.findAll();
	}

	@Transactional
	public StatusCode findById(int theId) {
		return statusCodeDAO.findById(theId);
	}
}
