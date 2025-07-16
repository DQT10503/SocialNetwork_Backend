package com.source_interaction.repository;

import com.source_interaction.entity.TblCommentLike;
import com.source_interaction.entity.embedded.TblCommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblCommentLikeRepository extends JpaRepository<TblCommentLike, TblCommentLikeId> {
}