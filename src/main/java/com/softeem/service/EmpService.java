package com.softeem.service;

import java.util.List;

import com.softeem.pojo.Dept;
import com.softeem.pojo.Emp;
import com.softeem.util.PageUtil;

public interface EmpService {
   List<Emp> getAll(); 
   PageUtil getPageData(Integer page,Integer rows,Emp emp,String start,String end);
   boolean addData(Emp p);
   boolean editData(Emp p);
   boolean deleteData(int empno);
   List<Dept> getAllDept();
}
