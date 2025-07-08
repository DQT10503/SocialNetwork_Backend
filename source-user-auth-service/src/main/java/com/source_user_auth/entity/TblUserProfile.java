package com.source_user_auth.entity;


import com.source_user_auth.utils.enummerate.AuthStatus;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tbl_user_profile")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class TblUserProfile extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "gender")
    private Short gender;

    @Column(name = "location")
    private String location;

    @Column(name = "website")
    private String website;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "education")
    private String education;

    @Type(type = "list-array")
    @Column(name = "interests")
    private List<String> interests;

    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AuthStatus status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public AuthStatus getStatus() {
        return status;
    }

    public void setStatus(AuthStatus status) {
        this.status = status;
    }

}