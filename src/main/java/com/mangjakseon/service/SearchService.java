package com.mangjakseon.service;

import com.mangjakseon.dto.SearchMovieDataDTO;
import com.mangjakseon.dto.SearchPersonDataDTO;

import java.util.ArrayList;

public interface SearchService {


    void movieData(SearchMovieDataDTO movieDataDto);

    void personData(SearchPersonDataDTO personDataDto);


}
