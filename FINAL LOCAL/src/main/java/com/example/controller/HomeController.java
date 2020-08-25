package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.domain.Costume_ReplyVO;
import com.example.domain.Criteria;
import com.example.domain.Lend_CostumeVO;
import com.example.domain.PageMaker;
import com.example.mapper.CartMapper;
import com.example.mapper.Costume_ReplyMapper;
import com.example.mapper.Lend_CostumeMapper;
import com.example.service.CostumeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	/*2020.07.29 ������ Ŀ��*/
	@Autowired
	Lend_CostumeMapper lmapper;
	@Autowired
	Costume_ReplyMapper cmapper;
	@Autowired
	CostumeService service;
	@Autowired
	CartMapper cartMapper;
	
	/*COSTUME_Reply---------------------------------------------------------------------*/
	@RequestMapping("/costume/costumeReply/reply")
	@ResponseBody
	public HashMap<String, Object> clist(String costume_reply_code,Criteria cri) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		cri.setPerPageNum(5);
		PageMaker pm=new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(cmapper.replyCount(costume_reply_code));
		map.put("pm",pm);
		
		HashMap<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("costume_reply_code",costume_reply_code);
		paraMap.put("cri", cri);
		
		map.put("clist", cmapper.clist(paraMap));
		return map;
	}
	@RequestMapping("/costume/costumeReply")
	public String reply() {
		return "/costume/costumeReply";
	}
	
	@RequestMapping("/costume/costumeInsert")
	public String costumInsert() {
		return "/costume/costumeInsert";
	}
	
	@RequestMapping(value="/costumeReplyInsert",method=RequestMethod.POST)
	@ResponseBody
	public void insert(Costume_ReplyVO vo) {
		cmapper.insert(vo);
	}
	
	@RequestMapping(value="/costumeReplyDelete")
	@ResponseBody
	public void delete(int costume_reply_rno) {
		cmapper.delete(costume_reply_rno);
	}
	
	/*COSTUME---------------------------------------------------------------------------------------------------------*/
	@RequestMapping("/costume/costumeDelete")
	@ResponseBody
	public int delete(String lend_costume_code, String costume_reply_code, String costume_cart_costume_code){
		int cnt=cartMapper.costumeCnt(costume_cart_costume_code);
		if(cnt==0){
			service.delete(lend_costume_code, costume_reply_code);
		}
		return cnt;
	}
	
	@RequestMapping("/costume/costumeList")
	public String list(Model model,Criteria cri) {
		cri.setPerPageNum(9);
		
		PageMaker pm=new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(lmapper.totalCount(cri));
		
		model.addAttribute("cri", cri);
		model.addAttribute("pm",pm);
		model.addAttribute("list", lmapper.list(cri));
		return "/costume/costumeList";
	}
	
	@RequestMapping("/costume/costumeRead")
	public void costumeRead(Model model,String lend_costume_code) {
		model.addAttribute("vo",lmapper.read(lend_costume_code));
		
	}
	
	@RequestMapping(value="/costumeInsert",method=RequestMethod.POST)
	public String costumeInsertPost(Lend_CostumeVO vo,MultipartHttpServletRequest multi) throws Exception{
		MultipartFile file=multi.getFile("file");
		//��ǥ���Ͼ��ε�
				if(!file.isEmpty()) {	//���ε��� ������ �������������
					String image=System.currentTimeMillis()+file.getOriginalFilename(); //�и��������� �ߺ�����
					file.transferTo(new File(path + File.separator + image));	//Separator�� ������, �������� �˾Ƽ�
					vo.setLend_costume_image(image);
				}
				lmapper.insert(vo);
		
		return "redirect:/costume/costumeList";
	}
	
	@RequestMapping(value="/costumeUpdate",method=RequestMethod.POST)
	public String updatePost(Lend_CostumeVO vo,MultipartHttpServletRequest multi)throws Exception {
		MultipartFile file=multi.getFile("file");	//"file"�� form������ �̸�
				//���Ͼ��ε�
				if(!file.isEmpty()) {	//���ε��� ������ �������������
					//�����̹����� ������ ����
					if(!vo.getLend_costume_image().equals("")) {	
						new File(path + File.separator + vo.getLend_costume_image()).delete();
					}
					String image=System.currentTimeMillis()+file.getOriginalFilename(); //�� �̹���
					file.transferTo(new File(path + File.separator + image));
					vo.setLend_costume_image(image);
				}
				
				lmapper.update(vo);
				System.out.println(vo.toString());
				return "redirect:/costume/costumeList";
	}
	
	/*������ Ŀ�� ����------------------------------------------*/
	/*�ε����󿡼� �˾�â �����ϱ�*/
	@RequestMapping("/index_include/popup")
	public void popup() {

	}
	
	@RequestMapping("/index")
	public void index() {

	}

	/* menu------------------------------------------------------------ */
	/* CART */
	@RequestMapping("/user/cart")
	public void cart() {
		
	}


	/* PACKAGE */
	@RequestMapping("/package/packageList")
	public void packageList() {

	}

	/* user����������------------------------------------------------------ */
	/* �α��� */
	@RequestMapping("/user/login")
	public void login() {

	}

	/* �̿��� */
	@RequestMapping("/user/signup")
	public void signup() {

	}

	/* ȸ������ */
	@RequestMapping("/user/realSignup")
	public void realSignup() {

	}

	/* ���������� */
	@RequestMapping("/user/mypage")
	public void mypage() {

	}

	/* �������� */
	@RequestMapping("/user/mypageRead")
	public void mypageRead() {

	}
	/* index���� read------------------------------------------------------ */
	@RequestMapping("/read/couple")
	public void couple() {

	}

	@RequestMapping("/read/family")
	public void family() {

	}

	@RequestMapping("/read/pet")
	public void pet() {

	}

	@RequestMapping("/read/friend")
	public void friend() {

	}

	@RequestMapping("/read/profile")
	public void profile() {

	}

	/* �Ұ��������� �̵� */
	@RequestMapping("/index_include/introduce")
	public void introduce() {

	}

	/*-------------------------------------------------------*/
	/* �̹������� �������� ��� */
	@Resource(name = "uploadPath") /* ���� ���ε带 ���� �ʿ� */
	private String path;

	@RequestMapping("display")
	@ResponseBody
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		ResponseEntity<byte[]> result = null;
		// display fileName�� �ִ� ���
		if (!fileName.equals("")) {
			File file = new File(path + File.separator + fileName);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}
		return result;
	}
}
