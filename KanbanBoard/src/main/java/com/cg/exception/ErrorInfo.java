package com.cg.exception;


public class ErrorInfo {
	
	private String url;  // URL associated with the error
	private String message;  // Error message
	private String dateTime;  // Date and time when the error occurred


	public ErrorInfo(String url, String message, String dateTime) {
		super();
		this.url = url;
		this.message = message;
		this.dateTime = dateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateTime() {
		return dateTime;
	}
	

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
