package io.github.kamilkapadia.karabast.components.data.result;

import java.util.List;


public interface ResultDAO {

	public List<Result> findAll();
	
	public Result findById(long theId);
	
	public List<Result> findByJobId(long jobId);
	
	public Result findByJobIdAndRunId(long jobId, long run);
	
	public void save(Result theResult);
	
	public void deleteById(long theId);
}
