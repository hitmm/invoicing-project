package com.glacier.frame.web.controller.basicdatas;
 
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.glacier.core.controller.AbstractController;
import com.glacier.frame.dto.query.basicdatas.BasisClientQueryDTO;
import com.glacier.frame.entity.basicdatas.BasisClient;
import com.glacier.frame.service.basicdatas.BasisClientService;
import com.glacier.frame.service.basicdatas.ParClientLevelService;
import com.glacier.frame.service.basicdatas.ParClientSourceService;
import com.glacier.frame.service.basicdatas.ParClientTypeService;
import com.glacier.frame.service.basicdatas.ParComCompanySizeService;
import com.glacier.frame.service.basicdatas.ParComNatureService;
import com.glacier.frame.service.system.UserService;
import com.glacier.jqueryui.util.JqPager;

/*** 
 * @ClassName:  BasisClientController
 * @Description: TODO(客户档案controller)
 * @author wuting
 * @email 920339213@QQ.com
 * @date 2015-1-09
 */
@Controller
@RequestMapping(value = "/client")
public class BasisClientController extends AbstractController{

	@Autowired
	private BasisClientService basisClientService;
	
	@Autowired
	private ParClientTypeService clientTypeService;
	
	@Autowired
	private ParClientSourceService clientSourceService;
	
	@Autowired
	private ParClientLevelService clientLevelService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ParComCompanySizeService companySizeService;
	
	@Autowired
	private ParComNatureService natureService; 
	
	//进入客户档案信息列表展示页面
    @RequestMapping(value = "/index.htm")
    private Object intoIndexPmember() {
        ModelAndView mav = new ModelAndView("basicdatas_mgr/basisClient_mgr/basisClient");
        return mav;
    } 
    
    //获取表格结构的所有客户档案数据
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    @ResponseBody
    private Object listActionAsGridByMenuId(JqPager jqPager, BasisClientQueryDTO carTypeQueryDTO) {
        return basisClientService.listAsGrid(jqPager, carTypeQueryDTO);
    }
      
    //进入客户档案信息Detail信息页面
    @RequestMapping(value = "/intoDetail.htm")
    private Object intoMemberGradeDetailPage(String clientId) { 
    	ModelAndView mav = new ModelAndView("basicdatas_mgr/basisClient_mgr/basisClient_detail");
        if(StringUtils.isNotBlank(clientId)){
            mav.addObject("clientDate", basisClientService.getBasisClient(clientId));
        }
	    return mav;
    }
    
    //进入客户档案信息Form表单页面
    @RequestMapping(value = "/intoForm.htm")
    private Object intoGradeFormPnews(String clientId) {
        ModelAndView mav = new ModelAndView("basicdatas_mgr/basisClient_mgr/basisClient_form");
        mav.addObject("clientCountDate", basisClientService.getClientCombo());//所有客户档案信息
    	mav.addObject("clientTypeDate", clientTypeService.getClientTypeCombo(null));//客户类型信息
    	mav.addObject("clientLevelDate", clientLevelService.getClientLevelCombo(null));//客户级别信息
    	mav.addObject("clientSourceDate", clientSourceService.getClientSourceCombo(null));//客户来源信息
    	mav.addObject("userDate", userService.getUserCombo(null));//员工信息
    	mav.addObject("natureDate", natureService.getParComNatureCombo());//公司性质
    	mav.addObject("companySizeDate", companySizeService.getParComCompanySizeCombo());//公司规模
        if(StringUtils.isNotBlank(clientId)){
            mav.addObject("clientDate", basisClientService.getBasisClient(clientId));
        }
        return mav;
    }
    
    //增加客户档案信息
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    private Object addGrade(@Valid BasisClient basisClient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {// 后台校验的错误信息
            return returnErrorBindingResult(bindingResult);
        }
        return basisClientService.addBasisClient(basisClient);
    }
    
    //修改客户档案信息
    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    private Object editGrade(@Valid BasisClient basisClient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {// 后台校验的错误信息
            return returnErrorBindingResult(bindingResult);
        }
        return basisClientService.editBasisClient(basisClient);
    }
    
    //批量删除客户档案信息
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    @ResponseBody
    public Object delGrade(@RequestParam List<String> basisClientIds,@RequestParam List<String> basisClientNames) {
    	return basisClientService.delBasisClient(basisClientIds, basisClientNames);
    }
}
