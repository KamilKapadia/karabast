package io.github.kamilkapadia.karabast.dto.process;

import java.util.ArrayList;
import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.HistoricalData;
import io.github.kamilkapadia.karabast.dto.data.LastRun;
import io.github.kamilkapadia.karabast.dto.data.Result;
import io.github.kamilkapadia.karabast.dto.data.RuleResult;
import io.github.kamilkapadia.karabast.dto.setup.Action;
import io.github.kamilkapadia.karabast.dto.setup.ContentPath;
import io.github.kamilkapadia.karabast.dto.setup.HistoricalName;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.dto.setup.Rule;

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
