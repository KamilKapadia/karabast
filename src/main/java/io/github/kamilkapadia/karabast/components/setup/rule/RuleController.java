package io.github.kamilkapadia.karabast.components.setup.rule;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kamilkapadia.karabast.components.lookup.rulecode.RuleCodeService;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCodeService;
import io.github.kamilkapadia.karabast.components.lookup.typecode.TypeCodeService;
import io.github.kamilkapadia.karabast.components.setup.job.Job;
import io.github.kamilkapadia.karabast.components.setup.job.JobService;

@Controller
public class RuleController {

	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private TypeCodeService typeCodeService;
	
	@Autowired
	private RuleCodeService ruleCodeService;
	
	@Autowired
	private StatusCodeService statusCodeService;
	
	@Autowired
	private JobService jobService;
	
	
	
	@GetMapping("/rules/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("ruleId") int theId, Model theModel) {
		
		// get the job from the service
		Rule theRule = ruleService.findById(theId);
		
		// set job as a model attribute to pre-populate the form
		theModel.addAttribute("rule", theRule);
		theModel.addAttribute("typeCodes", typeCodeService.findAll());
		theModel.addAttribute("ruleCodes", ruleCodeService.findAll());
		theModel.addAttribute("statusCodes", statusCodeService.findAll());
		
		// send over to our form
		return "rules//update-rule";
	}
	
	@GetMapping("/rules/showFormForAdd")
	public String showFormForAdd(@RequestParam("jobId") int jobId, Model theModel) {
		
		// create the model attribute to bind form data
		Rule theRule = new Rule();
		theRule.setCreationTime(new Timestamp(System.currentTimeMillis()));
		theRule.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		theRule.setActive(true);
		
		Job job = jobService.findById(jobId);
		
		theRule.setJob(job);
				
		theModel.addAttribute("rule", theRule);
		theModel.addAttribute("typeCodes", typeCodeService.findAll());
		theModel.addAttribute("ruleCodes", ruleCodeService.findAll());
		theModel.addAttribute("statusCodes", statusCodeService.findAll());
		
		return "/rules/update-rule";
	}
	
	@PostMapping("/rules/save")
	public String save(@ModelAttribute("rule") Rule theRule) {
		
		// save the job
		ruleService.save(theRule);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/jobs/job-details?jobId=" + theRule.getJob().getId();
	}
	
	@GetMapping("/rules/delete")
	public String delete(@RequestParam("ruleId") int theId) {
		
		Rule theRule = ruleService.findById(theId);
		ruleService.deleteById(theId);

		// send over to our form
		return "redirect:/jobs/job-details?jobId=" + theRule.getJob().getId();
	}
}
