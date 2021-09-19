package io.github.kamilkapadia.karabast.rest;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RestResponse {
	private int status;
	private String message;
	private Date timestamp = new Date();
	private String requestId;
	private String method;
	private String requestUrl;
	private Object requestBody;
	private int recordCount = 0;
	private Object data;
	
	
	public RestResponse(ServletUriComponentsBuilder servletUriComponentsBuilder) {
		this.requestId = UUID.randomUUID().toString();
		this.requestUrl = servletUriComponentsBuilder.toUriString();
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getRequestUrl() {
		return requestUrl;
	}


	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}


	public Object getRequestBody() {
		return requestBody;
	}


	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}


	public int getRecordCount() {
		return recordCount;
	}


	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
	
}
