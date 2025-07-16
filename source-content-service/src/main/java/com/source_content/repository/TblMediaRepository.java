package com.source_content.repository;

import com.source_content.entity.TblMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblMediaRepository extends JpaRepository<TblMedia, Long> {
}