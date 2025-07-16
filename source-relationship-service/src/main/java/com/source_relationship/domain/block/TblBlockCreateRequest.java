package com.source_relationship.domain.block;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblBlockCreateRequest {
    @ApiModelProperty(value = "Đây là UserId, người dùng này sẽ là người chặn")
    @NotNull
    private Long blockerId;
    @ApiModelProperty(value = "Cũng là UserId, người dùng này sẽ là người bị chặn")
    @NotNull
    private Long blockedId;

    public Long getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(Long blockerId) {
        this.blockerId = blockerId;
    }

    public Long getBlockedId() {
        return blockedId;
    }

    public void setBlockedId(Long blockedId) {
        this.blockedId = blockedId;
    }

    @Override
    public String toString() {
        return "TblBlockCreateRequest [blockerId=" + blockerId + ", blockedId=" + blockedId + "]";
    }
}
