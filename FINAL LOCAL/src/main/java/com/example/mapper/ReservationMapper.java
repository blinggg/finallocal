package com.example.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.domain.Criteria;
import com.example.domain.ReservationVO;

public interface ReservationMapper {
   
   public List<ReservationVO> list();
   public List<ReservationVO> listpage(HashMap<String, Object> map);
   public List<ReservationVO> privatelist(HashMap<String, Object> map);
   public void redelete(int reservation_reno);
   public void reinsert(ReservationVO vo);
   public ReservationVO read(Date reservation_redate);
   public int calendarCount();
   public int privateCount(String users_id);
}