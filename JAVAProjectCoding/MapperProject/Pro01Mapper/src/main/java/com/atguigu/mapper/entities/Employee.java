package com.atguigu.mapper.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tabple_emp")
public class Employee {

	/**
	 * 考虑到java的基本数据类型，在我们的就java中都有默认值，会导致Mybatis在
	 * 执行相关操作时很难判断当前字段是否为null，所以在Mybatis环境下使用java
	 * 实体类时尽量不要使用基本数据类型，都使用对应的包装类型。
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer empId;//emp_id
	
	private String empName;//emp_name
	
	@Column(name="emp_salary_apple")
	private Double empSalary;//emp_salary_apple
	
	private Integer empAge;//emp_age
	
	public Employee() {
		
	}

	public Employee(Integer empId, String empName, Double empSalary, Integer empAge) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.empAge = empAge;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + ", empAge=" + empAge
				+ "]";
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public Integer getEmpAge() {
		return empAge;
	}

	public void setEmpAge(Integer empAge) {
		this.empAge = empAge;
	}

}
