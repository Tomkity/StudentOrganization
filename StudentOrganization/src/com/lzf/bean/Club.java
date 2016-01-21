package com.lzf.bean;

// Generated 2016-1-21 13:46:58 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Club generated by hbm2java
 */
@Entity
@Table(name="club")
public class Club implements java.io.Serializable {

	@Id
	private Integer clubId;
	private String clubName;
	private String clubMessage;
	private Date activeTime;
	private Set<User> users = new HashSet<User>(0);
	private Set<Active> actives = new HashSet<Active>(0);

	public Club() {
	}

	public Club(String clubName, String clubMessage, Date activeTime) {
		this.clubName = clubName;
		this.clubMessage = clubMessage;
		this.activeTime = activeTime;
	}

	public Club(String clubName, String clubMessage, Date activeTime,
			Set<User> users, Set<Active> actives) {
		this.clubName = clubName;
		this.clubMessage = clubMessage;
		this.activeTime = activeTime;
		this.users = users;
		this.actives = actives;
	}

	public Integer getClubId() {
		return this.clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public String getClubName() {
		return this.clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubMessage() {
		return this.clubMessage;
	}

	public void setClubMessage(String clubMessage) {
		this.clubMessage = clubMessage;
	}

	public Date getActiveTime() {
		return this.activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Active> getActives() {
		return this.actives;
	}

	public void setActives(Set<Active> actives) {
		this.actives = actives;
	}

}