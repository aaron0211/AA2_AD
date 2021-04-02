package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Member;
import com.aaron.AA2_AD.exception.MemberNotFoundException;
import com.aaron.AA2_AD.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Set<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member modifyMember(long id, Member newMember) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException(id));
        newMember.setId(member.getId());
        return memberRepository.save(newMember);
    }

    @Override
    public void deleteMember(long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new MemberNotFoundException(id));
        memberRepository.deleteById(id);
    }
}
