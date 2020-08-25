package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardVO;
import com.example.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper mapper;

	@Transactional
	@Override 
	public BoardVO QnARead(int board_bno) {
		mapper.UpdateViewcnt(board_bno);
		return mapper.QnARead(board_bno);
	}
	  
	
}
