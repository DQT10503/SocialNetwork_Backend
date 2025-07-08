package com.source_user_auth.entity.repository;

import com.source_user_auth.entity.TblRole;
import com.source_user_auth.utils.enummerate.AuthStatus;
import com.source_user_auth.utils.enummerate.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblRoleRepository extends JpaRepository<TblRole, Long> {

    TblRole findByName(String roleEnum);
}