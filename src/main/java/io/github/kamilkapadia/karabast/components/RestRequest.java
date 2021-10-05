package io.github.kamilkapadia.karabast.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RestRequest {
	private String id;
	private String method;
	private String url;
	private Date timestamp = new Date();
	
	public RestRequest(String method, ServletUriComponentsBuilder servletUriComponentsBuilder) {
		this.id = UUID.randomUUID().toString();
		this.method = method;
		this.url = servletUriComponentsBuilder.toUriString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "RestRequest [id=" + id + ", method=" + method + ", url=" + url + ", timestamp=" + timestamp + "]";
	}
	
	
}
