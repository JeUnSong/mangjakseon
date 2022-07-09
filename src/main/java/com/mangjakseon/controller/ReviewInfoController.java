package com.mangjakseon.controller;


import com.mangjakseon.dto.MovieInfoDTO;
import com.mangjakseon.dto.ReviewInfoDTO;
import com.mangjakseon.dto.ReviewInfoDTOImpl;
import com.mangjakseon.entity.Review;
import com.mangjakseon.service.ReviewInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/reviewInfo")
@Log4j2
@RequiredArgsConstructor
public class ReviewInfoController {

    private final ReviewInfoService reviewInfoService;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ReviewInfoDTOImpl>> reviewAverageView(@PathVariable("movieId")Long movieId){
        List<ReviewInfoDTOImpl> reviewAverage = reviewInfoService.reviewAverageMovieInfo();
        log.info("어디 "+reviewAverage);
        return new ResponseEntity<>(reviewAverage, HttpStatus.OK);
    }

    @GetMapping("/movie")
    public ResponseEntity<List<ReviewInfoDTOImpl>> reviewAverageMovieListView(){
        List<ReviewInfoDTOImpl> reviewAverageList = reviewInfoService.reviewAverage();
        log.info(reviewAverageList+"어디");
        return new ResponseEntity<>(reviewAverageList,HttpStatus.OK);
    }

    @GetMapping("/index")
    public ResponseEntity<List<ReviewInfoDTOImpl>>reviewAverageMovieLankList(){
        List<ReviewInfoDTOImpl> reviewAverageLankList = reviewInfoService.reviewAverage();
        log.info("value : " + reviewAverageLankList);
        return new ResponseEntity<>(reviewAverageLankList,HttpStatus.OK);
    }
}
