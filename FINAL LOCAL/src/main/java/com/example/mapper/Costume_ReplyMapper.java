package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.domain.Costume_ReplyVO;

public interface Costume_ReplyMapper {
	public List<Costume_ReplyVO> clist(HashMap<String,Object> map);
	public int replyCount(String costume_reply_code);
	public void insert(Costume_ReplyVO vo);
	public void delete(int costume_reply_rno);
	public void cdelete(String costume_reply_code);
}
