package com.mangjakseon.controller;


import com.mangjakseon.dto.MovieDTO;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


@Controller
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    @GetMapping ("/review/{movieId}")
    public Object review(MovieDTO dto, Model model) {

        String key = "";

        Long movieId = dto.getMovieId();

        // 파싱한 데이터를 저장할 변수
        String result = "";

        JSONObject movieData = null;
        try {
            URL url = new URL("https://api.themoviedb.org/3/" +
                    "movie/" + movieId +
                    "?api_key=" + key +
                    "&language=ko&append_to_response=credits%2Cvideos%2Cwatch%2Fproviders");
            //log.info(url+"어디");

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            movieData = (JSONObject) jsonParser.parse(result);

            log.info(movieData + "어디");

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("movieData", movieData);
        return "/review";
    }

}

