package com.example.demo.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;
import java.util.logging.Logger;

import org.apache.juli.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
public class test {
	MemberRepository memberRepository;

	@BeforeEach
	void insertTestData() {
		Member member = new Member();
		member.setName("kim ori");
		memberRepository.save(member);

	}

	/*
	 * @Test void findAllTest() { // 저장된 데이터 모두를 Spring JPA에 미리 구현된 findAll 명령을 통해
	 * 불러온다 List<Member> userList = memberRepository.findAll(); for (Member u :
	 * userList) log.info("[FindAll]: " + u.getName() + " | "); }
	 */
}
