package com.example.demo.dto;

import java.sql.Date;

import com.example.demo.model.Member;
import com.example.demo.model.Use_YN;

import lombok.Data;

@Data
public class ResponseDTO {

	private String userName;
	private String userId;
	private String pw;
	private Date regiDate;
	private String regi_user;
	private Date updaDt;
	private String updaUser;
	private String userYN;

	public Member toEntity() {
		return Member.builder().user_id(userId).name(userName).pw(pw).regiDate(regiDate).build();
	}

}
