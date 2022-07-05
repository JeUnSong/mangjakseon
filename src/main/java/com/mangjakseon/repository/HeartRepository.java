package com.mangjakseon.repository;

import com.mangjakseon.entity.Heart;
import com.mangjakseon.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

//    Optional<Heart> findHeartByMemberAndReviewNum(Member member, String reviewNum);
}