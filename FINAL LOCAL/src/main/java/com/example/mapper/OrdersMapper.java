package com.example.mapper;

import java.util.List;

import com.example.domain.OrdersVO;

public interface OrdersMapper {
	public List<OrdersVO> packagelist(String users_id);
	public List<OrdersVO> costumelist(String users_id);
    public int point(String users_id);
    public void packageInsert(OrdersVO ordersVO);
    public void costumeInsert(OrdersVO ordersVO);
    public OrdersVO PackageOrdersCheck(int package_cart_no);
    public OrdersVO CostumeOrdersCheck(int costume_cart_no);
    public void pup(int orders_no);
    public void cup(int orders_no);
}