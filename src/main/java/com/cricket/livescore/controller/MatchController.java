package com.cricket.livescore.controller;

import com.cricket.livescore.dto.CricketInfoDto;
import com.cricket.livescore.model.CricketInfo;
import com.cricket.livescore.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cricket")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("live")
    ResponseEntity<List<CricketInfoDto>> getLiveMatches(String status){
        return new ResponseEntity<>(matchService.getLiveMatches(status), HttpStatus.OK);

    }
    @GetMapping("all")
    ResponseEntity<List<CricketInfo>> getAllMatches(String status){
        return new ResponseEntity<>(matchService.getAllMatches(status), HttpStatus.OK);

    }
    @GetMapping("matchDetails/{matchName}")
    ResponseEntity<CricketInfo> getMatcheDetails(String matchName){
        return new ResponseEntity<>(matchService.getMatcheDetails(matchName), HttpStatus.OK);

    }
}
