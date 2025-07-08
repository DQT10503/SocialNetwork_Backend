package com.api.framework.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collection;

public class PagingResponse {
    private Collection<?> data;
    private long totalRecords;

    public PagingResponse() {}

    public PagingResponse(Page<?> page) {
        this.data = page.getContent();
        this.totalRecords = page.getTotalElements();
    }

    public PagingResponse(Collection<?> data) { this.data = data; }

    public Collection<?> getData() {
        return data;
    }

    public void setData(Collection<?> data) {
        this.data = data;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public static PagingResponse getEmptyPagingRs(Pageable pageable) {
        Page<?> page = new PageImpl<Object>(new ArrayList<>(), pageable, 0);
        return new PagingResponse(page);
    }
}
