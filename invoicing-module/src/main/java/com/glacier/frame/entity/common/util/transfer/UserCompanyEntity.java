package com.glacier.frame.entity.common.util.transfer;

import com.glacier.frame.entity.system.Company;
import com.glacier.frame.entity.system.Dep;
import com.glacier.frame.entity.system.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserCompanyEntity {

    private String userId;

    private Role role;

    private Company company;

    private List<Dep> deps;
}
