package com.source_interaction.repository;

import com.source_interaction.entity.TblLike;
import com.source_interaction.entity.embedded.TblLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblLikeRepository extends JpaRepository<TblLike, TblLikeId> {
}