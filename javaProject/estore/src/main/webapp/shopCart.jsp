<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>购物车页面</title>
<link rel="stylesheet" href="css/fullCar.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<script type="text/javascript">
	function delShopCart(id) {
		alert('删除成功！');
		window.location.href="DeleteShopCartServlet?id="+id;
	}
	
	function numAdd(id) {
		var num=document.getElementById("num").value;
		num = parseInt(num)+1;
		window.location.href="UpdateShopCartServlet?id="+id+"&num="+num;
	}
	
	// 购物车中书本数量-1
	function numReduce(id) {
		// 获取当前标签对象 的文本值
		var num=document.getElementById("num").value;
		if (num > 1) {
			// parseInt()  js函数：将字符串解析成数字
			num = parseInt(num)-1;
			
			// 购物车中书本数量发生改变时，发送请求到后台，改变其数量
			window.location.href="UpdateShopCartServlet?id="+id+"&num="+num;
		}
	}
	
	function numChange(id) {
		var num=document.getElementById("num").value;
		if (num<1) {
			alert("输入数量不合法！");
		}else if(num>10){
			alert("对不起！每位用户限购10本！");
		}else{
			window.location.href="UpdateShopCartServlet?id="+id+"&num="+num;
		}
	} 

	window.onload = function() {

		var subtotal = document.getElementsByClassName("subtotal");
		var sum = 0;
		console.log(subtotal[0].innerHTML);
		for (var i = 0; i < subtotal.length; i++) {
			sum += parseFloat(subtotal[i].innerHTML);
			// console.log("sum = " + sum);
		}
		// 去结算时，将总计传入后台
		// document.getElementById("confirm").href = "SaveOrderServlet?total=" + sum;

		// 总价 = 每本的小计之和
		document.getElementById("total").innerHTML = "￥" + sum + "元";
	}
</script>
</head>
<body>
	<!--顶部-->
	<div class="top">
		<div class="top_center">
			<ul class="top_bars">
				<li><a href="#"><font color="red"></font></a></li>
				<li><a href="index.jsp">退出</a>|</li>
				<li><a href="orders.html">我的订单<span class="jt_down"></span></a>|</li>
				<li><a href="index.jsp">关注杰普<span class="jt_down"></span></a>|</li>
				<li><a href="#">网站导航<span class="jt_down"></span></a></li>
			</ul>
		</div>
	</div>
	<!--头部-->
	<div class="header3">
		<a href="index.jsp"><img src="images/logo.png" class="oneImg"></a>

		<div class="h3_right">
			<img src="images/play_01.png" alt="">
		</div>

	</div>
	<!--中间部分div-->
	<div class="empty">
		<div class="peisong">
			<pre>全部商品  </pre>
		</div>
		<div class="mainCenter">
			<!-- 购物车为空，跳到首页 -->

			<div class="allCheck">
				<input type="checkbox">
				<p>全选</p>
				<p class="leftM">商品</p>
				<p class="rightM">单价(元)</p>
				<p class="leftM">数量</p>
				<p class="leftM">小计(元)</p>
				<p class="leftM">操作</p>
			</div>
			<div class="mainPro">
				<div class="aa">
					<input type="checkbox"><span id="but">自营</span>
				</div>

				<!-- 遍历购物车 -->
				<c:forEach items="${cars }" var="car">
					<div class="bb">
						<input type="checkbox" checked />
						<c:forEach items="${car.lines }" var="lin">
							<img src="images/book_shopCart/${lin.book.image }">
								<span> ${lin.book.name }<br>
							</span>
							<div class="mm">
								<span>￥${lin.book.price }</span>
							</div>
						</c:forEach>
						<div>
							<dl>
							<!-- 点击数量 -->
								<dd class="dd1" onclick="numReduce(${lin.book.id})">-</dd>
							</dl>
							<input id="num1" type="text" value="${lin.num }" onblur="numChange(${lin.book.id})" />
							<dl>
								<dd class="dd2" onclick="numAdd(${lin.book.id})">+</dd>
							</dl>
						</div>
						<div class="ri">
							<font class="subtotal">￥${car.total}</font>
						</div>
						<div class="righ">
							<div class="rig" style="cursor: pointer;"
								onclick="delShopCart(${lin.book.id})">删除</div>
						</div>
					</div>
				</c:forEach> 



				<!-- <div class="bb">
					<input type="checkbox">
					<img src="images/book_shopCart/book4.jpg" >
					 <span>
	                  		 Spring Boot实战
	                    <br>
	                </span>
	                <div class="mm">
	                	<span>¥ 59</span>
	                </div>
	                <div>
	                	<dl>
	                		<dd class="dd1" onclick="numReduce(4,2)">-</dd>
	                	</dl>
                    	<input id="num2" type="text" value="2" onblur="numChange(4,2)" />
                    	<dl>
	                		<dd class="dd2" onclick="numAdd(4,2)">+</dd>
	                	</dl>
                    </div>
	                <div class="ri">
						￥<font class="subtotal">59</font>
					</div>
	               	<div class="righ">
	               		 <div class="rig" style="cursor: pointer;" onclick="delShopCart('4')" >删除</div>
	               	</div>
				</div> -->
			</div>
			<div class="allButtom">
				<p class="caozuo">
					<a href="confirm.jsp" id="confirm">去结算</a>
				</p>
				<span>已选择 
				<c:forEach items="${cars }" var="car">
					<font>${car.num }</font>
				</c:forEach>
					
					件商品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价(不含运费)：
					<font id="total"><%-- ${ } --%></font>
				</span>
			</div>
		</div>
	</div>
	<!--脚部-->

	<div class="footer3">
		<div class="f3_top">
			<div class="f3_center">
				<div class="ts1">品目繁多 愉悦购物</div>
				<div class="ts2">正品保障 天天低价</div>
				<div class="ts3">极速物流 特色定制</div>
				<div class="ts4">优质服务 以客为尊</div>
			</div>
		</div>
		<div class="f3_middle">
			<ul class="f3_center">
				<li class="f3_mi_li01">
					<h1>购物指南</h1>
					<p>常见问题</p>
					<p>找回密码</p>
					<p>会员介绍</p>
					<p>购物演示</p>
				</li>
				<li class="f3_mi_li01">
					<h1>配送方式</h1>
					<p>常见问题</p>
					<p>找回密码</p>
					<p>会员介绍</p>
					<p>购物演示</p>
				</li>
				<li class="f3_mi_li01">
					<h1>支付方式</h1>
					<p>常见问题</p>
					<p>找回密码</p>
					<p>会员介绍</p>
					<p>购物演示</p>
				</li>
				<li class="f3_mi_li01">
					<h1>售后服务</h1>
					<p>常见问题</p>
					<p>找回密码</p>
					<p>会员介绍</p>
					<p>购物演示</p>
				</li>
				<li class="f3_mi_li01">
					<h1>服务保障</h1>
					<p>常见问题</p>
					<p>找回密码</p>
					<p>会员介绍</p>
					<p>购物演示</p>
				</li>
				<li class="f3_mi_li06">
					<h1>客服中心</h1> <img src="images/qrcode_jprj.jpg" width="80px"
					height="80px">
					<p>抢红包、疑问咨询、优惠活动</p>
				</li>
			</ul>
		</div>
		<div class="f3_bottom">
			<p class="f3_links">
				<a href="#">关于我们</a>| <a href="#">联系我们</a>| <a href="#">友情链接</a>| <a
					href="#">供货商入驻</a>
			</p>
			<p>沪ICP备14033591号-8 杰普briup.com版权所有 杰普软件科技有限公司</p>
			<img src="images/police.png">
		</div>
	</div>
</body>
</html>
