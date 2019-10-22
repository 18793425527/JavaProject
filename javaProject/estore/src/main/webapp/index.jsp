<%@page import="com.briup.bean.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>briup电子商务-首页</title>
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="css/swiper.min.css">
<link rel="stylesheet" href="css/floatView.css" />
</head>
<body>
	<!--顶部-->
	<div class="top">
		<div class="top_center">

			<ul class="top_bars">

				<ul class="top_lr">
					<c:choose>
						<c:when test="${!empty customer.getUsername() }">
							<li>欢迎登录<a href="user/userinfo.jsp" style="color: red;">${sessionScope.customer.getUsername()}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="login.jsp">亲，请您登录</a></li>
							<li><a href="register.jsp">免费注册</a></li>
						</c:otherwise>
					</c:choose>
				</ul>


				<li><a href="ShowOrdersServlet">我的订单<span class="jt_down"></span></a>|</li>

				<li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
				<li><a href="#">网站导航<span class="jt_down"></span></a></li>
			</ul>
		</div>
	</div>
	<!--头部-->

	<div class="header3">
		<a href="index.jsp"><img src="images/logo.png"></a>
		<div class="h3_center">
			<div class="search_box">
				<input type="text" id="keyword" name="keyword" /> <span
					style="cursor: pointer;" onclick="search()">搜索</span>
			</div>
			<p>
				<a href="#">文学类</a>| <a href="#">教育类</a>| <a href="#">计算机</a>| <a
					href="#">儿童类</a>| <a href="#">漫画类</a>|
			</p>
		</div>

		<div class="h3_right">
			<c:if test="${!empty customer }">
				<div class="myyy">
					<a href="user/userinfo.jsp">个人信息<span class="sj_down"></span></a>
				</div>
				<div class="tsc">
					<a href="/estore/shopCart.jsp">去购物车结算</a> <span class="sj_right">
					</span>
				</div>
			</c:if>
		</div>
	</div>
	<!--头部导航-->
	<div class="nav_top">
		<div class="nav_top_center">
			<div>全部图书分类</div>
			<ul>

				<li><a href="list.html">文学类</a></li>
				<li><a href="list.html">教育类</a></li>
				<li><a href="list.html">计算机</a></li>
				<li><a href="list.html">儿童类</a></li>
				<li><a href="list.html">漫画类</a></li>
			</ul>
		</div>
	</div>
	<!-- 分类栏 -->
	<div class="container3">
		<div class="c3_b1">
			<div class="c3_b1_left">
				<dl>
					<c:forEach items="${category }" var="cate">
						<dd>
							<h1>${cate.name }</h1>
							<p>
								<c:forEach items="${cate.cates }" var="cat">
									<a href="#">${cat.name }</a>
								</c:forEach>
								<!-- <a href="#">散文</a> <a href="#">小说</a> <a
								href="#">剧本</a> -->
							</p>
						</dd>
					</c:forEach>
					<!-- <dd>
						<h1>教育类</h1>
						<p>
							<a href="#">高中</a> <a href="#">大学</a> <a href="#">考研</a> <a
								href="#">考公</a>
						</p>
					</dd>
					<dd>
						<h1>计算机</h1>
						<p>
							<a href="#">硬件</a> <a href="toShowBookServlet">编程</a> <a href="#">智能</a>
						</p>
					</dd>
					<dd>
						<h1>儿童类</h1>
						<p>
							<a href="#">益智</a> <a href="#">生活</a> <a href="#">特长</a> <a
								href="#">饮食</a>
						</p>
					</dd>
					<dd>
						<h1>漫画类</h1>
						<p>
							<a href="#">科幻</a> <a href="#">灾难</a> <a href="#">冒险</a> <a
								href="#">运动</a>
						</p>
					</dd>
					<dd>
						<h1>工具书</h1>
						<p>
							<a href="#">农业</a> <a href="#">金融</a> <a href="#">医药</a>
						</p>
					</dd>
					<dd class="last">
						<h1>期刊</h1>
						<p>
							<a href="#">会计</a> <a href="#">煤炭</a> <a href="#">软件</a> <a
								href="#">图像</a>
						</p>
					</dd> -->

				</dl>
			</div>
			<!-- 轮播图  -->
			<div class="c3_b1_center">
				<div class="bannerBox">
					<div class="swiper-container" id="swiper1">
						<div class="swiper-wrapper swiper-no-swiping">
							<div class="swiper-slide color-red">
								<img src="images/index/index1.jpg">
							</div>
							<div class="swiper-slide color-blue">
								<img src="images/index/index2.jpg">
							</div>
							<div class="swiper-slide color-yellow">
								<img src="images/index/index3.jpg">
							</div>
							<div class="swiper-slide color-green">
								<img src="images/index/index4.jpg">
							</div>
							<div class="swiper-slide color-pink">
								<img src="images/index/index5.jpg">
							</div>
							<div class="swiper-slide color-pink">
								<img src="images/index/index6.jpg">
							</div>
							<div class="swiper-slide color-pink">
								<img src="images/index/index7.jpg">
							</div>
							<div class="swiper-slide color-pink">
								<img src="images/index/index8.jpg">
							</div>
							<div class="swiper-slide color-pink">
								<img src="images/index/index9.jpg">
							</div>
							<div class="swiper-slide color-pink">
								<img src="images/index/index10.jpg">
							</div>
							<div class="swiper-slide color-pink">
								<img src="images/index/index11.jpg">
							</div>
						</div>
					</div>

					<div class="left">
						<img src="images/index/bannerL.png">
					</div>
					<div class="right">
						<img src="images/index/bannerR.png">
					</div>
				</div>

				<div class="c3_b1_c_bottom">
					<ul>
						<li><a href="viewBook.html"><img
								src="images/index/index_bottom1.jpg" /></a> <a href="viewBook.html">区块链开发指南</a></li>
						<li><a href="viewBook.html"><img
								src="images/index/index_bottom2.jpg" /></a> <a href="viewBook.html">Python程序设计</a></li>
						<li><a href="viewBook.html"><img
								src="images/index/index_bottom3.jpg" /></a> <a href="viewBook.html">Java从入门到精通</a></li>
					</ul>
				</div>
			</div>
			<div class="c3_b1_right">
				<h1>
					杰普快报<a href="#">更多</a>
				</h1>
				<ul>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
					<li><a href="#">〈加措〉相信这一切都是对我们最好的安排 </a></li>
				</ul>
			</div>
			<div style="clear: both"></div>
		</div>
		<div class="c3_b2">
			<ul>
				<c:forEach items="${bookes }" var="book">
					<li>
						<div class="c3_b2_txt">
							<a href="ToViewBookServlet?bookId=${book.id }"> 
								<img alt=""
									src="images/book_index/${book.image }" />
									<h1>${book.name }</h1>
									<h2>分类：${book.category.name }</h2>
									<h2>价格：${book.price }</h2>
									<h2>畅销书籍</h2>
									<h2>覆盖java基础及全新内容</h2>
									<p>
										<a href="ToViewBookServlet?bookId=${book.id }">更多精彩，点击进入</a>
									</p>
							</a>
						</div>
					</li>
				</c:forEach>
				<!-- 
				<li>
					<div class="c3_b2_txt">
						<a href="viewBook.html"> <img alt=""
							src="images/book_index/book2.jpg">
							<h1>Spring Cloud微服务实战</h1>
							<h2>分类：unix</h2>
							<h2>价格：21</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="viewBook.html"> <img alt=""
							src="images/book_index/book3.jpg">
							<h1>Python核心编程</h1>
							<h2>分类：数据库</h2>
							<h2>价格：108</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="viewBook.html"> <img alt=""
							src="images/book_index/book4.jpg">
							<h1>Spring Boot实战</h1>
							<h2>分类：jsp</h2>
							<h2>价格：59</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="viewBook.html"> <img alt=""
							src="images/book_index/book5.jpg">
							<h1>JAVA SERVLET 编程(第二版)</h1>
							<h2>分类：java</h2>
							<h2>价格：38</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="ViewBookServlet?id=6"> <img alt=""
							src="images/book_index/book6.jpg">
							<h1>XML 高级编程(第2版)</h1>
							<h2>分类：xml</h2>
							<h2>价格：97</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="viewBook.html"> <img alt=""
							src="images/book_index/book7.jpg">
							<h1>精通STRUTS:基于MVC的JAVA WEB设计与开发</h1>
							<h2>分类：java</h2>
							<h2>价格：36</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="ViewBookServlet?id=8"> <img alt=""
							src="images/book_index/book8.jpg">
							<h1>精通HIBERNATE：JAVA对象持久化技术详解</h1>
							<h2>分类：java</h2>
							<h2>价格：44</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="viewBook.html"> <img alt=""
							src="images/book_index/book9.jpg">
							<h1>SPRING IN ACTION中文版</h1>
							<h2>分类：spring</h2>
							<h2>价格：29</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>

				<li>
					<div class="c3_b2_txt">
						<a href="viewBook.html"> <img alt=""
							src="images/book_index/book10.jpg">
							<h1>精通EJB 3.0</h1>
							<h2>分类：javaee</h2>
							<h2>价格：44</h2>
							<h2>畅销书籍</h2>
							<h2>覆盖java基础及全新内容</h2>
							<p>
								<a href="#">更多精彩，点击进入</a>
							</p>
						</a>
					</div>
				</li>
 -->
			</ul>
		</div>
	</div>
	<div class="c20"></div>
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

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script src="js/swiper.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/floatView.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/search.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
