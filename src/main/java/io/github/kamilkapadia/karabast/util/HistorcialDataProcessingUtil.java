package io.github.kamilkapadia.karabast.util;

import java.util.List;

import io.github.kamilkapadia.karabast.components.data.historicaldata.HistoricalData;
import io.github.kamilkapadia.karabast.components.data.historicaldata.HistoricalDataService;
import io.github.kamilkapadia.karabast.components.data.result.Result;
import io.github.kamilkapadia.karabast.components.setup.historicalname.HistoricalName;
import io.github.kamilkapadia.karabast.components.setup.historicalname.HistoricalNameService;
import io.github.kamilkapadia.karabast.components.setup.job.Job;

public class HistorcialDataProcessingUtil {

	public static void persist(Object document, Job job, Result result, HistoricalNameService historicalNameService, 
			HistoricalDataService historicalDataService) {
		
		List<HistoricalName> historicalNames = historicalNameService.findByJobId(job.getId());
		
		for (HistoricalName historicalName : historicalNames) {
			if (historicalName.isActive()) {
			
				HistoricalData historicalData = new HistoricalData();
			
				historicalData.setResult(result);
				historicalData.setHistoricalName(historicalName);
			
				int typeCode = historicalName.getTypeCode().getId();
				
				if (typeCode == 1) {
					historicalData.setLongValue(JSONPathUtil.getLong(document, historicalName.getValuePath()));
				} else if (typeCode == 2) {
					historicalData.setDoubleValue(JSONPathUtil.getDouble(document, historicalName.getValuePath()));
				} else if (typeCode == 4) {
					historicalData.setStringValue(JSONPathUtil.getString(document, historicalName.getValuePath()));
				} else { // 8
					historicalData.setBooleanValue(JSONPathUtil.getBoolean(document, historicalName.getValuePath()));
				}

				historicalDataService.save(historicalData);
			}
		}
		
	}

}
