package com.example.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.domain.AttachVO;
import com.example.domain.Photo_PackageVO;
import com.example.mapper.Photo_PackageMapper;

@Controller
public class Photo_PakageController {
   @Resource(name = "uploadPath") /* 파일 업로드를 위해 필요 */
   private String path;
   
   @Autowired
   Photo_PackageMapper mapper;
   
   @RequestMapping("insert")
   public void insert() {
   }
   
   @RequestMapping("/package/packageRead")
   public void read(String photo_package_code, Model model)throws Exception {
      model.addAttribute("vo", mapper.read(photo_package_code));
      model.addAttribute("list", mapper.getAttach(photo_package_code));
   }
   
   @RequestMapping("list")
   public void list(Model model)throws Exception {
      model.addAttribute("list", mapper.list());
      
   }
   
   @RequestMapping(value="/image/update", method=RequestMethod.POST)
   public String updatePost(AttachVO vo, MultipartHttpServletRequest multi)throws Exception{
	   MultipartFile file=multi.getFile("file");
	   System.out.println(vo.toString());
		// 대표이미지 업로드
		if(!file.isEmpty()){
			String oldImage=mapper.getImage(vo.getA_no());
			// 기존 대표이미지가 있으면 삭제
			if(!oldImage.equals("")){
				new File(path + File.separator + oldImage).delete();
			}
			String nimage=System.currentTimeMillis() + file.getOriginalFilename();
			file.transferTo(new File(path + File.separator + nimage));
			vo.setImage(nimage);
		}
		String images=vo.getImage();
		if(!images.equals("")){
			mapper.updateAttach(images, vo.getA_no());
		}
		return "redirect:/package/packageRead?photo_package_code="+vo.getPhoto_package_code();
   }
   
   @RequestMapping("/image/delete")
   @ResponseBody
   public void delete(int a_no){
		String oldImage=mapper.getImage(a_no);
		// 기존 대표이미지가 있으면 삭제
		if(!oldImage.equals("")){
			new File(path + File.separator + oldImage).delete();
		}
		mapper.delAttach(a_no);
   }
}