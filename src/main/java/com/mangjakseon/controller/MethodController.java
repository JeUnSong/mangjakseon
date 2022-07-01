package com.mangjakseon.controller;

import com.mangjakseon.dto.MemberDTO;
import com.mangjakseon.security.validation.CheckEmailValidator;
import com.mangjakseon.security.validation.CheckNicknameValidator;
import com.mangjakseon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MethodController {

    /* Spring Security 담당 김준희 ********************************************/
    private final MemberService memberService;

    private final CheckEmailValidator checkEmailValidator;
    private final CheckNicknameValidator checkNicknameValidator;

    // 커스텀 유효성 검증
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkNicknameValidator);
    }

    // 회원가입
    @PostMapping("/register")
    public Object register(@Valid MemberDTO memberDTO, Errors errors, Model model)
            throws URISyntaxException {
        if (errors.hasErrors()){
            // 회원가입 실패시 입력 데이터값 유지
            model.addAttribute("memberDTO",memberDTO);

            // 유효성 통과 못한 필드와 메세지 처리
            Map<String,String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/sign-up";
        }

        memberService.checkEmailDuplication(memberDTO);
        memberService.checkNicknameDuplication(memberDTO);

        memberService.register(memberDTO);

        URI redirectUri = new URI("/");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectUri);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
    // 회원정보 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify")
    // 이름이 "profileImgUrl"인 파일을 MultipartFile 객체에 담아 처리
    public String modify(MemberDTO memberDTO, @RequestParam("profileImgUrl")MultipartFile multipartFile) {
        memberService.modify(memberDTO, multipartFile);
        return "redirect:/read";
    }
    // 회원탈퇴
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/remove")
    public String remove(String memberId){
        memberService.remove(memberId);

        return "redirect:/";
    }

    // 로그인 에러시 메시지
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/layout/login";
    }

    // 회원정보 수정시 닉네임 중복 체크
    @PostMapping("/nickChk")
    @ResponseBody
    public int nickChk(@RequestParam("nickname")String nickname, Model model){
        log.info("Nickname Check!!");
        log.info(nickname);

        return memberService.nicknameCheck(nickname);
    }
    /* Spring Security 담당 김준희 */
}
