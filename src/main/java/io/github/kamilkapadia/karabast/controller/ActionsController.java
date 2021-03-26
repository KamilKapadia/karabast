package io.github.kamilkapadia.karabast.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kamilkapadia.karabast.dto.setup.Action;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.dto.setup.Rule;
import io.github.kamilkapadia.karabast.service.lookup.RuleCodeService;
import io.github.kamilkapadia.karabast.service.lookup.StatusCodeService;
import io.github.kamilkapadia.karabast.service.lookup.TypeCodeService;
import io.github.kamilkapadia.karabast.service.setup.ActionService;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.service.setup.RuleService;

@Controller
public class ActionsController {
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private JobService jobService;
	
	
	
	@GetMapping("/actions/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("actionId") int theId, Model theModel) {
		
		// get the job from the service
		Action theAction = actionService.findById(theId);
		
		// set job as a model attribute to pre-populate the form
		theModel.addAttribute("action", theAction);
		//theModel.addAttribute("typeCodes", typeCodeService.findAll());
		//theModel.addAttribute("ruleCodes", ruleCodeService.findAll());
		//theModel.addAttribute("statusCodes", statusCodeService.findAll());
		
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
//		theModel.addAttribute("typeCodes", typeCodeService.findAll());
//		theModel.addAttribute("ruleCodes", ruleCodeService.findAll());
//		theModel.addAttribute("statusCodes", statusCodeService.findAll());
		
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
