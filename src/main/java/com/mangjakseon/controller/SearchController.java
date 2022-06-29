package com.mangjakseon.controller;


import com.mangjakseon.dto.SearchDTO;
import com.mangjakseon.dto.SearchMovieDataDTO;
import com.mangjakseon.dto.SearchPersonDataDTO;
import com.mangjakseon.service.SearchService;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;


@Controller
@Log4j2
@RequiredArgsConstructor
public class SearchController {


    private final SearchService service;

    @GetMapping("/search")
    public String search(SearchDTO dto, SearchPersonDataDTO personDataDto, SearchMovieDataDTO movieDataDto, Model model) {

        service.movieData(movieDataDto, dto);
        service.personData(personDataDto, dto);

        log.info(movieDataDto + "어디");
        model.addAttribute("movieData", movieDataDto);
        model.addAttribute("personData", personDataDto);

        return "/search";
    }
}

