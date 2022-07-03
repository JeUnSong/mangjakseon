package com.mangjakseon.controller;

import com.mangjakseon.dto.WriterDTO;
import com.mangjakseon.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Log4j2
public class IndexController {

    MemberService service;

    @GetMapping("/index")
    public void index(Principal principal){}

    @GetMapping("loginModal")
    public void test(){}

    @PreAuthorize("isAuthenticated()")  //로그인 한 사용자만 접근 가능
    @GetMapping("/")
    public String home(){

        return "/index";
    }

    @GetMapping("/test")
    public void test(Principal principal){
        String email = principal.getName();


        log.info(service.getWriter(email) +"어디");
    }
}
