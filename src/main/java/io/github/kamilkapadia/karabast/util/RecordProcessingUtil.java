package io.github.kamilkapadia.karabast.util;

import java.sql.Timestamp;
import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.Result;
import io.github.kamilkapadia.karabast.dto.data.RuleResult;
import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.data.ContentResultService;
import io.github.kamilkapadia.karabast.service.data.ContentService;
import io.github.kamilkapadia.karabast.service.data.HistoricalDataService;
import io.github.kamilkapadia.karabast.service.data.LastRunService;
import io.github.kamilkapadia.karabast.service.data.ResultService;
import io.github.kamilkapadia.karabast.service.data.RuleResultService;
import io.github.kamilkapadia.karabast.service.lookup.StatusCodeService;
import io.github.kamilkapadia.karabast.service.setup.ContentPathService;
import io.github.kamilkapadia.karabast.service.setup.HistoricalNameService;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.service.setup.RuleService;


public class RecordProcessingUtil {

	private static final String EXECUTION_TIME = "$.data.results.executionTime";
	
	public static void processRecord(String rawJson, JobService jobService, ResultService resultService, RuleService ruleService, 
			RuleResultService ruleResultService, StatusCodeService statusCodeService, 
			HistoricalNameService historicalNameService, HistoricalDataService historicalDataService, 
			ContentPathService contentPathService, ContentService contentService, 
			ContentResultService contentResultService, LastRunService lastRunService) {
		
		Object document = JSONPathUtil.getJSONObject(rawJson); //Configuration.defaultConfiguration().jsonProvider().parse(rawJson);
		
		Job job = JobProcessingUtil.getJob(jobService, document, rawJson);
		double executionTime = JSONPathUtil.getDouble(document, EXECUTION_TIME);
		
		if (job != null ) {
			List<RuleResult> ruleResults = RulesProcessingUtil.validate(ruleService, document, job);
			StatusCode statusCode = RulesProcessingUtil.getStatusCode(statusCodeService, ruleResults);
			
			Result result = new Result();
			
			result.setExecTime(executionTime);
			result.setJob(job);
			result.setStatusCode(statusCode);
			result.setTime(new Timestamp(System.currentTimeMillis()));
			
			List<Result> results = resultService.findByJobId(job.getId());
			
			if (results.size() == 0) {
				result.setRun(1);
			} else {
				result.setRun(results.get(0).getRun() + 1);
			}
			
			// 1. result
			resultService.save(result);
			
			// TODO - needs work - rule validation
			// 2. rules result
			RulesProcessingUtil.persist(ruleResultService, result, ruleResults);
			
			// 3. historical data
			HistorcialDataProcessingUtil.persist(document, job, result, historicalNameService, historicalDataService);
			
			// 4/5. content (and content_result)
			ContentProcessingUtil.persist(job, result, document, contentPathService, contentService, contentResultService);
			
			// 6. last_run
			LastRunProcessingUtil.persist(job, result, lastRunService);
		}
		
			
			
//			// Store the raw values for historical data to trace
//			List<HistoricalName> historicalNames = historicalNameService.findByJobId(jobID);
//			
//			for (HistoricalName historicalName : historicalNames) {
//				if (historicalName.isActive()) {
//				
//					long historicalNameID = historicalName.getId();
//					String theHistoricalName = historicalName.getName();
//					String historicalValuePath = historicalName.getValuePath();
//					TypeCode historivalTypeCode = historicalName.getTypeCode();
//					
//					Object historicalValue = JsonPath.read(document, historicalValuePath);
//					
//					System.err.println(theHistoricalName + "(" + historicalNameID + ")" + " = " + historicalValue + " (" + historivalTypeCode + ")");
//				}
//				
//			}
			
			
			// Store the raw data for the contents 

	}

}
