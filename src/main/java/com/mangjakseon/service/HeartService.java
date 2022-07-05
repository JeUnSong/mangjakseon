package com.mangjakseon.service;

import com.mangjakseon.dto.HeartDTO;
import com.mangjakseon.entity.Heart;
import com.mangjakseon.entity.Member;
import com.mangjakseon.repository.HeartRepository;
import com.mangjakseon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;

    public void heart(HeartDTO heartDto) {
        Heart heart = Heart.builder()
                .reviewNum(heartDto.getReviewNum())
                .member(memberRepository.findByMember(heartDto.getMemberId()).get())
                .build();
        heartRepository.save(heart);
    }

//    public void unHeart(HeartDTO heartDto) {
//        Optional<Heart> heartOpt = findHeartWithMemberAndReviewNum(heartDto);
//
//        heartRepository.delete(heartOpt.get());
//    }
//
//    public Optional<Heart> findHeartWithMemberAndReviewNum(HeartDTO heartDto) {
//        Optional<Member> userOpt = memberRepository.findByMember(heartDto.getMemberId());
//
//        return heartRepository.findHeartByMemberAndReviewNum(userOpt.get(), heartDto.getReviewNum());
//    }
}


