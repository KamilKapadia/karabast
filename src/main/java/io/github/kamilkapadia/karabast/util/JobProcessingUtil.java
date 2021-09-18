package io.github.kamilkapadia.karabast.util;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.setup.JobService;

public class JobProcessingUtil {

	private static final String JOB_NAME_PATH = "$.data.job.name"; 
	
	public static Job getJob(JobService jobService, Object document, String rawJson) {
		String jobName = JSONPathUtil.getString(document, JOB_NAME_PATH);
		
		List<Job> jobs =  jobService.findByName(jobName);

		if (jobs.size() == 1) {
			if (jobs.get(0).isActive()) {
				return jobs.get(0);
			}
		} else {
			// TODO ERROR
		}
		
		return null;
	}
}
