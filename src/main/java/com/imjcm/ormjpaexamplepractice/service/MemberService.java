package com.imjcm.ormjpaexamplepractice.service;

import com.imjcm.ormjpaexamplepractice.domain.Member;

import java.util.List;

public interface MemberService {
    public Member findMemberById(long id);
    public List<Member> findMembersByName(String name);
}
