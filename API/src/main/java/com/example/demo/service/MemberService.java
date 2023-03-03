package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Condition;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Transactional
	public void delete(String arr[]) {
		for (int i = 0; i < arr.length; i++) {
			Member member = memberRepository.findById(arr[i])
					.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id="));
			memberRepository.delete(member);
		}
	}

	@Transactional
	public Member add(Member member) {
		
		member.setRegiUser("ADMIN");
		member.setUseYn("Y");
		LocalDateTime date = LocalDateTime.now();
		member.setRegiDt(date);

		return memberRepository.save(member);
	}

	@Transactional
	public Member update(Member member) {
		Member member2 = memberRepository.findById(member.getUser_id())
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + member.getUser_id()));
		member2.setPw(member.getPw());
		LocalDateTime date = LocalDateTime.now();
		member2.setUpdaDt(date);
		member2.setUpdaUser("ADMIN");
		return memberRepository.save(member2);
	}

	public Map<String, Object> getMembers(Condition condition) {
		
		Map<String, Object> members = memberRepository.findMembers(condition);

		return members;
	}
	
	public List<Member> send(){
		List<Member> members = memberRepository.findAll();
		
		return members;
	}
}
