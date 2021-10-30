<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
	 	<title>게시판</title>
	</head>
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
				<form role="form" method="post">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="NUMBER">글 번호</label><input type="text" id="NUMBER" name="NUMBER" value="${read.NUMBER}" readonly/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="TITLE">제목</label><input type="text" id="TITLE" name="TITLE" value="${read.TITLE}" readonly/>
								</td>
							</tr>	
							
							<tr>
								<td>
									<label for="PRICE">가격</label><input type="text" id="PRICE" name="PRICE" value="${read.PRICE}" readonly/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="REGION">지역</label><input type="text" id="REGION" name="REGION" value="${read.REGION}" readonly/>
								</td>
							</tr>	
							
							<tr>
								<td>
									<label for="MASTER_NICKNAME">닉네임</label><textarea id="MASTER_NICKNAME" name="MASTER_NICKNAME" readonly><c:out value="${read.MASTER_NICKNAME}" /></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="MASTER_EMAIL">작성자이메일</label><input type="text" id="MASTER_EMAIL" name="MASTER_EMAIL" value="${read.MASTER_EMAIL}" readonly/>
								</td>
							</tr>
							<tr>
								<td>
									<label for="REGIST_DATE">작성날짜</label>
									<%-- <fmt:formatDate value="${read.REGIST_DATE}" pattern="yyyy-MM-dd"/>	 --%>
									<input type="text" id="REGIST_DATE" value="${read.REGIST_DATE}" disabled>				
								</td>
							</tr>		
						</tbody>			
					</table>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>