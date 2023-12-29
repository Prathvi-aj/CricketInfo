package com.cricket.livescore.repo;

import com.cricket.livescore.model.CricketInfo;
import com.cricket.livescore.model.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepo extends JpaRepository<CricketInfo,Long> {
    CricketInfo findByMatchHeading(String teamHeading);
    List<CricketInfo> findByMatchStatus(MatchStatus matchStatus);
}
