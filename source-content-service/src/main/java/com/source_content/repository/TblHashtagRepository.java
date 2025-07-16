package com.source_content.repository;

import com.source_content.entity.TblHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblHashtagRepository extends JpaRepository<TblHashtag, Long> {
}