package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;

@RestController
public class API2Controller {
	private final MenuService menuService;
	
	public API2Controller(MenuService menuService) {
		this.menuService = menuService;
	}

	@PostMapping("/table")
	public ResponseEntity<List<?>> table() {
		return new ResponseEntity<List<?>>(menuService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/regi")
	public ResponseEntity<Object> page1(@RequestBody Menu menu) {
		menuService.add(menu);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody int[] arr) {
		menuService.delete(arr);
		return new ResponseEntity<Object>(arr, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody Menu menu) {
		menuService.update(menu);
		return new ResponseEntity<Object>(menu, HttpStatus.OK);
	}
}
