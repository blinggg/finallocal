package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.domain.AttachVO;
import com.example.domain.Photo_PackageVO;

public interface Photo_PackageMapper {
   
   public void insert(Photo_PackageVO vo);
   public List<Photo_PackageVO> list();
   public Photo_PackageVO read(String photo_package_code);
   public void update(Photo_PackageVO vo);
   public void delete(String photo_package_code);
   public void addAttach(@Param("image") String image, @Param("photo_package_code") String photo_package_code);
   public List<AttachVO> getAttach(String photo_package_code);
   public void delAttach(int a_no);
   public void updateAttach(@Param("image") String image, @Param("a_no") int a_no);
   public String getImage(int a_no);
}