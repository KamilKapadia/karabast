package io.github.kamilkapadia.karabast.components.setup.job;

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
import io.github.kamilkapadia.karabast.components.setup.action.Action;
import io.github.kamilkapadia.karabast.components.setup.action.ActionService;
import io.github.kamilkapadia.karabast.components.setup.contentpath.ContentPathService;
import io.github.kamilkapadia.karabast.components.setup.historicalname.HistoricalNameService;
import io.github.kamilkapadia.karabast.components.setup.rule.RuleService;

@Controller
public class JobController {
	
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
		return "/jobs/update-job";
	}
	
	@GetMapping("/jobs/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create the model attribute to bind form data
		Job theJob = new Job();
		theJob.setCreationTime(new Timestamp(System.currentTimeMillis()));
		theJob.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		theJob.setActive(true);
				
		theModel.addAttribute("job", theJob);
				
		return "/jobs/update-job";
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
	
	
	
	
	@GetMapping("/jobs/job-details")
	public String jobDetails(@RequestParam("jobId") int theId, Model theModel) {
		theModel.addAttribute("jobs", jobService.findById(theId));
		theModel.addAttribute("rules", ruleService.findByJobId(theId));
		theModel.addAttribute("historical", historicalNameService.findByJobId(theId));
		theModel.addAttribute("content", contentPathService.findByJobId(theId));
		
		List<StatusCode> statusCodes = statusCodeService.findAll();
		List<ActionCode> actionCodes = actionCodeService.findAll();
		List<Action> actions = actionService.findByJobId(theId);
		
		for (Action action : actions) {
			List<StatusCode> theStatusCodes = new ArrayList<StatusCode>();
			
			int statusMask = action.getStatusMask();
			
			for (StatusCode statusCode : statusCodes) {
				if ( (statusMask & statusCode.getId()) > 0) {
					theStatusCodes.add(statusCode);
				}
			}
			
			action.setStatuses(theStatusCodes);
			
			List<ActionCode> theActionCodes = new ArrayList<ActionCode>();
			
			int actionMask = action.getActionMask();
			
			for (ActionCode actionCode : actionCodes) {
				if ( (actionMask & actionCode.getId()) > 0) {
					theActionCodes.add(actionCode);
				}
			}
			
			action.setActions(theActionCodes);
		}
		
		theModel.addAttribute("actions", actions);
		
		return "jobs/job-details";
	}
}
