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

import io.github.kamilkapadia.karabast.dto.lookup.TypeCode;


@Entity
@Table(name = "historical_name")
public class HistoricalName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value_path")
	private String valuePath;
		
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "creation_time")
	private Timestamp creationTime;
	
	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="job_id")
	private Job job;
	
	@ManyToOne
    @JoinColumn(name="type_code_id")
	private TypeCode typeCode;
	
//	@Column(name = "type_code_id")
//	private int typeCodeId;
	
	public HistoricalName() {
		
	}

	public HistoricalName(String name, String valuePath, TypeCode typeCode, boolean active) {
		
		this.name = name;
		this.valuePath = valuePath;
		this.typeCode = typeCode;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValuePath() {
		return valuePath;
	}

	public void setValuePath(String valuePath) {
		this.valuePath = valuePath;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public TypeCode getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(TypeCode typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public String toString() {
		return "HistoricalName [id=" + id + ", name=" + name + ", valuePath=" + valuePath + ", active=" + active
				+ ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime + ", job=" + job
				+ ", typeCode=" + typeCode + "]";
	}
}
