package com.softeem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softeem.pojo.Dept;
import com.softeem.pojo.Emp;
import com.softeem.service.EmpService;
import com.softeem.util.PageUtil;
//这个是@Controller+@Response,具有返回json数据的控制器
@RestController
public class EmpController {
	@Autowired
  private EmpService es;
//返回所有的emp数据,返回json数据可以是一个pojo对象,也可以用map来存,jackson会自动将java对象转换为json对象.
  @RequestMapping("/empList.action")
  public List<Emp> empList(){
		List<Emp> all = es.getAll();
	  return all;
  }
 //分页菜单的数据   
  @RequestMapping("/empPage.action")
  public PageUtil empPage(Integer page,Integer rows,Emp emp,String start,String end){
	  PageUtil data = es.getPageData(page, rows,emp,start,end);
	  return data;
  }
  //
  @RequestMapping("/addEmp.action")
  public Map<String, Object> addEmp(Emp p){
	  Map<String, Object> map=new HashMap<>();
	  try{
		  es.addData(p);
		  map.put("msg","插入成功");
	  }catch(Exception e){
		  map.put("msg","插入失败");
	  }
	return map;
  }
  
  @RequestMapping("/editEmp.action")
 public Map<String,Object> editEmp(Emp p){
	 Map<String, Object> map=new HashMap<>();
	  try{
		  es.editData(p);
		  map.put("msg","修改成功");
	  }catch(Exception e){
		  map.put("msg","修改失败");
	  }
	return map;
  }
  @RequestMapping("/deleteEmp.action")
  public  Map<String, Object> deleteEmp(String json) throws Exception{
	  Map<String, Object> map=new HashMap<>();
	  //这两句是将json的字符串改为java对象,如json的值为String,java对其值进行解析相应的类
	  ObjectMapper om=new ObjectMapper();
	  List<Integer> list= om.readValue(json, new TypeReference<List<Integer>>() {});
	 /* for (Integer integer : list) {
		  System.out.println(integer);
	 }*/
	  try{
		  for (Integer i : list) {
			es.deleteData(i);
		  }
		  map.put("msg","删除成功");
	  }catch(Exception e){
		  map.put("msg","删除失败");
	  }
	return map;
  }
  //是处理添加deptno的处理器映射
  @RequestMapping("/deptList.action")
  public List<Dept> deptList(){
	  List<Dept> list = es.getAllDept();
	return list;
  }
}
