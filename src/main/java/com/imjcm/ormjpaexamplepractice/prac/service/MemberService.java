package com.imjcm.ormjpaexamplepractice.prac.service;

import com.imjcm.ormjpaexamplepractice.prac.domain.Member;

import java.util.List;

public interface MemberService {
    /**
     * 멤버 id로 조회
     * @param id
     * @return
     */
    public Member findMemberById(long id);

    /**
     * name에 해당하는 멤버 모두 조회
     * @param name
     * @return
     */
    public List<Member> findMembersByName(String name);
}
