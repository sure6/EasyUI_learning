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
//�����@Controller+@Response,���з���json���ݵĿ�����
@RestController
public class EmpController {
	@Autowired
  private EmpService es;
//�������е�emp����,����json���ݿ�����һ��pojo����,Ҳ������map����,jackson���Զ���java����ת��Ϊjson����.
  @RequestMapping("/empList.action")
  public List<Emp> empList(){
		List<Emp> all = es.getAll();
	  return all;
  }
 //��ҳ�˵�������   
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
		  map.put("msg","����ɹ�");
	  }catch(Exception e){
		  map.put("msg","����ʧ��");
	  }
	return map;
  }
  
  @RequestMapping("/editEmp.action")
 public Map<String,Object> editEmp(Emp p){
	 Map<String, Object> map=new HashMap<>();
	  try{
		  es.editData(p);
		  map.put("msg","�޸ĳɹ�");
	  }catch(Exception e){
		  map.put("msg","�޸�ʧ��");
	  }
	return map;
  }
  @RequestMapping("/deleteEmp.action")
  public  Map<String, Object> deleteEmp(String json) throws Exception{
	  Map<String, Object> map=new HashMap<>();
	  //�������ǽ�json���ַ�����Ϊjava����,��json��ֵΪString,java����ֵ���н�����Ӧ����
	  ObjectMapper om=new ObjectMapper();
	  List<Integer> list= om.readValue(json, new TypeReference<List<Integer>>() {});
	 /* for (Integer integer : list) {
		  System.out.println(integer);
	 }*/
	  try{
		  for (Integer i : list) {
			es.deleteData(i);
		  }
		  map.put("msg","ɾ���ɹ�");
	  }catch(Exception e){
		  map.put("msg","ɾ��ʧ��");
	  }
	return map;
  }
  //�Ǵ������deptno�Ĵ�����ӳ��
  @RequestMapping("/deptList.action")
  public List<Dept> deptList(){
	  List<Dept> list = es.getAllDept();
	return list;
  }
}
