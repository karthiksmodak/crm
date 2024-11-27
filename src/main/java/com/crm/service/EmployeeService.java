package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    // Constructor injection
    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper=modelMapper;
    }

    //Commented so that the next method mentioned sends postman what data is stored in the database
//    public void addEmployee(Employee employee) {
//        employeeRepository.save(employee);
//    }

//    public Employee addEmployee(Employee employee) {
//        Employee emp=employeeRepository.save(employee);
//        return emp;
//    }

    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee=mapToEntity(dto);
        Employee emp=employeeRepository.save(employee);
        EmployeeDto employeeDto=mapToDto(emp);
//        employeeDto.setDate(new Date());
        return employeeDto;
    }


    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
//        Optional<Employee> opEmp=employeeRepository.findById(id);
//        Employee employee=opEmp.get();
//        employee.setName(dto.getName());
//        employee.setEmailId(dto.getEmailId());
//        employee.setMobile(dto.getMobile());
//        employeeRepository.save(employee);

        Employee employee=mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee=employeeRepository.save(employee);
        EmployeeDto employeeDto=mapToDto(updateEmployee);
        return employeeDto;
    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
//        return employeeRepository.findAll();
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto=employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    EmployeeDto mapToDto(Employee employee){
//        EmployeeDto dto=new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());

        //Using ModelMapper
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        return dto;
    }

    Employee mapToEntity(EmployeeDto dto){
//        Employee emp=new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());

        //Using ModelMapper
        Employee emp=modelMapper.map(dto,Employee.class);
        return emp;
    }

    public EmployeeDto getEmployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                ()->new ResourceNotFound("Record Not Found with id : "+empId)
        );
        EmployeeDto dto=mapToDto(employee);
        return dto;
    }
}