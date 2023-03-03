package com.example.demo.repository;

import java.util.Map;

import com.example.demo.model.Condition;

public interface MemberRepositorycustom {

	Map<String, Object> findMembers(Condition condition);


}
