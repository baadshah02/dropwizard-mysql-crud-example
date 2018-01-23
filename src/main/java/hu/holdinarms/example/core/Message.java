package hu.holdinarms.example.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String message;

	@JsonProperty
	private Integer parent;

	public Message(Integer id, String message, Integer parent){
		this.id = id;
		this.message = message;
		this.parent = parent;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
