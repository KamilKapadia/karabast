package io.github.kamilkapadia.karabast.components.data.result;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCode;
import io.github.kamilkapadia.karabast.components.setup.job.Job;


@Entity
@Table(name = "result")
public class Result {

	/*
	id LONG PRIMARY KEY auto_increment NOT NULL,
	job_id LONG NOT NULL, 
	run LONG NOT NULL, 
	status_code INT NOT NULL, 
	exec_time DOUBLE NOT NULL, 
	TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);
	 */
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="job_id")
	private Job job; 
	
	@Column(name = "run")
	private long run;
		
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="status_type_code")
	private StatusCode statusCode; 
	
	@Column(name = "exec_time")
	private double execTime;
	
	@Column(name = "time")
	private Timestamp time;
	
	public Result() {
		
	}
	
	public Result(long id, Job job, long run, StatusCode statusCode, double execTime, Timestamp time) {
		this.id = id;
		this.job = job;
		this.run = run;
		this.statusCode = statusCode;
		this.execTime = execTime;
		this.time = (Timestamp)time.clone();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
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
		return (Timestamp)time.clone();
	}

	public void setTime(Timestamp time) {
		this.time = (Timestamp)time.clone();
	}
		
	@Override
	public String toString() {
		return "Result [id=" + id + ", job=" + job + ", run=" + run + ", statusCode=" + statusCode + ", execTime="
				+ execTime + ", time=" + time + "]";
	}
}

