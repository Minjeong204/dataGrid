package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "test_nana0813")
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
	private String regiUser;

	@Column(name = "REGI_DT")
	private LocalDate regiDt;

	@Column(name = "UPDA_DT")
	private LocalDate updaDt;

	@Column(name = "UPDA_USER")
	private String updaUser;

	@Column(name = "USE_YN")
	@Enumerated(EnumType.STRING)
	private Use_YN useYn;

	@Builder
	public Member(String name, String user_id, String pw, LocalDate regiDate, String regiUser, LocalDate updaDt,
			String updaUser, Use_YN useYn) {
		this.name = name;
		this.user_id = user_id;
		this.pw = pw;
		this.regiUser = regiUser;
		this.useYn = useYn;
	}

}
