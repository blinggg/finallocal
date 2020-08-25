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
	/*2020.07.29 정은경 커밋*/
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
		//대표파일업로드
				if(!file.isEmpty()) {	//업로드할 파일이 비어있지않으면
					String image=System.currentTimeMillis()+file.getOriginalFilename(); //밀리세컨으로 중복방지
					file.transferTo(new File(path + File.separator + image));	//Separator는 슬래시, 역슬래시 알아서
					vo.setLend_costume_image(image);
				}
				lmapper.insert(vo);
		
		return "redirect:/costume/costumeList";
	}
	
	@RequestMapping(value="/costumeUpdate",method=RequestMethod.POST)
	public String updatePost(Lend_CostumeVO vo,MultipartHttpServletRequest multi)throws Exception {
		MultipartFile file=multi.getFile("file");	//"file"은 form에서의 이름
				//파일업로드
				if(!file.isEmpty()) {	//업로드할 파일이 비어있지않으면
					//기존이미지가 있으면 삭제
					if(!vo.getLend_costume_image().equals("")) {	
						new File(path + File.separator + vo.getLend_costume_image()).delete();
					}
					String image=System.currentTimeMillis()+file.getOriginalFilename(); //새 이미지
					file.transferTo(new File(path + File.separator + image));
					vo.setLend_costume_image(image);
				}
				
				lmapper.update(vo);
				System.out.println(vo.toString());
				return "redirect:/costume/costumeList";
	}
	
	/*정은경 커밋 종료------------------------------------------*/
	/*인덱스상에서 팝업창 구현하기*/
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

	/* user관련페이지------------------------------------------------------ */
	/* 로그인 */
	@RequestMapping("/user/login")
	public void login() {

	}

	/* 이용약관 */
	@RequestMapping("/user/signup")
	public void signup() {

	}

	/* 회원가입 */
	@RequestMapping("/user/realSignup")
	public void realSignup() {

	}

	/* 마이페이지 */
	@RequestMapping("/user/mypage")
	public void mypage() {

	}

	/* 정보수정 */
	@RequestMapping("/user/mypageRead")
	public void mypageRead() {

	}
	/* index에서 read------------------------------------------------------ */
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

	/* 소개페이지로 이동 */
	@RequestMapping("/index_include/introduce")
	public void introduce() {

	}

	/*-------------------------------------------------------*/
	/* 이미지파일 브라우저에 출력 */
	@Resource(name = "uploadPath") /* 파일 업로드를 위해 필요 */
	private String path;

	@RequestMapping("display")
	@ResponseBody
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		ResponseEntity<byte[]> result = null;
		// display fileName이 있는 경우
		if (!fileName.equals("")) {
			File file = new File(path + File.separator + fileName);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}
		return result;
	}
}
