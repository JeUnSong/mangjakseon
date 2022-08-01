package com.mangjakseon.controller;


import com.mangjakseon.service.utils.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

@RequiredArgsConstructor
@RestController
@Log4j2
public class EmailController {

    private final EmailService emailService;


    @PostMapping("/emailSend")
    @ResponseBody
    public void emailConfirm(String email)throws Exception{
        emailService.sendSimpleMessage(email);

    }
}