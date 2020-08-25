package com.example.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.example.domain.BoardReplyVO;
import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.domain.PageMaker;
import com.example.mapper.BoardMapper;
import com.example.mapper.BoardReplyMapper;
import com.example.service.BoardReplyService;
import com.example.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
    BoardMapper mapper; 

	@Autowired
    BoardReplyMapper rmapper; 
	
	@Autowired
	BoardService service;
    
    @RequestMapping("/QnA_FAQ/QnA")
    public String QnAList(Model model, Criteria cri, HttpServletRequest request) {
    	Cookie cookie=WebUtils.getCookie(request, "users_id");
		if(cookie != null) {
			request.getSession().setAttribute("users_id", cookie.getValue());
		}
    	
    	cri.setPerPageNum(5);
    	PageMaker pm=new PageMaker();
    	pm.setCri(cri);
    	pm.setTotalCount(mapper.totalCount());
    	
    	model.addAttribute("pm", pm);
    	model.addAttribute("QnAList", mapper.QnAList(cri));
    	return "/QnA_FAQ/QnA";
    }
    
    @RequestMapping("/QnA_FAQ/QnARead")
    public void QnARead(Model model, int board_bno) {
    	model.addAttribute("vo", service.QnARead(board_bno));
    }
    
    @RequestMapping("/QnA_FAQ/QnAInsert")
    public void QnAInsert() {
    	
    }
    
    @RequestMapping(value="/QnAInsert", method=RequestMethod.POST)
    public String QnAInsert(BoardVO vo) {
    	
    	mapper.QnAInsert(vo);
    	
    	return "redirect:/QnA_FAQ/QnA";
    }
    
    @RequestMapping(value="/QnAUpdate", method=RequestMethod.POST)
    public String QnAUpdate(BoardVO vo, int page) {
    	System.out.println(vo.toString());
    	mapper.QnAUpdate(vo);
    	
    	return "redirect:/QnA_FAQ/QnA?page="+page;
    }
    
    @RequestMapping(value="/QnADelete", method=RequestMethod.POST)
    public String QnADelete(int board_bno, int page) {
       rmapper.ReplyDeleteAll(board_bno);
       mapper.QnADelete(board_bno);
       
       return "redirect:/QnA_FAQ/QnA?page="+page;
    }
    
    @RequestMapping("/QnA_FAQ/FAQ")
    public void FAQList(Model model) {
    	model.addAttribute("FAQList", mapper.FAQList());
    }
    
    @RequestMapping("/QnA_FAQ/FAQRead")
    public void FAQRead(Model model, int board_bno) {
    	model.addAttribute("vo", mapper.FAQRead(board_bno));
    }
    
    
    @Autowired
    BoardReplyMapper brmapper;
    
    @Autowired
    BoardReplyService brservice;
  
    @RequestMapping("/QnA_FAQ/QnAReply")
    public void QnAReply() {
    	
    }
    
    @RequestMapping(value="/QnAReply.json")
    @ResponseBody
    public HashMap<String , Object> QnAList(int board_reply_bno, Criteria cri) {
    	HashMap<String , Object> map=new HashMap<String , Object>();
    	cri.setPerPageNum(5);
    	PageMaker pm=new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(mapper.replyCount(board_reply_bno));
		map.put("pm", pm);
		
		HashMap<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("board_reply_bno", board_reply_bno);
		paraMap.put("cri", cri);
		
		map.put("pm", pm);
    	map.put("QnAList", brmapper.QnAList(paraMap));
    	map.put("count", mapper.replyCount(board_reply_bno));
    	
    	return map;
    }
    
    @RequestMapping(value="/ReplyInsert", method=RequestMethod.POST)
    @ResponseBody
    public void ReplyInsert(BoardReplyVO vo) {
    	brservice.ReplyInsert(vo);
    }
    
    @RequestMapping(value="/ReplyDelete")
    @ResponseBody
    public void ReplyDelete(int board_reply_rno) {
    	brservice.ReplyDelete(board_reply_rno);
    }
    
}
