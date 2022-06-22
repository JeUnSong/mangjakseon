package com.mangjakseon.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
public class ReviewController {

    @GetMapping("/review")
    public void review(){

    }

    @RequestMapping("/review/{movieId}/{movieTitle}/{movieOverview}/{movieReleaseDate}/{moviePoster}/{movieBackdrop}")
    public String Login(@PathVariable Long movieId,
                        @PathVariable String movieTitle,
                        @PathVariable String movieOverview,
                        @PathVariable String movieReleaseDate,
                        @PathVariable String moviePoster,
                        @PathVariable String movieBackdrop , Model model){

        model.addAttribute("contentId", movieId);
        model.addAttribute("contentId", movieTitle);
        model.addAttribute("contentId", movieOverview);
        model.addAttribute("contentId", movieReleaseDate);
        model.addAttribute("contentId", moviePoster);
        model.addAttribute("contentId", movieBackdrop);

        return "/review";
    }


//    @PostMapping("/review/${contentId}")
//    public String reviewId(){
//        return "";
//    }
}
