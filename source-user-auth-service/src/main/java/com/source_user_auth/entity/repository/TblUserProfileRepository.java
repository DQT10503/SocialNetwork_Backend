package com.source_user_auth.entity.repository;

import com.source_user_auth.entity.TblUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserProfileRepository extends JpaRepository<TblUserProfile, Long> {
}