<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!-- 引入国际化标签 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
        #formtable tr td{padding:2px;} 
        
        #waresort_td{
            padding:2px;
        }
</style>

<form id="creditType_mgr_creditType_form" method="POST" modelAttribute="parameterCredit" style="padding:15px" enctype="multipart/form-data">
	<table class="formtable" id="formtable">
		<tr>
			<td style="width:120px;">仓库名称：</td>
			<td>
				<input type="hidden" name="warehouseId" value="${warehouseDate.warehouseId}" />
				<input name="warehouseName"  class="easyui-validatebox spinner" style="width:168px;height: 18px;"  required="true" value="${warehouseDate.warehouseName}"/>
			</td>
			 <td style="padding-left: 15px;">仓库排序：</td>
			<td>
			    <input maxlength="3" name="orderNum" class="easyui-numberspinner spinner" style="width:170px;height: 18px;"   value="${warehouseDate.orderNum}" data-options="min:0,max:99999,required:true,missingMessage:'请输入仓库序号'" /> 
			 </td>
		</tr>
		<tr>
		    <td>所属部门：</td>
			<td>
				<input name="departmentId" id="departmentId"  class="easyui-combobox spinner" style="width:170px;height: 18px;"  required="true" value="${warehouseDate.departmentId}"/>
			</td>
			<td style="padding-left: 15px;">仓库管理：</td>
			<td><input name="warehouseManager" id="warehouseManager" class="easyui-combobox spinner" style="width:170px;height: 18px;"  value="${warehouseDate.warehouseManager}" required="true"/></td>
		   
		</tr>
		<tr>
			<td>负责人员：</td>
			<td><input name="head" class="easyui-validatebox spinner" style="width:168px;height: 18px;"  value="${warehouseDate.head}" required="true" /></td>
		    <td style="padding-left: 15px;">联系电话：</td>
			<td><input  name="phone" class="easyui-validatebox spinner" style="width:168px;height: 18px;"  value="${warehouseDate.phone}" required="true" validType="customReg['^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$','<fmt:message key="User.mobile.illegal"/>']" missingMessage="请输入正确手机号"/ /></td>
		</tr>
		<tr>
			<td>零售库存：</td>
			<td><input name="retailLibraries" id="retailLibraries" style="width:170px;height: 18px;"  class="easyui-combobox spinner" value="${warehouseDate.retailLibraries}" required="true"/></td>
		     <td style="padding-left: 15px;">仓库地址：</td>
			<td><input name="address" class="easyui-validatebox spinner" style="width:168px;height: 18px;"  value="${warehouseDate.address}" required="true"/></td>
		</tr>
		<tr style="height: 120px;">
		  <td>仓库分类：</td>
		  <td colspan="3" >
		      <div style="width:420px;height:100px;border-top:1px solid #ABADB3;" >
		       <div id="waresort_td" style="width:420px;height:87px;overflow-y:auto;margin-top: 15px;" ></div>
		     </div>
		  </td>
	    </tr>
		<tr >
		  <td>备注信息：</td>
		  <td colspan="3" style="height:65px;">
		         <textarea   name="remark" class="easyui-validatebox spinner" style="width:420px;height:50px;" >${warehouseDate.remark}</textarea>
		   </td>
	    </tr>
	</table>
</form>
 <script>

    var str='${warehouseDate.warehouseId}';

     $('#retailLibraries').combobox({
			valueField : 'value',
			width : 168,
			textField : 'label',
			panelHeight : 'auto',
			editable : false,
			data : fields.yesOrNo
		});
     
        if($.parseJSON('${allTypeTreeNodeData}').length>0){
       	     $.each($.parseJSON('${allTypeTreeNodeData}'),function(i,v){
       	    	  if((i+1)%4==0){
           			var listHtml="<label style='padding:6px;'><input type='checkbox' name='warehouseTypeName' value='"+v.warGoodsTypeId+"'/>&nbsp;&nbsp;"+v.name+"</label ><br/><br/>";
           		  }else{
           			var listHtml="<label style='padding:6px;'><input type='checkbox' name='warehouseTypeName' value='"+v.warGoodsTypeId+"'/>&nbsp;&nbsp;"+v.name+"</label >";
           		  }
           		  $(listHtml).appendTo("#waresort_td");
           	 });    	
          }    
       
       if('${allClassifyDate}'){
			if($.parseJSON('${allClassifyDate}').length>0){
	        	 $.each($.parseJSON('${allClassifyDate}'),function(i,v){
	        		 $("input[name='warehouseTypeName'][value='"+v.warGoodsTypeId+"']").attr("checked","checked");
	        	 });
	         }
		}
         
         //初始化上级部门
		$("#departmentId").combotree({
			data :$.parseJSON('${allDepTreeNodeData}'),
			width:168,
			panelHeight : 'auto',
		    missingMessage:'请选择上级部门',
		    smooth: true,       //该属性用以启用当前 easyui-tree 控件对平滑数据格式的支持
		    lines : true,
		    editable:false,
		    onBeforeSelect:function(node){
		    	if(node.state){
                     $("#departmentId").combotree("unselect");
                 }
		    },
		    onSelect:function(record){
		    	$.ajax({
		    		type: "post", 
		    		url:ctx + "/do/user/dept.json?depId="+record.id,
		    		dataType:"json",
		    		success: function (date){
		    			   $("#warehouseManager").combobox({
		    				 	data:$.parseJSON(date),
		    					valueField:'id',    
		    				    textField:'text',
		    				    panelHeight : 'auto',
		    				    editable:false 
		    			   });
		    			   if($.parseJSON(date).length>0){
				    		   $("#warehouseManager").combobox('select', $.parseJSON(date)[0].id);
						   }else{
							   $("#warehouseManager").combobox('setValue', '');
						   }
		    			}
		    	});
		        
		    }
		});	
		
</script>
