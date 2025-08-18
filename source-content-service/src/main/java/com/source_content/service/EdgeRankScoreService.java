package com.source_content.service;

import java.io.IOException;
import java.util.Map;

public interface EdgeRankScoringService {
    Map<Long, Map<Long, Double>> calculateScore() throws IOException;
}
