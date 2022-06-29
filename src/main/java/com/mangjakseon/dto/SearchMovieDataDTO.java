package com.mangjakseon.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchMovieDataDTO {

    private String query;
    private Object id;
    private Object title;
    private Object posterPath;
    private Object firstAirDate;
    private Object overview;
    private Object mediaType;


}
