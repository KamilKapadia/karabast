package io.github.kamilkapadia.karabast.components.setup.historicalname;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kamilkapadia.karabast.components.lookup.typecode.TypeCodeService;
import io.github.kamilkapadia.karabast.components.setup.job.Job;
import io.github.kamilkapadia.karabast.components.setup.job.JobService;

@Controller
public class HistoricalNameController {

	@Autowired
	private HistoricalNameService historicalNameService;
	
	@Autowired
	private TypeCodeService typeCodeService;
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/historical/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("historicalNameId") int theId, Model theModel) {
		
		// get the job from the service
		HistoricalName theHistoricalName = historicalNameService.findById(theId);
		
		// set job as a model attribute to pre-populate the form
		theModel.addAttribute("historical", theHistoricalName);
		theModel.addAttribute("typeCodes", typeCodeService.findAll());
		
		// send over to our form
		return "historical/update-historical-name";
	}
	
	@GetMapping("/historical/showFormForAdd")
	public String showFormForAdd(@RequestParam("jobId") int jobId, Model theModel) {
		
		// create the model attribute to bind form data
		HistoricalName theHistoricalName = new HistoricalName();
		theHistoricalName.setCreationTime(new Timestamp(System.currentTimeMillis()));
		theHistoricalName.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		theHistoricalName.setActive(true);
		
		Job job = jobService.findById(jobId);
		
		theHistoricalName.setJob(job);
				
		theModel.addAttribute("historical", theHistoricalName);
		theModel.addAttribute("typeCodes", typeCodeService.findAll());
		
		return "/historical/update-historical-name";
	}
	
	@PostMapping("/historical/save")
	public String save(@ModelAttribute("historical") HistoricalName theHistoricalName) {
		
		// save the job
		historicalNameService.save(theHistoricalName);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/jobs/job-details?jobId=" + theHistoricalName.getJob().getId();
	}
	
	@GetMapping("/historical/delete")
	public String delete(@RequestParam("historicalNameId") int theId) {
		
		HistoricalName theHistoricalName = historicalNameService.findById(theId);
		historicalNameService.deleteById(theId);

		// send over to our form
		return "redirect:/jobs/job-details?jobId=" + theHistoricalName.getJob().getId();
	}
}
