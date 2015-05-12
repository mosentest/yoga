<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<jsp:include page="/WEB-INF/view/header.jsp"></jsp:include> 
  <div class="main-container" id="main-container"> 
   <script type="text/javascript">
		try {
			ace.settings.check('main-container', 'fixed')
		} catch (e) {
		}
	</script> 
   <div class="main-container-inner"> 
    <a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span> </a> 
    <div class="sidebar" id="sidebar"> 
     <script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'fixed')
				} catch (e) {
				}
	 </script>
	  <div class="sidebar-shortcuts" id="sidebar-shortcuts"> 
       <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large"> 
        <button class="btn btn-success" onclick="goPage('home.html')"><i class="icon-signal"></i></button> 
        <button class="btn btn-info" onclick="goPage('home.html')"> <i class="icon-pencil"></i></button> 
        <button class="btn btn-warning" onclick="goPage('home.html')"> <i class="icon-group"></i></button> 
        <button class="btn btn-danger" onclick="goPage('home.html')"> <i class="icon-cogs"></i></button> 
       </div><!--.sidebar-shortcuts-large  -->
       <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
           <span class="btn btn-success"></span>
           <span class="btn btn-info"></span>
           <span class="btn btn-warning"></span>
           <span class="btn btn-danger"></span>
      </div>
     </div><!--sidebar-shortcuts  -->
     <!-- 导航条 --> 
     <jsp:include page="/WEB-INF/view/nav.jsp"></jsp:include> 
     <div class="sidebar-collapse" id="sidebar-collapse"> 
      <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i> 
     </div> <!--.sidebar-collapse  -->
     <script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'collapsed')
				} catch (e) {
				}
			</script> 
    </div> <!--.sidebar  -->
    <div class="main-content"> 
     <div class="breadcrumbs" id="breadcrumbs"> 
      <script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script> 
      <ul class="breadcrumb"> 
       <li><i class="icon-home home-icon"></i> <a href="home.html">首页</a></li> 
       <li><a href="coop/xiaoshoudan.html">销售</a></li> 
      </ul> 
      <!-- .breadcrumb -->
     </div> 
     <div class="page-content"> 
      <div class="row"> 
       <div class="col-xs-12">
       <div class="space-6"></div>
         <div class="row">
           <div class="col-sm-10 col-sm-offset-1">
           <div class="widget-box transparent invoice-box">
             <div class="widget-header widget-header-large">
               <h3 class="grey lighter pull-left position-relative">
                 <i class="icon-leaf green"></i>销售单
               </h3>
               <div class="widget-toolbar no-border invoice-info">
                 <span class="invoice-info-label">时间:</span>
                 <span class="blue" id="time"></span>
<!--                  <br>
                 <span class="invoice-info-label">发票:</span>
                 <span class="red">#121212</span> -->
               </div>
               <div class="widget-toolbar hidden-480"><a href="javascript:void()"><i class="icon-print"></i></a></div><!--.widget-toolbar hidden-480  -->
             </div><!--.widget-header widget-header-large  -->
             <div class="widget-body">
               <div class="widget-main padding-24">
               <div class="row">
                 <div class="col-sm-6">
                   <div class="row">
                     <div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right"><b>客户信息</b></div>
                   </div><!--.row  -->
                   <div class="row">
                     <ul class="list-unstyled spaced">
                       <li>
                         <i class="icon-caret-right blue"></i>
                         <label name="customer">客户：</label>
                         <select id="customerName" class="chosen-select" data-placeholder="请选择一个客户"></select>
                       </li>
                       <li>
                         <i class="icon-caret-right blue"></i>
                         <label name="address">地址：</label>
                         <span name="address" id="address"></span>
                       </li>
                       <li>
                         <i class="icon-caret-right blue"></i>
                         <label name="contacts">联系人：</label>
                         <span name="contacts" id="contacts"></span>
                       </li>
                       <li>
                         <i class="icon-caret-right blue"></i>
                         <label name="telephone">联系电话：</label>
                         <span name="telephone" id="telephone"></span>
                       </li>
                     </ul>
                   </div><!--.row  -->
                 </div><!--.col-sm-6  -->
                 <div class="col-sm-6">
                   <div class="row">
                     <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right"><b>商品信息</b></div>
                   </div><!--.row  -->
                   <div class="row">
                     <ul class="list-unstyled spaced">
                       <li>
                         <i class="icon-caret-right green"></i>
                         <label name="goodName">商品：</label>
                         <select id="goodName" class="chosen-select" data-placeholder="请选择一个客户"></select>
                       </li>
                       <li>
                         <i class="icon-caret-right green"></i>
                         <label name="origin">产地：</label>
                         <span name="origin" id="origin"></span>
                       </li>
                       <li>
                         <i class="icon-caret-right green"></i>
                         <label name="pack">包装：</label>
                         <span name="pack" id="pack"></span>
                       </li>
                       <li>
                         <i class="icon-caret-right green"></i>
                         <label name="unitCost">价格：</label>
                         <span name="unitCost" id="unitCost"></span>
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
                       <th class="center">客户</th>
                       <th class="center">商品</th>
                       <th class="center">单价(元)</th>
                       <th class="center">数量</th>
                       <th class="center">总价(元)</th>
                       <th class="center">经手人</th>
                       <th class="center">结算方式</th>
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
                  <input type="hidden" id="form-customer-id">
                  <input type="hidden" id="form-customer-name">
                  <input type="hidden" id="form-good-id">
                  <input type="hidden" id="form-good-name">
<!--                   <div class="form-group"> -->
<!--                     <label>品种数量：</label><input type="text" placeholder="请输入品种数量" />&nbsp; -->
<!--                   </div> -->
                  <div class="form-group">
                    <label>单价(元)：</label><input type="text" placeholder="请输入单价" id="form-unitCost" onkeyup="checkDecimal(this)"/>&nbsp;
                  </div>
                  <div class="form-group">
                    <label>数量：</label><input type="text" placeholder="请输入数量" id="form-amount" onkeyup="checkNum(this)"/>&nbsp;
                  </div>
                  <div class="form-group">
                    <label>经手人：</label><input type="text" placeholder="请输入经手人" id="form-handler"/>&nbsp;
                  </div>
                  <div class="space-6"></div>
                  <div class="form-group">
                    <label>结算方式：</label><input type="text" placeholder="请输入结算方式" id="form-clearingForm"/>&nbsp;
                  </div>
                  <div class="space-6"></div>
                  <div class="hr hr8 hr-double hr-dotted"></div>
                  <div id="alert-tip"></div>
                  <div class="space-6"></div>
                  <button class="btn btn-sm btn-success" type="button" onclick="addStockTable()"><i class="icon-edit bigger-110"></i>添加</button>
                  <button class="btn btn-sm btn-danger" type="button" onclick="delTr2()"><i class="icon-trash bigger-110"></i>取消</button>
                  <button class="btn btn-sm btn-info" type="button" id="save"><i class="icon-ok bigger-110"></i>保存</button>
                  <button class="btn btn-sm " type="reset"><i class="icon-undo bigger-110"></i>重置</button>
                 </form>
         </div><!--.row  -->
       </div> <!-- .col-xs-12 -->
      </div> <!-- /.row --> 
     </div> <!-- /.page-content --> 
    </div> <!-- /.main-content --> 
    <script type="text/javascript">
	var sum = 0;
    
    var tArray = new Array();//先声明一维
    
	jQuery(function($) {
		
		systemTime();
		
		$.get("coop/searchallcustomername", function(msg){
			var result="<option value='0' select >&nbsp;</option>";
			for(var i = 0; i < msg.length;i++){
				result+="<option value=\""+msg[i]+"\">"+msg[i]+"</option>";
			}
			$("#customerName").html(result);
			$("#customerName").chosen();
			$("#customerName_chosen").css("width","260px");
	    });

		$.get("coop/searchallgoodname", function(msg){
			var result="<option value='0' select >&nbsp;</option>";
			for(var i = 0; i < msg.length;i++){
				result+="<option value=\""+msg[i]+"\">"+msg[i]+"</option>";
			}
			$("#goodName").html(result);
			$("#goodName").chosen();
			$("#goodName_chosen").css("width","260px");
	    });
	    
		//selcet事件
	    $("#customerName").change(function(){
		    $.get("coop/searchcustomerbyname",{name:$("#customerName").val()},function(msg){
			    $("#address").html(msg.address);
			    $("#contacts").html(msg.contacts);
			    $("#telephone").html(msg.telephone);
			    $("#form-customer-id").attr("value",msg.id);
			    $("#form-customer-name").attr("value",msg.name);
			});
		});

		//selcet事件
	    $("#goodName").change(function(){
		    $.get("coop/searchGoodsByName",{name:$("#goodName").val()},function(msg){
			    $("#origin").html(msg.origin);
			    $("#pack").html(msg.pack);
			    $("#unitCost").html(msg.unitCost);
			    $("#form-good-id").attr("value",msg.id);
			    $("#form-good-name").attr("value",msg.name);
			});
		});

		$("#save").click(function(){
			var saveJson = tableToJSONString();
			if(saveJson != null && saveJson != "[]"){
			     $.ajax({
 					 dataType:"json",
				     url:"coop/addSell",
					 headers : {
			                'Accept' : 'application/json',
			                'Content-Type' : 'application/json;charset=utf-8'
			         },
				     type:"POST",
					 data:saveJson,
					 success: function(data){
						 if(data.success == true){
					          $("#alert-tip").html('<div class="alert alert-block alert-success">'+
					                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
					                    '<div class="success bold-center">保存成功,等待管理员审批</div></div>');
						 }else{
					            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
					                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
					                    '<div class="danger bold-center">保存失败</div></div>');
						 }

					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
			                alert(XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus);
			            }
			     });
			}else if(saveJson == "[]"){
	            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
	                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
	                    '<div class="danger bold-center">请添加进货信息</div></div>');
			}
		});
		
	});

	function addStockTable(){
		var customername = $("#form-customer-name").val();
		var goodname = $("#form-good-name").val();
		if(customername == null || customername == "" || goodname == "" || customername == null){
            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
                    '<div class="danger bold-center">请选择供应商和商品再添加</div></div>');
		}else{
			$("#alert-tip").html();
			var unitCost =$("#form-unitCost").val();
			var amount = $("#form-amount").val();
			var handler = $("#form-handler").val();
			var clearingForm = $("#form-clearingForm").val();
			var checkConclusion = $("#form-checkConclusion").val();
			if(unitCost == "" || amount == "" || handler == "" || clearingForm == ""){
	            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
	                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
	                    '<div class="danger bold-center">请输入商品相应的信息</div></div>');
			}else{
				$("#stack-table");
				var str="<tr>"+
				        "<td class='center' ><input type='checkbox' id='ckb'/></td>"+
				        "<td class='center' >"+customername+"</td>"+
				        "<td class='center' >"+goodname+"</td>"+
				        "<td class='center' >"+unitCost+"</td>"+
				        "<td class='center' >"+amount+"</td>"+
				        "<td class='center' >"+changeTwoDecimal_f(unitCost * amount)+"</td>"+
				        "<td class='center' >"+handler+"</td>"+
				        "<td class='center' >"+clearingForm+"</td>"+
				        "</tr>";
				
				if($("#stack-table").find("tr").length >= 2){
		            $("#alert-tip").html('<div class="alert alert-block alert-danger">'+
		                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
		                    '<div class="danger bold-center">只能添加1行数据</div></div>');
				}else{
					$("#alert-tip").html("");
					addTr("stack-table", -1, str);
					sum+=parseFloat(changeTwoDecimal_f(unitCost * amount));
					$("#all-sum").html("总计:<span class='red'>"+changeTwoDecimal_f(sum)+"</span>元");
				}
			}
		}
	}
	
	function tableToJSONString() {
		var tabLen = document.getElementById("stack-table");
		var jsonT = "[";
		for(var i = 1; i < tabLen.rows.length; i++){
			if(i == 1 ){
				jsonT+="{\"customerName\":\""+tabLen.rows[i].cells[1].innerHTML+"\",\"goodName\":\""+tabLen.rows[i].cells[2].innerHTML+"\",\"unitCost\":\""+tabLen.rows[i].cells[3].innerHTML+"\",\"amount\":\""+tabLen.rows[i].cells[4].innerHTML+"\",\"sum\":\""+tabLen.rows[i].cells[5].innerHTML+"\",\"handler\":\""+tabLen.rows[i].cells[6].innerHTML+"\",\"clearingForm\":\""+tabLen.rows[i].cells[7].innerHTML+"\"}";
			}else{
				jsonT+=",{\"customerName\":\""+tabLen.rows[i].cells[1].innerHTML+"\",\"goodName\":\""+tabLen.rows[i].cells[2].innerHTML+"\",\"unitCost\":\""+tabLen.rows[i].cells[3].innerHTML+"\",\"amount\":\""+tabLen.rows[i].cells[4].innerHTML+"\",\"sum\":\""+tabLen.rows[i].cells[5].innerHTML+"\",\"handler\":\""+tabLen.rows[i].cells[6].innerHTML+"\",\"clearingForm\":\""+tabLen.rows[i].cells[7].innerHTML+"\"}";
			}
			for(var j = 0; j < tabLen.rows[i].cells.length; j++){
			    //temp +=tabLen.rows[i].cells[j].innerHTML;
			}
		}
		jsonT+="]";
		console.log(jsonT);
		return jsonT;
	}
	
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
	
	//检测输入的价格
	var record={num:""}
    var checkDecimal = function(n){
        var decimalReg = /^[1-9]\d{0,8}\.{0,1}(\d{1,2})?$/;
        if(n.value!=""&&decimalReg.test(n.value)){ 
           record.num = n.value; 
        }else{ 
           if(n.value != ""){ 
             n.value=record.num; 
		}
	  } 
    }
    
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
    
	//获取系统时间，将时间以指定格式显示到页面。  
    function systemTime()  
    {  
        //获取系统时间。  
        var dateTime=new Date();  
        var hh=dateTime.getHours();  
        var mm=dateTime.getMinutes();  
        var ss=dateTime.getSeconds();  
        //分秒时间是一位数字，在数字前补0。  
        mm = extra(mm);  
        ss = extra(ss);  
        //将时间显示到ID为time的位置，时间格式形如：19:18:02  
        document.getElementById("time").innerHTML=hh+":"+mm+":"+ss;  
        //每隔1000ms执行方法systemTime()。  
        setTimeout("systemTime()",1000);  
    }  
    
    //补位函数。  
    function extra(x)  
    {  
        //如果传入数字小于10，数字前补一位0。  
        if(x < 10)  
        {   
            return "0" + x;  
        }  
        else  
        {  
            return x;  
        }  
    }
    </script> 
    <!-- 工具导入 --> 
    <jsp:include page="/WEB-INF/view/tool.jsp"></jsp:include>
    <!-- /#ace-settings-container --> 
   </div><!-- /.main-container-inner --> 
   <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i> </a> 
  </div>
 </body>
</html>