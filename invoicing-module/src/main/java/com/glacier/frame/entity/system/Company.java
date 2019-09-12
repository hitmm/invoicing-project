package com.glacier.frame.entity.system;

public class Company {
    private String companyId;

    private String companyCnName;

    private String companyAlias;

    private String companyEnName;

    private String ceoId;

    private String fax;

    private String hostUrl;

    private String intro;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyCnName() {
        return companyCnName;
    }

    public void setCompanyCnName(String companyCnName) {
        this.companyCnName = companyCnName == null ? null : companyCnName.trim();
    }

    public String getCompanyAlias() {
        return companyAlias;
    }

    public void setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias == null ? null : companyAlias.trim();
    }

    public String getCompanyEnName() {
        return companyEnName;
    }

    public void setCompanyEnName(String companyEnName) {
        this.companyEnName = companyEnName == null ? null : companyEnName.trim();
    }

    public String getCeoId() {
        return ceoId;
    }

    public void setCeoId(String ceoId) {
        this.ceoId = ceoId == null ? null : ceoId.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl == null ? null : hostUrl.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}