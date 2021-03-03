package io.github.kamilkapadia.karabast.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kamilkapadia.karabast.dto.ActionDTO;
import io.github.kamilkapadia.karabast.dto.lookup.ActionCode;
import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;
import io.github.kamilkapadia.karabast.dto.setup.Action;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.setup.ContentPathService;
import io.github.kamilkapadia.karabast.service.lookup.ActionCodeService;
import io.github.kamilkapadia.karabast.service.lookup.StatusCodeService;
import io.github.kamilkapadia.karabast.service.setup.ActionService;
import io.github.kamilkapadia.karabast.service.setup.HistoricalNameService;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.service.setup.RuleService;

@Controller
public class JobsController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private HistoricalNameService historicalNameService;
	
	@Autowired
	private ContentPathService contentPathService;
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private StatusCodeService statusCodeService;
	
	@Autowired
	private ActionCodeService actionCodeService;
	
	@GetMapping("/jobs")
	public String jobs(Model theModel) {
		List<Job> jobs = jobService.findAll();
		theModel.addAttribute("jobs", jobs);
		
		return "jobs";
	}
	
	@GetMapping("/job-details")
	public String jobDetails(@RequestParam("jobId") int theId, Model theModel) {
		theModel.addAttribute("jobs", jobService.findById(theId));
		theModel.addAttribute("rules", ruleService.findByJobId(theId));
		theModel.addAttribute("historical", historicalNameService.findByJobId(theId));
		theModel.addAttribute("content", contentPathService.findByJobId(theId));
		
		Map<Integer, String> statusCodeMap = new HashMap<Integer, String>();
		List<StatusCode> statusCodes = statusCodeService.findAll();
		
		for (StatusCode statusCode : statusCodes) {
			statusCodeMap.put(statusCode.getId(), statusCode.getName());
		}
		
		Map<Integer, String> actionCodeMap = new HashMap<Integer, String>();
		List<ActionCode> actionCodes = actionCodeService.findAll();
		
		for (ActionCode actionCode : actionCodes) {
			actionCodeMap.put(actionCode.getId(), actionCode.getName());
		}
		
		
		
		List<ActionDTO> actionDTOs = new ArrayList<ActionDTO>();
		
		List<Action> actions = actionService.findByJobId(theId);
		
		for (Action action : actions) {
			ActionDTO actionDTO = new ActionDTO();
			
			actionDTO.setActive(action.isActive());
			
			int statusMask = action.getTypeMask();
			
			for (StatusCode statusCode : statusCodes) {
				if ( (statusMask & statusCode.getId()) > 0) {
					actionDTO.getStatuses().add(statusCodeMap.get(statusCode.getId()));
				}
			}
			
			int actionMask = action.getActionMask();
			
			for (ActionCode actionCode : actionCodes) {
				if ( (actionMask & actionCode.getId()) > 0) {
					actionDTO.getActions().add(actionCodeMap.get(actionCode.getId()));
				}
			}
			
			
			actionDTOs.add(actionDTO);
		}
		
		theModel.addAttribute("actions", actionDTOs);
		
		
		
		return "job-details";
	}
}
