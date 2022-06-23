package com.mangjakseon.controller;

import com.mangjakseon.dto.MovieDTO;
import com.mangjakseon.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final MovieService movieService;

    @GetMapping("/review")
    public void review(MovieDTO dto){
        log.info(dto+"----------------------------------------------------------");



    }
}
