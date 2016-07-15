package com.linkinghack.SellingSystem.meta;

public class ApiStatus {
	private int code;
	private String message;
	private boolean result;
	
	public ApiStatus(){}
	public ApiStatus(int code, String message, boolean result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
