package com.source_interaction.repository;

import com.source_interaction.entity.TblComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblCommentRepository extends JpaRepository<TblComment, Long> {
}