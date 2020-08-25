package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.domain.UsersVO;
import com.example.mapper.UsersMapper;
import com.example.service.KakaoAPI;
import com.example.service.UsersService;

@Controller
public class UsersController {
   @Autowired
   UsersMapper umapper;
   @Autowired
   private KakaoAPI kakao;
   
   @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
   public String delete(String users_id, HttpSession session) {
      session.invalidate();
      umapper.delete(users_id);
      return "redirect:/index";
   }
   
   @RequestMapping("/user/login2")
      public void login2() {
         
      }

   @RequestMapping(value="/user/update", method=RequestMethod.POST)
   public String update(UsersVO vo){
      umapper.update(vo);
      return "/user/mypage";
   }
   
   @RequestMapping("/user/read")
   public String read(String users_id, Model model, HttpSession session){
      users_id=(String)session.getAttribute("users_id");
      model.addAttribute("vo", umapper.read(users_id));
      return "/user/mypageRead";
   }
   
   @RequestMapping(value = "/kakaoLogin")
   public String login(@RequestParam("code") String code, HttpSession session) {
      String access_Token = kakao.getAccessToken(code);
      HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
      System.out.println("login Controller : " + userInfo);

      // Ŭ���̾�Ʈ�� �̸����� ������ �� ���ǿ� �ش� �̸��ϰ� ��ū ���
      if (userInfo.get("email") != null) {
         session.setAttribute("userId", userInfo.get("email"));
         session.setAttribute("users_id", userInfo.get("nickname"));
         session.setAttribute("users_note", 0);
         session.setAttribute("access_Token", access_Token);
      }
      return "/user/welcome";
   }
   
   @RequestMapping(value = "/kakaoLogin2")
   public String login2(@RequestParam("code") String code, HttpSession session) {
      String access_Token = kakao.getAccessToken2(code);
      HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
      System.out.println("login Controller : " + userInfo);

      // Ŭ���̾�Ʈ�� �̸����� ������ �� ���ǿ� �ش� �̸��ϰ� ��ū ���
      if (userInfo.get("email") != null) {
         session.setAttribute("userId", userInfo.get("email"));
         session.setAttribute("users_id", userInfo.get("nickname"));
         session.setAttribute("users_note", 0);
         session.setAttribute("access_Token", access_Token);
      }
      return "/index";
   }

   @RequestMapping("/naverlogin")
   public String naverlogin() {
      return "/user/naverlogin";
   }
   
   @RequestMapping("/naverlogin2")
   public String naverlogin2() {
      return "/user/naverlogin2";
   }

   @RequestMapping("/loginNaverResult")
   public String loginNaverResult(String email, String nickname, HttpSession session) {
      session.setAttribute("userId", email);
      session.setAttribute("users_id", nickname);
      session.setAttribute("users_note", 0);
      return "/user/welcome";
   }
   
   @RequestMapping("/loginNaverResult2")
   public String loginNaverResult2(String email, String nickname, HttpSession session) {
      session.setAttribute("userId", email);
      session.setAttribute("users_id", nickname);
      session.setAttribute("users_note", 0);
      return "/index";
   }

   @RequestMapping(value = "/user/login", method = RequestMethod.POST)
   @ResponseBody
   public int loginPost(Model model, boolean birthdayPointChk, UsersVO vo, boolean ex_chk, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException, ParseException {
      int result = 0;
      String users_id = vo.getUsers_id();
      String users_pass = vo.getUsers_pass();
      UsersVO usersVO = umapper.read(users_id);
      if (usersVO == null) {
         result = 0;
      } else if (!usersVO.getUsers_pass().equals(users_pass)) {
         result = 1;
      } else if(usersVO.getUsers_note()==2) {
         result = 3;
      } else if(usersVO.getUsers_note()==3) {
         result = 4;
      }else {
   	   Date realTime = new Date();
   	   int realYear=realTime.getMonth()+1;
   	   int realDate=realTime.getDate();
   	   String realTimeHap=Integer.toString(realYear)+Integer.toString(realDate);
   	   
   	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
   	   Date DBbirthday = sdf.parse(usersVO.getUsers_birthday());
   	   int DBmonth=DBbirthday.getMonth()+1;
   	   int DBdate=DBbirthday.getDate();
   	   String DBbirthdayHap=Integer.toString(DBmonth)+Integer.toString(DBdate);
   	   
   	   if(birthdayPointChk==false && realTimeHap.equals(DBbirthdayHap)){
   		   umapper.birthdayPointUpdate(users_id);
   		   birthdayPointChk=true;
   		   Cookie cookieId = new Cookie("users_idChk", users_id);
   		   Cookie cookieChk = new Cookie("birthdayPointChk", String.valueOf(birthdayPointChk));
   		   cookieId.setPath("/");
   		   cookieId.setMaxAge(60 * 60 * 24);
   		   cookieChk.setPath("/");
   		   cookieChk.setMaxAge(60 * 60 * 24);
   		   response.addCookie(cookieId);
           response.addCookie(cookieChk);
   	   }
         session.setAttribute("users_id", users_id);
         session.setAttribute("users_note", usersVO.getUsers_note());
         session.setAttribute("users_name", usersVO.getUsers_name());
         result = 2;
         if (ex_chk) {
            Cookie cookie = new Cookie("users_id", users_id);
            Cookie cookie2 = new Cookie("users_note", Integer.toString(usersVO.getUsers_note()));
            Cookie cookie3 = new Cookie("users_name", URLEncoder.encode(usersVO.getUsers_name(), "UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);
            cookie2.setPath("/");
            cookie2.setMaxAge(60 * 60);
            cookie3.setPath("/");
            cookie3.setMaxAge(60 * 60);
            response.addCookie(cookie);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
         }
      }
      return result;
   }
    

 @RequestMapping("/user/logout")
 public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    session.invalidate();
    Cookie cookie = WebUtils.getCookie(request, "users_id");
    if (cookie != null) {
       cookie.setPath("/");
       cookie.setMaxAge(0);
       response.addCookie(cookie);
    }
    Cookie cookie2 = WebUtils.getCookie(request, "users_note");
    if (cookie2 != null) {
       cookie2.setPath("/");
       cookie2.setMaxAge(0);
       response.addCookie(cookie2);
    }
    Cookie cookie3 = WebUtils.getCookie(request, "users_name");
    if (cookie3 != null) {
       cookie3.setPath("/");
       cookie3.setMaxAge(0);
       response.addCookie(cookie3);
    }
    return "redirect:/index";
 }

   @RequestMapping("/user/check")
   @ResponseBody
   public int check(String users_id) {
      int check = 1;
      if (umapper.readcnt(users_id) == 0) {
         check = 0;
      } else if (umapper.readcnt(users_id) == 1) {
         check = 1;
      }
      return check;
   }
   
   @RequestMapping("/user/pcheck")
   @ResponseBody
   public int pcheck(String users_phone) {
      int check = 1;
       if(umapper.pcnt(users_phone) == 0) {
         check = 2;
      } else if(umapper.pcnt(users_phone) == 1) {
         check = 3;
      }
      return check;
   }
   
   @RequestMapping("/user/echeck")
   @ResponseBody
   public int echeck(String users_email) {
      int check = 1;
       if(umapper.ecnt(users_email) == 0) {
         check = 2;
      } else if(umapper.ecnt(users_email) == 1) {
         check = 3;
      }
      return check;
   }

   @RequestMapping(value = "/user/insert", method = RequestMethod.POST)
   public String insert(UsersVO vo) {
      System.out.println(vo.toString());
      umapper.insert(vo);
      return "redirect:/user/welcome";
   }

   @RequestMapping("/user/welcome")
   public void welcome() {

   }

   @RequestMapping("/user/email")
   public void emailInjeung(UsersVO vo, String idCHK, Model model) {
      model.addAttribute("users_id", vo.getUsers_id());
      model.addAttribute("users_pass", vo.getUsers_pass());
      model.addAttribute("users_passChk", vo.getUsers_passChk());
      model.addAttribute("users_name", vo.getUsers_name());
      model.addAttribute("users_birthday", vo.getUsers_birthday());
      model.addAttribute("users_birthdayYear", vo.getUsers_birthdayYear());
      model.addAttribute("users_birthdayMonth", vo.getUsers_birthdayMonth());
      model.addAttribute("users_birthdayDay", vo.getUsers_birthdayDay());
      model.addAttribute("users_gender", vo.getUsers_gender());
      model.addAttribute("users_zipcode", vo.getUsers_zipcode());
      model.addAttribute("users_address", vo.getUsers_address());
      model.addAttribute("users_addresss", vo.getUsers_addresss());
      model.addAttribute("users_addressDetail", vo.getUsers_addressDetail());
      model.addAttribute("users_phone", vo.getUsers_phone());
      model.addAttribute("users_email", vo.getUsers_email());
      model.addAttribute("idCHK", idCHK);
   }

   @Inject // ���񽺸� ȣ���ϱ� ���ؼ� �������� ����
   JavaMailSender mailSender; // ���� ���񽺸� ����ϱ� ���� �������� ������.
   UsersService uservice; // ���񽺸� ȣ���ϱ� ���� �������� ����

   @RequestMapping(value = "/emailSendInjeung", method = RequestMethod.POST)
   public ModelAndView sendInjeung(UsersVO vo, String idCHK, Model model, HttpServletRequest request,
         String emailSendInjeung, HttpServletResponse response_email) throws IOException {
      Random r = new Random();
      int dice = r.nextInt(4589362) + 49311; // �̸��Ϸ� �޴� �����ڵ� �κ� (����)

      String setfrom = "korea78245@gamil.com";
      String tomail = request.getParameter("emailSendInjeung"); // �޴� ��� �̸���
      String title = "ȸ������ ���� �̸��� �Դϴ�."; // ����
      String content = System.getProperty("line.separator") + // ���پ� �ٰ����� �α�����
                                                // �ۼ�
            System.getProperty("line.separator") + "�ȳ��ϼ��� ȸ���� ���� Ȩ�������� ã���ּż� �����մϴ�"
            + System.getProperty("line.separator") + System.getProperty("line.separator") + " ������ȣ�� " + dice
            + " �Դϴ�. " + System.getProperty("line.separator") + System.getProperty("line.separator")
            + "������ ������ȣ�� Ȩ�������� �Է��� �ֽø� �������� �Ѿ�ϴ�."; // ����
      try {
         MimeMessage message = mailSender.createMimeMessage();
         MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

         messageHelper.setFrom(setfrom); // �����»�� �����ϸ� �����۵��� ����
         messageHelper.setTo(tomail); // �޴»�� �̸���
         messageHelper.setSubject(title); // ���������� ������ �����ϴ�
         messageHelper.setText(content); // ���� ����

         mailSender.send(message);
      } catch (Exception e) {
         System.out.println(e);
      }

      ModelAndView mv = new ModelAndView(); // ModelAndView�� ���� �������� �����ϰ�, ����
                                    // ���� �����Ѵ�.
      mv.setViewName("/user/email_injeung"); // �����̸�
      mv.addObject("dice", dice);

      model.addAttribute("users_id", vo.getUsers_id());
      model.addAttribute("users_pass", vo.getUsers_pass());
      model.addAttribute("users_passChk", vo.getUsers_passChk());
      model.addAttribute("users_name", vo.getUsers_name());
      model.addAttribute("users_birthday", vo.getUsers_birthday());
      model.addAttribute("users_birthdayYear", vo.getUsers_birthdayYear());
      model.addAttribute("users_birthdayMonth", vo.getUsers_birthdayMonth());
      model.addAttribute("users_birthdayDay", vo.getUsers_birthdayDay());
      model.addAttribute("users_gender", vo.getUsers_gender());
      model.addAttribute("users_zipcode", vo.getUsers_zipcode());
      model.addAttribute("users_address", vo.getUsers_address());
      model.addAttribute("users_addresss", vo.getUsers_addresss());
      model.addAttribute("users_addressDetail", vo.getUsers_addressDetail());
      model.addAttribute("users_phone", vo.getUsers_phone());
      model.addAttribute("users_email", tomail);
      model.addAttribute("idCHK", idCHK);

      response_email.setContentType("text/html; charset=UTF-8");
      PrintWriter out_email = response_email.getWriter();
      out_email.println("<script>alert('�̸����� �߼۵Ǿ����ϴ�. ������ȣ�� �Է����ּ���.');</script>");
      out_email.flush();

      return mv;
   }

   // �̸��Ϸ� ���� ������ȣ�� �Է��ϰ� ���� ��ư�� ������ ���εǴ� �޼ҵ�.
   // ���� �Է��� ������ȣ�� ���Ϸ� �Է��� ������ȣ�� �´��� Ȯ���ؼ� ������ ȸ������ �������� �Ѿ��, Ʋ���� �ٽ� ���� ��������
   // ���ƿ��� �޼ҵ�
   @RequestMapping(value = "/emailCheckInjeung", method = RequestMethod.POST)
   public ModelAndView emailCheckInjeung(UsersVO vo, String idCHK, Model model, String requestDice, String dice,
         HttpServletResponse response_equals) throws IOException {

      // �������̵��� �ڷḦ ���ÿ� �ϱ����� ModelAndView�� ����ؼ� �̵��� �������� �ڷḦ ����
      ModelAndView mv = new ModelAndView();
      mv.setViewName("/user/realSignup");
      mv.addObject("requestDice", requestDice);

      if (requestDice.equals(dice)) {
         // ������ȣ�� ��ġ�� ��� ������ȣ�� �´ٴ� â�� ����ϰ� ȸ������â���� �̵���
         mv.setViewName("/user/realSignup");
         mv.addObject("requestDice", requestDice);

         model.addAttribute("users_id", vo.getUsers_id());
         model.addAttribute("users_pass", vo.getUsers_pass());
         model.addAttribute("users_passChk", vo.getUsers_passChk());
         model.addAttribute("users_name", vo.getUsers_name());
         model.addAttribute("users_birthday", vo.getUsers_birthday());
         model.addAttribute("users_birthdayYear", vo.getUsers_birthdayYear());
         model.addAttribute("users_birthdayMonth", vo.getUsers_birthdayMonth());
         model.addAttribute("users_birthdayDay", vo.getUsers_birthdayDay());
         model.addAttribute("users_gender", vo.getUsers_gender());
         model.addAttribute("users_zipcode", vo.getUsers_zipcode());
         model.addAttribute("users_address", vo.getUsers_address());
         model.addAttribute("users_addresss", vo.getUsers_addresss());
         model.addAttribute("users_addressDetail", vo.getUsers_addressDetail());
         model.addAttribute("users_phone", vo.getUsers_phone());
         model.addAttribute("users_email", vo.getUsers_email());
         model.addAttribute("idCHK", idCHK);

         // ���� ������ȣ�� ���ٸ� �̸����� ȸ������ �������� ���� �Ѱܼ� �̸����� �ѹ��� �Է��� �ʿ䰡 ���� �Ѵ�.
         response_equals.setContentType("text/html; charset=UTF-8");
         PrintWriter out_equals = response_equals.getWriter();
         out_equals.println("<script>alert('������ȣ�� ��ġ�Ͽ����ϴ�.');</script>");
         out_equals.flush();
         return mv;

      } else if (requestDice != dice) {
         ModelAndView mv2 = new ModelAndView();
         mv2.setViewName("/user/email_injeung");

         response_equals.setContentType("text/html; charset=UTF-8");
         PrintWriter out_equals = response_equals.getWriter();
         out_equals.println("<script>alert('������ȣ�� ��ġ�����ʽ��ϴ�. ������ȣ�� �ٽ� �Է����ּ���.'); history.go(-1);</script>");
         out_equals.flush();
         return mv2;
      }
      return mv;
   }
   
   @RequestMapping(value = "/emailSendInjeungUpdate", method = RequestMethod.POST)
   public ModelAndView sendInjeungUpdate(UsersVO vo, String idCHK, Model model, HttpServletRequest request,
         String emailSendInjeung, HttpServletResponse response_email) throws IOException {
      Random r = new Random();
      int dice = r.nextInt(4589362) + 49311; // �̸��Ϸ� �޴� �����ڵ� �κ� (����)

      String setfrom = "korea78245@gamil.com";
      String tomail = request.getParameter("emailSendInjeung"); // �޴� ��� �̸���
      String title = "ȸ������ ���� �̸��� �Դϴ�."; // ����
      String content = System.getProperty("line.separator") + // ���پ� �ٰ����� �α�����
                                                // �ۼ�
            System.getProperty("line.separator") + "�ȳ��ϼ��� ȸ���� ���� Ȩ�������� ã���ּż� �����մϴ�"
            + System.getProperty("line.separator") + System.getProperty("line.separator") + " ������ȣ�� " + dice
            + " �Դϴ�. " + System.getProperty("line.separator") + System.getProperty("line.separator")
            + "������ ������ȣ�� Ȩ�������� �Է��� �ֽø� �������� �Ѿ�ϴ�."; // ����
      try {
         MimeMessage message = mailSender.createMimeMessage();
         MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

         messageHelper.setFrom(setfrom); // �����»�� �����ϸ� �����۵��� ����
         messageHelper.setTo(tomail); // �޴»�� �̸���
         messageHelper.setSubject(title); // ���������� ������ �����ϴ�
         messageHelper.setText(content); // ���� ����

         mailSender.send(message);
      } catch (Exception e) {
         System.out.println(e);
      }

      ModelAndView mv = new ModelAndView(); // ModelAndView�� ���� �������� �����ϰ�, ����
                                    // ���� �����Ѵ�.
      mv.setViewName("/user/email_injeung_update"); // �����̸�
      mv.addObject("dice", dice);
        
      
      model.addAttribute("email", tomail);
      

      response_email.setContentType("text/html; charset=UTF-8");
      PrintWriter out_email = response_email.getWriter();
      out_email.println("<script>alert('�̸����� �߼۵Ǿ����ϴ�. ������ȣ�� �Է����ּ���.');</script>");
      out_email.flush();

      return mv;
   }
   
   @RequestMapping("/user/emailUpdate")
   public void emailInjeungUpdate(UsersVO vo, Model model) {
      
      model.addAttribute("users_email", vo.getUsers_email());
      
   }

   // �̸��Ϸ� ���� ������ȣ�� �Է��ϰ� ���� ��ư�� ������ ���εǴ� �޼ҵ�.
   // ���� �Է��� ������ȣ�� ���Ϸ� �Է��� ������ȣ�� �´��� Ȯ���ؼ� ������ ȸ������ �������� �Ѿ��, Ʋ���� �ٽ� ���� ��������
   // ���ƿ��� �޼ҵ�
   @RequestMapping(value = "/emailCheckInjeungUpdate", method = RequestMethod.POST)
   public ModelAndView emailCheckInjeungUpdate(UsersVO vo, String idCHK, Model model, String requestDice, String dice,
         HttpServletResponse response_equals) throws IOException {

      // �������̵��� �ڷḦ ���ÿ� �ϱ����� ModelAndView�� ����ؼ� �̵��� �������� �ڷḦ ����
      ModelAndView mv = new ModelAndView();
      mv.setViewName("/user/mypageRead");
      mv.addObject("requestDice", requestDice);

      if (requestDice.equals(dice)) {
         // ������ȣ�� ��ġ�� ��� ������ȣ�� �´ٴ� â�� ����ϰ� ȸ������â���� �̵���
         mv.setViewName("/user/mypageRead");
         mv.addObject("requestDice", requestDice);
         
         model.addAttribute("users_email", vo.getUsers_email());
         System.out.println(vo.getUsers_email());
         // ���� ������ȣ�� ���ٸ� �̸����� ȸ������ �������� ���� �Ѱܼ� �̸����� �ѹ��� �Է��� �ʿ䰡 ���� �Ѵ�.
         response_equals.setContentType("text/html; charset=UTF-8");
         PrintWriter out_equals = response_equals.getWriter();
         out_equals.println("<script>alert('������ȣ�� ��ġ�Ͽ����ϴ�.'); window.close(); </script>");
         out_equals.flush();
         return mv;

      } else if (requestDice != dice) {
         ModelAndView mv2 = new ModelAndView();
         mv2.setViewName("/user/email_injeung_update");

         response_equals.setContentType("text/html; charset=UTF-8");
         PrintWriter out_equals = response_equals.getWriter();
         out_equals.println("<script>alert('������ȣ�� ��ġ�����ʽ��ϴ�. ������ȣ�� �ٽ� �Է����ּ���.'); history.go(-1);</script>");
         out_equals.flush();
         return mv2;
      }
      return mv;
   }
}