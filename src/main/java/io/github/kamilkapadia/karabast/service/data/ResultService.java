package io.github.kamilkapadia.karabast.service.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.data.ResultDAO;
import io.github.kamilkapadia.karabast.dto.data.Result;

@Service
public class ResultService {

	// The "regular" JPA WAY

	private ResultDAO resultDAO;

	@Autowired
	public ResultService(ResultDAO theResultDAO) {
		resultDAO = theResultDAO;
	}

	@Transactional
	public List<Result> findAll() {
		return resultDAO.findAll();
	}

	@Transactional
	public Result findById(long theId) {
		return resultDAO.findById(theId);
	}

	@Transactional
	public List<Result> findByJobId(long theId) {
		return resultDAO.findByJobId(theId);
	}

	@Transactional
	public Result findByJobIdAndRunId(long jobId, long run) {
		return resultDAO.findByJobIdAndRunId(jobId, run);
	}

	@Transactional
	public void save(Result theResult) {
		resultDAO.save(theResult);
	}

	@Transactional
	public void deleteById(long theId) {
		resultDAO.deleteById(theId);
	}
}

