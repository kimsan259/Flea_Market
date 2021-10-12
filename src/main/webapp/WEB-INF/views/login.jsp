<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>벼룩시장</title>
</head>
<link href="resources/css/login.css" type="text/css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<script>
function btn_submit(){
	alert("회원가입으로 넘기기");
	location.href="/join";

}
</script>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<body>
   <div class="container">
      <div class="col-md-6 mx-auto text-center">
         <div class="header-title">
            <h1 class="wv-heading--title">
               	Flea market login window. 
            </h1>
            <h2 class="wv-heading--subtitle">
               	Password retrieval is an email authentication method.
            </h2>
         </div>
      </div>
      <div class="row">
         <div class="col-md-4 mx-auto">
            <div class="myform form ">
               <form action="loginaction" method="post" name="login">
               
               
               	  <div class="form-group">
                     <input type="email" name="EMAIL"  class="form-control my-input" id="email" placeholder="Email">
                  </div>
                  <div class="form-group">
                     <input type="password" name="PASSWORD"  class="form-control my-input" id="password" placeholder="Password">
                  </div>
                  <div class="text-center ">
                   <input type="button" onClick="btn_submit()" value="Create Your Account"/>
                
            </div>
                  <br>
                   <div class="text-center ">
                     <button type="submit" class=" btn btn-block send-button tx-tfm">Log-in</button>
                  </div>
                  
                  <div class="col-md-12 ">
                     <div class="login-or">
                        <hr class="hr-or">
                        <span class="span-or">Forgot </span>
                     </div>
                  </div>
                  <div class="form-group">
                     <a class="btn btn-block g-button" href="/findpassword">
                     <i class="btn btn-block g-button"></i> This is the Forgot Password button.
                     </a>
                  </div>
                  <p class="small mt-3">By signing up, you are indicating that you have read and agree to the <a href="#" class="ps-hero__content__link">Terms of Use</a> and <a href="#">Privacy Policy</a>.
                  </p>
               </form>
               
               
            </div>
         </div>
      </div>
   </div>
</body>
</html>

