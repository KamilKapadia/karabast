package io.github.kamilkapadia.karabast.util;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import io.github.kamilkapadia.karabast.dto.data.Result;
import io.github.kamilkapadia.karabast.dto.data.RuleResult;
import io.github.kamilkapadia.karabast.dto.lookup.RuleCode;
import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;
import io.github.kamilkapadia.karabast.dto.lookup.TypeCode;
import io.github.kamilkapadia.karabast.dto.setup.HistoricalName;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.dto.setup.Rule;
import io.github.kamilkapadia.karabast.service.data.ResultService;
import io.github.kamilkapadia.karabast.service.data.RuleResultService;
import io.github.kamilkapadia.karabast.service.lookup.StatusCodeService;
import io.github.kamilkapadia.karabast.service.setup.HistoricalNameService;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.service.setup.RuleService;


public class RecordProcessingUtil {

	private static final String EXECUTION_TIME = "$.data.results.executionTime";
	
	public static void processRecord(String rawJson, JobService jobService, ResultService resultService, RuleService ruleService, 
			RuleResultService ruleResultService, StatusCodeService statusCodeService, HistoricalNameService historicalNameService) {
		
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(rawJson);
		
		Job job = JobProcessingUtil.getJob(jobService, document, rawJson);
		Object executionTime = JsonPath.read(document, EXECUTION_TIME);
		
		if (job != null ) {
			List<RuleResult> ruleResults = RulesProcessingUtil.validate(ruleService, document, job);
			StatusCode statusCode = RulesProcessingUtil.getStatusCode(statusCodeService, ruleResults);
			
			Result result = new Result();
			
			result.setExecTime(Double.parseDouble(executionTime.toString()));
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
			
			// 2. rules result
			RulesProcessingUtil.persist(ruleResultService, result, ruleResults);
			
			// 3. historical data
			
			// 4/5. content (and content_result)
			
			// 6. last_run
			
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
