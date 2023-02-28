package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "test_nana0813_Menu")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
	@Id
	@Column(name = "MENU_ID")
	private int menuId;
	@Column(name = "MENU_NM")
	private String menuNm;
	@Column(name = "SORT")
	private int sort;
	@Column(name = "UPR_MENU_ID")
	private int uprMenuId;
	@Column(name = "URL")
	private String url;
	@Column(name = "USE_YN")
	private String useYn;
	@Column(name = "REGI_USER")
	private String regiUser;
	@Column(name = "REGI_DT")
	private LocalDateTime regiDt;
	@Column(name = "UPDA_USER")
	private String updaUser;
	@Column(name = "UPDA_DT")
	private LocalDateTime updaDt;

}
