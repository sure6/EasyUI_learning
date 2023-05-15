package com.softeem.test;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.util.StringUtils;
import com.softeem.mapper.DeptMapper;
import com.softeem.mapper.EmpMapper;
import com.softeem.mapper.MenuMapper;
import com.softeem.pojo.Dept;
import com.softeem.pojo.Emp;
import com.softeem.pojo.Menu;
import com.softeem.service.EmpService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EmpTest {

	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Test
	public void test1() {
		Emp emp = empMapper.selectByPrimaryKey(7369);
		
		System.out.println(emp);
	}
	
	@Test
	public void test2() {
		
		String str1 = null;
		String str2 = "";
		
		System.out.println(StringUtils.isEmpty(str1));
		System.out.println(StringUtils.isEmpty(str2));
	}
	
	@Test
	public void test3() throws Exception{
		Emp emp = new Emp();
		emp.setEmpno(8002);
		emp.setEname("mysql123456");
		emp.setJob("mysql");
		emp.setMgr(7369);
		emp.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-04"));
		emp.setSal(1000.00);
		emp.setComm(1000.00);
		emp.setDeptno(20);
		
		empService.editData(emp);
	}
	
	

	@Test
	public void test6() {
		Dept dept = deptMapper.selectByPrimaryKey(30);
		
		System.out.println(dept);
	}
	
	@Test
	public void test7() {
		List<Menu> list = menuMapper.getMenuByPid("200");
		
		System.out.println(list);
	}
	
	@Test
	public void test8() {
		Menu menu = menuMapper.selectByPrimaryKey("0");
		
		System.out.println(menu);
	}
	
}
