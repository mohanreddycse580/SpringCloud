package com.java.employeeproducer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@RequestMapping("/getEmployee")
	public Employee getEmployee() {
		
		Employee emp = new Employee("Mohan", "1");
		return emp;
		
	}
}
