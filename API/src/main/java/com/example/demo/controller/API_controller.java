package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.RoleType;
import com.example.demo.model.Use_YN;
import com.example.demo.service.MemberService;

@RestController
public class API_controller {

	private final MemberService memberService;

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

	@PostMapping("/page1/table")
	public ResponseEntity<List<Member>> table() {
		return new ResponseEntity<List<Member>>(memberService.getMemberList(), HttpStatus.OK);
	}

	@PostMapping("/user/regiUser")
	public String page1(@ModelAttribute Member member) {
		// WEB-INF/views/index.jsp

		member.setRegiUser(RoleType.USER);
		member.setUseYn(Use_YN.Y);
		LocalDateTime date = LocalDateTime.now();
		member.setRegiDt(date);
		memberService.add(member);

		System.out.println("dadada");
		return "/page1/table";
	}

}
