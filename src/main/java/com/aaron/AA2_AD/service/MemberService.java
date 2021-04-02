package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Member;

import java.util.Optional;
import java.util.Set;

public interface MemberService {

    Set<Member> findAll();
    Optional<Member> findById(long id);
    Member addMember(Member member);
    Member modifyMember(long id, Member newMember);
    void deleteMember(long id);
}
