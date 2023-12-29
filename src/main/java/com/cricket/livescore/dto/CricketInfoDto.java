package com.cricket.livescore.dto;

import com.cricket.livescore.model.MatchStatus;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CricketInfoDto {
    private Long id;
    private String matchHeading;
    private String matchVenue;
    private String battingTeam;
    private String battingTeamScore;
    private String bowlingTeam;
    private String bowlingTeamScore;
    @Enumerated
    private MatchStatus matchStatus;
    private String matchLink;

    private String liveText;
    private String textComplete;
}
