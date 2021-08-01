package io.github.kamilkapadia.karabast.util;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.HistoricalData;
import io.github.kamilkapadia.karabast.dto.data.Result;
import io.github.kamilkapadia.karabast.dto.setup.HistoricalName;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.data.HistoricalDataService;
import io.github.kamilkapadia.karabast.service.setup.HistoricalNameService;

public class HistorcialDataProcessingUtil {

	public static void persist(Object document, Job job, Result result, HistoricalNameService historicalNameService, 
			HistoricalDataService historicalDataService) {
		
		List<HistoricalName> historicalNames = historicalNameService.findByJobId(job.getId());
		
		for (HistoricalName historicalName : historicalNames) {
			if (historicalName.isActive()) {
			
				HistoricalData historicalData = new HistoricalData();
			
				historicalData.setResult(result);
				historicalData.setHistoricalName(historicalName);
			
				if (historicalName.getTypeCode().getId() == 1) {
					historicalData.setLongValue(JSONPathUtil.getLong(document, historicalName.getValuePath()));
				} else if (historicalName.getTypeCode().getId() == 2) {
					historicalData.setDoubleValue(JSONPathUtil.getDouble(document, historicalName.getValuePath()));
				} else if (historicalName.getTypeCode().getId() == 4) {
					historicalData.setStringValue(JSONPathUtil.getString(document, historicalName.getValuePath()));
				} else { // 8
					historicalData.setBooleanValue(JSONPathUtil.getBoolean(document, historicalName.getValuePath()));
				}

				historicalDataService.save(historicalData);
			}
		}
		
	}

}
