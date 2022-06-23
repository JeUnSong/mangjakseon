package com.mangjakseon.service;

import com.mangjakseon.controller.ReviewController;
import com.mangjakseon.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieService {

    public Long getMovieId(MovieDTO dto){

        log.info(dto+"service---------------------------------------------------");
        return null;
    }
}

