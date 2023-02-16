package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Member;

public interface MemberRepositorycustom {

	List<Member> findBySearchOption(String user_id, String name);
}
