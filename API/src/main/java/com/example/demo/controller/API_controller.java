package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.example.demo.model.RoleType;
import com.example.demo.model.Use_YN;
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
		String starts = (String) map.get("start");
		String ends = (String) map.get("end");
		LocalDate start = null;
		LocalDate end = null;
		System.out.println(starts);
		if (!(starts == "") || !(ends == "")) {
			start = LocalDate.parse(starts);
			end = LocalDate.parse(ends);
		}

		System.out.println("home");

		System.out.println(id + "dddd");
		List<Member> members = memberService.getMembers(id, name, start, end);
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
		LocalDate date = LocalDate.now();
		member.setUpdaDt(date);
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
		member.setRegiUser(RoleType.USER);
		member.setUseYn(Use_YN.Y);
		LocalDate date = LocalDate.now();
		member.setRegiDt(date);
		memberService.add(member);

		System.out.println("dadada");
		return "/page1/table";
	}

}
