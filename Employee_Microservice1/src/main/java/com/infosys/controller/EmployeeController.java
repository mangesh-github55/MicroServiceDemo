package com.infosys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.dto.BankAccountDTO;
import com.infosys.dto.EmployeeDTO;
import com.infosys.exception.EmployeeAlreadyExistsException;
import com.infosys.exception.EmployeeNotFoundException;
import com.infosys.model.Employee;
import com.infosys.service.EmployeeService;

@RestController
@RequestMapping(value="/")
@EnableAutoConfiguration
@RibbonClient(name = "empRiboon")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	RestTemplate restTemplate;
	
	public String welcomeMessage() {
		return "Welocome to Spring MicroService";
	}
	
	@GetMapping("/employee")
	public List<EmployeeDTO> getAllEmployee()  {
		return service.getAllEmployee();
	}
	
	@GetMapping("/employee/{empId}")
	public EmployeeDTO getEmployeeById(@PathVariable String empId) throws EmployeeNotFoundException {
		
		Employee employee = service.getEmployee(empId);
		@SuppressWarnings("unchecked")
		List<BankAccountDTO> bankAccountDTOs = restTemplate.getForObject("http://empRiboon/bankAccount/"+employee.getEmpId(), List.class);
		EmployeeDTO employeeDTO = EmployeeDTO.getEmployeeEntity(employee);
		employeeDTO.setBankAccountDTOs(bankAccountDTOs);
		return employeeDTO;
	}

	@PostMapping("/employee")
	public String addEmployee( @Valid @RequestBody EmployeeDTO employeeDTO)  throws EmployeeAlreadyExistsException,MethodArgumentNotValidException{
		service.addEmployee(employeeDTO);
		List<BankAccountDTO> bankAccountDTOs=employeeDTO.getBankAccountDTOs();
		bankAccountDTOs.forEach(bankDTO-> bankDTO.setEmpId(employeeDTO.getEmpId()));
		restTemplate.postForObject("http://empRiboon/bankAccount",bankAccountDTOs, String.class);
	
		return "added successfuly";
	}
}
