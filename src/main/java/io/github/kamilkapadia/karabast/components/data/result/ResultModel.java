package io.github.kamilkapadia.karabast.components.data.result;

import java.sql.Timestamp;

import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCode;

public class ResultModel {
	private long id;
	private long jobId;
	private String job;
	private long run;
	private StatusCode statusCode;
	private double execTime;
	private Timestamp time;
	
	
	public ResultModel(Result result, String baseUrlString) {
		setId(result.getId());
		setJobId(result.getJob().getId());
		setJob(baseUrlString + "/api/jobs/" + getJobId());
		setRun(result.getRun());
		setStatusCode(result.getStatusCode());
		setExecTime(result.getExecTime());
		setTime(result.getTime());
	}	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public long getRun() {
		return run;
	}
	public void setRun(long run) {
		this.run = run;
	}
	public StatusCode getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}
	public double getExecTime() {
		return execTime;
	}
	public void setExecTime(double execTime) {
		this.execTime = execTime;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "ResultModel [id=" + id + ", jobId=" + jobId + ", job=" + job + ", run=" + run + ", statusCode="
				+ statusCode + ", execTime=" + execTime + ", time=" + time + "]";
	}
	
}
