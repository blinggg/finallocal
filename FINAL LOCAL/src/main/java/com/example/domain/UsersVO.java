package com.example.domain;

import java.util.Date;

public class UsersVO {
	private String users_id;
	private String users_pass;
	private String users_name;
	private String users_birthday;
	private String users_zipcode;
	private String users_address;
	private String users_phone;
	private String users_email;
	private int users_point;
	private String users_gender;
	private Date users_regdate;
	private int users_note;
	private String users_passChk;
	private String users_birthdayYear;
	private String users_birthdayMonth;
	private String users_birthdayDay;
	private String users_addresss;
	private String users_addressDetail;

	public String getUsers_id() {
		return users_id;
	}

	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}

	public String getUsers_pass() {
		return users_pass;
	}

	public void setUsers_pass(String users_pass) {
		this.users_pass = users_pass;
	}

	public String getUsers_name() {
		return users_name;
	}

	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}

	public String getUsers_birthday() {
		return users_birthday;
	}

	public void setUsers_birthday(String users_birthday) {
		this.users_birthday = users_birthday;
	}

	public String getUsers_zipcode() {
		return users_zipcode;
	}

	public void setUsers_zipcode(String users_zipcode) {
		this.users_zipcode = users_zipcode;
	}

	public String getUsers_address() {
		return users_address;
	}

	public void setUsers_address(String users_address) {
		this.users_address = users_address;
	}

	public String getUsers_phone() {
		return users_phone;
	}

	public void setUsers_phone(String users_phone) {
		this.users_phone = users_phone;
	}

	public String getUsers_email() {
		return users_email;
	}

	public void setUsers_email(String users_email) {
		this.users_email = users_email;
	}

	public int getUsers_point() {
		return users_point;
	}

	public void setUsers_point(int users_point) {
		this.users_point = users_point;
	}

	public String getUsers_gender() {
		return users_gender;
	}

	public void setUsers_gender(String users_gender) {
		this.users_gender = users_gender;
	}

	public Date getUsers_regdate() {
		return users_regdate;
	}

	public void setUsers_regdate(Date users_regdate) {
		this.users_regdate = users_regdate;
	}

	public int getUsers_note() {
		return users_note;
	}

	public void setUsers_note(int users_note) {
		this.users_note = users_note;
	}

	public String getUsers_passChk() {
		return users_passChk;
	}

	public void setUsers_passChk(String users_passChk) {
		this.users_passChk = users_passChk;
	}

	public String getUsers_birthdayYear() {
		return users_birthdayYear;
	}

	public void setUsers_birthdayYear(String users_birthdayYear) {
		this.users_birthdayYear = users_birthdayYear;
	}

	public String getUsers_birthdayMonth() {
		return users_birthdayMonth;
	}

	public void setUsers_birthdayMonth(String users_birthdayMonth) {
		this.users_birthdayMonth = users_birthdayMonth;
	}

	public String getUsers_birthdayDay() {
		return users_birthdayDay;
	}

	public void setUsers_birthdayDay(String users_birthdayDay) {
		this.users_birthdayDay = users_birthdayDay;
	}

	public String getUsers_addresss() {
		return users_addresss;
	}

	public void setUsers_addresss(String users_addresss) {
		this.users_addresss = users_addresss;
	}

	public String getUsers_addressDetail() {
		return users_addressDetail;
	}

	public void setUsers_addressDetail(String users_addressDetail) {
		this.users_addressDetail = users_addressDetail;
	}

	@Override
	public String toString() {
		return "UsersVO [users_id=" + users_id + ", users_pass=" + users_pass + ", users_name=" + users_name
				+ ", users_birthday=" + users_birthday + ", users_zipcode=" + users_zipcode + ", users_address="
				+ users_address + ", users_phone=" + users_phone + ", users_email=" + users_email + ", users_point="
				+ users_point + ", users_gender=" + users_gender + ", users_regdate=" + users_regdate + ", users_note="
				+ users_note + ", users_passChk=" + users_passChk + ", users_birthdayYear=" + users_birthdayYear
				+ ", users_birthdayMonth=" + users_birthdayMonth + ", users_birthdayDay=" + users_birthdayDay
				+ ", users_addresss=" + users_addresss + ", users_addressDetail=" + users_addressDetail + "]";
	}

}