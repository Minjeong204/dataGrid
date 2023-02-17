package com.example.demo.test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
@SpringBootTest
public class test {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	MemberRepository memberRepository;

//	@BeforeEach
//	public void setup() {
//		LocalDate date = LocalDate.now();
//		Member member = Member.builder().name("집에갈래요").user_id("mama").pw("1234").regiUser(RoleType.USER).regiDate(date)
//				.useYn(Use_YN.Y).build();
//
//		em.persist(member); // 미리 member를 생성한다.
//	}
//
//	@Test
//	public void membersSearchTest() {
//		JPAQueryFactory query = new JPAQueryFactory(em);
//		QMember qMember = QMember.member;
//
//		// querydsl을 이용해 java구문으로 query를 작성한다.
//		Member member = query.select(qMember).from(qMember).where(qMember.name.eq("집에갈래요")).fetchOne();
//
//		assertEquals(member.getName(), "집에갈래요");
//	}

	@Test
	public void search() {
		LocalDate date = LocalDate.parse("2023-02-14");
		LocalDate date2 = LocalDate.parse("2023-02-15");
		List<Member> nameSearch = memberRepository.findMembers(null, null, null, null);
		System.out.println(nameSearch);
	}

}
