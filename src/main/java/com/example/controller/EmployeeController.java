package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DAO.EmployeeDAO;
import com.example.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDAO dao;
	
	@GetMapping("/Employees")
	public List<Employee> getEmployees()
	{
		return dao.findAll();
	}
	
	@GetMapping("/Employee/{EId}")
	public Optional<Employee> getEmployee(@PathVariable("EId") int EId)
	{
		return dao.findById(EId);
	}
	//accepts data only in json format
	@PostMapping(path="/Employee",consumes={"application/json"})
	public Employee addEmployee(@RequestBody Employee employee)
	{
		dao.save(employee);
		return employee;
	}
	
	@DeleteMapping("/Employee/{EId}")
	public String deleteEmployee(@PathVariable("EId") int EId)
	{
		Employee emp=dao.getOne(EId);
		dao.delete(emp);
		return "Employee details deleted successfully";
	}
	@PutMapping("/Employee/{EId}")
	public Employee updateOrSaveEmployee(@RequestBody Employee employee)
	{
		dao.save(employee);
		return employee;
	}
	//To find employees whose experience is greater than the given input
	@GetMapping("/Employee/Experience/{Exp}")
	public List<Employee> getExperiencedEmployees(@PathVariable("Exp") double exp)
	{
		return dao.findByExperienceGreaterThan(exp);
	}
	
	@GetMapping("/Employee/Technology/{Tech}")
	public List<Employee> getEmployeesByTech(@PathVariable("Tech") String tech)
	{
		return dao.findByTechnology(tech);
	}
	
	
}
