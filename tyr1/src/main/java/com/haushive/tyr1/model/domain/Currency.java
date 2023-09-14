package com.haushive.tyr1.model.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("symbol")
	private String symbol;
	@JsonProperty("name")
	private String name;
	@JsonProperty("logo_url")
	private String logo_url;
	@JsonProperty("price")
	private String price;
	@JsonProperty("price_date")
	private String price_date;
	@JsonProperty("price_timestamp")
	private String price_timestamp;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice_date() {
		return price_date;
	}
	public void setPrice_date(String price_date) {
		this.price_date = price_date;
	}
	public String getPrice_timestamp() {
		return price_timestamp;
	}
	public void setPrice_timestamp(String price_timestamp) {
		this.price_timestamp = price_timestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
