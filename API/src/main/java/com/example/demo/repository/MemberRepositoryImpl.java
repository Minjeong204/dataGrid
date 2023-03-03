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
	public Map<String, Object> findMembers(Condition condition) {
		BooleanBuilder builder = new BooleanBuilder();
		StringTemplate dateTimeToString = Expressions.stringTemplate("CONVERT (CHAR(10), {0}, 23)", member.regiDt);

		if (!(condition.getUser_ids() == null || condition.getUser_ids().isEmpty())) {
			builder.and(member.user_id.contains(condition.getUser_ids()));
		}
		if (!(condition.getUser_names() == null || condition.getUser_names().isEmpty())) {
			builder.and(member.name.contains(condition.getUser_names()));
		}
		if (!(condition.getRegiUser() == null || condition.getRegiUser().isEmpty())) {
			builder.and(member.regiUser.contains(condition.getRegiUser()));
		}
		if (!(condition.getUpdaUser() == null || condition.getUpdaUser().isEmpty())) {
			builder.and(member.updaUser.contains(condition.getUpdaUser()));
		}
		if (!(condition.getRegi_dates_from() == null || condition.getRegi_dates_from().isEmpty())) {
			LocalDateTime regis = LocalDateTime.parse(condition.getRegi_dates_from()).withHour(0).withMinute(0);
			builder.and(member.regiDt.after(regis));
		}
		if (!(condition.getRegi_dates_to() == null || condition.getRegi_dates_to().isEmpty())) {
			LocalDateTime regie = LocalDateTime.parse(condition.getRegi_dates_to()).withHour(23).withMinute(59);
			builder.and(member.regiDt.before(regie));
		}
		if (!(condition.getUpda_dates_from() == null || condition.getUpda_dates_from().isEmpty())) {
			LocalDateTime updas = LocalDateTime.parse(condition.getUpda_dates_from()).withHour(0).withMinute(0);
			builder.and(member.updaDt.after(updas));
		}
		if (!(condition.getUpda_dates_to() == null || condition.getUpda_dates_to().isEmpty()) ){
			LocalDateTime updae = LocalDateTime.parse(condition.getUpda_dates_to()).withHour(23).withMinute(59);
			builder.and(member.updaDt.before(updae));
		}
		if (!(condition.getUse() == null || condition.getUse().isEmpty())) {
			builder.and(member.useYn.eq(condition.getUse()));
		}

		List<Member> members = queryFactory.selectFrom(member).where(builder).fetch();
		List<Tuple> query = queryFactory.select(dateTimeToString, member.user_id.count()).where(builder).from(member)
				.groupBy(dateTimeToString).fetch();

		List<Map<String, Object>> report = new ArrayList<>();

		for (Tuple a : query) {
			Map<String, Object> temp = new HashMap<>();
			temp.put("regiDt", a.get(dateTimeToString));
			temp.put("count", a.get(member.user_id.count()));
			report.add(temp);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("members", members);
		result.put("report",report );
		return result;
	}

}
