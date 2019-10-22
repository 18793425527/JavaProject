<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>briup电子商务-注册页</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="container2">
    	<div class="header2">
        	<div>
            	<a href="index.jsp"><img class="logo" src="images/logon_register.png"></a>
            </div>
            <div>
            	<ul class="tabs">
                	<li class="phone current">用手机登陆</li>
                </ul>
            </div>
            <div class="tlg">
            	已有账号 <a href="login.jsp">登录</a>
            </div>
        </div>
        <div class="content2">
        <font style="color:red;margin-left:210px;margin-top: 25px ;font-size: 15px;">${msg }</font>
        <form action="RegisterServlet" method="post">
			<ul class="reg_box">
            	<li id="li_name">
                	<span><b>*</b>用户名：</span>
                    <input id="name" required type="text" name="username" value="" placeholder="请输入2~4位用户名" />
                </li>
               <li>
                	<span><b>*</b>密码：</span>
                    <input id="password" required type="password" name="password" value="" placeholder="请输入6~8位密码"/>
                </li>
                <li>
                	<span><b>*</b>邮编：</span>
                    <input type="text" required name="zip" placeholder="请输入邮编"/>
                </li>
                <li>
                	<span><b>*</b>地址：</span>
                    <input type="text" required name="address" placeholder="请输入地址信息"/>
                </li>
                <li>
                	<span><b>*</b>电话：</span>
                    <input type="text" required name="phone" placeholder="请输入电话号码"/>
                </li>
                <li>
                	<span><b>*</b>电子邮箱：</span>
                    <input  type="text" required name="email" placeholder="请输入合法邮箱地址"/>
                </li>
              
            </ul>
			<p>
            	<input id="aaa" type="checkbox" name="chBox" value="true" checked/>
               	 我已阅读并同意
                <a href="#">用户注册协议</a>
            </p>
            <input class="btn_submit" required type="submit"  onclick="return check();"/>
           </form>
        </div>
   	</div>
</body>
<script type="text/javascript">

	function check() {
		var name = document.getElementById("name").value;
		var password = document.getElementById("password").value;
		var aaa = document.getElementById("aaa").checked;
		if (name == "") {
			alert("请输入用户名！！！");
			return false;
		}
		if (password == "") {
			alert("请输入密码！！！");
			return false;
		}
		if(aaa == ""){
			alert("请查看用户注册协议");
			return false;
		}
	}
	
</script>
</html>
