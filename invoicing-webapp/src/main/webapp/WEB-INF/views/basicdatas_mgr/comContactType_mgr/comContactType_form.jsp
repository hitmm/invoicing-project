<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 引入国际化标签 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="parContactType_mgr_parContactType_form" method="POST" modelAttribute="parameterCredit" style="padding:15px" enctype="multipart/form-data">
	<table class="formtable">
		<tr>
			<td>类型名称：</td>
			<td>
				<input type="hidden" name="contactTypeId" value="${contactTypeDate.contactTypeId}" />
				<input name="contactTypeName"  class="easyui-validatebox spinner" style="width:168px;height: 16px;" required="true" value="${contactTypeDate.contactTypeName}"/>
			</td>
		</tr>
		<tr>
			<td>序号：</td>
			<td><input maxlength="3" name="sequenced" class="easyui-numberspinner spinner" style="width: 170px;height:18px;" value="${contactTypeDate.sequenced}" data-options="min:0,max:999,required:true,missingMessage:'只能输入整数'" /></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input id="deliverType_from_deliverType" name="enabled" style="width: 170px;height:18px;" value="${contactTypeDate.enabled}"  class="easyui-combobox" data-options="valueField:'value',textField : 'label',panelHeight : 'auto',editable : false,required:true,data : fields.status"/></td>
		</tr>
	</table>
</form>