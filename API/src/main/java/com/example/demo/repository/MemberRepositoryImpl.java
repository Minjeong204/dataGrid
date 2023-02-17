package com.example.demo.repository;

import static com.example.demo.model.QMember.member;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.demo.model.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositorycustom {
	@Autowired
	private JPAQueryFactory queryFactory;

	public MemberRepositoryImpl() {
		super(Member.class);
	}

	@Override
	public List<Member> findMembers(String user_id, String name, LocalDate start, LocalDate end) {
		BooleanBuilder builder = new BooleanBuilder();
		if (user_id == null || user_id.isEmpty()) {
			builder.and(member.user_id.eq(user_id));
		}
		if (name == null || name.isEmpty()) {
			builder.and(member.name.eq(name));
		}
		if (start == null || end == null) {
			builder.and(member.regiDt.between(start, end));
		}
		List<Member> members = queryFactory.selectFrom(member).where(builder).fetch();
		return members;
	}

}
