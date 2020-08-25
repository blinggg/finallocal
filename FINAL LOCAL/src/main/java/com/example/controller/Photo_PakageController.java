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
   @Resource(name = "uploadPath") /* ���� ���ε带 ���� �ʿ� */
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
		// ��ǥ�̹��� ���ε�
		if(!file.isEmpty()){
			String oldImage=mapper.getImage(vo.getA_no());
			// ���� ��ǥ�̹����� ������ ����
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
		// ���� ��ǥ�̹����� ������ ����
		if(!oldImage.equals("")){
			new File(path + File.separator + oldImage).delete();
		}
		mapper.delAttach(a_no);
   }
}