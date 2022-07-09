package com.mangjakseon.service;

import com.mangjakseon.dto.ReviewInfoDTO;
import com.mangjakseon.dto.ReviewInfoDTOImpl;
import com.mangjakseon.entity.Review;
import com.mangjakseon.repository.ReviewInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewInfoServiceImpl implements ReviewInfoService{

    private final ReviewInfoRepository reviewInfoRepository;


    @Override
    public List<ReviewInfoDTOImpl> reviewAverage() {
        List<ReviewInfoDTOImpl> result = reviewInfoRepository.reviewScoreAverage();

        return result;
    }
}
