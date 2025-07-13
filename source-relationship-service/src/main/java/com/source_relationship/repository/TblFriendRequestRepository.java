package com.source_relationship.repository;

import com.source_relationship.entity.TblFriendRequest;
import com.source_relationship.entity.embedded.TblFriendRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblFriendRequestRepository extends JpaRepository<TblFriendRequest, TblFriendRequestId> {
}