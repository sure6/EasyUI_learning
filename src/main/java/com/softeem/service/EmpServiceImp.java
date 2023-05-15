package com.softeem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.mapper.DeptMapper;
import com.softeem.mapper.EmpMapper;
import com.softeem.pojo.Dept;
import com.softeem.pojo.DeptExample;
import com.softeem.pojo.Emp;
import com.softeem.pojo.EmpExample;
import com.softeem.pojo.EmpExample.Criteria;
import com.softeem.util.PageUtil;
@Service
public class EmpServiceImp implements EmpService{
  @Autowired
	private EmpMapper em;
 @Autowired
  private DeptMapper dm;
	@Override
	public List<Emp> getAll() {
		EmpExample ee=new EmpExample();
		List<Emp> list = em.selectByExample(ee);
		return list;
	}
	@Override
	public PageUtil getPageData(Integer page, Integer rows,Emp emp,String start,String end) {
		PageUtil pu=new PageUtil();
		EmpExample ee=new EmpExample();
		Criteria criteria = ee.createCriteria();
		
		String ename = emp.getEname();
		String job = emp.getJob();
		if(!StringUtils.isEmpty(ename)){
			criteria.andEnameLike("%"+ename+"%");
		}
		if(!StringUtils.isEmpty(job)){
			criteria.andJobLike("%"+job+"%");
		}
		if(!StringUtils.isEmpty(start)&&!StringUtils.isEmpty(end)){
			criteria.andEmpnoBetween(Integer.parseInt(start), Integer.parseInt(end));
		}
		PageHelper.startPage(page, rows);
		List<Emp> list = em.selectByExample(ee);
		PageInfo<Emp> pi=new PageInfo<>(list);
		pu.setTotal(pi.getTotal());
		pu.setRows(pi.getList());
		
		return pu;
	}
	@Override
	//提交事务注解
	@Transactional
	public boolean addData(Emp p) {
		boolean flag=false;
		int i = em.insertSelective(p);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean editData(Emp p) {
		boolean flag=false;
		int i = em.updateByPrimaryKeySelective(p);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean deleteData(int empno) {
		boolean flag=false;
		int i = em.deleteByPrimaryKey(empno);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	@Override
	public List<Dept> getAllDept() {
		DeptExample de=new DeptExample();
		List<Dept> list = dm.selectByExample(de);
		return list;
	}

}
