package com.source_user_auth.domain.user;

import com.api.framework.utils.converter.DateTimeJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.source_user_auth.entity.TblUser}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TblUserDetailResponse implements Serializable {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String bio;
    private String avatarUrl;
    private String phone;
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Instant createdAt;

    @Override
    public String toString() {
        return "TblUserDetailResponse [id=" + id + ", fullName=" + fullName + ", email=" + email + ", username=" + username + ", bio=" + bio + ", avatarUrl=" + avatarUrl + ", phone=" + phone + ", createdAt=" + createdAt + "]";
    }
}