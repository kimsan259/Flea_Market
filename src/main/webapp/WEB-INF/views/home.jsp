<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

  <!-- Custom styles for this template -->
  <link href="css/shop-homepage.css" rel="stylesheet">
  <script type="text/javascript">
  	
  	//게시판 자세히
  	function fn_readView(NUMBER) {
		location.href="/readView?NUMBER=" + NUMBER;
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



<c:forEach var="tb_board" items="${tb_boards}">

          <div class="col-lg-4 col-md-6 mb-4">
            <div style="cursor: pointer;" onclick="fn_readView(${tb_board.NUMBER})" class="card h-100">
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
    <script src="<c:url value="/resources/js/raphael.min.js" />">
  </body>
</html>
