package io.github.kamilkapadia.karabast.components.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.components.data.content.ContentService;
import io.github.kamilkapadia.karabast.components.data.contentresult.ContentResultService;
import io.github.kamilkapadia.karabast.components.data.historicaldata.HistoricalDataService;
import io.github.kamilkapadia.karabast.components.data.lastrun.LastRunService;
import io.github.kamilkapadia.karabast.components.data.result.ResultService;
import io.github.kamilkapadia.karabast.components.data.ruleresult.RuleResultService;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCodeService;
import io.github.kamilkapadia.karabast.components.setup.contentpath.ContentPathService;
import io.github.kamilkapadia.karabast.components.setup.historicalname.HistoricalNameService;
import io.github.kamilkapadia.karabast.components.setup.job.JobService;
import io.github.kamilkapadia.karabast.components.setup.rule.RuleService;
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
	
	@Autowired
	private ContentPathService contentPathService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private ContentResultService contentResultService;
	
	@Autowired
	private LastRunService lastRunService;
	
	@PostMapping("/process")
	public String process(@RequestBody String rawJson) {

		RecordProcessingUtil.processRecord(rawJson, jobService, resultService, ruleService, ruleResultService, 
				statusCodeService, historicalNameService, historicalDataService, contentPathService, 
				contentService, contentResultService, lastRunService);
		
		return "Record Processing Complete";
	}
}
