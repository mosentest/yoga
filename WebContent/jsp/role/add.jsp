<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
      <c:if test="${!empty update }">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font " for="id"> 角色编号： </label> 
        <div class="col-sm-9">
         <input type="text" id="id" class="col-xs-8" <c:if test="${!empty update }">readonly="readonly" value="${role.id }" </c:if>/><div id="id-tip"></div>
        </div> 
       </div>
       </c:if>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 角色名称： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name" class="col-xs-8" <c:if test="${!empty update }">value="${role.type }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
      <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >分配权限： </label> 
        <div class="col-sm-9">
          <ul id="role-limit">
          </ul>
        </div> 
       </div>
       <!-- 警告框 -->
       <div id="warning-block"></div>
       <input type="hidden" id="update" value="${update }">
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
    	//首次加载
     	$("#role-limit").empty();
        var html = "";
        if($update == "update"){
        	$.ajax({
				type:"get",
				url:"roleLimit/allList.html?roleId="+$("#id").val(),
				cache:false,
				success: function(data2) {
					
					$.each(data2.list, function(i2, item2) { //等于for语句
						$.ajax({
				            type: "get",
				            url: "limit/alllist.html",
				            cache:false,
				            success: function(data) {
				    	    	$.each(data.list, function(i, item) { //等于for语句
									if(item2.tbLimit.name == item.name){
										html+="<li><input type='checkbox' name='limit' value='&limit="+item.id+"@"+item.name+"' checked />"+item.name+"<li>";
									}else{
										html+="<li><input type='checkbox' name='limit' value='&limit="+item.id+"@"+item.name+"'/>"+item.name+"<li>";
									}
				    		    });
				            }
				        });
					})
					console.log(html);
					$("#role-limit").append(html);
				}
			});
        }else{
	    	$.ajax({
	            type: "get",
	            url: "limit/alllist.html",
	            cache:false,
	            success: function(data) {
	    	    	$.each(data.list, function(i, item) { //等于for语句
	    	    			//默认添加的时候
							//新增
							//获取勾选的id和name array数组
							//删除当前所有关系，保存 关系表
	    	    			html+="<li><input type='checkbox' name='limit' value='&limit="+item.id+"@"+item.name+"'/>"+item.name+"<li>";
	    		    });
	       	    	$("#role-limit").append(html);
	            }
	        });
        }
    	
		//返回
		$('#backid').click(function(){
				window.location.href="jsp/role/index.jsp";
		 });


		if($update == "update"){
			$url="role/edit";
		}else{
			$url="role/add";
		}
		$("#ok").on('click',function() { //提交事件
	        $.ajax({
	            type: "get",
	            url: $url,
	            data: "id="+$("#id").val()+
	            	  "&name="+$("#name").val()+
	            	  "&href="+$("#href").val(),
	            success: function(data) {
	            	if(data.success == true){
			            $("#warning-block").html('<div class="alert alert-block alert-success">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="success bold-center">'+data.msg+',<a href="jsp/role/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/role/index.jsp");
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
</script>
</html>