package com.mangjakseon.controller;


import com.mangjakseon.dto.MovieDTO;
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
import java.net.URL;


@Controller
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    @GetMapping ("/{movieId}")
    public Object review(MovieDTO dto, Model model) {

        String key = "";

        Long movieId = dto.getMovieId();

        // 파싱한 데이터를 저장할 변수
        String movieJson = "";
        String watchJson = "";
        String castJson = "";

        JSONObject movieData = null;
        JSONObject watchData1 = null;
        JSONObject watchData2 = null;
        JSONObject watchData3 = null;
        JSONArray watchArray = null;

        JSONObject castObject = null;

        try {
            URL movieUrl = new URL("https://api.themoviedb.org/3/" +
                    "movie/" + movieId +
                    "?api_key=" + key +
                    "&language=ko");

            URL watchUrl = new URL("https://api.themoviedb.org/3/" +
                    "movie/" + movieId +
                    "/watch/providers?" +
                    "api_key=" + key);

            URL castUrl = new URL("https://api.themoviedb.org/3/" +
                    "movie/" + movieId +
                    "/credits?" +
                    "api_key=" + key +
                    "&language=ko");

            BufferedReader movieBf;
            BufferedReader watchBf;
            BufferedReader castBf;

            movieBf = new BufferedReader(new InputStreamReader(movieUrl.openStream()));
            watchBf = new BufferedReader(new InputStreamReader(watchUrl.openStream()));
            castBf  = new BufferedReader(new InputStreamReader(castUrl.openStream()));

            movieJson = movieBf.readLine();
            watchJson = watchBf.readLine();
            castJson = castBf.readLine();

            JSONParser jsonParser = new JSONParser();
            movieData = (JSONObject) jsonParser.parse(movieJson);

            watchData1 = (JSONObject) jsonParser.parse(watchJson);
            watchData2 = (JSONObject) watchData1.get("results");
            watchData3 = (JSONObject) watchData2.get("KR");
            watchArray = (JSONArray) watchData3.get("flatrate");

            JSONObject cast = (JSONObject) jsonParser.parse(castJson);
            castObject = (JSONObject) cast.get("cast");
            log.info(castJson+"어디");
            log.info(cast +"어디");
            log.info(castObject +"어디2");

            //76 ~ 86 줄 아무 의미없음 안됨 개빡침



        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("movieData", movieData);
        model.addAttribute("watchLink", watchData3);
        model.addAttribute("watchLogo", watchArray);
        return "/review";
    }

}

