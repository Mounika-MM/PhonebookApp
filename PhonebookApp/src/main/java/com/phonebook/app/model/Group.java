package com.phonebook.app.model;

/***
 * This is the POJO(Plain Old Java Object) used to set or get the values
 * for all the groups.
 * @author Mounika
 */
public class Group {

	private int id;
	private String name;
	private String type;
	private String location;
	private boolean status;

	public Group() {
	}

	public Group(String name, String type, String location) {
		this.name = name;
		this.type = type;
		this.location = location;
	}

	public Group(int id, String name, String type, String location) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStatus(boolean status) {
		this.status = status;

	}

	public boolean getStatus() {
		return status;
	}
}
