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

    @RequestMapping("/review/{contentId}")
    public String Login(@PathVariable String contentId, Model model){

        model.addAttribute("contentId", contentId);

        return "/review";
    }


//    @PostMapping("/review/${contentId}")
//    public String reviewId(){
//        return "";
//    }
}
