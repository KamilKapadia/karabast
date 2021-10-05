package io.github.kamilkapadia.karabast.components.lookup.actioncode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
