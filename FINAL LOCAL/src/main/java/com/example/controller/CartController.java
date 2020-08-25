package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Costume_CartVO;
import com.example.domain.OrdersVO;
import com.example.domain.Package_CartVO;
import com.example.mapper.CartMapper;
import com.example.mapper.OrdersMapper;
import com.example.mapper.UsersMapper;

@Controller
public class CartController {
	@Autowired
	CartMapper mapper;
	@Autowired
	UsersMapper umapper;
	@Autowired
	OrdersMapper omapper;
	
	@RequestMapping(value="/cart/purchase")
	public String purchase(String users_id, HttpSession session, HttpServletRequest request){
		String[] packageNo = request.getParameterValues("packageChk[]");
		String[] costumeNo = request.getParameterValues("costumeChk[]");
		ArrayList<Package_CartVO> packageList=new ArrayList<Package_CartVO>();
		ArrayList<Costume_CartVO> costumeList=new ArrayList<Costume_CartVO>();
		session= request.getSession();
		int package_price=0;
		int costume_price=0;
		for (int i = 0; i < packageNo.length; i++) { 
			packageList.add(mapper.pread(Integer.parseInt(packageNo[i])));
			package_price += mapper.pread(Integer.parseInt(packageNo[i])).getPackage_cart_price()*mapper.pread(Integer.parseInt(packageNo[i])).getPackage_cart_quantity();
			session.setAttribute("psum", package_price);
		}
		
		for (int i = 0; i < costumeNo.length; i++) { 
			costumeList.add(mapper.cread(Integer.parseInt(costumeNo[i])));
			costume_price += mapper.cread(Integer.parseInt(costumeNo[i])).getCostume_cart_price()*mapper.cread(Integer.parseInt(costumeNo[i])).getCostume_cart_quantity();
			session.setAttribute("csum", costume_price);
		}
		session.setAttribute("point", umapper.read(users_id).getUsers_point());
		session.setAttribute("plist", packageList);
		session.setAttribute("clist", costumeList);
		return "/user/purchase";
	}
	
	@RequestMapping(value="/user/purchase")
	public void puchase(String users_id, Model model){
		model.addAttribute("vo", umapper.read(users_id));
	}
	
	@RequestMapping("/cart/plist")
	@ResponseBody
	public HashMap<String, Object> plist(String package_cart_id){
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("plist", mapper.plist(package_cart_id));
		return map;
	}
	
	@RequestMapping("/cart/clist")
	@ResponseBody
	public HashMap<String, Object> clist(String costume_cart_id){
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("clist", mapper.clist(costume_cart_id));
		return map;
	}
	
	@RequestMapping("/cart/pdelete")
	@ResponseBody
	public void pdelete(int package_cart_no){
		mapper.pdelete(package_cart_no);
	}
	
	@RequestMapping("/cart/cdelete")
	@ResponseBody
	public void cdelete(int costume_cart_no){
		mapper.cdelete(costume_cart_no);
	}
	
	@RequestMapping("/cart/pupdate")
	@ResponseBody
	public int pupdate(Package_CartVO package_cartVO){
		mapper.pUpdate(package_cartVO);
		int totalPrice=package_cartVO.getPackage_cart_quantity()*mapper.pread(package_cartVO.getPackage_cart_no()).getPackage_cart_price();
		return totalPrice;
	}
	
	@RequestMapping("/cart/cupdate")
	@ResponseBody
	public int cupdate(Costume_CartVO costume_cartVO){
		mapper.cUpdate(costume_cartVO);
		int totalPrice=costume_cartVO.getCostume_cart_quantity()*mapper.cread(costume_cartVO.getCostume_cart_no()).getCostume_cart_price();
		return totalPrice;
	}
	
	@RequestMapping("/cart/pinsert")
	@ResponseBody
	public void pinsert(Package_CartVO vo){
		Package_CartVO Pchk = mapper.PackageCartRead(vo);
		if(Pchk!=null){
			mapper.packageQuantityUpdate(Pchk);
		}else{
			mapper.pinsert(vo);
		}
		OrdersVO PCHK=omapper.PackageOrdersCheck(Pchk.getPackage_cart_no());
		if(PCHK!=null){
			mapper.pinsert(vo);
		}
	}
	
	@RequestMapping("/cart/cinsert")
	@ResponseBody
	public void cinsert(Costume_CartVO vo){
		Costume_CartVO Cchk= mapper.CostumeCartRead(vo);
		if(Cchk!=null){
			Cchk.setCostume_cart_quantity(vo.getCostume_cart_quantity());
			mapper.costumeQuantityUpdate(Cchk);
		}else{
			mapper.cinsert(vo);
		}
		OrdersVO CCHK=omapper.CostumeOrdersCheck(Cchk.getCostume_cart_no());
		if(CCHK!=null){
			mapper.cinsert(vo);
		}
	}
}