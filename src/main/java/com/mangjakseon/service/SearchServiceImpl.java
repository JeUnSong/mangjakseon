package com.mangjakseon.service;

import com.mangjakseon.dto.SearchDTO;
import com.mangjakseon.dto.SearchMovieDataDTO;
import com.mangjakseon.dto.SearchPersonDataDTO;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

@Service
@Log4j2
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    String key = "";

    String queryJson = "";

    JSONObject queryData = null;

    String keyword = null;

    JSONArray dataArray = null;

    JSONObject dataObject = null;

    @Override
    public void movieData(SearchMovieDataDTO movieDataDto, SearchDTO dto) {

        String query = dto.getQuery();

        try {
            keyword = new String(query.getBytes("8859_1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        try {
            URL queryUrl = new URL("https://api.themoviedb.org/3/search/multi" +
                    "?api_key=" + key +
                    "&language=ko" +
                    "&query=" + keyword +
                    "&page=1" +
                    "&include_adult=false");

            BufferedReader queryBf;


            queryBf = new BufferedReader(new InputStreamReader(queryUrl.openStream()));

            queryJson = queryBf.readLine();

            JSONParser jsonParser = new JSONParser();

            queryData = (JSONObject) jsonParser.parse(queryJson);

            dataArray = (JSONArray) queryData.get("results");

            for (int i = 0; i < dataArray.size(); i++) {
                dataObject = (JSONObject) dataArray.get(i);
                //System.out.println(dataObject + "객체");
                if (dataObject.get("media_type").equals("movie")) {
                    //System.out.println(dataObject + "영화만");
                    movieDataDto = SearchMovieDataDTO.builder()
                            .id(dataObject.get("id"))
                            .title(dataObject.get("title"))
                            .firstAirDate(dataObject.get("release_date"))
                            .posterPath(dataObject.get("poster_path"))
                            .overview(dataObject.get("overview"))
                            .mediaType(dataObject.get("media_type"))
                            .build();
                    System.out.println(movieDataDto+"으악");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        @Override
        public void personData (SearchPersonDataDTO personDataDto, SearchDTO dto){

            String query = dto.getQuery();

            try {
                keyword = new String(query.getBytes("8859_1"), "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            try {
                URL queryUrl = new URL("https://api.themoviedb.org/3/search/multi" +
                        "?api_key=" + key +
                        "&language=ko" +
                        "&query=" + keyword +
                        "&page=1" +
                        "&include_adult=false");

                BufferedReader queryBf;


                queryBf = new BufferedReader(new InputStreamReader(queryUrl.openStream()));

                queryJson = queryBf.readLine();

                JSONParser jsonParser = new JSONParser();

                queryData = (JSONObject) jsonParser.parse(queryJson);

                dataArray = (JSONArray) queryData.get("results");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

