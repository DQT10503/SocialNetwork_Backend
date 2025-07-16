package com.source_notification.repository;

import com.source_notification.entity.TblNotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblNotificationSettingRepository extends JpaRepository<TblNotificationSetting, Long> {
}
