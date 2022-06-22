package com.mangjakseon.service;

import com.mangjakseon.dto.MemberDTO;
import com.mangjakseon.entity.Member;
import com.mangjakseon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Long register(MemberDTO memberDTO) {
        Member member = dtoToEntity(memberDTO);
        memberRepository.save(member);
        return member.getMemberId();
    }

    @Override
    public MemberDTO read(Long memberId) {
        Optional<Member> result = memberRepository.findById(memberId);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        String memberEmail = memberDTO.getEmail();
        boolean isSocial = memberDTO.isFromSocial();
        Optional<Member> result = memberRepository.findByEmail(memberEmail,isSocial);

        log.info("Confirm: "+result);
        log.info("Is Social?? "+isSocial);

        if (result.isPresent() && isSocial){
            log.info("== MOD SOCIAL ==");
            Member entity = result.get();
            entity.modifyNickname(memberDTO.getNickname());
            memberRepository.save(entity);
        } else if (result.isPresent()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            log.info("== MOD USER ==");
            Member entity = result.get();
            entity.modifyPassword(passwordEncoder.encode(memberDTO.getPassword()));
            entity.modifyNickname(memberDTO.getNickname());
            memberRepository.save(entity);
        }
    }

    @Override
    public void remove(Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
