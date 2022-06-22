package com.mangjakseon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
public class RestAPIController {

    @GetMapping("/movieList")
    public String movieListCallAPIHttp(){

        StringBuffer result = new StringBuffer();
        try{
            String urlStr = "https://api.themoviedb.org/3/discover/movie?" +
                    "api_key=" +
                    "&language=ko&include_adult=false" +
                    "&include_video=false" +
                    "&page=1" +
                    "&vote_average.gte=0" +
                    "&vote_average.lte=6" +
                    "&with_watch_monetization_types=flatrate";

            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            String returnLine;
            result.append("<json>");
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result+"</json>";
    }
}
