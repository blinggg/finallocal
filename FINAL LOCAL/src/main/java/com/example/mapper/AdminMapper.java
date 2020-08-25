package com.example.mapper;

import java.util.List;

import com.example.domain.AdminVO;
import com.example.domain.Criteria;

public interface AdminMapper {
	
	public List<AdminVO> userList(Criteria cri);
	public void userinfoUpdate(AdminVO vo);
	public AdminVO userRead(String users_id);
	public List<AdminVO> blackList(Criteria cri);
	public void blackUpdate(AdminVO vo);
	public AdminVO blackRead(String users_id);
	public int totalCount(Criteria cri);
	public int totalCountBlack(Criteria cri);
	
}
