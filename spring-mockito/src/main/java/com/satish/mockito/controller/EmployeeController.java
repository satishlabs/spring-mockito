package com.satish.mockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satish.mockito.dao.EmployeeRepository;
import com.satish.mockito.model.Employee;
import com.satish.mockito.model.Response;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@PostMapping("/addEmployee")
	public Response addEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return new Response(employee.getId()+" inserted", Boolean.TRUE);
	}
	
	@GetMapping("/getEmployees")
	public Response getAllEmployee() {
		List<Employee> employeeList = employeeRepository.findAll();
		return new Response("record counts: " +employeeList.size(), Boolean.TRUE);
	}
}
