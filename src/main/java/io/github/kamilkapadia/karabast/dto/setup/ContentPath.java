package io.github.kamilkapadia.karabast.dto.setup;

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

@Entity
@Table(name = "content_path")
public class ContentPath {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="job_id")
	private Job job;
	
	@Column(name = "name_path")
	private String namePath;
	
	@Column(name = "mime_type_path")
	private String mimeTypePath;
		
	@Column(name = "content_path")
	private String contentPath;
	
	@Column(name = "content_disk_dir")
	private String diskDir;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "creation_time")
	private Timestamp creationTime;
	
	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;

	public ContentPath() {
		
	}
	
	public ContentPath(String namePath, String mimeTypePath, String contentPath, String diskDir, boolean active) {
		
		this.namePath = namePath;
		this.mimeTypePath = mimeTypePath;
		this.contentPath = contentPath;
		this.diskDir = diskDir;
		this.active = active;
		this.creationTime = new Timestamp(System.currentTimeMillis());
		this.lastUpdateTime = new Timestamp(System.currentTimeMillis());
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

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public String getMimeTypePath() {
		return mimeTypePath;
	}

	public void setMimeTypePath(String mimeTypePath) {
		this.mimeTypePath = mimeTypePath;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getDiskDir() {
		return diskDir;
	}

	public void setDiskDir(String diskDir) {
		this.diskDir = diskDir;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreationTime() {
		return (Timestamp)creationTime.clone();
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = (Timestamp)creationTime.clone();
	}

	public Timestamp getLastUpdateTime() {
		return (Timestamp)lastUpdateTime.clone();
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = (Timestamp)lastUpdateTime.clone();
	}

	@Override
	public String toString() {
		return "ContentPath [id=" + id + ", job_id=" + job.getId() + ", namePath=" + namePath + ", "
				+ "mimeTypePath=" + mimeTypePath + ", contentPath=" + contentPath + ", diskDir=" + diskDir 
				+ ", active=" + active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}

