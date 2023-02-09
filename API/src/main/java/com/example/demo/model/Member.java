package com.example.demo.model;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name = "test_nana0813")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Column(name = "USER_NM")
	private String name;

	@Id
	@Column(name = "USER_ID")
	private String user_id;

	@Column(name = "pw")
	private String pw;

	@Column(name = "REGI_USER")
	@Enumerated(EnumType.STRING)
	private RoleType regiUser;

	@Column(name = "REGI_DT")
	 private LocalDateTime regiDt;

	@Column(name = "UPDA_DT")
	private String updaDt;

	@Column(name = "UPDA_USER")
	private String updaUser;

	@Column(name = "USE_YN")
	@Enumerated(EnumType.STRING)
	private Use_YN useYn;

	@Builder
	public Member(String name, String user_id, String pw, Date regiDate, RoleType regiUser, String updaDt,
			String updaUser, Use_YN useYn) {
		this.name = name;
		this.user_id = user_id;
		this.pw = pw;
		this.regiUser = regiUser;
		this.useYn = useYn;
	}

}
