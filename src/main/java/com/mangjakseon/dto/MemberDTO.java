package com.mangjakseon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long memberId;

    private String email;

    private String password;

    private String nickname;

    private boolean fromSocial;

    private LocalDateTime regDate, modDate;

}
