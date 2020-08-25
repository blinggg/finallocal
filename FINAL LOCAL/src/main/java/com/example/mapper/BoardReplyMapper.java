package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import com.example.domain.BoardReplyVO;

public interface BoardReplyMapper { 
	
	public List<BoardReplyVO> QnAList(HashMap<String,Object> map);
	public void ReplyInsert(BoardReplyVO vo);
	public void ReplyDelete(int board_reply_rno);
	public BoardReplyVO ReplyRead(int board_reply_rno);
	public void ReplyDeleteAll(int board_bno);
}
