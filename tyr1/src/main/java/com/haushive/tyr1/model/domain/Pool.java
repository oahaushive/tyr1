package com.haushive.tyr1.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pool")
public class Pool {
	
	@Id
	private String poolId;
	private String changeUsername;
	@Transient
	private double poolTotal;
	@Transient
	private List<PoolDetail> details;

	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	public List<PoolDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PoolDetail> details) {
		this.details = details;
	}

	public String getChangeUsername() {
		return changeUsername;
	}

	public void setChangeUsername(String changeUsername) {
		this.changeUsername = changeUsername;
	}

	public double getPoolTotal() {
		return poolTotal;
	}

	public void setPoolTotal(double poolTotal) {
		this.poolTotal = poolTotal;
	}

}
