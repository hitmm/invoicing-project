/**
 * @Title: PurchaseReturnController.java 
 * @Package com.glacier.frame.web.controller.purchase 
 * @author junjie.zheng  
 * @email 1203807137@qq.com
 * @date 2015-1-16 下午4:25:24 
 * @company (开发公司)    珠海市冰川软件有限公司
 * @copyright (版权)    本文件归属珠海市冰川软件有限公司所有
 * @version V1.0
 * @modify (修改) 
 *        	<p>
				第一次修改：
				时间：2015-1-16
				修改人：xichao.dong 
				修改内容简介 ：
			</p>              
 * @Review (审核人) ：xichao.dong
 * 
 */
package com.glacier.frame.web.controller.purchase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.glacier.basic.util.JackJson;
import com.glacier.frame.dto.query.purchase.PurchaseReturnDetailQueryDTO;
import com.glacier.frame.dto.query.purchase.PurchaseReturnQueryDTO;
import com.glacier.frame.entity.purchase.PurchaseReturn;
import com.glacier.frame.entity.purchase.PurchaseReturnDetail;
import com.glacier.frame.service.basicdatas.ParPurchaseReturnReasonService;
import com.glacier.frame.service.basicdatas.ParPurchaseReturnedTypeService;
import com.glacier.frame.service.basicdatas.SuppliersService;
import com.glacier.frame.service.basicdatas.WarehouseService;
import com.glacier.frame.service.purchase.PurchaseReturnDetailService;
import com.glacier.frame.service.purchase.PurchaseReturnService;
import com.glacier.frame.service.system.DepService;
import com.glacier.jqueryui.util.JqGridReturn;
import com.glacier.jqueryui.util.JqPager;

/**
 * @ClassName: PurchaseReturnController 
 * @Description: TODO(采购退货控制层) 
 * @author junjie.zheng
 * @email 1203807137@qq.com
 * @date 2015-1-16 下午4:25:24
 */
@Controller
@RequestMapping(value = "/purchaseReturn")
public class PurchaseReturnController {
	@Autowired
	
    private PurchaseReturnService purchaseReturnService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private DepService depService;
	
	@Autowired
	private SuppliersService suppliersService;
	
	@Autowired
	private ParPurchaseReturnedTypeService returnedTypeService;
	
	@Autowired
	private ParPurchaseReturnReasonService returnReasonService;
	
	@Autowired
	private PurchaseReturnDetailService purchaseReturnDetailService;
	
	
	// 进入采购退货信息列表展示页面
    @RequestMapping(value = "/index.htm")
    private Object intoIndexPmember() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/purchaseReturn");
        return mav;
    } 
    
    //货物信息展示页
    @RequestMapping(value="/showGoods.htm")
    private Object goodsIndex() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/goods");
        return mav;
    }

    //附件上传页
    @RequestMapping(value="/upload.htm")
    private Object doUpload() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/upload");
        return mav;
    }
    
    // 获取表格结构的所有菜单数据
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    @ResponseBody
    private Object listActionAsGridByMenuId(JqPager jqPager, PurchaseReturnQueryDTO purchaseReturnQueryDTO) {
        return purchaseReturnService.listAsGrid(jqPager, purchaseReturnQueryDTO);
    }
    
    // 进入采购退货信息Detail信息页面
    @RequestMapping(value = "/intoDetail.htm")
    private Object intoReturnReasonDetail(String purReturnId) { 
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/purchaseReturn_detail");
        if(StringUtils.isNotBlank(purReturnId)){
            mav.addObject("purchaseReturnDate", purchaseReturnService.getPurchaseReturn(purReturnId));
        }
        return mav;
    }
    
    // 进入采购退货信息Form表单页面
    @RequestMapping(value = "/intoForm.htm")
    private Object intoGradeFormPnews(String purReturnId) {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/purchaseReturn_form");
        //仓库信息获取
        mav.addObject("allWareHouseDate",warehouseService.getWareHouseCombo());
        //获取部门信息
        mav.addObject("allDepTreeNodeData", depService.getAllTreeDepNode(true));
        //获取供应商信息
        mav.addObject("allSuppliesTreeNodeData",suppliersService.getSuppliersCombo());
        //获取退货方式信息
        mav.addObject("allReturnTypeData",returnedTypeService.listAllGrid());
        //获取退货原因信息
        mav.addObject("allReturnReasonData",returnReasonService.listAllGrid());        
        //判断主键标志
        if(StringUtils.isNotBlank(purReturnId)){
            mav.addObject("purchaseReturnDate", purchaseReturnService.getPurchaseReturn(purReturnId));
        }
        return mav;
    }
    
    //获取退货合同详细信息
    @RequestMapping(value = "/returnDetail.json", method = RequestMethod.POST)
    @ResponseBody
    private Object listReturnDetail(JqPager jqPager,PurchaseReturnDetailQueryDTO purchaseReturnDetailQueryDTO,String purReturnId) {
    	if(purReturnId==""){ 
    		JqGridReturn returnResult = new JqGridReturn();
    		return returnResult;
    	}else{
    		purchaseReturnDetailQueryDTO.setPurReturnId(purReturnId);
        	return purchaseReturnDetailService.listAsGrid(jqPager, purchaseReturnDetailQueryDTO);
    	} 
    }
    
    //增加采购退货信息
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    private Object addReturn(String purchaseReturn,String data) {
    	JSONObject purchase = JSONObject.fromObject(purchaseReturn);
    	PurchaseReturn purReturn=(PurchaseReturn)JSONObject.toBean(purchase, PurchaseReturn.class);
    	JSONArray array = JSONArray.fromObject(data);
    	List<PurchaseReturnDetail> list=new ArrayList<PurchaseReturnDetail>();
    	for(int i=0;i<array.size();i++){
    		JSONObject json=JSONObject.fromObject(array.toArray()[i]);
    		PurchaseReturnDetail returnBean=(PurchaseReturnDetail)JSONObject.toBean(json, PurchaseReturnDetail.class);
    		if(returnBean.getGoodsCode().equals("<b>统计：</b>")==false){
    			list.add(returnBean);
    		}
    	}
    	return purchaseReturnService.addPurchaseReturn(purReturn, list);
    }
    
    //修改采购退货类型信息
    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    private Object editGrade(String purchaseReturn,String data) {
    	JSONObject purchase = JSONObject.fromObject(purchaseReturn);
    	PurchaseReturn purReturn=(PurchaseReturn)JSONObject.toBean(purchase, PurchaseReturn.class);
    	JSONArray array = JSONArray.fromObject(data);
    	List<PurchaseReturnDetail> list=new ArrayList<PurchaseReturnDetail>();
    	for(int i=0;i<array.size();i++){
    		JSONObject json=JSONObject.fromObject(array.toArray()[i]);
    		PurchaseReturnDetail returnBean=(PurchaseReturnDetail)JSONObject.toBean(json, PurchaseReturnDetail.class);
    		if(returnBean.getGoodsCode().equals("<b>统计：</b>")==false){
    			list.add(returnBean);
    		}
    	}
        return purchaseReturnService.editPurchaseReturn(purReturn, list);
    }
    
    //删除采购退货信息
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    @ResponseBody
    public Object delGrade(@RequestParam List<String> purReturnIds) {
        return purchaseReturnService.delPurchaseReturn(purReturnIds);
    }
    
    //启用或禁用订购合同订购合同
    @RequestMapping(value = "/enableOrDisable.json", method = RequestMethod.POST)
    @ResponseBody
    public Object enableOrDisable(@RequestParam List<String> purReturnIds,String status) {
    	if(status.equals("disable")){ //禁用
    		return purchaseReturnService.disablePurchaseReturn(purReturnIds);
    	}else{//启用
    		return purchaseReturnService.enablePurchaseReturn(purReturnIds);
    	} 
    } 
    
    //附件上传
    @RequestMapping(value="/uploadFile",method=RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(HttpServletResponse response,HttpServletRequest request) throws IOException{ 
       MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
       /**构建返回结果集**/
       Map<String,Object> map=new HashMap<String,Object>();
       /**页面控件的文件流**/      
       MultipartFile multipartFile = multipartRequest.getFile("fileToUpload");   
       //文件保存路径
       String extendPath=request.getSession().getServletContext().getRealPath("/")+File.separator+"uploadFiles";
       //文件命名
       SimpleDateFormat sf=new SimpleDateFormat("yyyyMM");
       String real_date=sf.format(new Date());
       extendPath+=File.separator+real_date+File.separator;
       //创建文件夹
       File file=new File(extendPath);
       if(!file.exists()){
    	   file.mkdirs();
       }
       //文件存储
       String fileName=null;
       //文件上传
	   fileName=multipartFile.getOriginalFilename();
	   //文件名输出
	   File uploadFile=new File(extendPath+fileName);
	   try{
		   FileCopyUtils.copy(multipartFile.getBytes(),uploadFile ); 
		}catch(IOException e){
		   e.printStackTrace();
	   }
	   //数据存储
	   map.put("name", fileName);
	   map.put("path", "uploadFiles/"+real_date+"/"+fileName);
       return JackJson.fromObjectToJson(map);
    }
    
    //进入采购退货审核页面
    @RequestMapping(value = "/auditForm.htm")
    private Object audit(String purReturnId) {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/audit_form");
        mav.addObject("purchaseReturnData", purchaseReturnService.getPurchaseReturn(purReturnId));
        return mav;
    }
    
    //审核采购退货合同
    @RequestMapping(value = "/audit.json", method = RequestMethod.POST)
    @ResponseBody
    public Object auditPurchaseOrder(PurchaseReturn purchaseReturn) { 
    	return purchaseReturnService.auditPurchaseReturn(purchaseReturn);
    } 
    
    //取消采购退货合同
    @RequestMapping(value = "/cancelAudit.json", method = RequestMethod.POST)
    @ResponseBody
    public Object cancelAuditPurchaseOrder(String purReturnId) { 
    	return purchaseReturnService.cancelAuditPurchaseReturn(purReturnId);
    }  
    
    //进入未审核的列表展示页面
    @RequestMapping(value = "/batchAudit.htm")
    private Object batchAudit() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/batch/batchAudit/purchaseReturn");
        return mav;
    }
    
    //进入已审核的列表展示页面
    @RequestMapping(value = "/batchCancelAudit.htm")
    private Object batchCancelAudit() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/batch/batchCancelAudit/purchaseReturn");
        return mav;
    }  
    
    //进入已禁用的列表展示页面
    @RequestMapping(value = "/batchEnable.htm")
    private Object batchEnable() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/batch/batchEnable/purchaseReturn");
        return mav;
    }  
    
    //进入已启用的列表展示页面
    @RequestMapping(value = "/batchDisable.htm")
    private Object batchDisable() {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/batch/batchDisable/purchaseReturn");
        return mav;
    } 
    
    //进入订单批量审核页面
    @RequestMapping(value = "/auditFormList.htm")
    private Object auditFormList(@RequestParam List<String> purReturnIds,HttpSession session) {
        ModelAndView mav = new ModelAndView("purchase_mgr/purchaseReturn_mgr/batch/batchAudit/audit_form");
        if(purReturnIds.size()!=0){
        	session.setAttribute("auditIds", purReturnIds);//存放批量审核的ID
        }
        return mav;
    }
    
    //审核批量订购合同
    @RequestMapping(value = "/auditList.json", method = RequestMethod.POST)
    @ResponseBody
    public Object auditList(PurchaseReturn purchasereturn,HttpSession session) { 
    	@SuppressWarnings("unchecked")
		List<String> list=(List<String>)session.getAttribute("auditIds"); 
    	session.removeAttribute("auditIds");//删除session
    	return purchaseReturnService.auditPurchaseReturnList(purchasereturn,list);
    }
    
    //批量取消审核订购合同
    @RequestMapping(value = "/batchCancelAudit.json", method = RequestMethod.POST)
    @ResponseBody
    public Object batchCancelAudit(@RequestParam List<String> purReturnIds) { 
    	return purchaseReturnService.batchCancelAudit(purReturnIds);
    } 
    
}