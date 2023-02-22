package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	public void delete(List<Map<String, String>> map) {
		for (int i = 0; i < map.size(); i++) {
			String id = (String) map.get(i).get("user_id");
			Member member = memberRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
			memberRepository.delete(member);
		}
	}

	@Transactional
	public Member add(Map<String, String> map) {
		String id = (String) map.get("user_id");
		String name = (String) map.get("name");
		String pw = (String) map.get("pw");

		Member member = new Member();
		member.setName(name);
		member.setUser_id(id);
		member.setPw(pw);
		member.setRegiUser("USER");
		member.setUseYn("Y");
		LocalDateTime date = LocalDateTime.now();
		member.setRegiDt(date);

		return memberRepository.save(member);
	}

	@Transactional
	public Member update(Map<String, String> map) {
		String id = (String) map.get("user_id");
		String pw = (String) map.get("pw");
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
		member.setPw(pw);
		LocalDateTime date = LocalDateTime.now();
		member.setUpdaDt(date);
		member.setUpdaUser("USER");
		return memberRepository.save(member);
	}

	public List<Member> getMembers(Map<String, String> map) {
		Condition condition = new Condition();
		String id = (String) map.get("user_ids");
		String name = (String) map.get("user_names");
		String regiUser = (String) map.get("regiUser");
		String updaUser = (String) map.get("updaUser");
		String regif = (String) map.get("regi_dates_from");
		String regit = (String) map.get("regi_dates_to");
		String updaf = (String) map.get("upda_dates_from");
		String updat = (String) map.get("upda_dates_to");
		String useyn = (String) map.get("use");
		LocalDateTime regis = null;
		LocalDateTime regie = null;
		LocalDateTime updas = null;
		LocalDateTime updae = null;
		if (!(regif.isEmpty() || regif == null)) {
			regis = LocalDate.parse(regif).atStartOfDay();
		}
		if (!(regit.isEmpty() || regit == null)) {
			regie = LocalDate.parse(regit).atTime(LocalTime.MAX);
		}

		if (!(updaf.isEmpty() || updaf == null)) {
			updas = LocalDate.parse(updaf).atStartOfDay();
		}
		if (!(updat.isEmpty() || updat == null)) {
			updae = LocalDate.parse(updat).atTime(LocalTime.MAX);
		}
		if (useyn.isEmpty()) {
			useyn = null;
		}
		condition.setUser_id(id);
		condition.setUser_name(name);
		condition.setRegi_name(regiUser);
		condition.setUpda_name(updaUser);
		condition.setRegiStart(regis);
		condition.setRegiEnd(regie);
		condition.setUpdaStart(updas);
		condition.setUpdaEnd(updae);
		condition.setUse_YN(useyn);

		return (List<Member>) memberRepository.findMembers(condition);
	}

	public List<Map<String, Object>> report(Map<String, String> map) {
		Condition condition = new Condition();
		String id = (String) map.get("user_ids");
		String name = (String) map.get("user_names");
		String regiUser = (String) map.get("regiUser");
		String updaUser = (String) map.get("updaUser");
		String regif = (String) map.get("regi_dates_from");
		String regit = (String) map.get("regi_dates_to");
		String updaf = (String) map.get("upda_dates_from");
		String updat = (String) map.get("upda_dates_to");
		String useyn = (String) map.get("use");
		LocalDateTime regis = null;
		LocalDateTime regie = null;
		LocalDateTime updas = null;
		LocalDateTime updae = null;

		if (!(regif.isEmpty() || regif == null)) {
			regis = LocalDate.parse(regif).atStartOfDay();
		}
		if (!(regit.isEmpty() || regit == null)) {
			regie = LocalDate.parse(regit).atTime(LocalTime.MAX);
		}

		if (!(updaf.isEmpty() || updaf == null)) {
			updas = LocalDate.parse(updaf).atStartOfDay();
		}
		if (!(updat.isEmpty() || updat == null)) {
			updae = LocalDate.parse(updat).atTime(LocalTime.MAX);
		}
		if (useyn.isEmpty()) {
			useyn = null;
		}
		condition.setUser_id(id);
		condition.setUser_name(name);
		condition.setRegi_name(regiUser);
		condition.setUpda_name(updaUser);
		condition.setRegiStart(regis);
		condition.setRegiEnd(regie);
		condition.setUpdaStart(updas);
		condition.setUpdaEnd(updae);
		condition.setUse_YN(useyn);
		return memberRepository.report(condition);
	}
}
