package com.example.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

	// Custom search operations based on different fields of an Employee
	List<Employee> findByEName(String EName);
	List<Employee> findByGrade(String Grade);
	// to find the list of employees whose experience is greater than the given
	// experience
	List<Employee> findByExperienceGreaterThan(double Experience);
	//to find the employees based on technology and order them by grade
	@Query("from Employee where Technology=?1 order by Grade")
	List<Employee> findByTechnology(String Technology);

	
}
