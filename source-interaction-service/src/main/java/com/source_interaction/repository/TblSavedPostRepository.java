package com.source_interaction.repository;

import com.source_interaction.entity.TblSavedPost;
import com.source_interaction.entity.embedded.TblSavedPostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblSavedPostRepository extends JpaRepository<TblSavedPost, TblSavedPostId> {
}