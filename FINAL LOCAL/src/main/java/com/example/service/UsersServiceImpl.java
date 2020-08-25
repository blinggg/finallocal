package com.example.service;

import javax.inject.Inject;

import com.example.domain.UsersVO;

public class UsersServiceImpl implements UsersService{
   @Inject    
    UsersDAO usersdao;
   
   @Override
   public void authentication(UsersVO vo) {
      usersdao.authentication(vo);
      
   }
}