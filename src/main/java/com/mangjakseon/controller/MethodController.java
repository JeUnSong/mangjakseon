package com.mangjakseon.controller;

import com.mangjakseon.dto.MemberDTO;
import com.mangjakseon.entity.Member;
import com.mangjakseon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MethodController {

    /* Spring Security 담당 김준희 ********************************************/
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<Member>register(MemberDTO memberDTO) throws URISyntaxException {
        memberService.register(memberDTO);

        URI redirectUri = new URI("/");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectUri);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
    // 회원정보 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify")
    public String modify(MemberDTO memberDTO) {
        memberService.modify(memberDTO);

        return "redirect:/";
    }
    // 회원탈퇴
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/remove")
    public String remove(Long memberId){
        memberService.remove(memberId);

        return "redirect:/";
    }
    /* Spring Security 담당 김준희 */
}
