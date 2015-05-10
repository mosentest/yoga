<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font " for="id"> 会员编号： </label> 
        <div class="col-sm-9">
         <input type="text" id="id" class="col-xs-8" <c:if test="${!empty update }">readonly="readonly" value="${member.memberId }" </c:if>/><div id="id-tip"></div>
        </div> 
       </div> 
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 会员帐号： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="username" class="col-xs-8" <c:if test="${!empty update }">value="${member.memberUsername }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 会员名字： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name" class="col-xs-8" <c:if test="${!empty update }">value="${member.memberName }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 会员性别： </label> 
        <div class="col-sm-9">
          <select id="sex">
            <option value="-1">--请选择--</option>
            <option value="0" <c:if test="${!empty update }"><c:if test="${classrooms.classroomsState == false }">selected="selected"</c:if></c:if>>男</option>
            <option value="1" <c:if test="${!empty update }"><c:if test="${classrooms.classroomsState == true }">selected="selected"</c:if></c:if>>女</option>
          </select>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 会员身份证： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="card" class="col-xs-8" <c:if test="${!empty update }">value="${member.memberCard }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 会员联系电话： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="phone" class="col-xs-8" <c:if test="${!empty update }">value="${member.memberPhone }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 会员家庭住址： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="address" class="col-xs-8"  <c:if test="${!empty update }">value="${member.memberAddress }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >会员类型： </label> 
        <div class="col-sm-9">
          <select id="type" class="chosen-select" data-placeholder="请选择">
          </select>
        </div> 
       </div> 
       <!-- 警告框 -->
       <div id="warning-block"></div>
       <input type="hidden" id="typeId" value="${member.tbMemberType.id }">
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
        //首次加载
    	$("#type").empty();
    	$typeId=$("#typeId").val();
    	$.ajax({
            type: "get",
            url: "memberType/list.html",
            cache:false,
            success: function(data) {
		        var html = "";
    	    	$.each(data.list, function(i, item) {
        	    	if($typeId == item.id){
        	    		html +="<option value='&typeId="+item.id+"&type="+item.type+"' selected='selected'>&nbsp;&nbsp;&nbsp;&nbsp;"+item.type+"&nbsp;&nbsp;&nbsp;&nbsp;</option>";
            	    }else{
    	    			html +="<option value='&typeId="+item.id+"&type="+item.type+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+item.type+"&nbsp;&nbsp;&nbsp;&nbsp;</option>";
                	}
    		    });
    	    	$("#type").append(html);
    	    	$("#type").chosen();
    			$("#type").css("width","860px");
            }
        });
        
    	$("#type").empty();
    	//类型select
    	//第二次加载，延迟加载  TODO
    	$("#type").delay(1500).queue(function(){
    		$.ajax({
                type: "get",
                url: "memberType/list.html",
                cache:false,
                success: function(data) {
    		        var html = "";
        	    	$.each(data.list, function(i, item) {
            	    	if($typeId == item.id){
            	    		html +="<option value='&typeId="+item.id+"&type="+item.type+"' selected='selected'>&nbsp;&nbsp;&nbsp;&nbsp;"+item.type+"&nbsp;&nbsp;&nbsp;&nbsp;</option>";
                	    }else{
        	    			html +="<option value='&typeId="+item.id+"&type="+item.type+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+item.type+"&nbsp;&nbsp;&nbsp;&nbsp;</option>";
                    	}
            	    });
        	    	$("#type").append(html);
        	    	$("#type").chosen();
        			$("#type").css("width","860px");
                }
            });
        });
        
		//返回
		$('#backid').click(function(){
				window.location.href="jsp/member/index.jsp";
		 });

		 //访问的action
		$url ="";
		
		if(${!empty update }){
			$url="member/edit";
		}else{
			$url="member/add";
		}
		$("#ok").on('click',function() { //提交事件
	        $.ajax({
	            type: "get",
	            url: $url,
	            data: "id="+$("#id").val()+
	            	  "&username="+$("#username").val()+
	            	  "&name="+$("#name").val()+
	            	  "&sex="+$("#sex").val()+
	            	  "&card="+$("#card").val()+
	            	  "&phone="+$("#phone").val()+
	            	  "&address="+$("#address").val()+
	            	  $("#type").val(),
	            success: function(data) {
	            	if(data.success == true){
			            $("#warning-block").html('<div class="alert alert-block alert-success">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="success bold-center">添加成功,'+'<a href="jsp/member/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/member/index.jsp");
			        }
			        else{
					    $("#warning-block").html('<div class="alert alert-block alert-danger">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="danger bold-center">操作失败</div></div>');
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