package io.github.kamilkapadia.karabast.dto.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.github.kamilkapadia.karabast.dto.setup.Job;


@Entity
@Table(name = "last_run")
public class LastRun {
	/*
	id LONG PRIMARY KEY auto_increment NOT NULL,
	job_id LONG NOT NULL, 
	unknown LONG, 
	running LONG, 
	SLOWER LONG, 
	WARNING LONG, 
	STALLED LONG, 
	DOWN long
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="job_id")
	private Job job;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="unknown")
	private Result unknown;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="running")
	private Result running;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="slower")
	private Result slower;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="warning")
	private Result warning;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="stalled")
	private Result stalled;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="down")
	private Result down;

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
	
	public Result getUnknown() {
		return unknown;
	}

	public void setUnknown(Result unknown) {
		this.unknown = unknown;
	}

	public Result getRunning() {
		return running;
	}

	public void setRunning(Result running) {
		this.running = running;
	}

	public Result getSlower() {
		return slower;
	}

	public void setSlower(Result slower) {
		this.slower = slower;
	}

	public Result getWarning() {
		return warning;
	}

	public void setWarning(Result warning) {
		this.warning = warning;
	}

	public Result getStalled() {
		return stalled;
	}

	public void setStalled(Result stalled) {
		this.stalled = stalled;
	}

	public Result getDown() {
		return down;
	}

	public void setDown(Result down) {
		this.down = down;
	}	
}

