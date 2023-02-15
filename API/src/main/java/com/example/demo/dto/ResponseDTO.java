package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.model.Member;

import lombok.Data;

@Data
public class ResponseDTO {

	private String userName;
	private String userId;
	private String pw;
	private LocalDate regiDate;
	private String regi_user;
	private LocalDate updaDt;
	private String updaUser;
	private String userYN;

	public Member toEntity() {
		return Member.builder().user_id(userId).name(userName).pw(pw).regiDate(regiDate).build();
	}

}
