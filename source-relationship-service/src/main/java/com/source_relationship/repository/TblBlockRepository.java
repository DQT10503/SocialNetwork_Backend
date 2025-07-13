package com.source_relationship.repository;

import com.source_relationship.entity.TblBlock;
import com.source_relationship.entity.embedded.TblBlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblBlockRepository extends JpaRepository<TblBlock, TblBlockId> {
}