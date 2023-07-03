package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Userr_Table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  // Unique identifier for the user
	
	private String emailId;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private long mobileNumber;
	private String signAs;
	
	
	
	public User() {
		
	}

	

	public User(int id, String emailId, String username, String password, String fName, String lName, long mobileNumber,
			String signAs) {
		this.id = id;
		this.emailId = emailId;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.mobileNumber = mobileNumber;
		this.signAs = signAs;
		
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSignAs() {
		return signAs;
	}

	public void setSignAs(String signAs) {
		this.signAs = signAs;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", emailId=" + emailId + ", username=" + username + ", password=" + password
				+ ", fName=" + fName + ", lName=" + lName + ", mobileNumber=" + mobileNumber + ", signAs=" + signAs
				+ "]";
	}




}
