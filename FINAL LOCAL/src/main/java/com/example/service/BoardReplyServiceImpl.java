package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardReplyVO;
import com.example.mapper.BoardMapper;
import com.example.mapper.BoardReplyMapper;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {
	
	@Autowired  
	BoardMapper mapper; 
	
	@Autowired
	BoardReplyMapper brmapper;
	
	@Transactional
	@Override
	public void ReplyInsert(BoardReplyVO vo) {
		brmapper.ReplyInsert(vo);
		mapper.UpdateReplycnt(vo.getBoard_reply_bno(), 1);
		
	}
	
	@Transactional
	@Override
	public void ReplyDelete(int board_reply_rno) {
		BoardReplyVO vo=brmapper.ReplyRead(board_reply_rno);
		brmapper.ReplyDelete(board_reply_rno);
		mapper.UpdateReplycnt(vo.getBoard_reply_bno(), -1);
		
	}
	
	
}
