package com.example.domain;

public class Package_CartVO extends Photo_PackageVO{
	private int package_cart_no;
	private String package_cart_id;
	private String package_cart_package_code;
	private int package_cart_quantity;
	private int package_cart_price;
	private int package_cart_status;
	
	public int getPackage_cart_status() {
		return package_cart_status;
	}
	public void setPackage_cart_status(int package_cart_status) {
		this.package_cart_status = package_cart_status;
	}
	public int getPackage_cart_no() {
		return package_cart_no;
	}
	public void setPackage_cart_no(int package_cart_no) {
		this.package_cart_no = package_cart_no;
	}
	public String getPackage_cart_id() {
		return package_cart_id;
	}
	public void setPackage_cart_id(String package_cart_id) {
		this.package_cart_id = package_cart_id;
	}
	public String getPackage_cart_package_code() {
		return package_cart_package_code;
	}
	public void setPackage_cart_package_code(String package_cart_package_code) {
		this.package_cart_package_code = package_cart_package_code;
	}
	public int getPackage_cart_quantity() {
		return package_cart_quantity;
	}
	public void setPackage_cart_quantity(int package_cart_quantity) {
		this.package_cart_quantity = package_cart_quantity;
	}
	public int getPackage_cart_price() {
		return package_cart_price;
	}
	public void setPackage_cart_price(int package_cart_price) {
		this.package_cart_price = package_cart_price;
	}
	@Override
	public String toString() {
		return "Package_CartVO [package_cart_no=" + package_cart_no + ", package_cart_id=" + package_cart_id
				+ ", package_cart_package_code=" + package_cart_package_code + ", package_cart_quantity="
				+ package_cart_quantity + ", package_cart_price=" + package_cart_price + ", package_cart_status="
				+ package_cart_status + "]";
	}
} // class