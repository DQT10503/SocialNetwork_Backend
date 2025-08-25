package com.source_user_auth.domain.user;

import com.source_user_auth.utils.UserAnnotations.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.source_user_auth.entity.TblUser}
 */
@PasswordMatches(password = "password", passwordConfirm = "passwordConfirm")
public class TblUserUpdateRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;
    private String bio;
    private String avatarUrl;
    private String phone;

    public TblUserUpdateRequest() {
    }

    public TblUserUpdateRequest(Long id, String fullName, String email, String username, String password, String passwordConfirm, String bio, String avatarUrl, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TblUserUpdateRequest [id=" + id + ", fullName=" + fullName + ", email=" + email + ", username=" + username + ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", bio=" + bio + ", avatarUrl=" + avatarUrl + ", phone=" + phone + "]";
    }
}