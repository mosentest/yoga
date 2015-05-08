<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<div class="page-content"> 
      <div class="row"> 
       <div class="col-xs-12">
        <h3 class="header smaller lighter blue">
          <button id="bt-search-dialog" class="btn btn-purple btn-sm"  data-toggle="modal" data-target="#search-dialog-message"><i class="icon-search align-top bigger-125"  ></i>查询 </button>
          <button id="bt-add-dialog" class="btn  btn-purple btn-sm" data-toggle="modal"  data-target="#add-dialog-message"><i class="icon-plus-sign align-top bigger-125"></i>添加 </button> 
          <button id="bt-edit-dialog" class="btn btn-purple btn-sm" ><i class="icon-edit align-top bigger-125"></i>修改</button>
          <button id="bt-delete-dialog" class="btn btn-purple btn-sm" ><i class="icon-trash align-top bigger-125"></i>删除 </button> 
        </h3>
         <div id="alert"></div>
         <div id="table-result">
          <div class="table-header btn-purple" >所有信息 </div> 
          <div class="table-responsive"> 
           <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
              <div class="row" >
              <div class="col-sm-6"><div id="pager"  ><label >显示 <select size="1" onchange="javascript:gotoGongYingShangPage(1,'name=&beginTime=&endTime=')" id="p_pageSizeSelect">
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
                 <th  role="columnheader"  rowspan="1" colspan="1" style="width: 153px;" >名称</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 133px;" >地址</th> 
                 <th role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" > <i class="icon-time bigger-110 hidden-480"></i>录入时间</th> 
                 <th  role="columnheader"  rowspan="1" colspan="1" style="width: 130px;" >联系电话 </th> 
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
		
		/* 获取数据 */
		gotoGongYingShangPage(1,"name=&beginTime=&endTime=");
		
		/* 复选框操作 */
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});
		});
		
	});
</script>
</body>
</html>