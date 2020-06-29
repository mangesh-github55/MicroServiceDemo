package com.infosys.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infosys.model.Employee;

public class EmployeeDTO {

	@NotNull(message="Employee Id cannot be null")
	@NotBlank(message="Employee Id cannot be blank")
	@Size(min=5,max=20,message="Employee Id length must be between 5 to 20")
	private String empId;
	
	
	@NotNull(message="Employee name cannot be null")
	@NotBlank(message="Employee Name can not be blank")
	@Size(min=5,max=20,message="Employee name length must be between 5 to 20")
	private String name;
	
	@NotNull(message="Email id cannot be null")
	@Email(message="Not a valid email address")
	@NotBlank(message="Email id can not be blank")
	@Size(min=5,max=20,message="Email Id length must be between 5 to 20")
	private String email;
	
	@NotNull(message="Phone No cannot be null")
	@NotBlank(message="Phone no cannot be blank")
	@Size(min=10,max=10,message="Phone no should be 10 digit only")
	@Pattern(regexp="[7-9][0-9]{9}",message="Should starts with 7,8 or 9")
	private String phone;
	
	@NotNull(message="Bank Account details cannot be null")
	@NotEmpty(message="Bank Accounts detail cannot be empty")
	private List<BankAccountDTO> bankAccountDTOs;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<BankAccountDTO> getBankAccountDTOs() {
		return bankAccountDTOs;
	}
	public void setBankAccountDTOs(List<BankAccountDTO> bankAccountDTOs) {
		this.bankAccountDTOs = bankAccountDTOs;
	}
	@JsonIgnore
	public Employee getEmployee() {
		// TODO Auto-generated method stub
		Employee emp=new Employee();
		emp.setEmpId(this.getEmpId());
		emp.setName(this.getName());
		emp.setEmail(this.getEmail());
		emp.setPhone(this.getPhone());
		return emp;
	}
	public static EmployeeDTO getEmployeeEntity(Employee emp) {
		// TODO Auto-generated method stub
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmpId(emp.getEmpId());
		employeeDTO.setName(emp.getName());
		employeeDTO.setPhone(emp.getPhone());
		employeeDTO.setEmail(emp.getEmail());
		return employeeDTO;
	}

}
