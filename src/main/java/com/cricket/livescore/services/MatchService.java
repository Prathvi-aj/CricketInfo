package com.cricket.livescore.services;

import com.cricket.livescore.dto.CricketInfoDto;
import com.cricket.livescore.model.CricketInfo;
import com.cricket.livescore.model.MatchStatus;
import com.cricket.livescore.repo.MatchRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
@Service
public class MatchService {
    @Autowired
    private MatchRepo matchRepo;
    public List<CricketInfoDto> getLiveMatches(String matchStatus){
        List<CricketInfoDto> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                CricketInfoDto match1 = new CricketInfoDto();
                match1.setMatchHeading(teamsHeading);
                match1.setMatchVenue(matchNumberVenue);
                match1.setBattingTeam(battingTeam);
                match1.setBattingTeamScore(score);
                match1.setBowlingTeam(bowlTeam);
                match1.setBattingTeamScore(bowlTeamScore);
                match1.setLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setTextComplete(textComplete);
                match1.setMatchStatus(MatchStatus.LIVE);

                matches.add(match1);

//                update the match in database


                updateMatch(match1);


            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return matches;

    }

    private void updateMatch(CricketInfoDto match1) {
        ModelMapper modelMapper=new ModelMapper();
        CricketInfo cricketInfo=modelMapper.map(match1,CricketInfo.class);
        CricketInfo match = matchRepo.findByMatchHeading(match1.getMatchHeading());
        if (match == null) {
            matchRepo.save(cricketInfo);
        } else {
            matchRepo.save(match);
        }

    }
    public List<CricketInfo> getAllMatches(String matchStatus){

        return matchRepo.findAll();
    }

    public CricketInfo getMatcheDetails(String matchHeading){

        return matchRepo.findByMatchHeading(matchHeading);
    }

}
