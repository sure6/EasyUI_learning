package com.softeem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softeem.mapper.MenuMapper;
import com.softeem.pojo.Menu;
@Service
public class MenuServiceImpl implements MenuService {
   @Autowired
	private MenuMapper mm;
	
	@Override
	public Menu getMenuById(String menuid) {
		Menu menu = mm.selectByPrimaryKey(menuid);
		return menu;
	}

}
