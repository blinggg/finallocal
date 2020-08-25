package com.example.service;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.example.domain.UsersVO;

public class UsersDAOImpl implements UsersDAO{
   @Inject
    SqlSession sqlSession;

   @Override
   public void authentication(UsersVO vo) {
      sqlSession.insert("member.authentication", vo);   
   }
}