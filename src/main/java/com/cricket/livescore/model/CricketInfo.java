package com.cricket.livescore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class CricketInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
