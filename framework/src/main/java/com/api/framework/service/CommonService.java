package com.api.framework.service;

import com.api.framework.domain.PagingResponse;
import com.api.framework.utils.SimpleQueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

public interface CommonService {

    <T> PagingResponse executeSearchData(Pageable pageRequest, SimpleQueryBuilder simpleQueryBuilder, Map<String, Object> params, Class<T> clazz);

    <T> T executeGetDetail(SimpleQueryBuilder simpleQueryBuilder, Map<String, Object> params, Class<T> clazz);

    List<Tuple> executeGetListTuple(String sql, Map<String, Object> params);

    Tuple executeGetOneTuple(String sql, Map<String, Object> params);

    String getSortProperty(Sort sort);

    String getSortPropertyDirection(Sort sort);

}
