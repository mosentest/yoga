<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font " for="id"> 员工编号： </label> 
        <div class="col-sm-9">
         <input type="text" id="id" class="col-xs-8" <c:if test="${!empty update }">readonly="readonly" value="${tbStaffDetail.tbStaff.staffId }" </c:if>/><div id="id-tip"></div>
        </div> 
       </div> 
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工名字： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name" class="col-xs-8" <c:if test="${!empty update }">value="${tbStaffDetail.tbStaff.staffName }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工性别： </label> 
        <div class="col-sm-9">
          <select id="sex">
            <option value="-1">--请选择--</option>
            <option value="0" <c:if test="${!empty update }"><c:if test="${tbStaffDetail.tbStaff.staffSex == false }">selected="selected"</c:if></c:if>>男</option>
            <option value="1" <c:if test="${!empty update }"><c:if test="${tbStaffDetail.tbStaff.staffSex == true }">selected="selected"</c:if></c:if>>女</option>
          </select>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工年龄： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="age" class="col-xs-8" <c:if test="${!empty update }">value="${tbStaffDetail.tbStaff.staffAge }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工职务： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="post" class="col-xs-8" <c:if test="${!empty update }">value="${tbStaffDetail.tbStaff.staffPost }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工联系电话： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="phone" class="col-xs-8" <c:if test="${!empty update }">value="${tbStaffDetail.tbStaff.staffPhone }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工身份证： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="card" class="col-xs-8" <c:if test="${!empty update }">value="${tbStaffDetail.staffCard }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工家庭住址： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="address" class="col-xs-8"  <c:if test="${!empty update }">value="${tbStaffDetail.staffAddress }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工邮件： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="email" class="col-xs-8"  <c:if test="${!empty update }">value="${tbStaffDetail.staffEmail }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 员工入职时间： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="time" class="col-xs-8 date-picker"   data-date-format="yyyy-mm-dd"  <c:if test="${!empty update }">value="${tbStaffDetail.staffTime }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <!-- 警告框 -->
       <div id="warning-block"></div>
       <input type="hidden" id="staffDetailId" value="${tbStaffDetail.id }">
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
            url: "staffType/list.html",
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
                url: "staffType/list.html",
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
        
    	//日期选择器
	    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){   
	    });
    	
		//返回
		$('#backid').click(function(){
				window.location.href="jsp/staff/index.jsp";
		 });

		 //访问的action
		$url ="";
		
		if(${!empty update }){
			$url="staffDetail/edit";
		}else{
			$url="staffDetail/add";
		}
		$("#ok").on('click',function() { //提交事件
	        $.ajax({
	            type: "get",
	            url: $url,
	            data: "id="+$("#id").val()+
	            	  "&name="+$("#name").val()+
	            	  "&sex="+$("#sex").val()+
	            	  "&age="+$("#age").val()+
	            	  "&post="+$("#post").val()+
	            	  "&phone="+$("#phone").val()+
	            	  "&card="+$("#card").val()+
	            	  "&address="+$("#address").val()+
	            	  "&email="+$("#email").val()+
	            	  "&time="+$("#time").val()+
	            	  "&staffDetailId="+$("#staffDetailId").val(),
	            success: function(data) {
	            	if(data.success == true){
			            $("#warning-block").html('<div class="alert alert-block alert-success">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="success bold-center">'+data.msg+',<a href="jsp/staff/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/staff/index.jsp");
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