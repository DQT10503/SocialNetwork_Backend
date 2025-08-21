package com.source_content.scheduled;

import com.source_content.service.EdgeRankScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class EdgeRankScoreScheduled {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EdgeRankScoreService edgeRankScoreService;
    private final StringRedisTemplate redisTemplate;

    public EdgeRankScoreScheduled(EdgeRankScoreService edgeRankScoreService, StringRedisTemplate redisTemplate) {
        this.edgeRankScoreService = edgeRankScoreService;
        this.redisTemplate = redisTemplate;
    }


    @Scheduled(cron = "${schedule.cron.cache-score-edge-rank}")
    public void cacheScoreEdgeRank() throws IOException {
        logger.info("[Schedule} Start calculate score for EdgeRank");
        Map<Long, Map<Long, Double>> scoreMap = edgeRankScoreService.calculateScore();
        for (Map.Entry<Long, Map<Long, Double>> entry : scoreMap.entrySet()) {
            Long key = entry.getKey();
            Map<Long, Double> relatedEntities = entry.getValue();
            String redisKey = "edge-rank:" + key;

            // Chuyển Map<Long, Double> thành Map<String, String> để lưu vào Redis
            Map<String, String> redisValue = new HashMap<>();
            for (Map.Entry<Long, Double> related : relatedEntities.entrySet()) {
                redisValue.put(related.getKey().toString(), related.getValue().toString());
            }
            redisTemplate.opsForHash().putAll(redisKey, redisValue);
        }
        logger.info("[Schedule} Calculate score for EdgeRank successfully");
    }

}
