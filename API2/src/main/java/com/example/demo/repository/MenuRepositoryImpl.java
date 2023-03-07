package com.example.demo.repository;

import static com.example.demo.model.QMenu.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.demo.model.Menu;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MenuRepositoryImpl extends QuerydslRepositorySupport implements MenuRepositoryCustom {

	public MenuRepositoryImpl() {
		super(Menu.class);
	}
	@Autowired
	private JPAQueryFactory queryFactory;


	@Override
	public int findMax() {
		int max = queryFactory.select(menu.menuId.max()).from(menu).fetchOne();
		return max;
	}

}
