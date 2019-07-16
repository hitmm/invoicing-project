<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 引入自定义权限标签 -->
<%@ taglib prefix="glacierui" uri="http://com.glacier.permissions.com.cn/tag/easyui"%>

<script type="text/javascript">

	$.util.namespace('glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition');//自定义命名空间，相当于一个唯一变量(推荐按照webapp目录结构命名可避免重复)
	
	//定义toolbar的操作，对操作进行控制 
	glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.param = {
			toolbarId : 'purchaseRequisitionDataGrid_toolbar',
			actions : {
				edit:{flag:'edit',controlType:'single'},
				del:{flag:'del',controlType:'multiple'}
			}
	};
	
	//初始化采购申请DataGrid
	glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid = $('#purchaseRequisitionDataGrid').datagrid({
		fit:true,//控件自动resize占满窗口大小
		iconCls:'icon-save',//图标样式
		border:false,//是否存在边框
		fitColumns:true,//自动填充行
		nowrap: true,//禁止单元格中的文字自动换行
		autoRowHeight: false,//禁止设置自动行高以适应内容
		striped: true,//true就是把行条纹化。（即奇偶行使用不同背景色）
		singleSelect:true,//限制单选
		checkOnSelect:false,//选择复选框的时候选择该行
		selectOnCheck:false,//选择的时候复选框打勾
		url: ctx + '/do/purchaseRequisition/list.json',
		sortName: 'reqCode',//排序字段名称
		sortOrder: 'desc',//升序还是降序
		remoteSort: true,//开启远程排序，默认为false
		idField:'purReqId',
		columns:[[
			{
				field:'purReqId',
				title:'ID',
				checkbox:true
			},{
				field:'reqCode',
				title:'采购申请编号',
				width:120,
				sortable:true
			},{
				field:'req_date',
				title:'申请日期',
				width:120,
				sortable:true
			},{
				field:'enabled',
				title:'状态',
				width:120,
				sortable:true,
				formatter: function(value,row,index){
					return renderGridValue(value,fields.status);
				}
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
			}
		]],
		pagination : true,//True 就会在 datagrid 的底部显示分页栏
		pageSize : 10,//注意，pageSize必须在pageList存在
		pageList : [2,10,50,100],//从session中获取
		rownumbers:true,//True 就会显示行号的列
		toolbar:'#purchaseRequisitionDataGrid_toolbar',
		onCheck:function(rowIndex,rowData){//在用户勾选一行的时候触发事件
			action_controller(glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.param,this).check();
		},
		onCheckAll:function(rows){//在用户勾选所有行的时候触发
			action_controller(glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.param,this).check();
		},
		onUncheck:function(rowIndex,rowData){//在用户取消勾选一行的时候触发
			action_controller(glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.param,this).unCheck();
		},
		onUncheckAll:function(rows){//在用户取消勾选所有行的时候触发
			action_controller(glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.param,this).unCheck();
		},
		onSelect:function(rowIndex, rowData){//在用户选择一行的时候触发
			action_controller(glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.param,this).select();
		},
		onUnselectAll:function(rows){//在用户取消勾选所有行的时候触发
			action_controller(glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.param,this).unSelect();
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
			$.easyui.showDialog({
				title: "采购申请详细信息",
				href : ctx + '/do/purchaseRequisition/intoDetail.htm?purReqId='+rowData.purReqId,//从controller请求jsp页面进行渲染
				width : 530,
				height : 250,
				resizable: false,
				enableApplyButton : false,
				enableSaveButton : false
			});
			
		}
	});
	//点击增加按钮触发方法
	glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.addPurchaseRequisition = function(){
		glacier.basicAddOrEditDialog({
			title : '【采购申请】 - 增加',
			width : 420,
			height : 200,
			queryUrl : ctx + '/do/purchaseRequisition/intoForm.htm',
			submitUrl : ctx + '/do/purchaseRequisition/add.json',
			successFun : function (){
				glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid.datagrid('reload');
			}
		});
	};
	
	//点击编辑按钮触发方法
	glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.editPurchaseRequisition = function(){
		var row = glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid.datagrid("getSelected");
		glacier.basicAddOrEditDialog({
			title : '【采购申请】 - 编辑',
			width : 420,
			height : 200,
			queryUrl : ctx + '/do/purchaseRequisition/intoForm.htm',
			submitUrl : ctx + '/do/purchaseRequisition/edit.json',
			queryParams : {
				purReqId : row.purReqId
			},
			successFun : function (){
				glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid.datagrid('reload');
			}
		});
	};
	//点击删除按钮触发方法
	glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.delPurchaseRequisition = function(){
		var rows = glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid.datagrid("getChecked");
		var purReqIds = [];//删除的id标识
		var reqCodes = [];//日志记录引用名称
		for(var i =0;i<rows.length;i++){
			purReqIds.push(rows[i].purReqId);
			reqCodes.push(rows[i].reqCode);
		}
		if(purReqIds.length > 0){
			$.messager.confirm('请确认', '是否要删除该记录', function(r){
				if (r){
					$.ajax({
						   type: "POST",
						   url: ctx + '/do/purchaseRequisition/del.json',
						   data: {purReqIds:purReqIds.join(','),reqCodes:reqCodes.join(',')},
						   dataType:'json',
						   success: function(r){
							   if(r.success){//因为失败成功的方法都一样操作，这里故未做处理
								   $.messager.show({
										title:'提示',
										timeout:3000,
										msg:r.msg
									});
								   glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid.datagrid('reload');
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

<!-- 所有采购申请列表面板和表格 -->
<div class="easyui-layout" data-options="fit:true">
	<div id="purchaseRequisitionGridPanel" data-options="region:'center',border:true" >
		<table id="purchaseRequisitionDataGrid">
			<glacierui:toolbar panelEnName="PurchaseRequisitionList" toolbarId="purchaseRequisitionDataGrid_toolbar" menuEnName="purchaseRequisition"/><!-- 自定义标签：自动根据菜单获取当前用户权限，动态注册方法 -->
		</table>
	</div>
	<div data-options="region:'north',split:true"
		style="height: 40px; padding-left: 10px;">
		<form id="purchaseRequisitionSearchForm">
			<table>
				<tr>
					<td>采购申请名称：</td>
					<td><input name="purchaseRequisitionName" style="width: 80px;"
						class="spinner" /></td> 
					<td>状态：</td>
					<td><input id="purchaseRequisitionSearchForm_status" name="enabled" style="width: 80px;"
						 class="easyui-combobox" data-options="valueField:'value',textField : 'label',panelHeight : 'auto',editable : false,data : fields.status"/></td> 
					<td>创建时间：</td>
					<td><input name="createStartTime" class="easyui-datetimebox"
						style="width: 100px;" /> - <input name="createEndTime"
						class="easyui-datetimebox" style="width: 100px;" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-standard-zoom-in',plain:true"
						onclick="glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid.datagrid('load',glacier.serializeObject($('#purchaseRequisitionSearchForm')));">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-standard-zoom-out',plain:true"
						onclick="$('#purchaseRequisitionSearchForm input').val('');glacier.purchase_mgr.purchaseRequisition_mgr.purchaseRequisition.purchaseRequisitionDataGrid.datagrid('load',{});">重置条件</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
