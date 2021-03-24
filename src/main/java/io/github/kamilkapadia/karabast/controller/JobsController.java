package io.github.kamilkapadia.karabast.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/jobs/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("jobId") int theId, Model theModel) {
		
		// get the job from the service
		Job theJob = jobService.findById(theId);
		
		// set job as a model attribute to pre-populate the form
		theModel.addAttribute("job", theJob);
		
		// send over to our form
		return "/update-job";
	}
	
	@GetMapping("/jobs/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create the model attribute to bind form data
		Job theJob = new Job();
		theJob.setCreationTime(new Timestamp(System.currentTimeMillis()));
		theJob.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		theJob.setActive(true);
				
		theModel.addAttribute("job", theJob);
				
		return "/update-job";
	}
	
	@PostMapping("/jobs/save")
	public String saveJob(@ModelAttribute("job") Job theJob) {
		
		// save the job
		jobService.save(theJob);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/jobs";
	}
	
	@GetMapping("/jobs/delete")
	public String deleteJob(@RequestParam("jobId") int theId) {
		
		jobService.deleteById(theId);

		// send over to our form
		return "redirect:/jobs";
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
