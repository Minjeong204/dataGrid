package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepository;

import jakarta.transaction.Transactional;

@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;

	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public List<Menu> getAll() {

		return (List<Menu>) menuRepository.findAll();
	}

	@Transactional
	public Menu add(Map<String, String> map) {
		int menuId = Integer.parseInt(map.get("menuId"));
		String menuName = (String) map.get("menuName");
		int sort = Integer.parseInt(map.get("sort"));
		String uprMenuId = (String) map.get("uprMenuId");
		int uprMenuIdInt;
		String url = (String) map.get("url");
		uprMenuIdInt = -1;
		if (!(uprMenuId.isEmpty() || uprMenuId == null)) {
			uprMenuIdInt = Integer.parseInt(uprMenuId);
		}

		Menu menu = new Menu();
		menu.setMenuId(menuId);
		menu.setMenuNm(menuName);
		menu.setSort(sort);
		menu.setUprMenuId(uprMenuIdInt);
		menu.setUrl(url);
		LocalDateTime date = LocalDateTime.now();
		menu.setRegiDt(date);
		menu.setUseYn("Y");
		menu.setRegiUser("USER");

		return menuRepository.save(menu);
	}

	@Transactional
	public void delete(List<Map<String, String>> map) {
		for (int i = 0; i < map.size(); i++) {
			String ids = (String) map.get(i).get("menuId");
			int id = Integer.parseInt(ids);
			Menu menu = menuRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
			menuRepository.delete(menu);
		}
	}

	@Transactional
	public Menu update(Map<String, String> map) {
		int id = Integer.parseInt(map.get("menuId"));
		int sort = Integer.parseInt(map.get("sort"));
		String menuName = (String) map.get("menuName");
		Menu menu = menuRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
		menu.setMenuNm(menuName);
		menu.setSort(sort);
		LocalDateTime date = LocalDateTime.now();
		menu.setUpdaDt(date);
		menu.setUpdaUser("USER");
		return menuRepository.save(menu);
	}
}
