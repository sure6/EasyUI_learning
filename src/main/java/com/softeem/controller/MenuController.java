package com.softeem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softeem.pojo.Menu;
import com.softeem.service.MenuService;

@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/menutree.action")
	public Menu getMenuTree() {
		Menu menu = menuService.getMenuById("0");
		
		return menu;
	}
}

