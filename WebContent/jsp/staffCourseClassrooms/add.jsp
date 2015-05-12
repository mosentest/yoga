<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font " for="id"> 员工名字： </label> 
        <div class="col-sm-9">
       	  <select id="staffId" class="chosen-select" data-placeholder="请选择">
          </select>
        </div> 
       </div> 
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 课程名称： </label> 
        <div class="col-sm-9"> 
       	  <select id="courseId" class="chosen-select" data-placeholder="请选择">
          </select>        
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 课室名称： </label> 
        <div class="col-sm-9"> 
       	  <select id="classroomsId" class="chosen-select" data-placeholder="请选择">
          </select>        
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
        //首次加载
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
            }
        });
    	
    	$("#courseId").empty();
    	$.ajax({
            type: "get",
            url: "course/alllist.html",
            cache:false,
            success: function(data) {
		        var html = "";
    	    	$.each(data.list, function(i, item) {
    	    			html +="<option value='"+item.courseId+"'>"+item.couresName+"</option>";
    		    });
    	    	$("#courseId").append(html);
    	    	$("#courseId").chosen();
    	    	$(".chosen-container").css("width","100px");
            }
        });
    	
    	$("#classroomsId").empty();
    	$.ajax({
            type: "get",
            url: "classrooms/alllist.html?state=0",
            cache:false,
            success: function(data) {
		        var html = "";
    	    	$.each(data.list, function(i, item) {
    	    			html +="<option value='"+item.classroomsId+"'>"+item.classroomsName+"</option>";
    		    });
    	    	$("#classroomsId").append(html);
    	    	$("#classroomsId").chosen();
    	    	$(".chosen-container").css("width","100px");
            }
        });
        
    	
		//返回
		$('#backid').click(function(){
				window.location.href="jsp/staffCourseClassrooms/index.jsp";
		 });

		$("#ok").on('click',function() { //提交事件
	        $.ajax({
	            type: "get",
	            url: "staffCourseClassrooms/add",
	            data: "id="+
	            	  "&staffId="+$("#staffId").val()+
	            	  "&courseId="+$("#courseId").val()+
	            	  "&classroomsId="+$("#classroomsId").val(),
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