package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrdersVO {
	private int orders_no;
	private String orders_id;
	private String orders_package_code;
	private String orders_costume_code;

	@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
	private Date orders_day;
	private String photo_package_code;
	private String lend_costume_code;
	private int orders_paytype;
	private int orders_package_quantity;
	private int orders_costume_quantity;
	private int package_cart_ono;
	private int costume_cart_ono;
	private String photo_package_image;
	private String lend_costume_image;
	private String photo_package_title;
	private String lend_costume_name;
	private int package_cart_price;
	private int costume_cart_price;
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

	public String getOrders_package_code() {
		return orders_package_code;
	}

	public void setOrders_package_code(String orders_package_code) {
		this.orders_package_code = orders_package_code;
	}

	public String getOrders_costume_code() {
		return orders_costume_code;
	}

	public void setOrders_costume_code(String orders_costume_code) {
		this.orders_costume_code = orders_costume_code;
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

	public int getOrders_package_quantity() {
		return orders_package_quantity;
	}

	public void setOrders_package_quantity(int orders_package_quantity) {
		this.orders_package_quantity = orders_package_quantity;
	}

	public int getOrders_costume_quantity() {
		return orders_costume_quantity;
	}

	public void setOrders_costume_quantity(int orders_costume_quantity) {
		this.orders_costume_quantity = orders_costume_quantity;
	}

	public String getPhoto_package_image() {
		return photo_package_image;
	}

	public void setPhoto_package_image(String photo_package_image) {
		this.photo_package_image = photo_package_image;
	}

	public String getLend_costume_image() {
		return lend_costume_image;
	}

	public void setLend_costume_image(String lend_costume_image) {
		this.lend_costume_image = lend_costume_image;
	}

	public String getPhoto_package_title() {
		return photo_package_title;
	}

	public void setPhoto_package_title(String photo_package_title) {
		this.photo_package_title = photo_package_title;
	}

	public String getLend_costume_name() {
		return lend_costume_name;
	}

	public void setLend_costume_name(String lend_costume_name) {
		this.lend_costume_name = lend_costume_name;
	}

	public int getPackage_cart_price() {
		return package_cart_price;
	}

	public void setPackage_cart_price(int package_cart_price) {
		this.package_cart_price = package_cart_price;
	}

	public int getCostume_cart_price() {
		return costume_cart_price;
	}

	public void setCostume_cart_price(int costume_cart_price) {
		this.costume_cart_price = costume_cart_price;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getLend_costume_code() {
		return lend_costume_code;
	}

	public void setLend_costume_code(String lend_costume_code) {
		this.lend_costume_code = lend_costume_code;
	}

	public String getPhoto_package_code() {
		return photo_package_code;
	}

	public void setPhoto_package_code(String photo_package_code) {
		this.photo_package_code = photo_package_code;
	}

	public int getPackage_cart_ono() {
		return package_cart_ono;
	}

	public void setPackage_cart_ono(int package_cart_ono) {
		this.package_cart_ono = package_cart_ono;
	}

	public int getCostume_cart_ono() {
		return costume_cart_ono;
	}

	public void setCostume_cart_ono(int costume_cart_ono) {
		this.costume_cart_ono = costume_cart_ono;
	}

	@Override
	public String toString() {
		return "OrdersVO [orders_no=" + orders_no + ", orders_id=" + orders_id + ", orders_package_code="
				+ orders_package_code + ", orders_costume_code=" + orders_costume_code + ", orders_day=" + orders_day
				+ ", photo_package_code=" + photo_package_code + ", lend_costume_code=" + lend_costume_code
				+ ", orders_paytype=" + orders_paytype + ", orders_package_quantity=" + orders_package_quantity
				+ ", orders_costume_quantity=" + orders_costume_quantity + ", package_cart_ono=" + package_cart_ono
				+ ", costume_cart_ono=" + costume_cart_ono + ", photo_package_image=" + photo_package_image
				+ ", lend_costume_image=" + lend_costume_image + ", photo_package_title=" + photo_package_title
				+ ", lend_costume_name=" + lend_costume_name + ", package_cart_price=" + package_cart_price
				+ ", costume_cart_price=" + costume_cart_price + ", total=" + total + "]";
	}
}