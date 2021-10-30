<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content=:text.html;charset="UTF-8">
<!-- 뷰포트 -->
<meta name="viewport" content="width=device-width" initial-scale="1">
<!--  스타일시트 참조 -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<title>Flea_Market</title>
</head>
<body>
	<!--  네비게이션  -->
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse]" data-target="#bs-example-navbar-collapse-1"
				aria-expaned="false">
				<span class="icon-bar"></span><span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home.jsp">Flea_Market</a>
		</div>
		<div class="collapse navbar-collapse"
			id="#bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="home.jsp">메인</a></li>
				<li><a href="writing.jsp">게시판</a></li>

			</ul>

			<ul class="new navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="active"><a
							href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a><li>
						
					</ul>
					</li>
			</ul>
		</div>
	</nav>
	<!-- 로긴폼 -->
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<!--  점보트론 -->
			<div class = "jumbotron" style = "padding-top: 20px;">
				<!--  로그인 정보를 숨기면서 전송 post -->
				<form method="post" action="joinaction">
					<h3 style="text-align: center;">회원가입</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이메일"
							name="EMAIL" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							name="PASSWORD" maxlength="/20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름"
							name="NAME" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="닉네임"
							name="NICKNAME" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="폰넘버"
							name="PHONENUMBER" maxlength="20">
					</div>
					
					<input type = "submit" class = "btn btn-primary form-control"
						value="회원가입">
						
				</form>
			</div>
		</div>
	</div>
	<!-- 애니메이션 담당 JQUERY -->
	<script src= "http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!--  부트스트랩  JS -->
	<script src = "resources/js/bootstrap.js"></script>
	
				

</body>
</html>