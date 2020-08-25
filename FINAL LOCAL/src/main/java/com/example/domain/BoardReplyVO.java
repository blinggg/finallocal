package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BoardReplyVO {    
	
	private int board_reply_rno;  
	private int board_reply_bno;
	private String board_reply;
	private String board_reply_replyer;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="Asia/Seoul")
	private Date board_reply_replyDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="Asia/Seoul")
	private Date board_reply_updateDate;
	
	public int getBoard_reply_rno() {
		return board_reply_rno;
	}
	public void setBoard_reply_rno(int board_reply_rno) {
		this.board_reply_rno = board_reply_rno;
	}
	public int getBoard_reply_bno() {
		return board_reply_bno;
	}
	public void setBoard_reply_bno(int board_reply_bno) {
		this.board_reply_bno = board_reply_bno;
	}
	public String getBoard_reply() {
		return board_reply;
	}
	public void setBoard_reply(String board_reply) {
		this.board_reply = board_reply;
	}
	public String getBoard_reply_replyer() {
		return board_reply_replyer;
	}
	public void setBoard_reply_replyer(String board_reply_replyer) {
		this.board_reply_replyer = board_reply_replyer;
	}
	public Date getBoard_reply_replyDate() {
		return board_reply_replyDate;
	}
	public void setBoard_reply_replyDate(Date board_reply_replyDate) {
		this.board_reply_replyDate = board_reply_replyDate;
	}
	public Date getBoard_reply_updateDate() {
		return board_reply_updateDate;
	}
	public void setBoard_reply_updateDate(Date board_reply_updateDate) {
		this.board_reply_updateDate = board_reply_updateDate;
	}
	
	@Override
	public String toString() {
		return "BoardReplyVO [board_reply_rno=" + board_reply_rno + ", board_reply_bno=" + board_reply_bno
				+ ", board_reply=" + board_reply + ", board_reply_replyer=" + board_reply_replyer
				+ ", board_reply_replyDate=" + board_reply_replyDate + ", board_reply_updateDate="
				+ board_reply_updateDate + "]";
	}
	
	
	
}
