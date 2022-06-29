package com.mangjakseon.controller;


import com.mangjakseon.dto.SearchMovieDataDTO;
import com.mangjakseon.dto.SearchPersonDataDTO;
import com.mangjakseon.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@Log4j2
@RequiredArgsConstructor
public class SearchController {

    private final SearchService service;

    @GetMapping("/search")
    public Object search(SearchPersonDataDTO personDataDto, SearchMovieDataDTO movieDataDto, Model model) {


        service.movieData(movieDataDto);
        service.personData(personDataDto);

        log.info(movieDataDto + "어디");
        log.info(personDataDto + "어디");
        model.addAttribute("movieData", movieDataDto);
        model.addAttribute("personData", personDataDto);

        return "search";
    }
}

