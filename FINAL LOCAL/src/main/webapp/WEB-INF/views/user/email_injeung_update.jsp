<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-MAIL</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<style>
#e-mail{
   text-align:center;
}
* {
   font-family: 'Comic Sans MS';
   font-family: '@여기어때 잘난체';
}

body {
   background: black;
}

#e-maliPage1{
   background: white;
   margin: 15px;
   
}

h3{
   color:red;
}

button {
   width: 400px;
   height: 60px;
   font-size: 30px;
   cursor: pointer;
   background: #e6bbea;
   color: white;
   border: none;
   border-radius: 3px 3px 3px 3px;
   cursor: pointer;
   text-align:center;
   margin-bottom:20px;
}

input{
   height: 25px;
   
}

button:active {
   background: #cca6cf;
}

input::placeholder {
   text-align: center;
}
</style>
</head>
<body>
<div id="e-maliPage1">
   <form action="/emailCheckInjeungUpdate" method="post" name="frm">
   <div id="e-mail">
      
      <img id="logoimg" src="../display?fileName=gif7.gif" width=200>
         <h1>인증번호 입력</h1>
         <h4>(인증번호가 일치해야 다음 단계로 넘어가실 수 있습니다.)</h4>
         <h3>전송받은 인증번호를 입력하여 주세요!</h3>
         <input type="text" name="requestDice" value="" size=40 placeholder="인증번호">
         <input type="hidden" value="${dice}" name="dice">
      <br><br>
      <div>
         
         <input type="hidden" name="users_email" value="${email}">

      </div>
      <button type="submit">인증번호 입력</button>
      </div>
   </form>
   </div>
</body>
<script>

    var users_email="${email}";
   $(frm).submit(function(){
      opener.parent.$("#users_email").val(users_email);
   });
</script>
</html>