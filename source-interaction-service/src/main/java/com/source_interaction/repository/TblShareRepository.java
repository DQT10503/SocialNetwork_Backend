package com.source_interaction.repository;

import com.source_interaction.entity.TblShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblShareRepository extends JpaRepository<TblShare, Long> {
}