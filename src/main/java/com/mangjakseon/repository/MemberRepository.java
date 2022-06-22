package com.mangjakseon.repository;

import com.mangjakseon.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.fromSocial = :social and m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email, @Param("social") boolean social);

    @Query("SELECT m.memberId FROM Member m WHERE m.email = :email")
    Long findByMemberId(@Param("email") String email);

}
