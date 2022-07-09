package com.mangjakseon.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@Data
@Getter
public class ReviewInfoDTO {

    Long lank;

    Long movieId;

    double reviewAvg;

    public ReviewInfoDTO(Long lank, Long movieId,double reviewAverage){
        this.lank=lank;
        this.movieId=movieId;
        this.reviewAvg=reviewAverage;
    }


}
