package com.mangjakseon.repository;

import com.mangjakseon.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //리뷰 목록보기
//    List<Review> findByReviewNumBetweenOrderByReviewNumDesc (Long from, Long to);

    //movieId별로 출력
    List<Review> findByMovieIdOrderByReviewNumDesc(@Param("movieId") String movieId);

    //movieId 받아서 좋아요 많은 개수대로 정렬하는 쿼리
    //@Query("select r from Review r where r.movieId=:movieId order by r.likeCount desc")

    //제목 수정 쿼리
    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.reviewTitle =:reviewTitle WHERE r.reviewNum = :reviewNum")
    void modifyTitle(String reviewTitle, Long reviewNum);

    //내용 수정 쿼리
    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.reviewContent =:reviewContent WHERE r.reviewNum = :reviewNum")
    void modifyContent(String reviewContent, Long reviewNum);

    //별점 수정 쿼리
    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.score =:score WHERE r.reviewNum = :reviewNum")
    void modifyScore(double score, Long reviewNum);
}



