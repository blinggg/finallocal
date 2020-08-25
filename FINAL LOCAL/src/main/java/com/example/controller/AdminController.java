package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.AdminVO;
import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.domain.PageMaker;
import com.example.domain.UsersVO;
import com.example.mapper.AdminMapper;

@Controller
public class AdminController {
	@Autowired
	AdminMapper mapper;
	
	@RequestMapping("/admin/userlist")
	public String userList(Model model, Criteria cri) {
		
		cri.setPerPageNum(5);
    	PageMaker pm=new PageMaker();
    	pm.setCri(cri);
    	pm.setTotalCount(mapper.totalCount(cri));
    	
    	model.addAttribute("pm", pm);
    	model.addAttribute("cri", cri);
		model.addAttribute("userlist", mapper.userList(cri));
		return "/admin/userlist";
	}
	
	@RequestMapping("/admin/userread")
	public void userRead(Model model, String users_id) {
		model.addAttribute("vo", mapper.userRead(users_id));
		
	}
	
	@RequestMapping(value="/admin/userUpdate", method=RequestMethod.POST)
    public String userUpdate(AdminVO vo, int page) {
    	mapper.userinfoUpdate(vo);
    	
    	return "redirect:/admin/userlist?page="+page;
    }
	
	@RequestMapping("/admin/blacklist")
	public String blackList(Model model, Criteria cri) {
		
		cri.setPerPageNum(5);
    	PageMaker pm=new PageMaker();
    	pm.setCri(cri);
    	pm.setTotalCount(mapper.totalCountBlack(cri));
    	
    	model.addAttribute("pm", pm);
    	model.addAttribute("cri", cri);
		model.addAttribute("blacklist", mapper.blackList(cri));
		return "/admin/blacklist";
	}
	
	@RequestMapping("/admin/blackread")
	public void blackRead(Model model, String users_id) {
		model.addAttribute("vo", mapper.blackRead(users_id));
	}
	
	@RequestMapping(value="/admin/blackUpdate", method=RequestMethod.POST)
    public String blackUpdate(AdminVO vo, int page) {
    	mapper.blackUpdate(vo);
    	
    	return "redirect:/admin/blacklist?page="+page;
    }
}
