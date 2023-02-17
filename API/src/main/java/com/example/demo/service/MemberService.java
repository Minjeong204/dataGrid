package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public List<Member> getMemberList() {
		return (List<Member>) memberRepository.findAll();
	}

	@Transactional
	public void delete(String id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
		memberRepository.delete(member);
	}

	@Transactional
	public Member add(Member member) {
		return memberRepository.save(member);
	}

	public List<Member> getMembers(String user_id, String name, LocalDate start, LocalDate end) {
		return (List<Member>) memberRepository.findMembers(user_id, name, start, end);
	}
}
