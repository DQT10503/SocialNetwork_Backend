package com.source_user_auth.domain.user_profile;

import com.source_user_auth.utils.enummerate.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link com.source_user_auth.entity.TblUserProfile}
 */

public class TblUserProfileRequest implements Serializable {
    private Instant birthday;
    private Short gender;
    private String location;
    private String website;
    private String occupation;
    private String education;
    private List<String> interests;
    private AuthStatus status;

    public TblUserProfileRequest() {
    }

    public TblUserProfileRequest(Instant birthday, Short gender, String location, String website, String occupation, String education, List<String> interests, AuthStatus status) {
        this.birthday = birthday;
        this.gender = gender;
        this.location = location;
        this.website = website;
        this.occupation = occupation;
        this.education = education;
        this.interests = interests;
        this.status = status;
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
        return "TblUserProfileRequest [birthday=" + birthday + ", gender=" + gender + ", location=" + location + ", website=" + website + ", occupation=" + occupation + ", education=" + education + ", interests=" + interests + ", status=" + status + "]";
    }
}