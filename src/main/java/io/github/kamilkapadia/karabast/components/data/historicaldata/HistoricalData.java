package io.github.kamilkapadia.karabast.components.data.historicaldata;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.github.kamilkapadia.karabast.components.data.result.Result;
import io.github.kamilkapadia.karabast.components.setup.historicalname.HistoricalName;


@Entity
@Table(name = "historical_data")
public class HistoricalData {
	/*
	id LONG PRIMARY KEY auto_increment NOT NULL,
	historical_name_id LONG NOT NULL, 
	result_id LONG NOT NULL,
	long_value LONG,
	double_value DOUBLE,
	string_value VARCHAR(256),
	boolean_value BOOL
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "historical_name_id")
	private HistoricalName historicalName;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="result_id")
	private Result result;
	
	@Column(name = "long_value")
	private Long longValue;
	
	@Column(name = "double_value")
	private Double doubleValue;
	
	@Column(name = "string_value")
	private String stringValue;
	
	@Column(name = "boolean_value")
	private Boolean booleanValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HistoricalName getHistoricalName() {
		return historicalName;
	}

	public void setHistoricalName(HistoricalName historicalName) {
		this.historicalName = historicalName;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
}

