package com.example.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.OrdersVO;
import com.example.mapper.CartMapper;
import com.example.mapper.OrdersMapper;
import com.example.mapper.UsersMapper;

@Controller
public class OrdersController {
   @Autowired
   OrdersMapper mapper;
   @Autowired
   CartMapper cmapper;
   @Autowired
   UsersMapper umapper;
   
   @RequestMapping("/order/packagelist")
   @ResponseBody
   public HashMap<String,Object> packagelist(String users_id){
      HashMap<String, Object> map=new HashMap<String, Object>();
      map.put("packagelist", mapper.packagelist(users_id));
      return map;
   }
   
   @RequestMapping("/order/costumelist")
   @ResponseBody
   public HashMap<String,Object> costumelist(String users_id){
      HashMap<String, Object> map=new HashMap<String, Object>();
      map.put("costumelist", mapper.costumelist(users_id));
      return map;
   }
   
   @RequestMapping("/order/packageStatusUp")
   @ResponseBody
   public void pup(int orders_no){
      mapper.pup(orders_no);
   }
   
   @RequestMapping("/order/costumeStatusUp")
   @ResponseBody
   public void cup(int orders_no){
      mapper.cup(orders_no);
   }
   
   @RequestMapping("/order/insert")
   public String insert(int totalsum, int point, int orders_paytype, String orders_id, OrdersVO ordersVO, HttpServletRequest request){
	   ordersVO.setOrders_id(orders_id);
	   ordersVO.setOrders_paytype(orders_paytype);
	   String[] packageNo = request.getParameterValues("packageNO[]");
	   String[] costumeNo = request.getParameterValues("costumeNO[]");
	   String[] packageQuantity = request.getParameterValues("packageQuantity[]");
	   String[] costumeQuantity = request.getParameterValues("costumeQuantity[]");
	   
	   for (int i = 0; i < packageNo.length; i++) {
		   ordersVO.setPackage_cart_ono(Integer.parseInt(packageNo[i]));
		   ordersVO.setOrders_package_quantity(Integer.parseInt(packageQuantity[i]));
		   mapper.packageInsert(ordersVO);
		   
		   if(Integer.parseInt(packageQuantity[i])==cmapper.pread(Integer.parseInt(packageNo[i])).getPackage_cart_quantity()){
			   int packageCartNo=Integer.parseInt(packageNo[i]);
			   cmapper.PackageStatusUpdate(packageCartNo);
	       }
	   }
		
	   for (int i = 0; i < costumeNo.length; i++) {
		   ordersVO.setCostume_cart_ono(Integer.parseInt(costumeNo[i]));
		   ordersVO.setOrders_costume_quantity(Integer.parseInt(costumeQuantity[i]));
		   mapper.costumeInsert(ordersVO);
		   
		   if(Integer.parseInt(costumeQuantity[i])==cmapper.cread(Integer.parseInt(costumeNo[i])).getCostume_cart_quantity()){
			   int costumeCartNo=Integer.parseInt(costumeNo[i]);
			   cmapper.CostumeStatusUpdate(costumeCartNo);
		   }
	   }
	   umapper.pointUpdate(orders_id, point);
	   cmapper.pointAdd(totalsum, orders_id);

	   return "/user/mypage";
   }
   
   @RequestMapping("/user/point")
   @ResponseBody
   public int point(String users_id){
      int point=mapper.point(users_id);
      return point;
   }
}