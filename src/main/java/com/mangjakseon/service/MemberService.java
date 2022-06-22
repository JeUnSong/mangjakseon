package com.mangjakseon.service;

import com.mangjakseon.dto.MemberDTO;
import com.mangjakseon.entity.Member;
import com.mangjakseon.entity.MemberRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService {

    Long register(MemberDTO memberDTO);

    MemberDTO read(Long memberId);

    void modify(MemberDTO memberDTO);

    void remove(Long memberId);

    default Member dtoToEntity(MemberDTO memberDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
                .fromSocial(false)
                .build();

        member.addMemberRole(MemberRole.USER);
        return member;
    }

    default MemberDTO entityToDto(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .fromSocial(member.isFromSocial())
                .regDate(member.getRegDate())
                .modDate(member.getModDate())
                .build();

        return memberDTO;
    }
}

/**
 * 비밀번호 입력을 하게 하고
 * 맞는지 select 쿼리를 날려서
 * .password(passwordEncoder.encode(memberDTO.getPassword()))
 * // 중복검사
 * select count(*) from member where id = #{id} and password = #{암호화 pw}
 * =>> return : 1 - 정상 , 0 - 비밀번호 틀림 or 아이디 없음
 *
 *
 * 맞으면 비밀번호 업데이트
 * update member set id = '' password = '';
 * @param member
 * @return
 */