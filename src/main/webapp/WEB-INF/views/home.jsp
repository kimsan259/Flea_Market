<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>

  <!-- Custom styles for this template -->
  <link href="css/shop-homepage.css" rel="stylesheet">
  <script type="text/javascript">
  	
  	//게시판 자세히 (파라미터 수정 211121)
  	function fn_readView(NUMBER) {
		location.href="/readView?NUMBER=" + NUMBER;
	}
  	
  	function moveSearch(pageNo) {
  		var $form = $("#boardForm");
  		
  		$("#currentPageNo").val(pageNo);
  		
  		$form.attr("action", "/home");
  		$form.submit();
  	}
  	
  	function searchForm() {
		var $form = $("#boardForm");
  		
  		$("#currentPageNo").val(1);
  		
  		$form.attr("action", "/home");
  		$form.submit();
	}
  	
  </script>
</head>
   <body>
	<%@ include file="header.jsp" %>
	<div style="height:100px"></div>
  <!-- Page Content -->

  <!-- Page Content -->
  <div  style="margin-left:10px">

    <div class="row">

      <!-- /.col-lg-3 -->
      
      <div class="col-lg-9">

        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>

          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>

        <div class="row">
		<c:if test="${fn:length(tb_boards) == 0}">
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100">
					조회된 데이터가 없습니다.
				</div>
			</div>
		</c:if>
		<c:forEach var="tb_board" items="${tb_boards}">
		
		          <div class="col-lg-4 col-md-6 mb-4">
		            <div style="curser: pointer;" onclick="fn_readView(${tb_board.NUMBER})" class="card h-100">
		              <a href="#"><img  style="width:250px;height:100px"
		               src="resources\images\\still.png" alt="still.png"></a>
		               <span style="font-style: normal ; font-weight: bolder; font-size: 1.5em; line-height: 1.0em; color: red;font-family: arial;">
		              <h5> ${tb_board.REGION} </h5>
		</span>
		              <div class="card-body">
		                <h4 class="card-title">
		                  <a href="#"> ${tb_board.TITLE}  </a>
		                </h4>
		                <h5>${tb_board.PRICE}   $  </h5>
		                <p class="card-text">${tb_board.CONTENT}  </p>
		              </div>
		              <div class="card-footer">
		           		<small class="text-muted">${tb_board.MASTER_EMAIL}</small>
		              </div>
		            </div>
		          </div>
		</c:forEach>



        </div>
        <!-- /.row -->
		<form id="boardForm" method="get">
		<input type="hidden" id="currentPageNo" name="currentPageNo" value="${paramVo.currentPageNo}">
		<div class="">
	 
			<ul class="pagination">
				<!-- 페이지 리스트의 첫 페이지 번호가 1이 아닐때만 처음 버튼을 생성한다. -->
				<c:if test="${pagerMap['firstPageNoOnPageList'] ne 1}">
					<li class="page-item">
						<a class="page-link" href="#" onclick="moveSearch(1); return false;" >&lt;&lt;</a>
					</li>
				</c:if>
		
				<!-- 페이지 리스트의 첫 페이지 번호가 1이 아닐때만 이전 버튼을 생성한다. -->
				<c:if test="${pagerMap['firstPageNoOnPageList'] ne 1}">
					<li class="page-item">
						<a class="page-link" href="#" onclick="moveSearch(${pagerMap['prevPageNoOnPageList']}); return false;" >&lt;</a>
					</li>
				</c:if>
		
				<!-- 페이지리스트의 첫페이지 번호 begin, 페이지 리스트의 마지막 페이지 번호 end -->
				<c:forEach var="i" begin="${pagerMap['firstPageNoOnPageList']}" end="${pagerMap['lastPageNoOnPageList']}" step="1">
					<li class="page-item<%--  <c:if test="${i == pagerMap['currentPageNo']}"> on </c:if> --%>">
						<a class="page-link" href="#" onclick="moveSearch(${i}); return false;" >${i}</a>
					</li>
				</c:forEach>
		
				<!-- 마지막 페이지 번호가 총 페이지 개수 보다 작을 경우만 다음 버튼을 생성 한다. -->
				<c:if test="${pagerMap['lastPageNoOnPageList'] < pagerMap['totalPageCount']}">
					<li class="page-item">
						<a class="page-link" href="#" onclick="moveSearch(${pagerMap['nextPageNoOnPageList']}); return false;" >&gt;</a>
					</li>
				</c:if>
		
				<!-- 마지막 페이지 번호가 총 페이지 개수 보다 작을 경우만 맨끝 버튼을 생성 한다. -->
				<c:if test="${pagerMap['lastPageNoOnPageList'] < pagerMap['totalPageCount']}">
					<li class="page-item">
						<a class="page-link" href="#" onclick="moveSearch(${pagerMap['totalPageCount']}); return false;" >&gt;&gt;</a>
					</li>
				</c:if>
			</ul>
		
		
		</div>
		
		<div style="" class="box">
			<select id="searchType" name="searchType">
				<option value="TITLE" <c:if test="${paramVo.searchType eq 'TITLE'}">checked</c:if> >제목</option>
				<option value="CONTENT" <c:if test="${paramVo.searchType eq 'CONTENT'}">checked</c:if>>내용</option>
				<option value="REGION" <c:if test="${paramVo.searchType eq 'REGION'}">checked</c:if>>지역</option>
			</select>
			<input type="text" id="searchText" name="searchText" value="${paramVo.searchText}">
			<button type="button" onclick="searchForm()">검색</button>
		</div>

		</form>
      </div>
      <!-- /.col-lg-9 -->
    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->


	<div style="height:100px"></div>

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/resources/js/raphael.min.js" />"></script>
  </body>
</html>
