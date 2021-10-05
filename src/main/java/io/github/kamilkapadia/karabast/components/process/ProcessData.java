package io.github.kamilkapadia.karabast.components.process;

import java.util.ArrayList;
import java.util.List;

import io.github.kamilkapadia.karabast.components.data.historicaldata.HistoricalData;
import io.github.kamilkapadia.karabast.components.data.lastrun.LastRun;
import io.github.kamilkapadia.karabast.components.data.result.Result;
import io.github.kamilkapadia.karabast.components.data.ruleresult.RuleResult;
import io.github.kamilkapadia.karabast.components.setup.action.Action;
import io.github.kamilkapadia.karabast.components.setup.contentpath.ContentPath;
import io.github.kamilkapadia.karabast.components.setup.historicalname.HistoricalName;
import io.github.kamilkapadia.karabast.components.setup.job.Job;
import io.github.kamilkapadia.karabast.components.setup.rule.Rule;

public class ProcessData {

	private String jobName;
	private double execTime;
	
	private Job job;
	private List<HistoricalName> historicalName = new ArrayList<HistoricalName>();
	private List<ContentPath> contentPaths = new ArrayList<ContentPath>();
	private List<Rule> rules = new ArrayList<Rule>();
	private List<Action> actions = new ArrayList<Action>();
	
	private List<RuleResult> ruleResults = new ArrayList<RuleResult>();
	private Result result;
	private List<HistoricalData> historicalData = new ArrayList<HistoricalData>();
	private LastRun lastRun;
	
	// content
	// content_result
	
	
	
}
