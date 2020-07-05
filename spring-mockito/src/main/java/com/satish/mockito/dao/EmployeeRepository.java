package com.satish.mockito.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.mockito.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
