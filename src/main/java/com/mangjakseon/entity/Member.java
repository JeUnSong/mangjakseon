package com.mangjakseon.entity;

import com.mangjakseon.utils.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String nickname;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void modifyPassword(String password){
        this.password = password;
    }

    public void modifyNickname(String nickname){
        this.nickname = nickname;
    }

    public void addMemberRole(MemberRole memberRole) { roleSet.add(memberRole); }
}
