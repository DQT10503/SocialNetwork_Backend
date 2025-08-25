package com.source_user_auth.domain.user_profile;

import com.source_user_auth.utils.enummerate.AuthStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link com.source_user_auth.entity.TblUserProfile}
 */
public class TblUserProfileCreateRequest implements Serializable {
    @ApiModelProperty(value = "Ngày sinh")
    private Instant birthday;
    @ApiModelProperty(value = "Giới tính")
    private Short gender;
    @ApiModelProperty(value = "Địa chỉ")
    private String location;
    @ApiModelProperty(value = "Trang mạng khác")
    private String website;
    @ApiModelProperty(value = "Nghề nghiệp")
    private String occupation;
    @ApiModelProperty(value = "Trường học")
    private String education;
    @ApiModelProperty(value = "Sở thích")
    private List<String> interests;

    public TblUserProfileCreateRequest() {
    }

    public TblUserProfileCreateRequest(Instant birthday, Short gender, String location, String website, String occupation, String education, List<String> interests) {
        this.birthday = birthday;
        this.gender = gender;
        this.location = location;
        this.website = website;
        this.occupation = occupation;
        this.education = education;
        this.interests = interests;
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

    @Override
    public String toString() {
        return "TblUserProfileCreateRequest [birthday=" + birthday + ", gender=" + gender + ", location=" + location + ", website=" + website + ", occupation=" + occupation + ", education=" + education + ", interests=" + interests + "]";
    }
}