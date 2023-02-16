package com.example.demo.repository;

import static com.example.demo.model.QMember.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.demo.model.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositorycustom {
	@Autowired
	private JPAQueryFactory queryFactory;

	public MemberRepositoryImpl() {
		super(Member.class);
	}

	@Override
	public List<Member> findBySearchOption(String user_id, String name) {
		List<Member> members = queryFactory.selectFrom(member).where(eqId(user_id), containName(name)).fetch();
		return members;
	}

	private BooleanExpression eqId(String user_id) {
		if (user_id == null || user_id.isEmpty()) {
			return null;
		}
		return member.user_id.eq(user_id);
	}

	private BooleanExpression containName(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		return member.name.containsIgnoreCase(name);
	}

}
