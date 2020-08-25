package com.example.domain;

import java.util.Date;

public class ChartReadVO {
	
	private int orders_no;
	private String orders_id;
	private String users_name; 
	private String photo_package_title;
	private int orders_package_quantity;
	private int photo_package_price;
	private int package_total;
	private String lend_costume_name;
	private int orders_costume_quantity;
	private int lend_costume_price;
	private int costume_total;
	private Date orders_day;
	private int orders_paytype;
	private int discount;
	private int total;
	public int getOrders_no() {
		return orders_no;
	}
	public void setOrders_no(int orders_no) {
		this.orders_no = orders_no;
	}
	public String getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(String orders_id) {
		this.orders_id = orders_id;
	}
	public String getUsers_name() {
		return users_name;
	}
	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}
	public String getPhoto_package_title() {
		return photo_package_title;
	}
	public void setPhoto_package_title(String photo_package_title) {
		this.photo_package_title = photo_package_title;
	}
	public int getOrders_package_quantity() {
		return orders_package_quantity;
	}
	public void setOrders_package_quantity(int orders_package_quantity) {
		this.orders_package_quantity = orders_package_quantity;
	}
	public int getPhoto_package_price() {
		return photo_package_price;
	}
	public void setPhoto_package_price(int photo_package_price) {
		this.photo_package_price = photo_package_price;
	}
	public int getPackage_total() {
		return package_total;
	}
	public void setPackage_total(int package_total) {
		this.package_total = package_total;
	}
	public String getLend_costume_name() {
		return lend_costume_name;
	}
	public void setLend_costume_name(String lend_costume_name) {
		this.lend_costume_name = lend_costume_name;
	}
	public int getOrders_costume_quantity() {
		return orders_costume_quantity;
	}
	public void setOrders_costume_quantity(int orders_costume_quantity) {
		this.orders_costume_quantity = orders_costume_quantity;
	}
	public int getLend_costume_price() {
		return lend_costume_price;
	}
	public void setLend_costume_price(int lend_costume_price) {
		this.lend_costume_price = lend_costume_price;
	}
	public int getCostume_total() {
		return costume_total;
	}
	public void setCostume_total(int costume_total) {
		this.costume_total = costume_total;
	}
	public Date getOrders_day() {
		return orders_day;
	}
	public void setOrders_day(Date orders_day) {
		this.orders_day = orders_day;
	}
	public int getOrders_paytype() {
		return orders_paytype;
	}
	public void setOrders_paytype(int orders_paytype) {
		this.orders_paytype = orders_paytype;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
