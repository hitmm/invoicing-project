<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 引入自定义权限标签 -->
<%@ taglib prefix="glacierui" uri="http://com.glacier.permissions.com.cn/tag/easyui"%>

<script type="text/javascript">

	$.util.namespace('glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn');//自定义命名空间，相当于一个唯一变量(推荐按照webapp目录结构命名可避免重复)
	
	//定义toolbar的操作，对操作进行控制 
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.param= {
			toolbarId : 'purchaseReturnDataGrid_toolbar',
			actions : {
				edit:{flag:'edit',controlType:'single'},
				del:{flag:'del',controlType:'multiple'},
				audit:{flag:'audit',controlType:'single'},
				cancelAudit:{flag:'cancelAudit',controlType:'single'},
				enable:{flag:'enable',controlType:'multiple'},
				disable:{flag:'disable',controlType:'multiple'},
				pay:{flag:'pay',controlType:'single'}
			}
	};
	
	//初始化采购到货DataGrid
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid = $('#purchaseReturnDataGrid').datagrid({
		fit:true,//控件自动resize占满窗口大小
		iconCls:'icon-save',//图标样式
		border:false,//是否存在边框
		fitColumns:false,//自动填充行
		nowrap: true,//禁止单元格中的文字自动换行
		autoRowHeight: false,//禁止设置自动行高以适应内容
		striped: true,//true就是把行条纹化。（即奇偶行使用不同背景色）
		singleSelect:true,//限制单选
		checkOnSelect:false,//选择复选框的时候选择该行
		selectOnCheck:false,//选择的时候复选框打勾
		url: ctx + '/do/purchaseReturn/list.json',
		sortName: 'createTime',//排序字段名称
		sortOrder: 'desc',//升序还是降序
		remoteSort: true,//开启远程排序，默认为false
		idField:'purReturnId',
		columns:[[
			{
				field:'purReturnId',
				title:'ID',
				checkbox:true
			},{
				field:'returnCode',
				title:'采购退货编号',
				width:120,
				sortable:true
			},{
				field:'auditState',
				title:'审核状态',
				width:120,
				sortable:true,
				formatter: function(value,row,index){
					return renderGridValue(value,fields.auditState);
				}
			},{
				field:'enabled',
				title:'启用/禁用',
				width:120,
				sortable:true,
				formatter: function(value,row,index){
					return renderGridValue(value,fields.status);
				}
			},{
				field:'storageDisplay',
				title:'仓库',
				width:120,
				sortable:true
			},{
				field:'supplierIdDisplay',
				title:'供应商',
				width:120,
				sortable:true
			},{
				field:'creater',
				title:'创建人',
				sortable:true,
				width:100
			},{
				field:'createTime',
				title:'创建时间',
				sortable:true,
				width:200
			},{
				field:'updater',
				title:'更新人',
				sortable:true,
				width:100
			},{
				field:'updateTime',
				title:'更新时间',
				sortable:true,
				width:200
			},{
				field:'remark',
				title:'备注',
				sortable:true,
				width:200
			}
		]],
		pagination : true,//True 就会在 datagrid 的底部显示分页栏
		pageSize : 10,//注意，pageSize必须在pageList存在
		pageList : [2,10,50,100],//从session中获取
		rownumbers:true,//True 就会显示行号的列
		toolbar:'#purchaseReturnDataGrid_toolbar',
		onCheck:function(rowIndex,rowData){//在用户勾选一行的时候触发事件
			action_controller(glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.param,this).check();
		},
		onCheckAll:function(rows){//在用户勾选所有行的时候触发
			action_controller(glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.param,this).check();
		},
		onUncheck:function(rowIndex,rowData){//在用户取消勾选一行的时候触发
			action_controller(glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.param,this).unCheck();
		},
		onUncheckAll:function(rows){//在用户取消勾选所有行的时候触发
			action_controller(glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.param,this).unCheck();
		},
		onSelect:function(rowIndex, rowData){//在用户选择一行的时候触发
			action_controller(glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.param,this).select();
		},
		onUnselectAll:function(rows){//在用户取消勾选所有行的时候触发
			action_controller(glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.param,this).unSelect();
		},
		onLoadSuccess:function(index, record){//加载数据成功触发事件
			$(this).datagrid('clearSelections');
			$(this).datagrid('clearChecked');
			var rows=$(this).datagrid("getRows");
			if(rows.length==0){   
				var body = $(this).data().datagrid.dc.body2;
				body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;color:red">暂时没有记录</td></tr>');
			}
		},
		onDblClickRow:function(rowIndex, rowData){
			$("#layout_center_panel").panel("setTitle","采购退货");
			$('#layout_center_panel').panel('refresh',ctx + '/do/purchaseReturn/intoDetail.htm?purReturnId='+rowData.purReturnId);
		}
	});
	//点击增加按钮触发方法
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.addPurchaseReturn = function(){
		$("#layout_center_panel").panel("setTitle","【采购退货】新增");
		$('#layout_center_panel').panel('refresh', ctx + '/do/purchaseReturn/intoForm.htm');
	};
	
	//点击编辑按钮触发方法
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.editPurchaseReturn = function(){
		var row = glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid("getSelected");
		$("#layout_center_panel").panel("setTitle","【采购退货】编辑");
		$('#layout_center_panel').panel('refresh',ctx +'/do/purchaseReturn/intoForm.htm?purReturnId='+ row.purReturnId); 
	};
	
	//点击审核按钮触发方法
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.auditPurchaseReturn=function(){
		var row = glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid("getSelected");
		glacier.basicAddOrEditDialog({
			title : '审核采购退货单',
			width : 410,
			height : 250,
			queryUrl : ctx + '/do/purchaseReturn/auditForm.htm',
			submitUrl : ctx + '/do/purchaseReturn/audit.json',
			queryParams : {
				purReturnId : row.purReturnId
			},
			successFun : function (){
				glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid('reload');
			}
		});
	}
	
	//取消审核按钮触发事件
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.cancelAuditPurchaseReturn=function(){
		var row = glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid("getSelected");  
		if(row.auditState=='authstr'){
		    $.messager.alert('提示','该记录未进行过审核操作！','info'); 
		    return ;
		}
		$.messager.confirm('请确认','是否要取消审核该记录?',function(r){
            if (r){
            	 $.ajax({ 
            		type: "POST",
            	    url : ctx+ '/do/purchaseReturn/cancelAudit.json',
					data : {
						purReturnId :row.purReturnId 
					},
					dataType : 'json',
					success : function(r) {
						if (r.success) {//因为失败成功的方法都一样操作，这里故未做处理
							$.messager.show({
								title : '提示',
								timeout : 3000,
								msg : r.msg
							});
							glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid('reload');
						} else {
							$.messager.show({//后台验证弹出错误提示信息框
										title : '错误提示',
										width : 380,
										height : 120,
										msg : '<span style="color:red">'+ r.msg+ '<span>',
										timeout : 4500
							 });
						}
					}
				});
			}
		}); 
	}
	
	//启用按钮触发事件
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.enablePurchaseReturn=function(){
		var rows =glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid("getChecked");
		var purReturnIds = [];//启用的id标识 
		for ( var i = 0; i < rows.length; i++) {
			purReturnIds.push(rows[i].purReturnId); 
		 }
		if (purReturnIds.length > 0) {
		$.messager.confirm('请确认','是否要启用这些记录?',function(r){
            if (r){
            	 $.ajax({ 
            		type: "POST",
            	    url : ctx+ '/do/purchaseReturn/enableOrDisable.json?status=enable',
					data : {
						purReturnIds : purReturnIds.join(',') 
					},
					dataType : 'json',
					success : function(r) {
						if (r.success) {//因为失败成功的方法都一样操作，这里故未做处理
							$.messager.show({
								title : '提示',
								timeout : 3000,
								msg : r.msg
							});
							glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid('reload');
						} else {
							$.messager.show({//后台验证弹出错误提示信息框
										title : '错误提示',
										width : 380,
										height : 120,
										msg : '<span style="color:red">'+ r.msg+ '<span>',
										timeout : 4500
									});
						}
					}
				});
			}
		});
	  }
	}
	
	//禁用按钮触发事件
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.disablePurchaseReturn=function(){
		var rows =glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid("getChecked");
		var purReturnIds = [];//启用的id标识 
		for ( var i = 0; i < rows.length; i++) {
			purReturnIds.push(rows[i].purReturnId);  
		 }
		if (purReturnIds.length > 0) {
		$.messager.confirm('请确认','是否要禁用这些记录?',function(r){
            if (r){
            	 $.ajax({ 
            		type: "POST",
            	    url : ctx+ '/do/purchaseReturn/enableOrDisable.json?status=disable',
					data : {
						purReturnIds : purReturnIds.join(',') 
					},
					dataType : 'json',
					success : function(r) {
						if (r.success) {//因为失败成功的方法都一样操作，这里故未做处理
							$.messager.show({
								title : '提示',
								timeout : 3000,
								msg : r.msg
							});
							glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid('reload');
						} else {
							$.messager.show({//后台验证弹出错误提示信息框
										title : '错误提示',
										width : 380,
										height : 120,
										msg : '<span style="color:red">'+ r.msg+ '<span>',
										timeout : 4500
							 });
						}
					}
				});
			}
		});
	  }
	}
    
	//付款按钮触发事件
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.payPurchaseReturn=function(){
		alert("我是付款！");
	}

	//点击删除按钮触发方法
	glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.delPurchaseReturn= function(){
		var rows =  glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid("getChecked");
		var purReturnIds = [];//删除的id标识
		for(var i =0;i<rows.length;i++){
			purReturnIds.push(rows[i].purReturnId);
		}
		if(purReturnIds.length > 0){
			$.messager.confirm('请确认', '是否要删除该记录', function(r){
				if (r){
					$.ajax({
						   type: "POST",
						   url: ctx + '/do/purchaseReturn/del.json',
						   data: {purReturnIds:purReturnIds.join(',')},
						   dataType:'json',
						   success: function(r){
							   if(r.success){//因为失败成功的方法都一样操作，这里故未做处理
								   $.messager.show({
										title:'提示',
										timeout:3000,
										msg:r.msg
									});
								   glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid('reload');
							   }else{
									$.messager.show({//后台验证弹出错误提示信息框
										title:'错误提示',
										width:380,
										height:120,
										msg: '<span style="color:red">'+r.msg+'<span>',
										timeout:4500
									});
								}
						   }
					});
				}
			});
		}
	};
</script>

<!-- 所有采购到货列表面板和表格 -->
<div class="easyui-layout" data-options="fit:true">
	<div id="creditGridPanel" data-options="region:'center',border:true" >
		<table id="purchaseReturnDataGrid">
			<glacierui:toolbar panelEnName="PurchaseReturnList" toolbarId="purchaseReturnDataGrid_toolbar" menuEnName="purchaseReturn"/><!-- 自定义标签：自动根据菜单获取当前用户权限，动态注册方法 -->
		</table>
	</div>
	<div data-options="region:'north',split:true"
		style="height: 40px; padding-left: 10px;">
		<form id="purchaseReturnSearchForm">
			<table>
				<tr>
					<td>采购到货单号：</td>
					<td><input name="returnCode" style="width: 80px;"
						class="spinner" /></td> 
					<td>状态：</td>
					<td><input id="purchaseReturnSearchForm_status" name="enabled" style="width: 80px;"
						 class="easyui-combobox" data-options="valueField:'value',textField : 'label',panelHeight : 'auto',editable : false,data : fields.status"/></td> 
					<td>创建时间：</td>
					<td><input name="createStartTime" class="easyui-datetimebox"
						style="width: 100px;" /> - <input name="createEndTime"
						class="easyui-datetimebox" style="width: 100px;" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-standard-zoom-in',plain:true"
						onclick="glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid('load',glacier.serializeObject($('#purchaseReturnSearchForm')));">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-standard-zoom-out',plain:true"
						onclick="$('#purchaseReturnSearchForm input').val('');glacier.purchase_mgr.purchaseReturn_mgr.purchaseReturn.purchaseReturnDataGrid.datagrid('load',{});">重置条件</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
