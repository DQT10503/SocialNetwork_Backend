package com.source_content.repository;

import com.source_content.entity.TblPostDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblPostDraftRepository extends JpaRepository<TblPostDraft, Long> {
}