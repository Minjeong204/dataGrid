package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;

@RestController
public class API_controller {

	private final MemberService memberService;
	/*
	 * @Autowired private MemberRepository memberRepository;
	 */

	public API_controller(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/table")
	public ResponseEntity<Map<String, Object>> search(@RequestBody Map<String, String> map) {
		List<Member> members = memberService.getMembers(map);
		List<Map<String, Object>> report = memberService.report(map);
		Map<String, Object> result = new HashMap<>();
		result.put("members", members);
		result.put("report", report);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<List<Map<String, String>>> delete(@RequestBody List<Map<String, String>> map) {
		memberService.delete(map);
		return new ResponseEntity<List<Map<String, String>>>(map, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Map<String, String> map) {
		memberService.update(map);
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	@PostMapping("/regi")
	public ResponseEntity<?> page1(@RequestBody Map<String, String> map) {
		memberService.add(map);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
