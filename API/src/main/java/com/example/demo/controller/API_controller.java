package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Condition;
import com.example.demo.model.Member;
import com.example.demo.service.MemberService;

@RestController
public class API_controller {

	private final MemberService memberService;

	public API_controller(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/table")
	public ResponseEntity<Map<String, Object>> search(@RequestBody Condition condition) {
		Map<String, Object> members = memberService.getMembers(condition);
		return new ResponseEntity<Map<String, Object>>(members, HttpStatus.OK);
	}

	@PostMapping("/send")
	public ResponseEntity<Map<String, String>> search2(@RequestBody Map<String, String> ids) {
		Map<String, String> members = memberService.send(ids);
		return new ResponseEntity<Map<String, String>>(members, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody String[] arr) {
		memberService.delete(arr);
		return new ResponseEntity<Object>(arr, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Member member) {
		memberService.update(member);
		return new ResponseEntity<Object>(member, HttpStatus.OK);
	}

	@PostMapping("/regi")
	public ResponseEntity<?> page1(@RequestBody Member member) {
		memberService.add(member);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	
}
