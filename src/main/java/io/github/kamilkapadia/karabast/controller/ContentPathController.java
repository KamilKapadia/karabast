package io.github.kamilkapadia.karabast.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kamilkapadia.karabast.dto.setup.ContentPath;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.setup.ContentPathService;
import io.github.kamilkapadia.karabast.service.setup.JobService;

@Controller
public class ContentPathController {
	@Autowired
	private ContentPathService contentPathService;
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/content/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("contentPathId") int theId, Model theModel) {
		
		// get the job from the service
		ContentPath theContentPath = contentPathService.findById(theId);
		
		// set job as a model attribute to pre-populate the form
		theModel.addAttribute("content", theContentPath);
		
		// send over to our form
		return "content/update-content-path";
	}
	
	@GetMapping("/content/showFormForAdd")
	public String showFormForAdd(@RequestParam("jobId") int jobId, Model theModel) {
		
		// create the model attribute to bind form data
		ContentPath theContentPath = new ContentPath();
		theContentPath.setCreationTime(new Timestamp(System.currentTimeMillis()));
		theContentPath.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		theContentPath.setActive(true);
		
		Job job = jobService.findById(jobId);
		
		theContentPath.setJob(job);
				
		theModel.addAttribute("content", theContentPath);
		
		return "/content/update-content-path";
	}
	
	@PostMapping("/content/save")
	public String save(@ModelAttribute("content") ContentPath theContentPath) {
		
		// save the job
		contentPathService.save(theContentPath);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/jobs/job-details?jobId=" + theContentPath.getJob().getId();
	}
	
	@GetMapping("/content/delete")
	public String delete(@RequestParam("contentPathId") int theId) {
		
		ContentPath theContentPath = contentPathService.findById(theId);
		contentPathService.deleteById(theId);

		// send over to our form
		return "redirect:/jobs/job-details?jobId=" + theContentPath.getJob().getId();
	}
}
