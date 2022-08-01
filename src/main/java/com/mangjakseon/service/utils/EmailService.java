package com.mangjakseon.service.utils;

import com.mangjakseon.entity.Member;
import com.mangjakseon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Log4j2
public class EmailService {

    private final JavaMailSender emailSender;
    public static final String ePw = createKey();
    private final MemberService memberService;


    private MimeMessage createMessage(String email) throws Exception{
        log.info("보내는 대상 : " + email);
        log.info("인증 번호 : " + ePw);
        MimeMessage message = emailSender.createMimeMessage();

        String code = createCode(ePw);
        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("변경된 임시 비밀번호 입니다.");

        String msg = "";
        msg += "<h1>임시 비밀번호</h1>";
        msg += "<p>"+code+"</p>";

        message.setText(msg,"UTF-8","HTML");
        message.setFrom(new InternetAddress("mangjakseon@naver.com","망작선"));

        memberService.passModify(email,code);

        return message;
    }


    // 인증코드 만들기
    public static String createKey(){
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for(int i = 0; i < 8; i++){
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public void sendSimpleMessage(String email)throws Exception{
        MimeMessage message = createMessage(email);
        try{
            emailSender.send(message);
        }catch (MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public String createCode(String ePw){
        return ePw.substring(0,8);
    }


}
