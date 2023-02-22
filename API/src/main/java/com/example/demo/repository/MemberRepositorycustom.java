package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Condition;
import com.example.demo.model.Member;

public interface MemberRepositorycustom {

	List<Member> findMembers(Condition condition);

	List<Map<String, Object>> report(Condition condition);

}
