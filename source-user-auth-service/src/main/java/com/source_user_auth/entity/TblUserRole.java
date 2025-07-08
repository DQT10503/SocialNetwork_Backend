package com.source_user_auth.entity;

import com.source_user_auth.utils.enummerate.AuthStatus;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user_role")
public class TblUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AuthStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public AuthStatus getStatus() {
        return status;
    }

    public void setStatus(AuthStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblUserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + ", status=" + status + "]";
    }
}