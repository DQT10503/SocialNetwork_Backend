package com.source_user_auth.domain.user_profile;

import com.api.framework.utils.converter.DateTimeJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.source_user_auth.utils.enummerate.AuthStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link com.source_user_auth.entity.TblUserProfile}
 */
public class TblUserProfileDetailResponse implements Serializable {
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Instant createdAt;
    private String createdBy;
    private String updatedBy;
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Instant updatedAt;
    private Long id;
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Instant birthday;
    private Short gender;
    private String location;
    private String website;
    private String occupation;
    private String education;
    private List<String> interests;
    private AuthStatus status;

    public TblUserProfileDetailResponse() {
    }

    public TblUserProfileDetailResponse(Instant createdAt, String createdBy, String updatedBy, Instant updatedAt, Long id, Instant birthday, Short gender, String location, String website, String occupation, String education, List<String> interests, AuthStatus status) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.id = id;
        this.birthday = birthday;
        this.gender = gender;
        this.location = location;
        this.website = website;
        this.occupation = occupation;
        this.education = education;
        this.interests = interests;
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "TblUserProfileDto [createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + ", id=" + id + ", birthday=" + birthday + ", gender=" + gender + ", location=" + location + ", website=" + website + ", occupation=" + occupation + ", education=" + education + ", interests=" + interests + ", status=" + status + "]";
    }
}