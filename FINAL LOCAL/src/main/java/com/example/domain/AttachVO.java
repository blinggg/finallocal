package com.example.domain;

public class AttachVO {
	private int a_no;
	private String photo_package_code;
	private String image;
	
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getPhoto_package_code() {
		return photo_package_code;
	}
	public void setPhoto_package_code(String photo_package_code) {
		this.photo_package_code = photo_package_code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "AttachVO [a_no=" + a_no + ", photo_package_code=" + photo_package_code + ", image=" + image + "]";
	}
}