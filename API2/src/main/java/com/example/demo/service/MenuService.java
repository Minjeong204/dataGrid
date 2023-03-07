package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepository;

@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private RestTemplate restTemplate;

	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public List<Menu> getAll() {
		// List<Map<String, Object>> userList =
		// restTemplate.getForObject("http://page1/table", List.class);
		@SuppressWarnings("unused")
		Map<String, String> nameList = new HashMap<>();

//		for (Map<String, Object> map : userList) {
//			nameList.put((String)map.get("user_id"), (String)map.get("name"));
//		}

		List<Menu> menus = menuRepository.findByUseYn("Y");

		Map<String, String> idmap = new HashMap<>();

		for (Menu a : menus) {

			if (a.getRegiUser() != null)
				idmap.put(a.getRegiUser(), null);

			if (a.getUpdaUser() != null)
				idmap.put(a.getUpdaUser(), null);
		}

		@SuppressWarnings("unchecked")
		Map<String, String> mapped = restTemplate.postForObject("http://page1/send", idmap, Map.class);

		for (Menu menu : menus) {
//			menu.setRegiUser(nameList.get(menu.getRegiUser()));
//			menu.setUpdaUser(nameList.get(menu.getUpdaUser()));
			menu.setRegiUser(mapped.get(menu.getRegiUser()));
			menu.setUpdaUser(mapped.get(menu.getUpdaUser()));
		}
		return menus;
	}

	public Menu add(Menu menu) {
		int uprMenuIdInt = menu.getUprMenuId();
		if (uprMenuIdInt == 0) {
			menu.setUprMenuId(-1);
		}
		int max = menuRepository.findMax();
		menu.setMenuId(max + 1);
		menu.setRegiUser("ADMIN");
		menu.setRegiDt(LocalDateTime.now());
		menu.setUseYn("Y");

		return menuRepository.save(menu);
	}

	public void delete(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			Menu menu = menuRepository.findById(arr[i])
					.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id="));
			menuRepository.delete(menu);
		}
	}

	public Menu update(Menu menu) {
		int id = menu.getMenuId();
		int sort = menu.getSort();
		String menuNm = menu.getMenuNm();
		Menu menu2 = menuRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
		menu2.setMenuNm(menuNm);
		menu2.setSort(sort);
		LocalDateTime date = LocalDateTime.now();
		menu2.setUpdaDt(date);
		menu2.setUpdaUser("USER");
		return menuRepository.save(menu2);
	}
}
