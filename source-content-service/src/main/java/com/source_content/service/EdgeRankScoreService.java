package com.source_content.service;

import java.io.IOException;
import java.util.Map;

public interface EdgeRankScoreService {
    Map<Long, Map<Long, Double>> calculateScore() throws IOException;
}
