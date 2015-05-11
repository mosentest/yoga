<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font " for="id"> 权限编号： </label> 
        <div class="col-sm-9">
         <input type="text" id="id" class="col-xs-8" <c:if test="${!empty update }">readonly="readonly" value="${limit.id }" </c:if>/><div id="id-tip"></div>
        </div> 
       </div> 
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 权限名称： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name" class="col-xs-8" <c:if test="${!empty update }">value="${limit.name }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >权限访问地址： </label> 
        <div class="col-sm-9"> 
          <input type="text" id="href" class="col-xs-8" <c:if test="${!empty update }">readonly="readonly" value="${limit.href }"</c:if>/><div id="href-tip"></div>
        </div> 
       </div>
       <!-- 警告框 -->
       <div id="warning-block"></div>
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
		//返回
		$('#backid').click(function(){
				window.location.href="jsp/limit/index.jsp";
		 });

		 //访问的action
		$url ="";
		
		if(${!empty d }){
			$url="limit/edit";
		}else{
			$url="limit/add";
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
			                    '<div class="success bold-center">'+data.msg+',<a href="jsp/limit/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/limit/index.jsp");
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