<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font " for="id"> 课程编号： </label> 
        <div class="col-sm-9">
         <input type="text" id="id" class="col-xs-8" <c:if test="${!empty update }">readonly="readonly" value="${course.courseId }" </c:if>/><div id="id-tip"></div>
        </div> 
       </div> 
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 课程名称： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name" class="col-xs-8" <c:if test="${!empty update }">value="${course.couresName }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 课程开课日期： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="date" class="col-xs-8 date-picker"  data-date-format="yyyy-mm-dd" <c:if test="${!empty update }">value="${course.courseDate }"</c:if>/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 课程开课时间： </label> 
        <div class="col-sm-9">
          <div class="col-sm-8 input-group bootstrap-timepicker">
            <input type="text" id="time1" class="form-control" <c:if test="${!empty update }">value="${course.courseTime1 }"</c:if>/><div id="name-tip"></div>
			<span class="input-group-addon">
				<i class="icon-time bigger-110"></i>
			</span>
		  </div><!--col-sm-8 input-group bootstrap-timepicker  -->
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" > 课程开课时间： </label> 
        <div class="col-sm-9">
          <div class="col-sm-8 input-group bootstrap-timepicker">
            <input type="text" id="time2" class="form-control" <c:if test="${!empty update }">value="${course.courseTime2 }"</c:if>/><div id="name-tip"></div>
			<span class="input-group-addon">
				<i class="icon-time bigger-110"></i>
			</span>
		  </div><!--col-sm-8 input-group bootstrap-timepicker  -->        
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >课程价格： </label> 
        <div class="col-sm-9"> 
          <input type="text" id="price" class="col-xs-8" <c:if test="${!empty update }">value="${course.coursePrice }"</c:if>/>元<div id="price-tip"></div>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" >课程类型： </label> 
        <div class="col-sm-9">
          <select>
            <option>--选择--</option>
            <option>--选择--</option>
            <option>--选择--</option>
            <option>--选择--</option>
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

	    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){   
	    });

		$('#time1').timepicker({
			minuteStep: 1,
			showSeconds: true,
			showMeridian: false
		}).next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
		
		$('#time2').timepicker({
			minuteStep: 1,
			showSeconds: true,
			showMeridian: false
		}).next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
		
		$('#backid').click(function(){
				window.location.href="jsp/course/index.jsp";
		 });
		 
		$url ="";
		
		if(${!empty update }){
			$url="course/edit";
		}else{
			$url="course/add";
		}
		$("#ok").on('click',function() { //提交事件
	        $.ajax({
	            type: "get",
	            url: $url,
	            data: "id="+$("#id").val()+"&name="+$("#name").val()+"&price="+$("#price").val(),
	            success: function(data) {
	            	if(data.success == true){
			            $("#warning-block").html('<div class="alert alert-block alert-success">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="success bold-center">添加成功,'+'<a href="jsp/course/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/course/index.jsp");
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