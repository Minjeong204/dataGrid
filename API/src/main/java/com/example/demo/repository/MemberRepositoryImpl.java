package com.example.demo.repository;

import static com.example.demo.model.QMember.member;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.demo.model.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositorycustom {
	@Autowired
	private JPAQueryFactory queryFactory;

	public MemberRepositoryImpl() {
		super(Member.class);
	}

	@Override
	public List<Member> findMembers(String user_id, String user_name, String regi_name, String upda_name,
			LocalDateTime regiStart, LocalDateTime regiEnd, LocalDateTime updaStart, LocalDateTime updaEnd,
			String use_YN) {
		BooleanBuilder builder = new BooleanBuilder();
		if (!(user_id == null || user_id.isEmpty())) {
			builder.and(member.user_id.contains(user_id));
		}
		if (!(user_name == null || user_name.isEmpty())) {
			builder.and(member.name.contains(user_name));
		}
		if (!(regi_name == null || regi_name.isEmpty())) {
			builder.and(member.regiUser.contains(regi_name));
		}
		if (!(upda_name == null || upda_name.isEmpty())) {
			builder.and(member.updaUser.contains(upda_name));
		}
		if (!(regiStart == null && regiEnd == null)) {
			builder.and(member.regiDt.between(regiStart, regiEnd));
		}
		if (!(regiStart == null)) {
			builder.and(member.regiDt.after(regiStart));
		}
		if (!(regiEnd == null)) {
			builder.and(member.regiDt.before(regiEnd));
		}
		if (!(updaStart == null && updaEnd == null)) {
			builder.and(member.updaDt.between(updaStart, updaEnd));
		}
		if (!(updaStart == null)) {
			builder.and(member.updaDt.after(updaStart));
		}
		if (!(updaEnd == null)) {
			builder.and(member.updaDt.before(updaEnd));
		}
		if (!(use_YN == null)) {
			builder.and(member.useYn.eq(use_YN));
		}
		List<Member> members = queryFactory.selectFrom(member).where(builder).fetch();
		return members;
	}

}
