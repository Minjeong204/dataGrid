package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Member;

public interface MemberRepositorycustom {

	List<Member> findMembers(String user_id, String user_name, String regi_name, String upda_name, LocalDate regiStart,
			LocalDate regiEnd, LocalDate updaStart, LocalDate updaEnd);

}
