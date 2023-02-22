package com.example.demo.repository;

import static com.example.demo.model.QMember.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.demo.model.Condition;
import com.example.demo.model.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
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
	public List<Member> findMembers(Condition condition) {
		BooleanBuilder builder = new BooleanBuilder();

		if (!(condition.getUser_id() == null || condition.getUser_id().isEmpty())) {
			builder.and(member.user_id.contains(condition.getUser_id()));
		}
		if (!(condition.getUser_name() == null || condition.getUser_name().isEmpty())) {
			builder.and(member.name.contains(condition.getUser_name()));
		}
		if (!(condition.getRegi_name() == null || condition.getRegi_name().isEmpty())) {
			builder.and(member.regiUser.contains(condition.getRegi_name()));
		}
		if (!(condition.getUpda_name() == null || condition.getUpda_name().isEmpty())) {
			builder.and(member.updaUser.contains(condition.getUpda_name()));
		}
		if (!(condition.getRegiStart() == null)) {
			builder.and(member.regiDt.after(condition.getRegiStart()));
		}
		if (!(condition.getRegiEnd() == null)) {
			builder.and(member.regiDt.before(condition.getRegiEnd()));
		}
		if (!(condition.getUpdaStart() == null)) {
			builder.and(member.updaDt.after(condition.getUpdaStart()));
		}
		if (!(condition.getUpdaEnd() == null)) {
			builder.and(member.updaDt.before(condition.getUpdaEnd()));
		}
		if (!(condition.getUse_YN() == null)) {
			builder.and(member.useYn.eq(condition.getUse_YN()));
		}

		List<Member> members = queryFactory.selectFrom(member).where(builder).fetch();
		return members;
	}

	@Override
	public List<Map<String, Object>> report(Condition condition) {
		BooleanBuilder builder = new BooleanBuilder();
		StringTemplate dateTimeToString = Expressions.stringTemplate("CONVERT (CHAR(10), {0}, 23)", member.regiDt);
		if (!(condition.getUser_id() == null || condition.getUser_id().isEmpty())) {
			builder.and(member.user_id.contains(condition.getUser_id()));
		}
		if (!(condition.getUser_name() == null || condition.getUser_name().isEmpty())) {
			builder.and(member.name.contains(condition.getUser_name()));
		}
		if (!(condition.getRegi_name() == null || condition.getRegi_name().isEmpty())) {
			builder.and(member.regiUser.contains(condition.getRegi_name()));
		}
		if (!(condition.getUpda_name() == null || condition.getUpda_name().isEmpty())) {
			builder.and(member.updaUser.contains(condition.getUpda_name()));
		}
		if (!(condition.getRegiStart() == null)) {
			builder.and(member.regiDt.after(condition.getRegiStart()));
		}
		if (!(condition.getRegiEnd() == null)) {
			builder.and(member.regiDt.before(condition.getRegiEnd()));
		}
		if (!(condition.getUpdaStart() == null)) {
			builder.and(member.updaDt.after(condition.getUpdaStart()));
		}
		if (!(condition.getUpdaEnd() == null)) {
			builder.and(member.updaDt.before(condition.getUpdaEnd()));
		}
		if (!(condition.getUse_YN() == null)) {
			builder.and(member.useYn.eq(condition.getUse_YN()));
		}

		List<Tuple> query = queryFactory.select(dateTimeToString, member.user_id.count()).where(builder).from(member)
				.groupBy(dateTimeToString).fetch();
		
		List<Map<String, Object>> result = new ArrayList<>();

		for (Tuple a : query) {
			Map<String, Object> temp = new HashMap<>();
			temp.put("regiDt", a.get(dateTimeToString));
			temp.put("count", a.get(member.user_id.count()));
			result.add(temp);
		}
		return result;
	}

}
