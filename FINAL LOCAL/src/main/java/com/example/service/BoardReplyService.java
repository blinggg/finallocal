package com.example.service;

import com.example.domain.BoardReplyVO;

public interface BoardReplyService { 
	
	public void ReplyInsert(BoardReplyVO vo);
	public void ReplyDelete(int board_reply_rno);
}
  