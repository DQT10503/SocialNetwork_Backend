package com.source_notification.repository;

import com.source_notification.entity.TblNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblNotificationRepository extends JpaRepository<TblNotification, Long> {
}