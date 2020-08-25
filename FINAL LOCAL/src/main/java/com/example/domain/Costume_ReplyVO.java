package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Costume_ReplyVO {
	private int costume_reply_rno;
	private String costume_reply_code;
	private String costume_reply;
	private String costume_reply_replyer;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="Asia/Seoul")
	private Date costume_reply_replyDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="Asia/Seoul")
	private Date costume_reply_updateDate;
	
	public int getCostume_reply_rno() {
		return costume_reply_rno;
	}
	public void setCostume_reply_rno(int costume_reply_rno) {
		this.costume_reply_rno = costume_reply_rno;
	}
	public String getCostume_reply_code() {
		return costume_reply_code;
	}
	public void setCostume_reply_code(String costume_reply_code) {
		this.costume_reply_code = costume_reply_code;
	}
	public String getCostume_reply() {
		return costume_reply;
	}
	public void setCostume_reply(String costume_reply) {
		this.costume_reply = costume_reply;
	}
	public String getCostume_reply_replyer() {
		return costume_reply_replyer;
	}
	public void setCostume_reply_replyer(String costume_reply_replyer) {
		this.costume_reply_replyer = costume_reply_replyer;
	}
	public Date getCostume_reply_replyDate() {
		return costume_reply_replyDate;
	}
	public void setCostume_reply_replyDate(Date costume_reply_replyDate) {
		this.costume_reply_replyDate = costume_reply_replyDate;
	}
	public Date getCostume_reply_updateDate() {
		return costume_reply_updateDate;
	}
	public void setCostume_reply_updateDate(Date costume_reply_updateDate) {
		this.costume_reply_updateDate = costume_reply_updateDate;
	}
	@Override
	public String toString() {
		return "Costume_ReplyVO [costume_reply_rno=" + costume_reply_rno + ", costume_reply_code=" + costume_reply_code
				+ ", costume_reply=" + costume_reply + ", costume_reply_replyer=" + costume_reply_replyer
				+ ", costume_reply_replyDate=" + costume_reply_replyDate + ", costume_reply_updateDate="
				+ costume_reply_updateDate + "]";
	}
	
	
	
}
