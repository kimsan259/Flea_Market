<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	 	<title>게시판</title>
	</head>
		<script type="text/javascript">
		var formObj=null;
		var a=null;
		$(document).ready(function(){
		
			var formObj = $("form[name='readForm']");
			var a="dddd";
			console.log(formObj);
			console.log(a);
			
			// 수정 
			/* $(".update_btn").on("click", function(){
				alert("updatebtn")
				formObj.attr("action", "/updateView");
				formObj.attr("method", "get");
				formObj.submit();				
			}) */
			
			// 삭제
			/* $(".delete_btn").on("click", function(){
				formObj.attr("action", "/delete");
				formObj.attr("method", "post");
				formObj.submit();
			}) */
			
			// 목록
			$(".list_btn").on("click", function(){
				
				location.href= "/home";
			})
		})
		
		
		//게시판 수정뷰
		function fn_updateView(number) {
			location.href="/updateView?NUMBER=" + number;
		}
		
		//게시판 삭제
		function fn_deleteBoard(number) {
			if (confirm('삭제하시겠습니까?')) {
				location.href="/delete?NUMBER=" + number;
			}
		}
		
		//댓글 작성
		function fn_reply() {
			var r_content = $("#R_CONTENT").val();
			var boardNumber = $("#NUMBER").val();
			
			if (confirm('댓글 등록 하시겠습니까?')) {
				
				location.href="/reply?CONTENT=" +  r_content + "&BOARD_NUMBER=" + boardNumber;
			}
			
		}
		
		//댓글 수정
		function fn_replyUpdate(number) {
			
			var form = $("#formReply");
//			var content = $("#R_CONTENT_U").val();
			
//			form.attr('action', '/replyUpdate?CONTENT=' + content + '&NUMBER=' + number);

			if (confirm('댓글 수정 하시겠습니까?')) {
				form.attr('action', '/replyUpdate?NUMBER=' + number);
				form.submit();
			}
			
		}
		
		//댓글 삭제
		function fn_replyDelete(number) {
			var form = $("#formReply");
			
			if (confirm('댓글 삭제 하시겠습니까?')) {
				form.attr('action', "/replyDelete?NUMBER=" +  number);
				form.submit();
			}
		}
		
	</script>
	<body>
		<h2>게시물 보기 </h2> 
	
		<div class="input-group input-group-sm" role="group" style = "text-align:left">
		<table class="table table-striped table-bordered">
		<tread>
		<tr>
		<td><input type = "hidden" id="NUMBER" name="NUMBER" value="${read.NUMBER}" class="form-control" aria-describedby="basic-addon1" class="form-control" aria-describedby="basic-addon1" >글번호 : ${read.NUMBER}</td>
		</tr>
		 
		 <br><br>
		 
		<tr>
		<td><input id="TITLE" name="TITLE" value="${read.TITLE}" size="80"
		                     class="form-control" aria-describedby="basic-addon1" readonly="readonly" ></td></tr>
		                    
		                              
		<!-- placeholder은 제목을 입력할 수 있도록 도움말을 출력함 -->
		<tr>
		<div style="width:800px;">
		<td><textarea class="form-control" id="CONTENT" name="CONTENT"  rows="3" cols="80" 
		readonly="readonly">${read.CONTENT}</textarea></td>
		</div>
		</tr>

		<tr>
		<td><input id="PRICE" name="PRICE" value="${read.PRICE}" size="80"
		                     class="form-control" aria-describedby="basic-addon1" readonly="readonly" ></td></tr>
		                    

		<tr>
		<td><input id="REGION" name="REGION" value="${read.REGION}" size="80"
		                     class="form-control" aria-describedby="basic-addon1" readonly="readonly" ></td></tr>
		                    

		<tr>
		<td><input id="MASTER_NICKNAME" name="MASTER_NICKNAME" value="${read.MASTER_NICKNAME}" size="80"
		                     class="form-control" aria-describedby="basic-addon1" readonly="readonly" ></td></tr>
		                    

		<tr>
		<td><input id="MASTER_EMAIL" name="MASTER_EMAIL" value="${read.MASTER_EMAIL}" size="80"
		                     class="form-control" aria-describedby="basic-addon1" readonly="readonly" ></td></tr>
		                    

		<tr>
		<td>
		<input id="REGIST_DATE" value="${read.REGIST_DATE}" size="80"
		                     class="form-control" aria-describedby="basic-addon1" readonly="readonly" ></td></tr>
		                    

		</tread>
		</table>
		 
		</div>
		</div>
		 
	
		<div style = "width:700px; text-align:center;">
		    
		    <!-- 본인만 수정, 삭제 버튼을 표시한다. -->
		    <c:if test="${read.MASTER_NICKNAME eq sessionMember.NICKNAME}">
		    <div class="btn-group btn-group-sm" role="group" aria-label="...">
		    <div style = "text-align:center;" >
		    
		    <button type = "submit" class="btn btn-default update_btn" onclick="fn_updateView(${read.NUMBER})">수정</button>
		    <button type = "submit" class="btn btn-default delete_btn" onclick="fn_deleteBoard(${read.NUMBER})">삭제</button>
		    </div></div>
			</c:if>		   
		    
		    
		    <!-- 글목록은 본인이 아니어도 확인 가능하게 한다. -->
		        <div class="btn-group btn-group-sm" role="group" aria-label="...">
		    <div style = "text-align:center;" >
		    
		    <button type = "submit" class="btn btn-default list_btn">목록</button>
		    </div></div>
		    
		    <!-- 로그인이 되어있는 상태에서만 댓글 작성 버튼이 출력되도록 한다. -->
		    
		    <br><br><br>
		    
		    <hr>
		    
		    <h1>댓글 리스트</h1>
		    
		    <!-- 세션에 값이 없으면 가리기 -->
		    <c:if test="${sessionMember != null}">
		    <div class="btn-group btn-group-sm" role="group" aria-label="...">
		    <div style = "text-align:center;" >
		    <textarea rows = "5" cols = "80" id = "R_CONTENT" name = "CONTENT" class="form-control" aria-describedby="basic-addon1" placeholder="댓글을 입력하세요."></textarea>
		    </div></div>
		    <br>
		    
		    
		    <!-- 댓글쓰기 버튼을 클릭하면 위쪽에 있는 자바스크립트 구문이 실행되어서 컨트롤러로 맵핑됨 --><br><br>
		    <div class="btn-group btn-group-sm" role="group" aria-label="...">
		    <div style = "text-align:center;" >
		    
		    <button type = "button" id = "btnReply" class="btn btn-default" onclick="fn_reply()" >댓글쓰기</button>
		    </div></div>
		    </c:if>
		    
		    <!-- 댓글 목록 -->
		    <!-- 댓글이 등록이 되면 listReply에 댓글이 쌓이게 된다. -->
		    <div id = "listReply">
			 	
			 	<c:if test="${fn:length(replyList) == 0}">
					<h2>조회된 데이터가 없습니다.</h2>
				</c:if>
			
	 		<form method="POST" id="formReply">
	 		<input type="hidden" id="BOARD_NUMBER" name="BOARD_NUMBER" value="${read.NUMBER}">
	        <div class="input-group input-group-sm" role="group"
	            style="text-align: left; width: 800px">
	            <table class="table table-striped table-bordered" border="1"
	                width="800px" align="left">
	 
	 				
	                <c:forEach var="row" items="${replyList}">
	 				
	                    <tr>
	                        <td><br></td>
	                    </tr>
	 
	                    <tr>
	                        <td>닉네임 : ${row.MASTER_NICKNAME}</td>
	                    </tr>
	 
	                    <tr>
	                        <td>작성일자 : ${row.REGIST_DATE} 댓글번호 : ${row.NUMBER }</td>
	                    </tr>
	 
	                    <tr>
	                        <td>댓글 내용 : ${row.CONTENT}</td>
	                    </tr>
	 
	 
	                    <!-- 폼태그 안에 위쪽에 있는 자바스크립트 구문에 필요한 값들을 노출시키지 않게 하기 위해 hidden타입으로 값들을 전달한다. -->
	                    <!-- 본인일 경우에만 댓글 수정버튼과 댓글 삭제 버튼이 출력되도록 설정함 -->
	 					<c:if test="${row.MASTER_NICKNAME eq sessionMember.NICKNAME}">
	 						<!-- if절을 만들어서 댓글 수정버튼 클릭시 보여주게 설정 -->
	                        <input type="hidden" id="R_NUMBER" name="NUMBER" value="${row.NUMBER}">
	                        <input type="hidden" id="R_MASTER_NICKNAME" name="MASTER_NICKNAME" value="${row.MASTER_NICKNAME}"> 
	 
	                        <tr>
	                            <td>
	                                <div style="width: 800px;">
	                                    <textarea class="form-control" placeholder="수정할 내용을 입력하세요" id="R_CONTENT_U" name="CONTENT" rows="4" cols="80"></textarea>
	                                </div>
	                                <br>
	                            <br>
	                            </td>
	                        </tr>
	                        
	 
	 
		                    <div style="width: 700px; text-align: right;">
		 
		                            <tr>
		                                <td>
		                                    <div class="btn-group btn-group-sm" role="group" aria-label="...">
		                                        <div style="text-align: center;">
		                                            <button type="button" id="btn_reply_Update" class="btn btn-default" onclick="fn_replyUpdate(${row.NUMBER})">댓글 수정</button>
		                                            <button type="button" id="btn_reply_Delete" class="btn btn-default" onclick="fn_replyDelete(${row.NUMBER})">댓글 삭제</button>
		                                        </div>
		                                    </div>
		                                </td>
		                            </tr>
		                    </div>
	                    </c:if>
	                    <br>
	                    <br>
	                </c:forEach>
	            </table>
		    </div>
	        </form>
		    </div>
    </div>
    <!-- end -->
	<%-- 
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<nav>
			  홈 - 글 작성
			</nav>
			<hr />
			
			<div class="container">
				<div class="col-lg-5">
					<div class="container">
					
					</div>
				</div>
			</div>
			
			<section class="container">
					<table class="table table-haver" style="border: 1px solid #dddddd">
						<tbody>
							<tr>
								<td colspan="5" align="left" bgcolor="beige">
									<label for="NUMBER">글 번호</label><input type="text" id="NUMBER" name="NUMBER" value="${read.NUMBER}" readonly/>
								</td>
							</tr>	
							<tr>
								<td colspan="5" align="left" bgcolor="beige">
									<label for="TITLE">제목</label><input type="text" id="TITLE" name="TITLE" value="${read.TITLE}" readonly/>
								</td>
							</tr>	
							
							<tr>
								<td colspan="5" align="center" bgcolor="gray">
									<label for="CONTENT">내용</label><input type="text" id="CONTENT" name="CONTENT" value="${read.CONTENT}" readonly/>
								</td>
							</tr>	
							
							<tr>
								<td colspan="5" align="left" bgcolor="red">
									<label for="PRICE">가격</label><input type="text" id="PRICE" name="PRICE" value="${read.PRICE}" readonly/>
								</td>
							</tr>	
							<tr>
								<td colspan="5" align="left" bgcolor="red">
									<label for="REGION">지역</label><input type="text" id="REGION" name="REGION" value="${read.REGION}" readonly/>
								</td>
							</tr>	
							
							<tr>
								<td colspan="3" align="left">
									<label for="MASTER_NICKNAME">닉네임</label><input type="text" id="MASTER_NICKNAME" name="MASTER_NICKNAME" value="${read.MASTER_NICKNAME}" readonly>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="left">
									<label for="MASTER_EMAIL">작성자이메일</label><input type="text" id="MASTER_EMAIL" name="MASTER_EMAIL" value="${read.MASTER_EMAIL}" readonly/>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="right">
									<label for="REGIST_DATE">작성날짜</label>
									<fmt:formatDate value="${read.REGIST_DATE}" pattern="yyyy-MM-dd"/>	
									<input type="text" id="REGIST_DATE" value="${read.REGIST_DATE}" disabled>				
								</td>
							</tr>		
						</tbody>			
					</table>
					<div>
						<button type="submit" class="update_btn" onclick="fn_updateView(${read.NUMBER})">수정</button>
						<button type="submit" class="delete_btn" onclick="fn_deleteBoard(${read.NUMBER})">삭제</button>
						<button type="submit" class="list_btn">목록</button>	
					</div>
					<!-- 댓글 -->
						<div id="reply">
						  <ol class="replyList">
						    <c:forEach items="${replyList}" var="replyList">
						      <li>
						        <p>
						       	 작성자 : ${replyList.MASTER_NICKNAME}<br />
						       	 작성 날짜 :  <fmt:formatDate value="${replyList.REGIST_DATE}" pattern="yyyy-MM-dd" />
						        </p>
						
						        <p>${replyList.CONTENT}</p>
						      </li>
						    </c:forEach>   
						  </ol>
						</div>
					</section>
					
					<hr/>
					
					<!-- 댓글 페이징 기능 필요 없으면 삭제 디자인 넣어야됨.. -->
						<form id="replyForm" method="get">
						
						<!-- 조회된 댓글이 없습니다 추가 -->
						
						 <div>
						    <label for="writer">댓글 작성자</label><input type="text" id="writer" name="writer" />
						    <br/>
						    <label for="content">댓글 내용</label><input type="text" id="content" name="content" />
						  </div>
						  <div>
						     <button type="button" class="replyWriteBtn">작성</button>
						  </div>
						
						
						
						<input type="hidden" id="currentPageNo" name="currentPageNo" value="${paramVo.currentPageNo}">
						<input type="hidden" id="BOARD_NUMBER" name="BOARD_NUMBER" value="${paramVo.BOARD_NUMBER}">
						
						</form>
				
			</div> --%>
		</body>
	</html>