package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.ChartsVO;
import com.example.domain.Criteria;
import com.example.domain.PageMaker;
import com.example.mapper.ChartMapper;

@Controller
public class ChartController {
	
	@Autowired
	ChartMapper mapper;
	
	@RequestMapping("/admin/pchart")
	public void PordersList(Model model, Criteria cri)  {
		
		cri.setPerPageNum(5);
    	PageMaker pm=new PageMaker();
    	pm.setCri(cri);
    	pm.setTotalCount(mapper.packageorderCount(cri));
    	
    	model.addAttribute("cri", cri);
    	model.addAttribute("pm", pm);
		model.addAttribute("ordersList", mapper.packageOrdersList(cri));
	}
	
	@RequestMapping("/admin/cchart")
	public void CordersList(Model model, Criteria cri) throws Exception {
		
		cri.setPerPageNum(5);
    	PageMaker pm=new PageMaker();
    	pm.setCri(cri);
    	pm.setTotalCount(mapper.costumeorderCount(cri));
    	
    	model.addAttribute("cri", cri);
    	model.addAttribute("pm", pm);
		model.addAttribute("ordersList", mapper.costumeOrdersList(cri));
		
	}
	
	@RequestMapping("/admin/PCchart")
	public void PCchart() {
		
	}
	
	@RequestMapping("/admin/packagechart")
	public void packagechart() {
		
	}
	
	@RequestMapping("/packagechartt")
	   @ResponseBody
	   public List<ChartsVO> packagechartt(int month, int year){
	      ArrayList arrList=new ArrayList();
	      ArrayList arr=new ArrayList();
	      arr.add("패키지명");
	      arr.add("패키지별 매출총합");
	      arrList.add(arr);
	      List<ChartsVO> list=mapper.packagechart(month, year);
	      for(ChartsVO vo:list) {
	         arr=new ArrayList();
	         arr.add(vo.getName());
	         arr.add(vo.getValue());
	         arrList.add(arr);
	      }
	      return arrList;
	   }
	   
	   @RequestMapping("/packagechartt2")
	   @ResponseBody
	   public List<ChartsVO> packagechartt2(int month, int year){
	      ArrayList arrList=new ArrayList();
	      ArrayList arr=new ArrayList();
	      arr.add("패키지명");
	      arr.add("패키지별 판매수");
	      arrList.add(arr);
	      List<ChartsVO> list=mapper.packagechart2(month, year);
	      for(ChartsVO vo:list) {
	         arr=new ArrayList();
	         arr.add(vo.getName());
	         arr.add(vo.getValue());
	         arrList.add(arr);
	      }
	      return arrList;
	   }
	   
	   
	   @RequestMapping("/admin/costumechart")
	   public void costumechart() {
	      
	   }
	   
	   @RequestMapping("/costumechartt")
	   @ResponseBody
	   public List<ChartsVO> costumechartt(int month, int year){
	      ArrayList arrList=new ArrayList();
	      ArrayList arr=new ArrayList();
	      arr.add("코스튬명");
	      arr.add("코스튬별 매출총합");
	      arrList.add(arr);
	      List<ChartsVO> list=mapper.costumechart(month, year);
	      for(ChartsVO vo:list) {
	         arr=new ArrayList();
	         arr.add(vo.getName());
	         arr.add(vo.getValue());
	         arrList.add(arr);
	      }
	      return arrList;
	   }
	   
	   @RequestMapping("/costumechartt2")
	   @ResponseBody
	   public List<ChartsVO> costumechartt2(int month, int year){
	      ArrayList arrList=new ArrayList();
	      ArrayList arr=new ArrayList();
	      arr.add("코스튬명");
	      arr.add("코스튬별 판매수");
	      arrList.add(arr);
	      List<ChartsVO> list=mapper.costumechart2(month, year);
	      for(ChartsVO vo:list) {
	         arr=new ArrayList();
	         arr.add(vo.getName());
	         arr.add(vo.getValue());
	         arrList.add(arr);
	      }
	      return arrList;
	   }
}
