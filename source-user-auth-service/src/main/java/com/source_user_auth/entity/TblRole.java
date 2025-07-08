package com.source_user_auth.entity;

import com.source_user_auth.utils.enummerate.AuthStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_role")
public class TblRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AuthStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthStatus getStatus() {
        return status;
    }

    public void setStatus(AuthStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblRole [name=" + name + ", status=" + status + "]";
    }
}