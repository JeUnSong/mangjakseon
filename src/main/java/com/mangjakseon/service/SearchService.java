package com.mangjakseon.service;

import com.mangjakseon.dto.SearchDTO;
import com.mangjakseon.dto.SearchMovieDataDTO;
import com.mangjakseon.dto.SearchPersonDataDTO;

public interface SearchService {


    void movieData(SearchMovieDataDTO movieDataDto, SearchDTO dto);

    void personData(SearchPersonDataDTO personDataDto, SearchDTO dto);


}
