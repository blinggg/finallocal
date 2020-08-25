package com.example.domain;

import java.util.ArrayList;

public class Photo_PackageVO {
	private String photo_package_code;
	private String photo_package_title;
	private String photo_package_image;
	private int photo_package_amount;
	private int photo_package_price;
	private ArrayList<String> images;
	
	public String getPhoto_package_code() {
		return photo_package_code;
	}
	public void setPhoto_package_code(String photo_package_code) {
		this.photo_package_code = photo_package_code;
	}
	public String getPhoto_package_title() {
		return photo_package_title;
	}
	public void setPhoto_package_title(String photo_package_title) {
		this.photo_package_title = photo_package_title;
	}
	public String getPhoto_package_image() {
		return photo_package_image;
	}
	public void setPhoto_package_image(String photo_package_image) {
		this.photo_package_image = photo_package_image;
	}
	public int getPhoto_package_amount() {
		return photo_package_amount;
	}
	public void setPhoto_package_amount(int photo_package_amount) {
		this.photo_package_amount = photo_package_amount;
	}
	public int getPhoto_package_price() {
		return photo_package_price;
	}
	public void setPhoto_package_price(int photo_package_price) {
		this.photo_package_price = photo_package_price;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
}
