<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 引入国际化标签 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="purchaseArrival_mgr_purchaseArrival_batcharrival_form" method="POST" modelAttribute="parameterCredit" style="padding:15px" enctype="multipart/form-data">
	<table class="formtable"> 
		<tr>
			<td>采购单号：</td>
			<td> ${purchaseDate.arrivalCode}</td>  
		</tr>
		<tr>
			<td>审核状态：</td>
			<td>
			 	<input id="purchaseArrival_mgr_purchaseArrival_batcharrival_auditState" name="auditState" type="radio" value="pass"  /><span>审核成功</span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="purchaseArrival_mgr_purchaseArrival_batcharrival_auditState" name="auditState" type="radio" value="failure"  /><span>审核失败</span>
			 </td> 
		</tr> 
		<tr>
		  <td>审核备注：</td>
		  <td>
		      <textarea   name="auditRemark" class="easyui-validatebox spinner" style="width:300px;height: 70px"></textarea>
		   </td>
	 	</tr>
	</table>
</form>