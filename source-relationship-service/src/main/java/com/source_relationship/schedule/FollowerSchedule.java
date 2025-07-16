package com.source_relationship.schedule;

import com.api.framework.utils.DateTimeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.source_relationship.cache.FollowerCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FollowerSchedule {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final FollowerCacheService followerCacheService;

    public FollowerSchedule(FollowerCacheService followerCacheService) {
        this.followerCacheService = followerCacheService;
    }

    @Scheduled(cron = "${spring.schedule.job.friend-cache}")
    public void cacheFriend() throws JsonProcessingException {
        Instant now = DateTimeUtils.getCurrentTimeUTC();
        logger.info("Starting cache friend at {}", now);
        followerCacheService.cacheUserWithFollowed();
        logger.info("Finish cache friend");
    }
}
