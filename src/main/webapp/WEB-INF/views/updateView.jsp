<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>게시판</title>
	</head>
	<script type="text/javascript">
	
	</script>
	<body>
	
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<nav>
			  홈 - 글 작성
			</nav>
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post" action="/update">
					<input type="hidden" name="NUMBER" value="${update.NUMBER}" readonly="readonly"/>
					<table>
						<tbody>
							<tr>
								<td>
									<label for="TITLE">제목</label><input type="text" id="TITLE" name="TITLE" value="${update.TITLE}"/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="PRICE">가격</label><textarea id="PRICE" name="PRICE"><c:out value="${update.PRICE}" /></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="REGION">지역</label><textarea id="REGION" name="REGION"><c:out value="${update.REGION}" /></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="CONTENT">내용</label><textarea id="CONTENT" name="CONTENT"><c:out value="${update.CONTENT}" /></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="MASTER_EMAIL">작성자이메일</label><input type="text" id="MASTER_EMAIL" name="MASTER_EMAIL" value="${update.MASTER_EMAIL}" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td>
									<label for="REGIST_DATE">작성날짜</label>
									<input type="text" name="REGIST_DATE" value="${update.REGIST_DATE}" />					
								</td>
							</tr>		
						</tbody>			
					</table>
					<div>
						<button type="submit" class="update_btn">저장</button>
						<button type="submit" class="cancel_btn" onclick="javascript:history.back();">취소</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>