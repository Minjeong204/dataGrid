package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Condition {
	String user_id;
	String user_name;
	String regi_name;
	String upda_name;
	LocalDateTime regiStart;
	LocalDateTime regiEnd;
	LocalDateTime updaStart;
	LocalDateTime updaEnd;
	String use_YN;
}
