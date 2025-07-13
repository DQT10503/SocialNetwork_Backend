package com.source_relationship.domain.block;

import com.source_relationship.utils.enumerate.RelationshipStatus;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblBlockUpdateRequest extends TblBlockCreateRequest{
    @ApiModelProperty(value = "Trạng thái")
    @NotNull
    private RelationshipStatus status;

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }

    @Override
    public Long getBlockerId() {
        return super.getBlockerId();
    }

    @Override
    public void setBlockerId(Long blockerId) {
        super.setBlockerId(blockerId);
    }

    @Override
    public Long getBlockedId() {
        return super.getBlockedId();
    }

    @Override
    public void setBlockedId(Long blockedId) {
        super.setBlockedId(blockedId);
    }

    @Override
    public String toString() {
        return "TblBlockUpdateRequest [status=" + status + "]";
    }
}
