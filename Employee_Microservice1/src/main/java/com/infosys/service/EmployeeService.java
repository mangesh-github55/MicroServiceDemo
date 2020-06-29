package com.infosys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;

import com.infosys.dto.BankAccountDTO;
import com.infosys.dto.EmployeeDTO;
import com.infosys.exception.EmployeeAlreadyExistsException;
import com.infosys.exception.EmployeeNotFoundException;
import com.infosys.model.Employee;
import com.infosys.repository.EmployeeRepository;
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	

	public List<EmployeeDTO> getAllEmployee() {
		// TODO Auto-generated method stub
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		List<Employee> employees = repository.findAll();
		/*
		 * for (Employee employee : employees) {
		 * 
		 * @SuppressWarnings("unchecked") List<BankAccountDTO> bankAccountDTOs =
		 * restTemplate.getForObject("http://empRiboon/bankAccount/"+employee.getEmpId()
		 * , List.class); EmployeeDTO employeeDTO =
		 * EmployeeDTO.getEmployeeEntity(employee);
		 * employeeDTO.setBankAccountDTOs(bankAccountDTOs);
		 * employeeDTOs.add(employeeDTO); }
		 */
		return employeeDTOs;
	}

	public Employee getEmployee(String empId) throws EmployeeNotFoundException {
		// TODO Auto-generated method stub
		Employee emp = null;
		if(repository.existsById(empId)) {	
			 emp= repository.getOne(empId);
		}else {
			throw new EmployeeNotFoundException("Employee Not found");
		}
		return emp;
	}


	public void addEmployee(EmployeeDTO employeeDTO) throws EmployeeAlreadyExistsException{
		// TODO Auto-generated method stub
		if(repository.existsById(employeeDTO.getEmpId())) {
			throw new EmployeeAlreadyExistsException("Employee Already Exists");
		}else {
			repository.save(employeeDTO.getEmployee());
			}
	}
}
