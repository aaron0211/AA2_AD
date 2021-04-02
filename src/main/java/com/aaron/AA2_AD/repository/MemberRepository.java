package com.aaron.AA2_AD.repository;

import com.aaron.AA2_AD.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface MemberRepository extends CrudRepository<Member,Long> {

    Set<Member> findAll();
    Member findByName(String name);
}
