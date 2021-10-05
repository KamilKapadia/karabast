package io.github.kamilkapadia.karabast.components.data.result;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResultRestContoller {

	@Autowired
	private ResultService resultService;
	
	@GetMapping("/results/{id}")
	public Result find(@PathVariable long id) {
		return resultService.findById(id);
	}
	
	@GetMapping("/results")
	public List<Result> findAll() {
		return resultService.findAll();
	}
	
	@GetMapping("/results/jobs/{id}")
	public List<Result> findByJobId(@PathVariable long id) {
		return resultService.findByJobId(id);
	}
	
	@GetMapping("/results/jobs/{jobId}/runs/{run}")
	public Result findByJobIdAndRunId(@PathVariable long jobId, @PathVariable long run) {
		return resultService.findByJobIdAndRunId(jobId, run);
	}
	
	
}

