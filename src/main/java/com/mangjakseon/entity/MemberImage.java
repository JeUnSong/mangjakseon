package com.mangjakseon.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MemberImage {

    @Id
    private String imgNum;

    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member memberId;
}
