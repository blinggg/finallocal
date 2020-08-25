package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.Costume_ReplyMapper;
import com.example.mapper.Lend_CostumeMapper;

@Service
public class CostumeServiceImpl implements CostumeService {
	@Autowired
	Lend_CostumeMapper mapper;
	@Autowired
	Costume_ReplyMapper cmapper;
	
	@Transactional
	@Override
	public void delete(String lend_costume_code, String costume_reply_code) {
		cmapper.cdelete(costume_reply_code);
		mapper.delete(lend_costume_code);
	}
}