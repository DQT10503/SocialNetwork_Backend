package com.source_user_auth.domain.user;

import com.api.framework.utils.Annotations.Operator;
import com.source_user_auth.utils.enummerate.AuthStatus;
import io.swagger.annotations.ApiModelProperty;

public class TblUserRequest {
    @ApiModelProperty(value = "Họ và tên")
    @Operator(value = "LIKE")
    private String fullName;
    @ApiModelProperty(value = "Email")
    @Operator(value = "LIKE")
    private String email;
    @ApiModelProperty(value = "Username")
    private String username;
    @ApiModelProperty(value = "Số điện thoại")
    private String phone;
    @ApiModelProperty(value = "Trạng thái")
    private AuthStatus status;

    public TblUserRequest(String fullName, String email, String username, String phone, AuthStatus status) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AuthStatus getStatus() {
        return status;
    }

    public void setStatus(AuthStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblUserRequest [fullName=" + fullName + ", email=" + email + ", username=" + username + ", phone=" + phone + ", status=" + status + "]";
    }
}
