package io.github.kamilkapadia.karabast.components.setup.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kamilkapadia.karabast.components.lookup.actioncode.ActionCode;
import io.github.kamilkapadia.karabast.components.lookup.actioncode.ActionCodeService;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCode;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCodeService;
import io.github.kamilkapadia.karabast.components.setup.job.Job;
import io.github.kamilkapadia.karabast.components.setup.job.JobService;

@Controller
public class ActionController {
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ActionCodeService actionCodeService;
	
	@Autowired
	private StatusCodeService statusCodeService;
	
	
	
	@GetMapping("/actions/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("actionId") int theId, Model theModel) {
		
		Action theAction = actionService.findById(theId);
		List<StatusCode> allStatusCodes = statusCodeService.findAll();
		List<ActionCode> allActionCodes = actionCodeService.findAll();
		
		int statusMask = theAction.getStatusMask();
		
		List<StatusCode> statusCodes = new ArrayList<StatusCode>();
		
		for (StatusCode statusCode : allStatusCodes) {
			if ( (statusMask & statusCode.getId()) > 0) {
				//theAction.getStatuses().add(statusCode);
				statusCodes.add(statusCode);
			}
		}
			
		theAction.setStatuses(statusCodes);
				
		int actionMask = theAction.getActionMask();
		
		List<ActionCode> actionCodes = new ArrayList<ActionCode>();
		
		for (ActionCode actionCode : allActionCodes) {
			if ( (actionMask & actionCode.getId()) > 0) {
				//theAction.getActions().add(actionCode);
				actionCodes.add(actionCode);
			}
		}
			
		theAction.setActions(actionCodes);
		
		theModel.addAttribute("action", theAction);
		theModel.addAttribute("selectedStatuses", theAction.getStatuses());
		theModel.addAttribute("allStatuses", allStatusCodes);
		theModel.addAttribute("selectedActions", theAction.getActions());
		theModel.addAttribute("allActions", allActionCodes);
		
		
		// send over to our form
		return "actions/update-action";
	}
	
	@GetMapping("/actions/showFormForAdd")
	public String showFormForAdd(@RequestParam("jobId") int jobId, Model theModel) {
		
		// create the model attribute to bind form data
		Action theAction = new Action();
		theAction.setCreationTime(new Timestamp(System.currentTimeMillis()));
		theAction.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		theAction.setActive(true);
		
		Job job = jobService.findById(jobId);
		
		theAction.setJob(job);
				
		theModel.addAttribute("action", theAction);
		theModel.addAttribute("selectedStatuses", theAction.getStatuses());
		theModel.addAttribute("allStatuses", statusCodeService.findAll());
		theModel.addAttribute("selectedActions", theAction.getActions());
		theModel.addAttribute("allActions", actionCodeService.findAll());
		
		return "/actions/update-action";
	}
	
	@PostMapping("/actions/save")
	public String save(@ModelAttribute("action") Action theAction) {
		
		// save the job
		actionService.save(theAction);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/jobs/job-details?jobId=" + theAction.getJob().getId();
	}
	
	@GetMapping("/actions/delete")
	public String delete(@RequestParam("actionId") int theId) {
		
		Action theAction = actionService.findById(theId);
		actionService.deleteById(theId);

		// send over to our form
		return "redirect:/jobs/job-details?jobId=" + theAction.getJob().getId();
	}
}
