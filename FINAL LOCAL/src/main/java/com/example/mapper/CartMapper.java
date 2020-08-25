package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.domain.Costume_CartVO;
import com.example.domain.Package_CartVO;

public interface CartMapper {
	public List<Package_CartVO> plist(String package_cart_id); // 패키지 목록
	public List<Costume_CartVO> clist(String costume_cart_id); // 의상대여 목록
	public void pdelete(int package_cart_no); // 패키지 삭제
	public void cdelete(int costume_cart_no); // 의상 삭제
	public void pUpdate(Package_CartVO package_cartVO); // 패키지 수량변경
	public void cUpdate(Costume_CartVO costume_cartVO); // 의상 수량변경
	public void pinsert(Package_CartVO vo); // 패키지 장바구니 담기
	public void cinsert(Costume_CartVO vo); // 의상대여 장바구니 담기
	public Package_CartVO pread(int package_cart_no);
	public Costume_CartVO cread(int costume_cart_no);
	public Package_CartVO PackageCartRead(Package_CartVO vo);
	public Costume_CartVO CostumeCartRead(Costume_CartVO vo);
	public void packageQuantityUpdate(Package_CartVO vo);
	public void costumeQuantityUpdate(Costume_CartVO vo);
	public void PackageStatusUpdate(int packageCartNo);
	public void CostumeStatusUpdate(int costumeCartNo);
	public int costumeCnt(String costume_cart_costume_code);
	public void pointAdd(@Param("totalsum") int totalsum, @Param("orders_id") String orders_id);
}