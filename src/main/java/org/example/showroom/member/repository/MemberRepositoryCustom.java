package org.example.showroom.member.repository;


import org.example.showroom.member.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);
}
