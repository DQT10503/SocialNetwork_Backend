package com.api.framework.security;

import java.io.Serializable;
import java.util.List;

public class BearerContext implements Serializable {
    private static final long serialVersionUID = 1L;

    private String masterAccount;
    private String httpMethod;
    private List<Long> roleIds;
    private String token;

    public String getMasterAccount() {
        return masterAccount;
    }

    public void setMasterAccount(String masterAccount) {
        this.masterAccount = masterAccount;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
