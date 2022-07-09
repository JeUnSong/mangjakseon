package com.mangjakseon.repository;

import com.mangjakseon.dto.ReviewInfoDTO;
import com.mangjakseon.dto.ReviewInfoDTOImpl;
import com.mangjakseon.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewInfoRepository extends JpaRepository<Review,Long> {

    //망작선 별점 평균순으로 나열하는 쿼리
    @Query(value = "SELECT @rownum \\:= @rownum+1 AS lank, movie_id AS movieId, ROUND(avg(score),1) AS reviewAvg " +
            "FROM review, (SELECT @rownum\\:=0) AS lank  " +
            "GROUP BY movie_id ORDER BY reviewAvg DESC  limit 30;",nativeQuery = true)
    List<ReviewInfoDTOImpl> reviewScoreAverage();



}
