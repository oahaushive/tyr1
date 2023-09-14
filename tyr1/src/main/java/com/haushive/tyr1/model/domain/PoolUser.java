package com.haushive.tyr1.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pool_user")
public class PoolUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String poolId;
	private double percentage;
	@Transient
	private double userAmount;
	@Transient
	private Pool pool;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getPoolId() {
		return poolId;
	}
	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	public double getUserAmount() {
		return userAmount;
	}
	public void setUserAmount(double userAmount) {
		this.userAmount = userAmount;
	}
	public Pool getPool() {
		return pool;
	}
	public void setPool(Pool pool) {
		this.pool = pool;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
