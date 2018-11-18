package com.atguigu.mapper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mapper.entities.Employee;
import com.atguigu.mapper.mappers.EmployeeMapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;

	public List<Employee> getAll()
	{
		Employee employee=new Employee();
		Example example = new Example(employee);

		employeeMapper.selectByExample()
		return employeeMapper.selectAll();
	}

}
