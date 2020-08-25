package com.example.domain;

import java.util.Date;

public class BoardVO {   
	
	   private int board_bno;
	   private String board_title;
	   private String board_content;  
	   private String board_writer;
	   private Date board_regdate;
	   private Date board_updatedate;
	   private int board_viewcnt;
	   private int board_replycnt;
	   
	   
	public int getBoard_bno() {
		return board_bno;
	}
	public void setBoard_bno(int board_bno) {
		this.board_bno = board_bno;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public Date getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(Date board_regdate) {
		this.board_regdate = board_regdate;
	}
	public Date getBoard_updatedate() {
		return board_updatedate;
	}
	public void setBoard_updatedate(Date board_updatedate) {
		this.board_updatedate = board_updatedate;
	}
	public int getBoard_viewcnt() {
		return board_viewcnt;
	}
	public void setBoard_viewcnt(int board_viewcnt) {
		this.board_viewcnt = board_viewcnt;
	}
	public int getBoard_replycnt() {
		return board_replycnt;
	}
	public void setBoard_replycnt(int board_replycnt) {
		this.board_replycnt = board_replycnt;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [board_bno=" + board_bno + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_writer=" + board_writer + ", board_regdate=" + board_regdate + ", board_updatedate="
				+ board_updatedate + ", board_viewcnt=" + board_viewcnt + ", board_replycnt=" + board_replycnt + "]";
	}
	   
	   
}
