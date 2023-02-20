package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

@RestController
public class API_controller {

	private final MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	public API_controller(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/user/table")
	public ResponseEntity list(@ModelAttribute Member member, Model model) {
		List<Member> emps = memberService.getMemberList();
		for (Member mem : emps) {
			System.out.println(mem);
		}
		model.addAttribute("emps", emps);
		return new ResponseEntity(emps, HttpStatus.OK);
	}

//	@PostMapping("/page1/table")
//	public ResponseEntity<List<Member>> table() {
//		return new ResponseEntity<List<Member>>(memberService.getMemberList(), HttpStatus.OK);
//	}

	@PostMapping("/page1/table")
	public ResponseEntity<List<Member>> search(@RequestBody Map<String, String> map) {
		String id = (String) map.get("user_id");
		String name = (String) map.get("name");
		String regiUser = (String) map.get("regiUser");
		String updaUser = (String) map.get("updaUser");
		String regif = (String) map.get("regif");
		String regit = (String) map.get("regit");
		String updaf = (String) map.get("updaf");
		String updat = (String) map.get("updat");
		String useyn = (String) map.get("useyn");
		LocalDateTime regis = null;
		LocalDateTime regie = null;
		LocalDateTime updas = null;
		LocalDateTime updae = null;

		if (!(regif.isEmpty())) {
			regis = LocalDate.parse(regif).atStartOfDay();
		}
		if (!(regit.isEmpty())) {
			regie = LocalDate.parse(regit).atTime(LocalTime.MAX);
		}

		if (!(updaf.isEmpty())) {
			updas = LocalDate.parse(updaf).atStartOfDay();
		}
		if (!(updat.isEmpty())) {
			updae = LocalDate.parse(updat).atTime(LocalTime.MAX);
		}
//		if (useyn.isEmpty()) {
//			useyn = null;
//		}

		System.out.println(id + "dddd");
		List<Member> members = memberService.getMembers(id, name, regiUser, updaUser, regis, regie, updas, updae,
				useyn);
		System.out.println(members);
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}

	@PostMapping("/page1/delete")
	public void delete(@RequestBody String id) {
		memberService.delete(id);
	}

	@PostMapping("/page1/update")
	public ResponseEntity<List<Map<String, String>>> update(@RequestBody List<Map<String, String>> map) {

		String id = (String) map.get(0).get("user_id");
		/* String name = (String) map.get(0).get("name"); */
		String pw = (String) map.get(0).get("pw");
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
		member.setPw(pw);
		LocalDateTime date = LocalDateTime.now();
		member.setUpdaDt(date);
		member.setUpdaUser("USER");
		memberService.add(member);
		return new ResponseEntity<List<Map<String, String>>>(map, HttpStatus.OK);
	}

	@PostMapping("/page1/regi")
	public String page1(@RequestBody List<Map<String, String>> map, Member member) {
		// WEB-INF/views/index.jsp
		String id = (String) map.get(0).get("user_id");
		String name = (String) map.get(0).get("name");
		String pw = (String) map.get(0).get("pw");
		member.setName(name);
		member.setUser_id(id);
		member.setPw(pw);
		member.setRegiUser("USER");
		member.setUseYn("Y");
		LocalDateTime date = LocalDateTime.now();
		member.setRegiDt(date);
		memberService.add(member);

		System.out.println("dadada");
		return "/page1/table";
	}

}
