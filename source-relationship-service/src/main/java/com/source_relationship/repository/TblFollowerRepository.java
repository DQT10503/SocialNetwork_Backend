package com.source_relationship.repository;

import com.source_relationship.entity.TblFollower;
import com.source_relationship.entity.embedded.TblFollowerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblFollowerRepository extends JpaRepository<TblFollower, TblFollowerId> {
}