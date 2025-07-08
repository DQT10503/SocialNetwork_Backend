package com.source_user_auth.domain.auth;

import com.api.framework.utils.Annotations.Email;
import com.source_user_auth.utils.UserAnnotations.PasswordMatches;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@PasswordMatches(password = "password", passwordConfirm = "passwordConfirm")
public class RegisterRequest {
    @ApiModelProperty(value = "Họ và tên")
    @NotBlank
    private String fullName;
    @ApiModelProperty(value = "Địa chỉ email")
    @Email
    @NotBlank
    private String email;
    @ApiModelProperty(value = "Số điện thoại")
    @NotBlank
    private String phone;
    @ApiModelProperty(value = "Mật khẩu")
    private String password;
    @ApiModelProperty(value = "Xác nhận mật khẩu")
    private String passwordConfirm;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String toString() {
        return "RegisterRequest [fullName=" + fullName + ", email=" + email + ", phone=" + phone + ", password=" + password + ", passwordConfirm=" + passwordConfirm + "]";
    }
}
