package com.lzf.bean;

// Generated 2016-1-21 13:46:58 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user")
public class User implements java.io.Serializable {

	@Id
	private Integer userId;
	private String userName;
	private String userRealName;
	private String userSex;
	private String userPassword;
	private String userActor;
	private Set<Club> clubs = new HashSet<Club>(0);

	public User() {
	}

	public User(String userName, String userRealName, String userSex,
			String userPassword, String userActor) {
		this.userName = userName;
		this.userRealName = userRealName;
		this.userSex = userSex;
		this.userPassword = userPassword;
		this.userActor = userActor;
	}

	public User(String userName, String userRealName, String userSex,
			String userPassword, String userActor, Set<Club> clubs) {
		this.userName = userName;
		this.userRealName = userRealName;
		this.userSex = userSex;
		this.userPassword = userPassword;
		this.userActor = userActor;
		this.clubs = clubs;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealName() {
		return this.userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserActor() {
		return this.userActor;
	}

	public void setUserActor(String userActor) {
		this.userActor = userActor;
	}

	public Set<Club> getClubs() {
		return this.clubs;
	}

	public void setClubs(Set<Club> clubs) {
		this.clubs = clubs;
	}

}
