package com.libang.entity;

public class JsonResult {
	
 private static final String JSON_STATE_SUCCESS="success";
 private static final String JSON_STATE_ERROR="error";
 
 	private String state;
 	private Object data;
 	private String message;
 	
 	public JsonResult() {
 		
 	}
	public static JsonResult success() {
 		JsonResult jr = new JsonResult();
 		jr.setState(JSON_STATE_SUCCESS);
		return jr;
 	}
	public static JsonResult success(Object data) {
 		JsonResult jr = new JsonResult();
 		jr.setState(JSON_STATE_SUCCESS);
 		jr.setData(data);
		return jr;
 	}
	public static JsonResult error(String message) {
 		JsonResult jr = new JsonResult();
 		jr.setState(JSON_STATE_ERROR);
 		jr.setMessage(message);
		return jr;
 	}
	
 	
 	
 	
	public String getState() {
		
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
 	
 
 
 
}
