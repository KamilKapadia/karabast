package io.github.kamilkapadia.karabast.util;

import io.github.kamilkapadia.karabast.dto.data.LastRun;
import io.github.kamilkapadia.karabast.dto.data.Result;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.data.LastRunService;

public class LastRunProcessingUtil {

	public static void persist(Job job, Result result, LastRunService lastRunService) {
		LastRun lastRun = lastRunService.findByJobId(job.getId());
		
		if (lastRun == null) {
			lastRun = new LastRun();
			lastRun.setJob(job);
		}
		
		int statusCode = result.getStatusCode().getId();
		
		if (statusCode == 1) {
			lastRun.setUnknown(result);	
		} else if (statusCode == 2) {
			lastRun.setRunning(result);
		} else if (statusCode == 4) {
			lastRun.setSlower(result);
		} else if (statusCode == 8) {
			lastRun.setWarning(result);
		} else if (statusCode == 16) {
			lastRun.setStalled(result);
		} else { // 32
			lastRun.setDown(result);
		}
		
		lastRunService.save(lastRun);
	}
}
