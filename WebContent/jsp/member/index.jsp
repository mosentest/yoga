<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<div class="page-content">
      <div class="page-header fixed-div">
        <p>
          <lable>会员编号：</lable><input type="text" id="id"/>
          <lable>会员名字：</lable><input type="text" id="name"/>
          <lable>家庭住址：</lable><input type="text" id="address"/>
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
                 <th  role="columnheader"  rowspan="1" colspan="1" style="width: 153px;" >会员编号</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 133px;" >会员帐号</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" >会员名字</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" >性别</th>
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" >身份证</th>
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" >手机号</th>
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" >家庭住址</th>
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

		//条件查询
	    $("#search").click(function () {
	           var id=$("#id").val();
	           var name=$("#name").val();
	           var address=$("#address").val();
	           gotoPage(1,"id="+id+"&name="+name+"&address="+address);
	    });
	    
		/* 获取数据 */
		gotoPage(1,"id=&name=&address=");
		
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
			countDown(2, "jsp/member/add.jsp");
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
			url : "member/list.html",
			type : 'get',
			data : "page=" + pageIndex + "&size=" + pageSize+"&"+cond,
			aysnc : false,
			beforeSend:function(XMLHttpRequest){
	              //alert('远程调用开始...');
	              $("#table-result").showLoading();
	         },
			success : function(msg) {
				if(msg.page.totalElement == 0){
					$('#tb').append("<tr><td colspan="+10+"><div class='alert alert-block alert-danger'><div class='danger bold-center'>没结果</div><div></td></tr>");
					$('#pages').html("");
					$("#other").html("");
				}else{
					$.each(msg.page.content, function(i, item) {
			              $('#tb').append( "<tr>"
			            		  +"<td><label> <input type='checkbox' class='ace' name='checkbox' value='"+item.memberId+"' /><span class='lbl'></span> </label></td>"
			            		  +"<td >"+(++i)+"</td> "
			            		  +"<td >"+item.memberId+"</td> "
			            		  +"<td >"+item.memberUsername+"</td> "
			            		  +"<td >"+item.memberName+"</td> "
			            		  +"<td >"+showinfo(item.memberSex)+"</td> "
			            		  +"<td >"+item.memberCard+"</td> "
			            		  +"<td >"+item.memberPhone+"</td> "
			            		  +"<td >"+item.memberAddress+"</td> "
			            		  +"<td >"+"<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons' id='buttontools'>"
			            		  				+"<a class='green' href='member/showOne.html?id="+item.memberId+"' > <i class='icon-pencil bigger-130'></i> </a>"
			            		  				+"<a class='red' href='member/delete?id="+
			            		  											item.memberId+
			            		  											"&username="+item.memberUsername+
			            		  											"&name="+item.memberName+
			            		  											"&sex="+item.memberSex+
			            		  											"&card="+item.memberCard+
			            		  											"&phone="+item.memberPhone+
			            		  											"&address="+item.memberAddress+
			            		  											"&typeId="+item.tbMemberType.id+
			            		  											"&type="+item.tbMemberType.type+"' > <i class='icon-trash bigger-130'></i> </a>"
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
	              // alert('远程调用成功，状态文本值：'+textStatus);
				$("#table-result").hideLoading();
	         },
	         error:function(XMLHttpRequest,textStatus,errorThrown){
	              alert('error...状态文本值：'+textStatus+" 异常信息："+errorThrown);
	              $("#table-result").hideLoading();
	          }
		});
	}
	function showinfo(flag){
		if(flag == false){
			return "男";
		}else{
			return "女";
		}
	}
</script>
</body>
</html>