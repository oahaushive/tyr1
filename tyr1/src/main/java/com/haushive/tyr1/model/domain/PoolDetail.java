package com.haushive.tyr1.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pool_detail")
public class PoolDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String poolId;
	private String currencySymbol;
	private double volume;
	private String changeUsername;
	@Transient
	private double currencyTotal;
	@Transient
	private Currency currency;
	
	public String getPoolId() {
		return poolId;
	}
	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public String getChangeUsername() {
		return changeUsername;
	}
	public void setChangeUsername(String changeUsername) {
		this.changeUsername = changeUsername;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getCurrencyTotal() {
		return currencyTotal;
	}
	public void setCurrencyTotal(double currencyTotal) {
		this.currencyTotal = currencyTotal;
	}

}
