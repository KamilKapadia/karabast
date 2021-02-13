package io.github.kamilkapadia.karabast.service.lookup;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.lookup.ActionCodeDAO;
import io.github.kamilkapadia.karabast.dto.lookup.ActionCode;

@Service
public class ActionCodeService {

	private ActionCodeDAO actionCodeDAO;
	
	@Autowired
	public ActionCodeService(ActionCodeDAO theActionCodeDAO) {
		actionCodeDAO = theActionCodeDAO;
	}

	@Transactional
	public List<ActionCode> findAll() {
		return actionCodeDAO.findAll();
	}

	@Transactional
	public ActionCode findById(int theId) {
		return actionCodeDAO.findById(theId);
	}
}
