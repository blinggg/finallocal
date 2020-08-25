package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.domain.BoardVO;
import com.example.domain.Criteria;

public interface BoardMapper { 
    public List<BoardVO> QnAList(Criteria cri);
    public List<BoardVO> FAQList();
    public BoardVO QnARead(int board_bno);
    public BoardVO FAQRead(int board_bno);
    public void QnAInsert(BoardVO vo);
    public void QnAUpdate(BoardVO vo);
    public void QnADelete(int board_bno);
    public int totalCount();
    public void UpdateViewcnt(int board_bno);
    public int replyCount(int board_bno);
    public void UpdateReplycnt(@Param("board_bno") int board_bno, @Param("amount") int amount);
}
