/**
 * @Title: PurchaseOrderController.java 
 * @Package com.glacier.frame.web.controller.purchase 
 * @author song.jundong   
 * @email 985776597@qq.com
 * @date 2015-1-15 下午1:48:58 
 * @company (开发公司)    珠海市冰川软件有限公司
 * @copyright (版权)    本文件归属珠海市冰川软件有限公司所有
 * @version V1.0
 * @modify (修改) 
 *        	<p>
				第一次修改：
				时间：2015-1-15
				修改人：song.jundong 
				修改内容简介 ：
			</p>              
 * @Review (审核人) ：song.jundong 
 * 
 */
package com.glacier.frame.web.controller.purchase;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;  

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView; 
import com.glacier.core.controller.AbstractController; 
import com.glacier.frame.dto.query.purchase.PurchaseOrderDetailQueryDTO;
import com.glacier.frame.dto.query.purchase.PurchaseOrderQueryDTO;
import com.glacier.frame.entity.purchase.PurchaseArrival;
import com.glacier.frame.entity.purchase.PurchaseOrder;
import com.glacier.frame.entity.purchase.PurchaseOrderDetail;
import com.glacier.frame.service.basicdatas.ParComDeliverTypeService;
import com.glacier.frame.service.basicdatas.ParComPaymentTypeService;
import com.glacier.frame.service.basicdatas.ParPurchaseTypeService;
import com.glacier.frame.service.basicdatas.SuppliersService;
import com.glacier.frame.service.basicdatas.WarehouseService;
import com.glacier.frame.service.purchase.PurchaseOrderDetailService;
import com.glacier.frame.service.purchase.PurchaseOrderService;
import com.glacier.frame.service.system.UserService;
import com.glacier.jqueryui.util.JqGridReturn;
import com.glacier.jqueryui.util.JqPager; 

/**
 * @ClassName:  PurchaseOrderController
 * @Description: TODO(采购订货合同控制器)
 * @author songjundong
 * @email 985776597@QQ.com
 * @date 2015-1-15  下午1:48:58
 */ 
@Controller
@RequestMapping(value = "/purchaseOrder")
public class PurchaseOrderController extends AbstractController{ 
	 
    @Autowired
    private PurchaseOrderService purchaseOrderService;// 注入service
 
    @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;// 注入service
    
	@Autowired
	private ParPurchaseTypeService purchaseTypeService;
	
	@Autowired
	private ParComDeliverTypeService deliverTypeService;
	
	@Autowired
	private ParComPaymentTypeService paymentTypeService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SuppliersService suppliersService;
    
    //进入列表展示页面
    @RequestMapping(value = "/index.htm")
    private Object intoIndexParea() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/purchaseOrder");
        return mav;
    } 
    
    //进入未审核的列表展示页面
    @RequestMapping(value = "/batchAudit.htm")
    private Object batchAudit() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/batch/batchAudit/purchaseOrder");
        return mav;
    } 
    
    //进入到货展示页面
    @RequestMapping(value = "/arr.htm")
    private Object arr(String purOrderId,HttpSession session) {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/arr");
    	mav.addObject("userDate", userService.getUserCombo(null));//员工信息
    	mav.addObject("deliverTypeDate", deliverTypeService.getDeliverTypeCombo());//所属仓库
    	mav.addObject("purchaseTypeDate", purchaseTypeService.getParPurchaseTypeCombo());//采购类型
    	mav.addObject("suppliersDate", suppliersService.getSuppliersCombo());//供应商
    	mav.addObject("suppliersLogisticsDate", suppliersService.getSuppliersLogisticsCombo());//物流供应商
    	mav.addObject("paymentTypeDate", paymentTypeService.getParComPaymentTypeCombo());//约定支付
    	mav.addObject("warehouseDate", warehouseService.getWareHouseCombo());//仓库 
        mav.addObject("purchaseDate", purchaseOrderService.getPurchaseOrder(purOrderId));
        session.setAttribute("arrId", purOrderId);
        return mav;
    }  
    
    //进入已审核的列表展示页面
    @RequestMapping(value = "/batchCancelAudit.htm")
    private Object batchCancelAudit() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/batch/batchCancelAudit/purchaseOrder");
        return mav;
    }  
    
    //进入已禁用的列表展示页面
    @RequestMapping(value = "/batchEnable.htm")
    private Object batchEnable() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/batch/batchEnable/purchaseOrder");
        return mav;
    }  
    
    //进入已启用的列表展示页面
    @RequestMapping(value = "/batchDisable.htm")
    private Object batchDisable() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/batch/batchDisable/purchaseOrder");
        return mav;
    }  
     
    //进入订单审核页面
    @RequestMapping(value = "/auditForm.htm")
    private Object audit(String purOrderId) {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/audit_form");
        mav.addObject("purchaseOrderData", purchaseOrderService.getPurchaseOrder(purOrderId));
        return mav;
    }
    
    //进入货品展示页面
    @RequestMapping(value = "/goodsIndex.htm")
    private Object goodsIndex() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/goods");
        return mav;
    }
    //根据仓库ID查询出所有货物信息展示页面
    @RequestMapping(value = "/goodsDetail.htm")
    private Object storageList() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/purchaseGoods");
        return mav;
    }
    
    //到货，商品筛选页面
    @RequestMapping(value = "/goodsDetailArr.htm")
    private Object goodsDetailArr() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/purchaseGoodsArr");
        return mav;
    }
    
    //到货操作显示商品页面
    @RequestMapping(value = "/goodsArr.htm")
    private Object goodsArr() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/goodsArr");
        return mav;
    }
    
    //获取表格结构的所有订购合同数据
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    @ResponseBody
    private Object listPurchaseOrder(JqPager jqPager,PurchaseOrderQueryDTO purchaseOrderQueryDTO) {
        return purchaseOrderService.listAsGrid(jqPager, purchaseOrderQueryDTO);
    }
     
    //获取订购合同详细信息
    @RequestMapping(value = "/orderDetail.json", method = RequestMethod.POST)
    @ResponseBody
    private Object listOrderDetail(JqPager jqPager,PurchaseOrderDetailQueryDTO purchaseOrderDetailQueryDTO,String orderId) {
     
    	if(orderId==""){ 
    		JqGridReturn returnResult = new JqGridReturn();
    		return returnResult;
    	}else{
    		purchaseOrderDetailQueryDTO.setPurOrderId(orderId);
        	return purchaseOrderDetailService.listAsGrid(jqPager, purchaseOrderDetailQueryDTO);
    	} 
    }
    
    //进入表单页面
    @RequestMapping(value = "/intoForm.htm")
    private Object inForme(String purOrderId) {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/purchaseOrder_form");// purchaseArrival_form2
        if(StringUtils.isNotBlank(purOrderId)){
            mav.addObject("purchaseOrderData", purchaseOrderService.getPurchaseOrder(purOrderId));
        } 
    	mav.addObject("warehouseDate", warehouseService.getWareHouseCombo());//仓库
    	mav.addObject("deliverTypeDate", deliverTypeService.getDeliverTypeCombo());//交货方式
    	mav.addObject("purchaseTypeDate", purchaseTypeService.getParPurchaseTypeCombo());//采购类型
    	mav.addObject("paymentTypeDate", paymentTypeService.getParComPaymentTypeCombo());//约定支付
      	return mav;
    }
    
    //进入Detail信息页面
    @RequestMapping(value = "/intoDetail.htm")
    private Object intoPurchaseOrder(String purOrderId) { 
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/purchaseOrder_detail");
        if(StringUtils.isNotBlank(purOrderId)){
            mav.addObject("purchaseOrderData", purchaseOrderService.getPurchaseOrder(purOrderId));
        }
        return mav;
    }
    
    //新增订购合同
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    private Object addPurchaseOrder(String purchaseOrder,String data) {   
    	JSONObject purchase = JSONObject.fromObject(purchaseOrder);  
    	PurchaseOrder order = (PurchaseOrder) JSONObject.toBean(purchase,PurchaseOrder.class);
    	JSONArray array = JSONArray.fromObject(data);  
    	List<PurchaseOrderDetail> list=new ArrayList<PurchaseOrderDetail>();  
    	for (int i = 0; i < array.toArray().length; i++) {//遍历循环,去除最后一项统计栏的信息
		   JSONObject json = JSONObject.fromObject(array.toArray()[i]);
		   PurchaseOrderDetail resourceBean = (PurchaseOrderDetail) JSONObject.toBean(json,PurchaseOrderDetail.class);
		   if(resourceBean.getGoodsCode().equals("<b>统计：</b>")){
			 	continue; 
			}   
		   list.add(resourceBean);  
		}    
     	return  purchaseOrderService.addPurchaseOrder(order,list);
    }  
    
    //到货操作
    @RequestMapping(value = "/addArr.json", method = RequestMethod.POST)
    @ResponseBody
    private Object addArr(String purchaseArr,String data) {   
    	JSONObject purchase = JSONObject.fromObject(purchaseArr);  
    	PurchaseArrival arr = (PurchaseArrival) JSONObject.toBean(purchase,PurchaseArrival.class); 
    	JSONArray array = JSONArray.fromObject(data);    
    	List<PurchaseOrderDetail> list=new ArrayList<PurchaseOrderDetail>();  
    	for (int i = 0; i < array.toArray().length; i++) {//遍历循环,去除最后一项统计栏的信息
		   JSONObject json = JSONObject.fromObject(array.toArray()[i]);
		   PurchaseOrderDetail resourceBean = (PurchaseOrderDetail) JSONObject.toBean(json,PurchaseOrderDetail.class);
			if(resourceBean.getGoodsCode().equals("<b>统计：</b>")){
			 	continue; 
			}     
			if(arr.getNotPayAmo()==null){
				arr.setNotPayAmo(new BigDecimal(0));
			}
			 if(arr.getAlrInvAmo()==null){
				arr.setAlrInvAmo(new BigDecimal(0));
			} 
			 if(arr.getNotReturnAmo()==null){
				arr.setNotReturnAmo(new BigDecimal(0));
			}
		   arr.setNotPayAmo(arr.getNotPayAmo().add(resourceBean.getMoney()));//未付款金额
		   arr.setAlrInvAmo(arr.getAlrInvAmo().add(resourceBean.getMoney()));//已开票金额
		   arr.setNotReturnAmo(arr.getNotReturnAmo().add(resourceBean.getMoney()));//未退货金额
		   list.add(resourceBean);  
		}     
      	return  purchaseOrderService.addPurchaseArr(arr,list);
    }  
     
    //修改订购合同
    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    private Object editPurchaseOrder(String purchaseOrder,String data) {
    	JSONObject purchase = JSONObject.fromObject(purchaseOrder);  
    	PurchaseOrder order = (PurchaseOrder) JSONObject.toBean(purchase,PurchaseOrder.class);
    	JSONArray array = JSONArray.fromObject(data);  
    	List<PurchaseOrderDetail> list=new ArrayList<PurchaseOrderDetail>(); 
    	for (int i = 0; i < array.toArray().length; i++) {//遍历循环,去除最后一项统计栏的信息 
		   JSONObject json = JSONObject.fromObject(array.toArray()[i]);
		   PurchaseOrderDetail resourceBean = (PurchaseOrderDetail) JSONObject.toBean(json,PurchaseOrderDetail.class); 
		   if(resourceBean.getGoodsCode().equals("<b>统计：</b>")==false){
		 	  list.add(resourceBean); 
		  }  
		}  
        return purchaseOrderService.editPurchaseOrder(order,list);
    }
    
    //删除订购合同
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    @ResponseBody
    public Object delPurchaseOrder(@RequestParam List<String> purOrderIds,@RequestParam List<String> orderCodes) {
    	return purchaseOrderService.delPurchaseOrder(purOrderIds, orderCodes);
    } 
     
    //审核订购合同
    @RequestMapping(value = "/audit.json", method = RequestMethod.POST)
    @ResponseBody
    public Object auditPurchaseOrder(PurchaseOrder order) { 
    	return purchaseOrderService.auditPurchaseOrder(order);
    } 
    
    //取消审核订购合同
    @RequestMapping(value = "/cancelAudit.json", method = RequestMethod.POST)
    @ResponseBody
    public Object cancelAuditPurchaseOrder(String purOrderId) { 
    	return purchaseOrderService.cancelAuditPurchaseOrder(purOrderId);
    } 
    
    //启用或禁用订购合同订购合同
    @RequestMapping(value = "/enableOrDisable.json", method = RequestMethod.POST)
    @ResponseBody
    public Object enableOrDisable(@RequestParam List<String> purOrderIds,String status) {
    	if(status.equals("disable")){ //禁用
    		return purchaseOrderService.disablePurchaseOrder(purOrderIds);
    	}else{ //启用
    		return purchaseOrderService.enablePurchaseOrder(purOrderIds);
    	} 
    } 
    
    //进入订单批量审核页面
    @RequestMapping(value = "/auditFormList.htm")
    private Object auditFormList(@RequestParam List<String> purOrderIds,HttpSession session) {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseOrder_mgr/batch/batchAudit/audit_form");
        if(purOrderIds.size()!=0){
        	session.setAttribute("auditIds", purOrderIds);//存放批量审核的ID
        }
        return mav;
    }
    
    //审核批量订购合同
    @RequestMapping(value = "/auditList.json", method = RequestMethod.POST)
    @ResponseBody
    public Object auditList(PurchaseOrder order,HttpSession session) { 
    	@SuppressWarnings("unchecked")
		List<String> list=(List<String>)session.getAttribute("auditIds"); 
    	session.removeAttribute("auditIds");//删除session
    	return purchaseOrderService.auditPurchaseOrderList(order,list);
    } 
    
    //批量取消审核订购合同
    @RequestMapping(value = "/batchCancelAudit.json", method = RequestMethod.POST)
    @ResponseBody
    public Object batchCancelAudit(@RequestParam List<String> purOrderIds) { 
    	return purchaseOrderService.batchCancelAudit(purOrderIds);
    } 
    
}
