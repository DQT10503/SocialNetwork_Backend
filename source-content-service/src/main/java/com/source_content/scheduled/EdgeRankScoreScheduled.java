package com.source_content.schedule;

import com.source_content.service.EdgeRankScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class EdgeRankScoreSchedule {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EdgeRankScoreService edgeRankScoreService;
    private final StringRedisTemplate redisTemplate;

    public EdgeRankScoreSchedule(EdgeRankScoreService edgeRankScoreService, StringRedisTemplate redisTemplate) {
        this.edgeRankScoreService = edgeRankScoreService;
        this.redisTemplate = redisTemplate;
    }


    @Scheduled(cron = "${schedule.cron.cache-score-edge-rank}")
    public void cacheScoreEdgeRank() throws IOException {
        logger.info("[Schedule} Start calculate score for EdgeRank");
        Map<Long, Map<Long, Double>> scoreMap = edgeRankScoreService.calculateScore();
        redisTemplate.opsForValue().set("user:edge-rank:point", scoreMap);
        logger.info("[Schedule} Calculate score for EdgeRank successfully");
    }

}
