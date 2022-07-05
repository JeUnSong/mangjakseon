package com.mangjakseon.repository;

import com.mangjakseon.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Query(value = "SELECT COUNT(id) from heart WHERE review_num = %:reviewNum%", nativeQuery=true)
    Optional<Heart> howManyHeart(@Param("reviewNum") String reviewNum);

//    Optional<Heart> findHeartByMemberAndReviewNum(Member member, String reviewNum);
}