package com.phonebook.app.model;

/***
 * This is the POJO(Plain Old Java Object) used to set or get the values
 * for all the contacts.
 * @author Mounika
 */
import java.util.List;

public class Contact {
	private int id;
	private String firstName;
	private String lastName;
	private String home;
	private String mobile;
	private String email;
	private boolean status;

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	private String groupIds;

	public Contact() {

	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	private List<Group> groups;

	public String getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}

	private String groupNames;

	public Contact(String firstName, String lastName, String home, String mobile, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.home = home;
		this.mobile = mobile;
		this.email = email;
	}

	public Contact(int id, String firstName, String lastName, String home, String mobile, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.home = home;
		this.mobile = mobile;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStatus(boolean status) {
		this.status = status;

	}

	public boolean getStatus() {
		return status;
	}

}
