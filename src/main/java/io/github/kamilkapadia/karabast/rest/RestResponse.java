package io.github.kamilkapadia.karabast.rest;

public class RestResponse {
	private boolean success = false;
	private int statusCode;
	private String message;
	private RestRequest request;
	private int recordCount = 0;
	private Object data;

	public RestResponse(RestRequest request) {
		this.request = request;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}




	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public RestRequest getRequest() {
		return request;
	}


	public void setRequest(RestRequest request) {
		this.request = request;
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


	@Override
	public String toString() {
		return "RestResponse [success=" + success + ", statusCode=" + statusCode + ", message=" + message + ", request="
				+ request + ", recordCount=" + recordCount + ", data=" + data + "]";
	}


	
}
