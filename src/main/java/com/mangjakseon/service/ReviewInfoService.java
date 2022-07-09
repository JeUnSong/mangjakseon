package com.mangjakseon.service;

import com.mangjakseon.dto.ReviewInfoDTO;
import com.mangjakseon.dto.ReviewInfoDTOImpl;
import com.mangjakseon.entity.Review;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewInfoService {

    List<ReviewInfoDTOImpl> reviewAverage();

}
