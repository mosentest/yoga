<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
 	<div class="widget-main padding-24">
             <div class="row">
             <div class="col-sm-6">
               <div class="row">
                 <div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right"><b>会员信息</b></div>
               </div><!--.row  -->
               <div class="row">
                 <ul class="list-unstyled spaced">
                   <li>
                     <i class="icon-caret-right blue"></i>
                     <label name="customer">会员姓名：</label>
                     <select id="memberName" class="chosen-select" data-placeholder="请选择一个会员"></select>
                   </li>
                   <li>
                     <i class="icon-caret-right blue"></i>
                     <label name="address">会员性别：</label>
                     <span name="address" id="memberSex"></span>
                   </li>
                   <li>
                     <i class="icon-caret-right blue"></i>
                     <label name="contacts">会员电话：</label>
                     <span name="contacts" id="memberPhone"></span>
                   </li>
                   <li>
                     <i class="icon-caret-right blue"></i>
                     <label name="telephone">会员类型：</label>
                     <span name="telephone" id="memberType"></span>
                   </li>
                 </ul>
               </div><!--.row  -->
             </div><!--.col-sm-6  -->
             <div class="col-sm-6">
               <div class="row">
                 <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right"><b>课程信息</b></div>
               </div><!--.row  -->
               <div class="row">
                 <ul class="list-unstyled spaced">
                   <li>
                     <i class="icon-caret-right green"></i>
                     <label name="goodName">课程名称：</label>
                     <select id="courseName" class="chosen-select" data-placeholder="请选择一个商品"></select>
                   </li>
                   <li>
                     <i class="icon-caret-right green"></i>
                     <label name="origin">价格：</label>
                     <span name="origin" id="coursePrice">0</span>元
                   </li>
                   <li>
                     <i class="icon-caret-right green"></i>
                     <label name="pack">授课老师：</label>
                      <span name="pack" id="staffName"></span> 
                   </li>
                 </ul>
               </div><!--.row  -->
             </div><!--.col-sm-6  -->
           </div><!--.row  -->
           <div class="space"></div>
           <div>
              <table class="table table-striped table-bordered" id="stack-table">
                <thead>
                  <tr>
                    <th class="center">&nbsp;</th>
                    <th class="center">会员编号</th>
                    <th class="center">会员姓名</th>
                    <th class="center">课程编号</th>
                    <th class="center">课程名称</th>
                    <th class="center">价格(元)</th>
                  </tr>
                </thead>
              </table>
           </div>
           
           <div class="row">
             <div class="col-sm-5 pull-right"><h4 class="pull-right" id="all-sum"></h4></div>
             <div class="col-sm-7 pull-left"> &nbsp;&nbsp; &nbsp; &nbsp;  </div>
           </div>
           <div class="space-6"></div>
           
           <div class="row">
                 <form class="form-inline">
                  <input type="hidden" id="form-member-id">
                  <input type="hidden" id="form-member-name">
                  <input type="hidden" id="form-course-id">
                  <input type="hidden" id="form-course-name">
                  <input type="hidden" id="form-course-price">
                  <input type="hidden" id="form-staffName">
                  <div class="space-6"></div>
                  <div class="hr hr8 hr-double hr-dotted"></div>
                  <div id="alert-tip"></div>
                  <div class="space-6"></div>
                  <button class="btn btn-sm " type="button" onclick="addStockTable()"><i class="icon-edit bigger-110"></i>添加</button>
                  <button class="btn btn-sm btn-info" type="button" id="save"><i class="icon-ok bigger-110"></i>保存</button>
                  <button class="btn btn-sm btn-danger" type="button"  onclick="delTr2()" ><i class="icon-trash bigger-110"></i>取消</button>
                  <button type="button" class="btn btn-success btn-sm" name="backid" id="backid">返回列表</button>
                 </form>
           </div><!--.row  -->
	  </div><!--widget-main padding-24  -->
  </div>
</body>
<script>

	var sum = 0;
	
	var tArray = new Array();//先声明一维

    $(function () {
    	
    	
		$.get("member/alllist.html", function(data){
			var result="<option value='-1' select >--请选择--</option>";
			for(var i = 0; i < data.list.length;i++){
				result+="<option value=\""+data.list[i].memberId+"\">"+data.list[i].memberName+"</option>";
			}
			$("#memberName").html(result);
			$("#memberName").chosen();
			$("#memberName_chosen").css("width","130px");
	    });

		$.get("staffCourseClassrooms/alllist.html", function(data){
			var result="<option value='-1' select >--请选择--</option>";
			for(var i = 0; i < data.list.length;i++){
				result+="<option value=\""+data.list[i].id+"\">"+data.list[i].tbCourse.couresName+"</option>";
			}
			$("#courseName").html(result);
			$("#courseName").chosen();
			$("#courseName_chosen").css("width","130px");
	    });
	    
		//selcet事件
	    $("#memberName").change(function(){
		    $.get("member/show2One",{id:$("#memberName").val()},function(data){
		    	if(data.memberSex == true){
		    		$("#memberSex").html("女");
		    	}else{
		    		$("#memberSex").html("男");
		    	}
			    $("#memberPhone").html(data.memberPhone);
			    $("#memberType").html(data.tbMemberType.type);
			    $("#form-member-id").attr("value",data.memberId);
			    $("#form-member-name").attr("value",data.memberName);
			});
		});

		//selcet事件
	    $("#courseName").change(function(){
		    $.get("staffCourseClassrooms/show2One",{id:$("#courseName").val()},function(data){
			    $("#coursePrice").html(data.tbCourse.coursePrice);
			    $("#staffName").html(data.tbStaff.staffName);
			    $("#form-course-id").attr("value",data.id);
			    $("#form-course-name").attr("value",data.tbCourse.couresName);
			    $("#form-course-price").attr("value",data.tbCourse.coursePrice);
			    $("#form-staffName").attr("value",data.tbStaff.staffName);
			});
		});
		
		//返回
		$('#backid').click(function(){
				window.location.href="jsp/memberCourse/index.jsp";
		 });

	    //访问的action
		var $url ="";
		$("#save").on('click',function() { //提交事件
			var saveJson = tableToJSONString();
			if(saveJson != null && saveJson != "[]"){
				$.ajax({
		            url: "memberCourse/add",
					 headers : {
			                'Accept' : 'application/json',
			                'Content-Type' : 'application/json;charset=utf-8'
			         },
				     type:"POST",
					 data:saveJson,
		            success: function(data) {
		            	if(data.success == true){
				            $("#alert-tip").html('<div class="alert alert-block alert-success">'+
				                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
				                    '<div class="success bold-center">'+data.msg+',<a href="jsp/member/index.jsp" class="green">'+
				                    '<span id="mysecond" class="green">'+5+
				                    '</span>秒自动跳转</a><div></div>');
			            	 countDown(5, "jsp/memberCourse/index.jsp");
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
			}else if(saveJson == "[]"){
	            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
	                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
	                    '<div class="danger bold-center">请添加信息</div></div>');
			}
 	   });//提交事件
    });
    
	//获取table的数据
	function tableToJSONString() {
		var tabLen = document.getElementById("stack-table");
		var jsonT = "[";
		for(var i = 1; i < tabLen.rows.length; i++){
			if(i == 1 ){
				jsonT+="{\"memberId\":\""+tabLen.rows[i].cells[1].innerHTML+"\",\"courseId\":\""+tabLen.rows[i].cells[3].innerHTML+"\"}";
			}else{
				jsonT+=",{\"memberId\":\""+tabLen.rows[i].cells[1].innerHTML+"\",\"courseId\":\""+tabLen.rows[i].cells[3].innerHTML+"\"}";
			}
		}
		jsonT+="]";
		console.log(jsonT);
		return jsonT;
	}
	
	//添加记录到table里面
	function addStockTable(){
		
		//获取上面的信息
		var memberid = $("#form-member-id").val();
		var membername = $("#form-member-name").val();
		var courseid = $("#form-course-id").val();
		var coursename = $("#form-course-name").val();
		var courseprice = $("#form-course-price").val();
		
		if(memberid == null || memberid == "" || courseid == "" || courseid == null){
            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
                    '<div class="danger bold-center">请选择会员和课程再添加</div></div>');
		}else{
			$("#alert-tip").html();
			//获取课程的数量
			var courseNum =$("#courseNum").val();
// 			if(false){
// 	            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
// 	                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
// 	                    '<div class="danger bold-center">请输入课程的数量</div></div>');
// 			}else{
				$("#stack-table");
				var str="<tr>"+
				        "<td class='center' ><input type='checkbox' id='ckb'/></td>"+
				        "<td class='center' >"+memberid+"</td>"+
				        "<td class='center' >"+membername+"</td>"+
				        "<td class='center' >"+courseid+"</td>"+
				        "<td class='center' >"+coursename+"</td>"+
				        "<td class='center' >"+courseprice+"</td>"+
				        "</tr>";
				if($("#stack-table").find("tr").length >= 5){
		            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
		                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
		                    '<div class="danger bold-center">只能添加5行数据</div></div>');
				}else{
					$("#alert-tip").html("");
					addTr("stack-table", -1, str);
					sum+=parseFloat(changeTwoDecimal_f(courseprice));
					$("#all-sum").html("总计:<span class='red'>"+changeTwoDecimal_f(sum)+"</span>元");
				}
// 			}
		}
	}
	
	//添加一行数据
	function addTr(tab, row, trHtml){
	     //获取table最后一行 $("#tab tr:last")
	     //获取table第一行 $("#tab tr").eq(0)
	     //获取table倒数第二行 $("#tab tr").eq(-2)
	     var $tr=$("#"+tab+" tr").eq(row);
	     if($tr.size() == 0){
	         $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
	                 '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
	                 '<div class="danger bold-center">指定的table id或行数不存在！</div></div>');
	        return;
	     }
	     $("#alert-tip").html("");
	     $tr.after(trHtml);
	}
	
	
	function delTr(ckb){
		  //获取选中的复选框，然后循环遍历删除
		  var ckbs=$("input[id="+ckb+"]:checked");
		  if(ckbs.size()==0){
	         $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
	                 '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
	                 '<div class="danger bold-center">要删除指定行，需选中要删除的行！</div></div>');
		      return;
		  }
	      $("#alert-tip").html("");
		  ckbs.each(function(){
		      $(this).parent().parent().remove();
		  });
	}
		
	function delTr2(){
	   delTr('ckb');
	}
	
	//检测输入的数量
	var record={num:""}
    //检测输入的数量
    var checkNum = function(n){
        var decimalReg = /^[1-9]\d{0,8}?$/;
        if(n.value!=""&&decimalReg.test(n.value)){ 
           record.num = n.value; 
        }else{ 
           if(n.value != ""){ 
             n.value=record.num; 
		}
	  } 
    }
    
    //格式输入总数
    function changeTwoDecimal_f(x) {
        var f_x = parseFloat(x);
        if (isNaN(f_x)) {
            alert('function:changeTwoDecimal->parameter error');
            return false;
        }
        var f_x = Math.round(x * 100) / 100;
        var s_x = f_x.toString();
        var pos_decimal = s_x.indexOf('.');
        if (pos_decimal < 0) {
            pos_decimal = s_x.length;
            s_x += '.';
        }
        while (s_x.length <= pos_decimal + 2) {
            s_x += '0';
        }
        return s_x;
    }
</script>
</html>