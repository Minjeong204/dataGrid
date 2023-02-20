package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.Member;

public interface MemberRepositorycustom {

	List<Member> findMembers(String user_id, String user_name, String regi_name, String upda_name, LocalDateTime regiStart,
			LocalDateTime regiEnd, LocalDateTime updaStart, LocalDateTime updaEnd, String use_YN);

}
