<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div user="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" user="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font " for="id">用户编号： </label> 
        <div class="col-sm-9">
         <input type="text" id="id" class="col-xs-8" <c:if test="${!empty update }">readonly="readonly" value="${user.userId }" </c:if>/><div id="id-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >用户帐号： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="username" class="col-xs-8" <c:if test="${!empty update }">value="${user.userUsername }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
      <c:choose>
		<c:when test="${ update  ==  'update'}">
		    <input type="hidden" id="password"  value="${user.userPassword }"/>
       	</c:when>
       	<c:otherwise>
		 <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right font" >用户密码： </label> 
              <div class="col-sm-9">
       	    <input type="password" id="password" class="col-xs-8" /><div id="name-tip"></div>
	       </div> 
              </div>
       	</c:otherwise>
       </c:choose>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >员工姓名： </label> 
        <div class="col-sm-9">
	        <c:choose>
				<c:when test="${ update  ==  'update'}">
	        	  <input type="text" id="staffName"  value="${user.tbStaff.staffName }" readonly="readonly"/>
	        	  <input type="hidden" id="staffId"  value="${user.tbStaff.staffId }"/>
	        	</c:when>
	        	<c:otherwise>
	        	  <select id="staffId" class="chosen-select" data-placeholder="请选择">
		          </select>
	        	</c:otherwise>
	        </c:choose>
         </div> 
       </div>
      <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >分配角色： </label> 
        <div class="col-sm-9">
           <ul id="user-role" class="spaced" style="list-style:none">
          </ul>
        </div> 
       </div>
       <!-- 警告框 -->
       <div id="warning-block"></div>
       <input type="hidden" id="update" value="${update }">
       <input type="hidden" id="userId" value="${user.userId }">
      </form> 
     </div> 
     <div class="modal-footer"> 
      <button type="button" class="btn btn-primary btn-sm"  id="ok" autocomplete="off"  data-loading-text="正在处理中..." ><i class="icon-ok bigger-110" ></i>确定</button> 
      <button type="button" class="btn btn-success btn-sm" name="backid" id="backid">返回列表</button>
     </div> 
    </div> 
   </div> 
  </div>
</body>
<script>
    $(function () {
    	
    	//获取是否更新的页面
    	var $update = $("#update").val();
    	
    	//首次加载,加载员工编号
    	if($update != "update"){
    		//只有添加的时候才执行的代码
	    	$("#staffId").empty();
	    	$.ajax({
	            type: "get",
	            url: "staff/alllist.html",
	            cache:false,
	            success: function(data) {
			        var html = "";
	    	    	$.each(data.list, function(i, item) {
	        	    		html +="<option value='"+item.staffId+"'>"+item.staffName+"</option>";
	    		    });
	    	    	$("#staffId").append(html);
	    	    	$("#staffId").chosen();
	    	    	$(".chosen-container").css("width","100px");
	    			$("#staffId").css("width","860px");
	            }
	        });
    	}
    	
    	//首次加载
     	$("#user-role").empty();
     	//这里是处理显示 权限列表
        if($update == "update"){
        	$.ajax({
	            type: "get",
	            url: "role/alllist.html",
	            cache:false,
	            success: function(data) {
		            //在此请求数据
	            	$.ajax({
	    				type:"get",
	    				url:"userRole/alllist.html?userId="+$("#id").val(),
	    				cache:false,
	    				success: function(data2) {
		    				//动态显示出来
							var j = 0;
				            for(var i = 0; i < data.list.length; i++){
					            if(j < data2.list.length && data.list[i].type == data2.list[j].tbRole.type ){
			    	    			var html="<li><i class='icon-bell bigger-110 purple'><input type='checkbox' name='role' value='"+data.list[i].id+"' checked/>"+data.list[i].type+"<li>";
						      	    $("#user-role").append(html);
						      	  	j++;
						        }else{
			    					var html="<li><i class='icon-bell bigger-110 purple'><input type='checkbox' name='role' value='"+data.list[i].id+"'/>"+data.list[i].type+"<li>";
						      	    $("#user-role").append(html);
							    }
			    			}
	    				}
	    			});
		        }
	        });
        }else{
	    	$.ajax({
	            type: "get",
	            url: "role/alllist.html",
	            cache:false,
	            success: function(data) {
	    	    	$.each(data.list, function(i, item) { //等于for语句
	    	    			//默认添加的时候
							//新增
							//获取勾选的id和name array数组
							//删除当前所有关系，保存 关系表
	    	    			html="<li><i class='icon-bell bigger-110 purple'><input type='checkbox' name='role' value='"+item.id+"'/>"+item.type+"<li>";
				      	    $("#user-role").append(html);
	    		    });
	            }
	        });
        }
    	
		//返回
		$('#backid').click(function(){
				window.location.href="jsp/user/index.jsp";
		 });

		//这里是提交表单
		if($update == "update"){
			$url="user/edit";
		}else{
			$url="user/add";
		}
		$("#ok").on('click',function() { //提交事件
			//获取用户勾选的权限
			var selectedItems = new Array();
			$("input[name='role']:checked").each(function() {
				selectedItems.push($(this).val());
			});
			if (selectedItems.length == 0) {
				$("#warning-block").html('<div class="alert alert-block alert-danger">'+
					'<button type="button" class="close" data-dismiss="alert" id="close"> <i class="icon-remove"></i> </button>'+
					'<div class="danger bold-center">没勾选权限</div> </div>');
				//结束
				return;
			}
	        $.ajax({
	            type: "get",
	            url: $url,
	            data: "id="+$("#id").val()+
	                  "&username="+$("#username").val()+
	                  "&password="+encode64($("#password").val())+
	                  "&staffId="+$("#staffId").val()+
	            	  "&roleIds="+selectedItems.join(','),
	            success: function(data) {
	            	if(data.success == true){
			            $("#warning-block").html('<div class="alert alert-block alert-success">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="success bold-center">'+data.msg+',<a href="jsp/user/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/user/index.jsp");
			        }
			        else{
					    $("#warning-block").html('<div class="alert alert-block alert-danger">'+
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
</html>