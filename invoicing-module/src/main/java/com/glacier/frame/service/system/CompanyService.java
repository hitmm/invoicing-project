package com.glacier.frame.service.system;

import com.glacier.basic.util.RandomGUID;
import com.glacier.frame.dao.system.CompanyMapper;
import com.glacier.frame.dao.system.RoleMapper;
import com.glacier.frame.dao.system.UserMapper;
import com.glacier.frame.dao.system.UserRoleMapper;
import com.glacier.frame.dto.query.system.CompanyQueryDTO;
import com.glacier.frame.entity.system.*;
import com.glacier.frame.util.MethodLog;
import com.glacier.jqueryui.util.JqGridReturn;
import com.glacier.jqueryui.util.JqPager;
import com.glacier.jqueryui.util.JqReturnJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description 企业服务
 * @author huguangyin
 *
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED )
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * @Title: FineDep
     * @Description: TODO(获取当前部门名)
     * @param @return    设定文件
     * @return Object    返回类型
     * @throws
     */
    public Object FineCompany(){
        Subject pricipalSubject = SecurityUtils.getSubject();
        return (Company) pricipalSubject.getPrincipal();
    }

    public Object getCompanyById(String companyId){
        if(StringUtils.isEmpty(companyId)){
            return null;
        }
        return companyMapper.selectByPrimaryKey(companyId);
    }

    @Transactional(readOnly = false)
    @MethodLog(opera = "CompanyList_add")
    public Object addCompany(Company company){
        JqReturnJson returnResult = new JqReturnJson();// 构建返回结果，默认结果为false
        CompanyExample companyExample = new CompanyExample();
        int count = 0;
        // 防止管理员名称重复
        companyExample.createCriteria().andCompanyCnNameEqualTo(company.getCompanyCnName());
        count = companyMapper.countByExample(companyExample);// 查找相同管理员名称数量
        if(count > 0){
            returnResult.setMsg("该公司已注册，不允许再次注册");
            return returnResult;
        }

        String ceoId = company.getCeoId();
        if(StringUtils.isEmpty(ceoId)){
            returnResult.setMsg("请提供该公司的法人Id,否则我们将不允许继续注册！！！");
            return returnResult;
        }
        User user = userMapper.selectByPrimaryKey(ceoId);
        if(user == null){
            returnResult.setMsg("法人ID无效，或未在平台注册，请先前往注册！");
            return returnResult;
        }

        company.setCompanyId(RandomGUID.getRandomGUID());
        int insert = companyMapper.insert(company);
        if(insert == 1){
            returnResult.setMsg("[" + company.getCompanyCnName() + "]" + "公司信息已保存");
            returnResult.setSuccess(true);
        } else {
            returnResult.setMsg("发生未知错误，公司信息保存失败");
        }
        return returnResult;
    }

    @Transactional(readOnly = false)
    @MethodLog(opera = "CompanyList_edit")
    public Object editCompany(Company company){
        JqReturnJson returnJson = new JqReturnJson();
        if(StringUtils.isEmpty(company.getCompanyId())){
            returnJson.setMsg("修改的公司未在平台注册，请联系平台管理员注册！！！");
            return returnJson;
        }

        String ceoId = company.getCeoId();
        if(StringUtils.isEmpty(ceoId)){
            returnJson.setMsg("请提供该公司的法人Id,否则我们将不允许修改！！！");
            return returnJson;
        }
        User user = userMapper.selectByPrimaryKey(ceoId);
        if(user == null){
            returnJson.setMsg("法人ID无效，或未在平台注册，请先前往注册！");
            return returnJson;
        }

        CompanyExample companyExample = new CompanyExample();
        // 防止公司名称重复
        companyExample.createCriteria().andCompanyCnNameEqualTo(company.getCompanyCnName());
        List<Company> cnNameEqualsCompany = companyMapper.selectByExample(companyExample);// 查找相同管理员名称数量
        if(!(cnNameEqualsCompany.size()==1&&cnNameEqualsCompany.get(0).getCompanyId().equals(company.getCompanyCnName()))){
            returnJson.setMsg("该公司中文名已被其他公司注册，无法修改。");
            return returnJson;
        }
        Company oldCompany = companyMapper.selectByPrimaryKey(company.getCompanyId());

        if(oldCompany!=null){
            company.setCreateTime(oldCompany.getCreateTime());
            int count = companyMapper.updateByPrimaryKey(company);

            if (count == 1) {
                returnJson.setMsg("[" + company.getCompanyCnName() + "]" + "公司信息已修改");
                returnJson.setSuccess(true);
            } else {
                returnJson.setMsg("发生未知错误，公司信息修改失败");
            }
        }

        return returnJson;
    }


    @Transactional(readOnly = false)
    @MethodLog(opera = "CompanyList_delete")
    public Object deleteCompany(String companyId){
        JqReturnJson returnJson = new JqReturnJson();
        if(StringUtils.isEmpty(companyId)){
            returnJson.setMsg("请提供合法公司标示！！！");
            return returnJson;
        }
        Subject pricipalSubject = SecurityUtils.getSubject();
        User pricipalUser = (User) pricipalSubject.getPrincipal();
        Company company = companyMapper.selectByPrimaryKey(companyId);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(pricipalUser.getUserId());
        List<UserRoleKey> userRoleKeys = userRoleMapper.selectByExample(userRoleExample);
       boolean isAdmin = false;
        if(userRoleKeys!=null&&!userRoleKeys.isEmpty()){
            for(UserRoleKey key : userRoleKeys){
                if(key == null){
                    continue;
                }
                String roleId = key.getRoleId();
                Role role = roleMapper.selectByPrimaryKey(roleId);
                if(role == null){
                    continue;
                }
                String builtin = role.getBuiltin();
                if("admin".equalsIgnoreCase(builtin)){
                    isAdmin = true;
                }
            }
        }
        if(company == null){
            returnJson.setMsg("删除的公司已不存在！！！");
            return returnJson;
        }

        String ceoId = company.getCeoId();
        if(StringUtils.isEmpty(ceoId)){
            returnJson.setMsg("该公司无CEO标示，请立即确认，否则我们将取消该公司的使用权，并删除其全部数据！！！");
            return returnJson;
        }
        if(!ceoId.equals(pricipalUser.getUserId())||!isAdmin){
            returnJson.setMsg("您当前的身份不是公司的CEO或者平台的超级管理员，无权进行删除操作！！！");
            return returnJson;
        }

        int count = companyMapper.deleteByPrimaryKey(companyId);
        if (count == 1) {
            returnJson.setSuccess(true);
            returnJson.setMsg("[" +company.getCompanyCnName()+ "] 公司信息已删除");
        } else {
            returnJson.setMsg("发生未知错误，公司信息删除失败");
        }
        return returnJson;
    }


    /**
     * @Title: listAsGrid
     * @Description: TODO(分页浏览管理员列表)
     * @param @param pager
     * @param @return设定文件
     * @return Object 返回类型
     * @throws
     */
    public Object listAsGrid(CompanyQueryDTO companyQueryDTO, JqPager pager) {
        JqGridReturn returnResulte = new JqGridReturn();
        CompanyExample companyExample = new CompanyExample();

        CompanyExample.Criteria queryCriteria = companyExample.createCriteria();
        companyQueryDTO.setQueryCondition(queryCriteria);

        if (null != pager.getPage() && null != pager.getRows()) {// 设置排序信息
            companyExample.setLimitStart((pager.getPage() - 1) * pager.getRows());
            companyExample.setLimitEnd(pager.getPage()*pager.getRows());
        }
        if (StringUtils.isNotBlank(pager.getOrder()) && StringUtils.isNotBlank(pager.getSort())) {// 设置排序信息
            companyExample.setOrderByClause(pager.getOrderBy("temp_dep_"));
        }
        List<Company> companyList = companyMapper.selectByExample(companyExample);
        int total = companyMapper.countByExample(companyExample);
        returnResulte.setRows(companyList);
        returnResulte.setTotal(total);
        return returnResulte;
    }
}
