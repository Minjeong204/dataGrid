package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repository.MenuRepository;

import jakarta.transaction.Transactional;
@Transactional
@SpringBootTest
class Api2ApplicationTests {
	@Autowired
	MenuRepository menuRepository;

	@Test
	void contextLoads() {
//List<Menu> menu =  menuRepository.findByUseYn("Y");
//		List<Menu>menu = menuRepository.findAll();
		int menu = menuRepository.findMax();
		
		
		System.out.println(menu);
	}

}
