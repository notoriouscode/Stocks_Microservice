package com.stock.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stocks")
public class Stocks {

	//{"sector_id":2,"stock_name":"HAL","stock_price":"5000","opening_price":"6000","closing_price":"7000","stock_current_volumn":"154000000"}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int sector_id;
	private String stock_name;
	private String stock_price;
	private String opening_price;
	private String closing_price;
	private String stock_current_volumn;
	public Stocks() {}
	public Stocks(int id, int sector_id, String stock_name, String stock_price, String opening_price,
			String closing_price, String stock_current_volumn) {
		super();
		this.id = id;
		this.sector_id = sector_id;
		this.stock_name = stock_name;
		this.stock_price = stock_price;
		this.opening_price = opening_price;
		this.closing_price = closing_price;
		this.stock_current_volumn = stock_current_volumn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSector_id() {
		return sector_id;
	}
	public void setSector_id(int sector_id) {
		this.sector_id = sector_id;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public String getStock_price() {
		return stock_price;
	}
	public void setStock_price(String stock_price) {
		this.stock_price = stock_price;
	}
	public String getOpening_price() {
		return opening_price;
	}
	public void setOpening_price(String opening_price) {
		this.opening_price = opening_price;
	}
	public String getClosing_price() {
		return closing_price;
	}
	public void setClosing_price(String closing_price) {
		this.closing_price = closing_price;
	}
	public String getStock_current_volumn() {
		return stock_current_volumn;
	}
	public void setStock_current_volumn(String stock_current_volumn) {
		this.stock_current_volumn = stock_current_volumn;
	}
	@Override
	public String toString() {
		return "Stocks [id=" + id + ", sector_id=" + sector_id + ", stock_name=" + stock_name + ", stock_price="
				+ stock_price + ", opening_price=" + opening_price + ", closing_price=" + closing_price
				+ ", stock_current_volumn=" + stock_current_volumn + "]";
	}
}
