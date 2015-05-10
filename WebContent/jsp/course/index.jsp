<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<div class="page-content">
      <div class="page-header fixed-div">
        <p>
          <lable>课程编号：</lable><input type="text" id="id"/>
          <lable>课程名称：</lable><input type="text" id="name"/>
          <lable>课程开课时间：</lable><input type="text" id="date" class="date-picker" data-date-format="yyyy-mm-dd" />
          <lable>课程价格：</lable><input type="text" id="price"/>元
        </p>
        <p>
          <button class="btn btn-primary btn-sm" id="search"><i class="icon-search align-top bigger-125"></i>查询</button>
          <button class="btn btn-success btn-sm" id="add"><i class="icon-plus-sign align-top bigger-125"></i>添加</button>
        </p>
      </div><!--page-header fixed-div  -->
      <div class="row"> 
       <div class="col-xs-12">
         <div id="alert"></div>
         <div id="table-result">
          <div class="table-header btn-purple" >所有信息 </div> 
          <div class="table-responsive"> 
           <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
              <div class="row" >
              <div class="col-sm-6"><div id="pager"  ><label >显示 <select size="1" onchange="javascript:gotoPage(1,'id=&name=&price=')" id="p_pageSizeSelect">
                <option value="10" selected="selected" >10</option>
                <option value="25" >25</option>
                <option value="50" >50</option>
                <option value="100" >100</option></select>记录</label></div ><!--#page  -->
              </div>
              <div id="pages"></div>
             </div><!--.row  -->
            <form>
              <!-- 显示列表数据 -->
              <table id="table" class="table table-striped table-bordered table-hover dataTable" aria-describedby="sample-table-2_info"> 
               <thead> 
                <tr role="row"> 
                 <th role="columnheader" rowspan="1" colspan="1" style="width: 57px;" aria-label=""> <label> <input type="checkbox" class="ace"  id="checkall"/> <span class="lbl"></span> </label> </th> 
                 <th  role="columnheader"  rowspan="1" colspan="1" style="width: 50px;" >序号</th>
                 <th  role="columnheader"  rowspan="1" colspan="1" style="width: 153px;" >课程编号</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 133px;" >课程名称</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" > <i class="icon-time bigger-110 hidden-480"></i>课程开课日期</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" >课程价格</th> 
                 <th  role="columnheader" rowspan="1" colspan="1" style="width: 156px;" aria-label="">操作</th> 
                </tr> 
               </thead> 
               <tbody role="alert" aria-live="polite" aria-relevant="all"  id="tb">
               </tbody> 
              </table>
             </form>
             <div class="row" ><div class="col-sm-6"  id="other"></div></div><!--.row  -->
           </div>
          </div> 
         </div>
       </div> <!-- .col-xs-12 -->
       <!-- /.col --> 
      </div> 
      <!-- /.row --> 
     </div> 
     <!-- /.page-content -->
    <script type="text/javascript">
	jQuery(function($) {

	    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){   
	    });
	    
		//条件查询
	    $("#search").click(function () {
	           var id=$("#id").val();
	           var name=$("#name").val();
	           var state=$("#price").val();
	           var date=$("#date").val();
	           gotoPage(1,"id="+id+"&name="+name+"&date="+date+"&price="+state);
	    });
	    
		/* 获取数据 */
		gotoPage(1,"id=&name=&date=&price=");
		
		/* 复选框操作 */
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});
		});

		//跳转到新增页面
		$('#add').click(function(){
			window.location.href="jsp/course/add.jsp";
	 	});
	
	});

	//分页显示工具
	function gotoPage(pageIndex, cond) {
		var pagerRange = 6;//
		var pageSize =  $("#p_pageSizeSelect").val(); //获取每一页显示多少记录
		var loc="<div class='col-sm-6'><div class='dataTables_paginate paging_bootstrap'><ul class='pagination'>";
		$('#tb').html("");
		//TODO 测试用
		//alert("page=" + pageIndex + "&size=" + pageSize+"&"+cond);
		$.ajax({
			url : "course/list.html",
			type : 'get',
			data : "page=" + pageIndex + "&size=" + pageSize+"&"+cond,
			aysnc : false,
			beforeSend:function(XMLHttpRequest){
	              //alert('远程调用开始...');
	              $("#table-result").showLoading();
	         },
			success : function(msg) {
				if(msg.totalElement == 0){
					$('#tb').append("<tr><td colspan=7><div class='alert alert-block alert-danger'><div class='danger bold-center'>没结果</div><div></td></tr>");
					$('#pages').html("");
					$("#other").html("");
				}else{
					$.each(msg.page.content, function(i, item) {
			              $('#tb').append( "<tr>"
			            		  +"<td><label> <input type='checkbox' class='ace' name='checkbox' value='"+item.courseId+"' /><span class='lbl'></span> </label></td>"
			            		  +"<td >"+(++i)+"</td> "
			            		  +"<td >"+item.courseId+"</td> "
			            		  +"<td >"+item.couresName+"</td> "
			            		  +"<td >"+getSmpFormatDateByLong(item.courseDate,false)+"</td> "
			            		  +"<td >"+item.coursePrice+"元/"+item.tbCourseType.type+"</td> "
			            		  +"<td >"+"<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons' id='buttontools'>"
			            		  				+"<a class='green' href='course/showOne.html?id="+item.courseId+"' > <i class='icon-pencil bigger-130'></i> </a>"
			            		  				+"<a class='red' href='course/delete?id="+
			            		  											item.courseId+
			            		  											"&name="+item.courseName+
			            		  											"&price="+item.coursePrice+
			            		  											"&date="+item.courseDate+
			            		  											"&time1="+item.courseTime1+
			            		  											"&time2="+item.courseTime2+
			            		  											"&typeId="+item.tbCourseType.id+
			            		  											"&type="+item.tbCourseType.type+"' > <i class='icon-trash bigger-130'></i> </a>"
			            		  				+"</td> "+"</tr>");
			            });
						var begin = Math.max(1, msg.page.currentPage - pagerRange/2 );
						var end = Math.min(begin + (pagerRange - 1), msg.page.totalPage);
						if(msg.page.currentPage !=1){
							loc+="<li class='prev '><a href='javascript:gotoPage(1,\""+cond+"\")'><i class=' icon-double-angle-left '></i></a></li><li class='prev '><a href='javascript:gotoPage("+(msg.page.currentPage - 1)+",\""+cond+"\")'><i class=' icon-angle-left '></i></a></li>";
						}else{
							loc+="<li class='prev disabled'><a href='javascript:void(0)'><i class=' icon-double-angle-left '></i></a></li><li class='prev disabled'><a href='javascript:void(0)'><i class=' icon-angle-left '></i></a></li>";
						}
						for(var i = begin; i <= end; i++){
							if(msg.page.currentPage == i){
								loc+="<li class='active'><a href='javascript:void(0)'>"+i+"</a></li>"
							}else{
								loc+="<li><a href='javascript:gotoPage("+i +",\""+cond+"\")' >"+i+"</a></li>"
							}
						}
						if(msg.page.currentPage!=msg.page.totalPage){
							loc+="<li class='next'><a href='javascript:gotoPage("+(msg.page.currentPage + 1)+",\""+cond+"\")'><i class='icon-angle-right '></i></a></li><li class='next '><a href='javascript:gotoPage("+msg.page.totalPage+",\""+cond+"\")'><i class='icon-double-angle-right '></i></a></li>";
						}else{
							loc+="<li class='next disabled'><a href='javascript:void(0)'><i class='icon-angle-right '></i></a></li><li class='next disabled'><a href='javascript:void(0)'><i class='icon-double-angle-right '></i></a></li>";
						}
						loc+="</ul></div></div>";
						$('#pages').html(loc);
						$("#other").html("<a href='javascript:gotoPage(1,\""+cond+"\")' ><i class='icon-refresh'></i></a>&nbsp;|&nbsp;<label >共 "+msg.page.totalElement+" 记录&nbsp;|&nbsp;共 "+msg.page.totalPage +" 页</label>");
				}
				$("#table-result").hideLoading();
			},
			complete:function(XMLHttpRequest,textStatus){
				  //TODO 测试用
	              // alert('远程调用成功，状态文本值：'+textStatus);
				$("#table-result").hideLoading();
	         },
	         error:function(XMLHttpRequest,textStatus,errorThrown){
	              alert('error...状态文本值：'+textStatus+" 异常信息："+errorThrown);
	              $("#table-result").hideLoading();
	          }
		});
	}
</script>
</body>
</html>