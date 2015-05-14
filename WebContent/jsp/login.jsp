<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<HTML>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
    <title>瑜伽管管理系统</title>
	<meta charset="UTF-8">
   	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap-responsive.css" />
    <script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="assets/js/scm.js"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }

    </style>  
</head>
<body>
<div class="container">

    <form class="form-signin">
        <h2 class="form-signin-heading">欢迎使用，请登录</h2>
        帐号：<input type="text" name="username" id="username" class="input-block-level" placeholder="账号">
       密码: <input type="password" name="password"  id="password" class="input-block-level" placeholder="密码">
        <p><button class="btn btn-large btn-primary" type="button" id="login">登录</button></p>
        <div id="alert-tip"></div>
    </form>
</div>
</body>
<script type="text/javascript">
//访问的action
$(function () {
	$("#login").on('click',function() { //提交事件
		$.ajax({
         url: "login.html",
		 headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json;charset=utf-8'
         },
	     type:"POST",
		 data:JSON.stringify({"username":$("#username").val(),"password":encode64($("#password").val())}),
           success: function(data) {
           	if(data.success == true){
            	 countDown(1, "jsp/index.jsp");
	        }
	        else{
			    $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
	                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
	                    '<div class="danger bold-center">'+data.msg+'</div></div>');
		    }
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
               alert(XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus);
           }
	     });//ajax
	});//提交事件
});


// base64加密开始
var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"
		+ "wxyz0123456789+/" + "=";

function encode64(input) {

	var output = "";
	var chr1, chr2, chr3 = "";
	var enc1, enc2, enc3, enc4 = "";
	var i = 0;
	do {
		chr1 = input.charCodeAt(i++);
		chr2 = input.charCodeAt(i++);
		chr3 = input.charCodeAt(i++);
		enc1 = chr1 >> 2;
		enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
		enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
		enc4 = chr3 & 63;
		if (isNaN(chr2)) {
			enc3 = enc4 = 64;
		} else if (isNaN(chr3)) {
			enc4 = 64;
		}
		output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
				+ keyStr.charAt(enc3) + keyStr.charAt(enc4);
		chr1 = chr2 = chr3 = "";
		enc1 = enc2 = enc3 = enc4 = "";
	} while (i < input.length);

	return output;
}
</script>
</HTML>