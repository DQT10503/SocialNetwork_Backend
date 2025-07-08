package com.source_user_auth.entity.repository;

import com.source_user_auth.entity.TblUser;
import com.source_user_auth.utils.enummerate.AuthStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserRepository extends JpaRepository<TblUser, Long> {
    TblUser findByEmailAndStatus(String email, AuthStatus AuthStatus);

    TblUser findByEmailOrPhoneAndStatus(String email, String phone, AuthStatus AuthStatus);

    boolean existsByUsername(String username);
}
