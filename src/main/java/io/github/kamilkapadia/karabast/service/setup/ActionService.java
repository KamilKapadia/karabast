package io.github.kamilkapadia.karabast.service.setup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.setup.ActionDAO;
import io.github.kamilkapadia.karabast.dto.setup.Action;

@Service
public class ActionService {

	// The "regular" JPA WAY

	private ActionDAO actionDAO;

	@Autowired
	public ActionService(ActionDAO theActionDAO) {
		actionDAO = theActionDAO;
	}

	@Transactional
	public List<Action> findAll() {
		return actionDAO.findAll();
	}

	@Transactional
	public Action findById(long theId) {
		return actionDAO.findById(theId);
	}

	@Transactional
	public List<Action> findByJobId(long theId) {
		return actionDAO.findByJobId(theId);
	}
	
	@Transactional
	public void save(Action theAction) {
		actionDAO.save(theAction);
	}

	@Transactional
	public void deleteById(long theId) {
		actionDAO.deleteById(theId);
	}
}
