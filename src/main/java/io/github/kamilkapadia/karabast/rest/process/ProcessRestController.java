package io.github.kamilkapadia.karabast.rest.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.service.data.ResultService;
import io.github.kamilkapadia.karabast.service.data.RuleResultService;
import io.github.kamilkapadia.karabast.service.lookup.StatusCodeService;
import io.github.kamilkapadia.karabast.service.setup.HistoricalNameService;
import io.github.kamilkapadia.karabast.service.data.HistoricalDataService;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.service.setup.RuleService;
import io.github.kamilkapadia.karabast.util.RecordProcessingUtil;

@RestController
@RequestMapping("/api")

public class ProcessRestController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ResultService resultService;
		
	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private RuleResultService ruleResultService;
	
	@Autowired
	private StatusCodeService statusCodeService;
	
	@Autowired
	private HistoricalNameService historicalNameService;
	
	@Autowired
	private HistoricalDataService historicalDataService;
	
	@PostMapping("/process")
	public String process(@RequestBody String rawJson) {

		RecordProcessingUtil.processRecord(rawJson, jobService, resultService, ruleService, ruleResultService, statusCodeService, historicalNameService, historicalDataService);
		
		return "Record Processing Complete";
	}
}
