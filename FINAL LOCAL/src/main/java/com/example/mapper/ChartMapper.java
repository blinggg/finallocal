package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.domain.ChartReadVO;
import com.example.domain.ChartsVO;
import com.example.domain.Criteria;

public interface ChartMapper {
	
	public List<HashMap<String, Object>> packageOrdersList(Criteria cri);
	public int packageorderCount(Criteria cri);
	public List<HashMap<String, Object>> costumeOrdersList(Criteria cri);
	public int costumeorderCount(Criteria cri);
	public List<ChartsVO> packagechart(@Param("month") int month, @Param("year") int year);
    public List<ChartsVO> packagechart2(@Param("month") int month, @Param("year") int year);
    public List<ChartsVO> costumechart(@Param("month") int month, @Param("year") int year);
    public List<ChartsVO> costumechart2(@Param("month") int month, @Param("year") int year);
	public ChartReadVO orderread(int orders_no);
}
