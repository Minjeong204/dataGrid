//package com.example.demo.test;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.demo.model.Condition;
//import com.example.demo.repository.MemberRepository;
//import com.example.demo.service.MemberService;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//@Transactional
//@SpringBootTest
//public class test {
//
//	@PersistenceContext
//	private EntityManager em;
//
//	@Autowired
//	MemberRepository memberRepository;
//	@Autowired
//	MemberService memberservice;
//
////	@BeforeEach
////	public void setup() {
////		LocalDate date = LocalDate.now();
////		Member member = Member.builder().name("집에갈래요").user_id("mama").pw("1234").regiUser(RoleType.USER).regiDate(date)
////				.useYn(Use_YN.Y).build();
////
////		em.persist(member); // 미리 member를 생성한다.
////	}
////
////	@Test
////	public void membersSearchTest() {
////		JPAQueryFactory query = new JPAQueryFactory(em);
////		QMember qMember = QMember.member;
////
////		// querydsl을 이용해 java구문으로 query를 작성한다.
////		Member member = query.select(qMember).from(qMember).where(qMember.name.eq("집에갈래요")).fetchOne();
////
////		assertEquals(member.getName(), "집에갈래요");
////	}
//
//	@Test
//	public void search() {
//		LocalDate date = LocalDate.parse("2023-02-14");
//		LocalDate date2 = LocalDate.parse("2023-02-15");
//		String dates = "2023-02-14";
//		Condition condition = new Condition();
//		condition.setUser_id(null);
//		condition.setUser_name(null);
//		condition.setRegi_name(null);
//		condition.setUpda_name(null);
//		condition.setRegiStart(null);
//		condition.setRegiEnd(null);
//		condition.setUpdaStart(null);
//		condition.setUpdaEnd(null);
//		condition.setUse_YN(null);
//
//		List<Map<String, Object>> report = memberRepository.report(condition);
//		System.out.println(report.get(0));
//	}
//
//}
