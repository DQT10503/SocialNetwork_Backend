package com.source_content.repository;

import com.source_content.entity.TblPostMention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblPostMentionRepository extends JpaRepository<TblPostMention, Long> {
}