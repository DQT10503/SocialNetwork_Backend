package com.source_content.repository;

import com.source_content.entity.TblPost;
import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PrivacyLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TblPostRepository extends JpaRepository<TblPost, Long> {
    Page<TblPost> findAllByStatusAndPrivacyLevelIsNot(ContentStatus status, PrivacyLevel privacyLevel, PageRequest pageRequest);
}