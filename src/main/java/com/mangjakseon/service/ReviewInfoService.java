package com.mangjakseon.service;

import com.mangjakseon.dto.ReviewInfoDTO;
import com.mangjakseon.dto.ReviewInfoDTOImpl;
import com.mangjakseon.entity.Review;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewInfoService {

    //망작선 별점 평균순으로 리뷰 30개 나열
    List<ReviewInfoDTOImpl> reviewAverage();

    //망작선 별점 평균순으로 리뷰 전부 나열
    List<ReviewInfoDTOImpl> reviewAverageMovieInfo();

}
